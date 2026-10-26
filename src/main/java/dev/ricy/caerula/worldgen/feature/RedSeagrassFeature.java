package dev.ricy.caerula.worldgen.feature;

import com.mojang.serialization.Codec;
import dev.ricy.caerula.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class RedSeagrassFeature extends Feature<ProbabilityFeatureConfiguration> {

    public RedSeagrassFeature(Codec<ProbabilityFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> context) {
        boolean placed = false;
        RandomSource random = context.random();
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        ProbabilityFeatureConfiguration config = context.config();
        int offsetX = random.nextInt(8) - random.nextInt(8);
        int offsetZ = random.nextInt(8) - random.nextInt(8);
        int surfaceY = level.getHeight(Heightmap.Types.OCEAN_FLOOR, origin.getX() + offsetX, origin.getZ() + offsetZ);
        BlockPos targetPos = new BlockPos(origin.getX() + offsetX, surfaceY, origin.getZ() + offsetZ);
        if (level.getBlockState(targetPos).is(Blocks.WATER)) {
            boolean tall = random.nextDouble() < (double) config.probability;
            BlockState state = tall ? ModBlocks.TALL_RED_SEAGRASS.get().defaultBlockState() : ModBlocks.RED_SEAGRASS.get().defaultBlockState();
            if (state.canSurvive(level, targetPos)) {
                if (tall) {
                    BlockState upperState = state.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER);
                    BlockPos abovePos = targetPos.above();
                    if (level.getBlockState(abovePos).is(Blocks.WATER)) {
                        level.setBlock(targetPos, state, 2);
                        level.setBlock(abovePos, upperState, 2);
                    }
                } else {
                    level.setBlock(targetPos, state, 2);
                }

                placed = true;
            }
        }

        return placed;
    }
}
