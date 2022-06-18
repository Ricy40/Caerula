package com.ricy40.caerula.world.gen.configuredfeatures.features.hugeseashroom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class HugePurpleSeashroomFeature extends AbstractHugeSeashroomFeature {

    public HugePurpleSeashroomFeature(Codec<HugeSeashroomFeatureConfiguration> config) {
        super(config);
    }

    protected void makeCap(LevelAccessor levelAccessor, RandomSource random, BlockPos pos, int height, BlockPos.MutableBlockPos mutablePos, int radius, BlockStateProvider block) {
        int i = radius;

        for(int j = -i; j <= i; ++j) {
            for(int k = -i; k <= i; ++k) {
                boolean flag = j == -i;
                boolean flag1 = j == i;
                boolean flag2 = k == -i;
                boolean flag3 = k == i;
                boolean flag4 = flag || flag1;
                boolean flag5 = flag2 || flag3;
                if (!flag4 || !flag5) {
                    mutablePos.setWithOffset(pos, j, height, k);
                    if (!levelAccessor.getBlockState(mutablePos).isSolidRender(levelAccessor, mutablePos)) {
                        boolean flag6 = flag || flag5 && j == 1 - i;
                        boolean flag7 = flag1 || flag5 && j == i - 1;
                        boolean flag8 = flag2 || flag4 && k == 1 - i;
                        boolean flag9 = flag3 || flag4 && k == i - 1;
                        BlockState blockstate = block.getState(random, pos);
                        if (blockstate.hasProperty(HugeMushroomBlock.WEST) && blockstate.hasProperty(HugeMushroomBlock.EAST) && blockstate.hasProperty(HugeMushroomBlock.NORTH) && blockstate.hasProperty(HugeMushroomBlock.SOUTH)) {
                            blockstate = blockstate.setValue(HugeMushroomBlock.WEST, flag6).setValue(HugeMushroomBlock.EAST, flag7).setValue(HugeMushroomBlock.NORTH, flag8).setValue(HugeMushroomBlock.SOUTH, flag9);
                        }

                        this.setBlock(levelAccessor, mutablePos, blockstate);
                    }
                }
            }
        }
    }

    protected int getTreeRadiusForHeight(HugeSeashroomFeatureConfiguration config, int height) {
        if (height == this.bottomCapLevel) {
            return config.bottomFoliageRadius;
        } else if (config.layers != HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.ONE && height == this.midCapLevel) {
            return config.midFoliageRadius;
        } else if (config.layers == HugeSeashroomFeatureConfiguration.HugeSeashroomLayers.THREE && height == this.topCapLevel) {
            return config.topFoliageRadius;
        } else {
            return 0;
        }
    }
}