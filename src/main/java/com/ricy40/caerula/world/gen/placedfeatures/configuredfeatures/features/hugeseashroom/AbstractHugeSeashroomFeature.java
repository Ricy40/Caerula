package com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.features.hugeseashroom;

import com.mojang.serialization.Codec;
import com.ricy40.caerula.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import javax.annotation.Nullable;

public abstract class AbstractHugeSeashroomFeature extends Feature<HugeSeashroomFeatureConfiguration> {

    public int bottomCapLevel;
    @Nullable
    public int midCapLevel;
    @Nullable
    public int topCapLevel;

    public AbstractHugeSeashroomFeature(Codec<HugeSeashroomFeatureConfiguration> codec) {
        super(codec);
    }

    protected void placeTrunk(LevelAccessor levelAccessor, RandomSource random, BlockPos pos, HugeSeashroomFeatureConfiguration config, int height, BlockPos.MutableBlockPos mutablePos) {
        for(int i = 0; i < height; ++i) {
            mutablePos.set(pos).move(Direction.UP, i);
            if (!levelAccessor.getBlockState(mutablePos).isSolidRender(levelAccessor, mutablePos)) {
                BlockState state = config.stemProvider.getState(random, pos);
                if (i == height - 1) {state.setValue(HugeMushroomBlock.UP, true);}
                this.setBlock(levelAccessor, mutablePos, state);
            }
        }

    }

    protected int getTreeHeight(HugeSeashroomFeatureConfiguration config, RandomSource random) {

        System.out.println(config.layers);

        int maxHeight = config.layers.getMaxHeight();
        int minHeight = config.layers.getMinHeight();

        int variation = Mth.floor((maxHeight - minHeight) * random.nextFloat());

        int height = minHeight + variation;

        return height;
    }

    protected void generateCapHeights(HugeSeashroomFeatureConfiguration config, RandomSource random, int height) {

        if (config.layers == HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.ONE) {
            this.bottomCapLevel = 2 == height ? 2 : random.nextInt(2, height);
        } else if (config.layers == HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.TWO) {
            this.bottomCapLevel = 2 == height - 2 ? 2 : random.nextInt(2, height - 2);
            this.midCapLevel = this.bottomCapLevel + 2 == height ? height : random.nextInt(this.bottomCapLevel + 2, height);
        } else {
            this.bottomCapLevel = 2 == height - 4 ? 2 : random.nextInt(2, height - 4);
            this.midCapLevel = this.bottomCapLevel + 2 == height - 2 ? height -2 : random.nextInt(this.bottomCapLevel + 2, height - 2);
            this.topCapLevel = this.midCapLevel + 2 == height ? height : random.nextInt(this.midCapLevel + 2, height);
        }
    }

    protected boolean isValidPosition(LevelAccessor pLevel, BlockPos pPos, int pMaxHeight, BlockPos.MutableBlockPos pMutablePos, HugeSeashroomFeatureConfiguration pConfig) {
        int y = pPos.getY();
        if (y >= pLevel.getMinBuildHeight() + 1 && y + pMaxHeight + 1 < pLevel.getMaxBuildHeight()) {
            BlockState blockstate = pLevel.getBlockState(pPos.below());
            if (!isDirt(blockstate) && !blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
                return false;
            } else {
                for(int j = 0; j <= pMaxHeight; ++j) {
                    int k = this.getTreeRadiusForHeight(pConfig, j);

                    for(int l = -k; l <= k; ++l) {
                        for(int i = -k; i <= k; ++i) {
                            BlockState blockstate1 = pLevel.getBlockState(pMutablePos.setWithOffset(pPos, l, j, i));
                            if (!blockstate1.is(BlockTags.LEAVES) && !blockstate1.isAir() && blockstate1 != Blocks.WATER.defaultBlockState() && blockstate1 != ModBlocks.PURPLE_SEASHROOM.get().defaultBlockState()) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }
    
    public boolean place(FeaturePlaceContext<HugeSeashroomFeatureConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        BlockPos blockpos = pContext.origin();
        RandomSource randomsource = pContext.random();
        HugeSeashroomFeatureConfiguration config = pContext.config();

        int i = this.getTreeHeight(config, randomsource);
        this.generateCapHeights(config, randomsource, i);

        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        if (!this.isValidPosition(worldgenlevel, blockpos, i, blockpos$mutableblockpos, config)) {
            return false;
        } else {
            this.makeCap(worldgenlevel, randomsource, blockpos, this.bottomCapLevel, blockpos$mutableblockpos, config.bottomFoliageRadius, config.bottomCapProvider);
            if (config.layers != HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.ONE) {
                this.makeCap(worldgenlevel, randomsource, blockpos, this.midCapLevel, blockpos$mutableblockpos, config.midFoliageRadius, config.midCapProvider);
            }
            if (config.layers == HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.THREE) {
                this.makeCap(worldgenlevel, randomsource, blockpos, this.topCapLevel, blockpos$mutableblockpos, config.topFoliageRadius, config.topCapProvider);
            }
            this.placeTrunk(worldgenlevel, randomsource, blockpos, config, i, blockpos$mutableblockpos);
            return true;
        }
    }

    protected abstract int getTreeRadiusForHeight(HugeSeashroomFeatureConfiguration config, int height);

    protected abstract void makeCap(LevelAccessor levelAccessor, RandomSource random, BlockPos pos, int height, BlockPos.MutableBlockPos mutablePos, int radius, BlockStateProvider block);
}
