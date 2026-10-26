package dev.ricy.caerula.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SpreadingSnowyBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public abstract class UnderWaterGrassBlock extends SpreadingSnowyBlock {

    private final Block baseBlock;

    public UnderWaterGrassBlock(BlockBehaviour.Properties properties, Block baseBlock) {
        super(properties, baseBlock.builtInRegistryHolder().key());
        this.baseBlock = baseBlock;
    }

    protected static boolean canBeGrass(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);
        if (aboveState.is(net.minecraft.world.level.block.Blocks.SNOW) && aboveState.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        } else if (aboveState.is(net.minecraft.world.level.block.Blocks.WATER)) {
            return true;
        } else {
            return !aboveState.canOcclude();
        }
    }

    protected static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        return canBeGrass(state, level, pos) || level.getFluidState(abovePos).is(FluidTags.WATER);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canBeGrass(state, level, pos)) {
            if (!level.isAreaLoaded(pos, 1))
                return;
            level.setBlockAndUpdate(pos, this.baseBlock.defaultBlockState());
        } else {
            if (!level.isAreaLoaded(pos, 3))
                return;
            if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
                BlockState grassState = this.defaultBlockState();

                for (int i = 0; i < 4; ++i) {
                    BlockPos targetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (level.getBlockState(targetPos).is(this.baseBlock) && canPropagate(grassState, level, targetPos)) {
                        level.setBlockAndUpdate(targetPos, grassState.setValue(SNOWY, level.getBlockState(targetPos.above()).is(net.minecraft.world.level.block.Blocks.SNOW)));
                    }
                }
            }
        }
    }
}
