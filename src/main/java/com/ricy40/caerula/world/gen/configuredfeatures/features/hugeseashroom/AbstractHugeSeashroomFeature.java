package com.ricy40.caerula.world.gen.configuredfeatures.features.hugeseashroom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

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
                this.setBlock(levelAccessor, mutablePos, config.stemProvider.getState(random, pos));
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

        int minAboveBottomCap;
        int minAboveMidCap;

        if (config.layers == HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.THREE) {
            minAboveBottomCap = 4;
            minAboveMidCap = 2;
        } else if (config.layers == HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.TWO) {
            minAboveBottomCap = 2;
            minAboveMidCap = 0;
        } else {
            minAboveBottomCap = 0;
            minAboveMidCap = 0;
        }

        this.bottomCapLevel = random.nextInt(1, height - minAboveBottomCap);
        this.midCapLevel = random.nextInt(this.bottomCapLevel + 1, height - minAboveMidCap);
        this.topCapLevel = random.nextInt(this.midCapLevel + 1, height);

        System.out.println("botCap: " + this.bottomCapLevel);
        System.out.println("midCap: " + this.midCapLevel);
        System.out.println("topCap: " + this.topCapLevel);
    }

    protected boolean isValidPosition(LevelAccessor pLevel, BlockPos pPos, int pMaxHeight, BlockPos.MutableBlockPos pMutablePos, HugeSeashroomFeatureConfiguration pConfig) {
        int i = pPos.getY();
        if (i >= pLevel.getMinBuildHeight() + 1 && i + pMaxHeight + 1 < pLevel.getMaxBuildHeight()) {
            BlockState blockstate = pLevel.getBlockState(pPos.below());
            if (!isDirt(blockstate) && !blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
                return false;
            } else {
                for(int j = 0; j <= pMaxHeight; ++j) {
                    int k = this.getTreeRadiusForHeight(-1, -1, Math.max(pConfig.bottomFoliageRadius, Math.max(pConfig.midFoliageRadius, pConfig.topFoliageRadius)), j);

                    for(int l = -k; l <= k; ++l) {
                        for(int i1 = -k; i1 <= k; ++i1) {
                            BlockState blockstate1 = pLevel.getBlockState(pMutablePos.setWithOffset(pPos, l, j, i1));
                            if ((!blockstate1.isAir() && !blockstate1.is(BlockTags.LEAVES)) || blockstate1 != Blocks.WATER.defaultBlockState() ) {
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
        HugeSeashroomFeatureConfiguration hugeseashroomfeatureconfiguration = pContext.config();

        int i = this.getTreeHeight(hugeseashroomfeatureconfiguration, randomsource);
        System.out.println("Height: " + i);
        this.generateCapHeights(hugeseashroomfeatureconfiguration, randomsource, i);

        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        if (!this.isValidPosition(worldgenlevel, blockpos, i, blockpos$mutableblockpos, hugeseashroomfeatureconfiguration)) {
            return false;
        } else {
            this.makeCap(worldgenlevel, randomsource, blockpos, this.bottomCapLevel, blockpos$mutableblockpos, hugeseashroomfeatureconfiguration);
            if (hugeseashroomfeatureconfiguration.layers != HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.ONE) {
                this.makeCap(worldgenlevel, randomsource, blockpos, this.midCapLevel, blockpos$mutableblockpos, hugeseashroomfeatureconfiguration);
            }
            if (hugeseashroomfeatureconfiguration.layers == HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.THREE) {
                this.makeCap(worldgenlevel, randomsource, blockpos, this.topCapLevel, blockpos$mutableblockpos, hugeseashroomfeatureconfiguration);
            }
            this.placeTrunk(worldgenlevel, randomsource, blockpos, hugeseashroomfeatureconfiguration, i, blockpos$mutableblockpos);
            return true;
        }
    }

    protected abstract int getTreeRadiusForHeight(int p_65094_, int p_65095_, int p_65096_, int p_65097_);

    protected abstract void makeCap(LevelAccessor levelAccessor, RandomSource random, BlockPos pos, int height, BlockPos.MutableBlockPos mutablePos, HugeSeashroomFeatureConfiguration config);
}
