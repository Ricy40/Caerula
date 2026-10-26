package dev.ricy.caerula.worldgen.feature.hugeseashroom;

import com.mojang.serialization.Codec;
import dev.ricy.caerula.block.ModBlocks;
import dev.ricy.caerula.tag.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public abstract class AbstractHugeSeashroomFeature extends Feature<HugeSeashroomFeatureConfiguration> {

    protected int bottomCapLevel;
    protected int midCapLevel;
    protected int topCapLevel;

    public AbstractHugeSeashroomFeature(Codec<HugeSeashroomFeatureConfiguration> codec) {
        super(codec);
    }

    protected void placeTrunk(WorldGenLevel level, RandomSource random, BlockPos pos, HugeSeashroomFeatureConfiguration config, int height, BlockPos.MutableBlockPos mutablePos) {
        for (int i = 0; i < height; ++i) {
            mutablePos.set(pos).move(Direction.UP, i);
            if (!level.getBlockState(mutablePos).isSolidRender()) {
                BlockState state = config.stemProvider.getState(level, random, pos);
                if (i == height - 1) {
                    state = state.setValue(HugeMushroomBlock.UP, true);
                }
                this.setBlock(level, mutablePos, state);
            }
        }
    }

    protected int getTreeHeight(HugeSeashroomFeatureConfiguration config, RandomSource random) {
        int maxHeight = config.layers.getMaxHeight();
        int minHeight = config.layers.getMinHeight();
        int variation = Mth.floor((maxHeight - minHeight) * random.nextFloat());
        return minHeight + variation;
    }

    protected void generateCapHeights(HugeSeashroomFeatureConfiguration config, RandomSource random, int height) {
        if (config.layers == HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.ONE) {
            this.bottomCapLevel = 2 == height ? 2 : random.nextInt(2, height);
        } else if (config.layers == HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.TWO) {
            this.bottomCapLevel = 2 == height - 2 ? 2 : random.nextInt(2, height - 2);
            this.midCapLevel = this.bottomCapLevel + 2 == height ? height : random.nextInt(this.bottomCapLevel + 2, height);
        } else {
            this.bottomCapLevel = 2 == height - 4 ? 2 : random.nextInt(2, height - 4);
            this.midCapLevel = this.bottomCapLevel + 2 == height - 2 ? height - 2 : random.nextInt(this.bottomCapLevel + 2, height - 2);
            this.topCapLevel = this.midCapLevel + 2 == height ? height : random.nextInt(this.midCapLevel + 2, height);
        }
    }

    protected boolean isValidPosition(WorldGenLevel level, BlockPos pos, int maxHeight, BlockPos.MutableBlockPos mutablePos, HugeSeashroomFeatureConfiguration config) {
        int y = pos.getY();
        if (y >= level.getMinY() + 1 && y + maxHeight + 1 < level.getMaxY()) {
            BlockState belowState = level.getBlockState(pos.below());
            if (!belowState.is(ModTags.Blocks.MUSHROOM_GROW_BLOCK)) {
                return false;
            }
            for (int j = 0; j <= maxHeight; ++j) {
                int radius = this.getTreeRadiusForHeight(config, j);
                for (int l = -radius; l <= radius; ++l) {
                    for (int i = -radius; i <= radius; ++i) {
                        BlockState state = level.getBlockState(mutablePos.setWithOffset(pos, l, j, i));
                        if (!state.is(BlockTags.LEAVES) && !state.isAir() && state != Blocks.WATER.defaultBlockState() && state != ModBlocks.PURPLE_SEASHROOM.get().defaultBlockState()) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean place(FeaturePlaceContext<HugeSeashroomFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        HugeSeashroomFeatureConfiguration config = context.config();

        int height = this.getTreeHeight(config, random);
        this.generateCapHeights(config, random, height);

        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        if (!this.isValidPosition(level, origin, height, mutablePos, config)) {
            return false;
        }
        this.makeCap(level, random, origin, this.bottomCapLevel, mutablePos, config.bottomFoliageRadius, config.bottomCapProvider);
        if (config.layers != HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.ONE) {
            this.makeCap(level, random, origin, this.midCapLevel, mutablePos, config.midFoliageRadius, config.midCapProvider);
        }
        if (config.layers == HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.THREE) {
            this.makeCap(level, random, origin, this.topCapLevel, mutablePos, config.topFoliageRadius, config.topCapProvider);
        }
        this.placeTrunk(level, random, origin, config, this.topmostCapLevel(config), mutablePos);
        return true;
    }

    private int topmostCapLevel(HugeSeashroomFeatureConfiguration config) {
        if (config.layers == HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.THREE) {
            return this.topCapLevel;
        } else if (config.layers == HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.TWO) {
            return this.midCapLevel;
        } else {
            return this.bottomCapLevel;
        }
    }

    protected abstract int getTreeRadiusForHeight(HugeSeashroomFeatureConfiguration config, int height);

    protected abstract void makeCap(WorldGenLevel level, RandomSource random, BlockPos pos, int height, BlockPos.MutableBlockPos mutablePos, int radius, BlockStateProvider block);
}
