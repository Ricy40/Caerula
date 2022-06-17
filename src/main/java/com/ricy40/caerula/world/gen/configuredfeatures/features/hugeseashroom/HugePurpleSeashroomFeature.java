package com.ricy40.caerula.world.gen.configuredfeatures.features.hugeseashroom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HugePurpleSeashroomFeature extends AbstractHugeSeashroomFeature {

    public HugePurpleSeashroomFeature(Codec<HugeSeashroomFeatureConfiguration> config) {
        super(config);
    }

    protected void makeCap(LevelAccessor levelAccessor, RandomSource random, BlockPos pos, int height, BlockPos.MutableBlockPos mutablePos, HugeSeashroomFeatureConfiguration config) {
        int i = config.topFoliageRadius;

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
                        BlockState blockstate = config.topCapProvider.getState(random, pos);
                        if (blockstate.hasProperty(HugeMushroomBlock.WEST) && blockstate.hasProperty(HugeMushroomBlock.EAST) && blockstate.hasProperty(HugeMushroomBlock.NORTH) && blockstate.hasProperty(HugeMushroomBlock.SOUTH)) {
                            blockstate = blockstate.setValue(HugeMushroomBlock.WEST, flag6).setValue(HugeMushroomBlock.EAST, flag7).setValue(HugeMushroomBlock.NORTH, flag8).setValue(HugeMushroomBlock.SOUTH, flag9);
                        }

                        this.setBlock(levelAccessor, mutablePos, blockstate);
                    }
                }
            }
        }
    }

    protected int getTreeRadiusForHeight(int p_65881_, int p_65882_, int p_65883_, int p_65884_) {
        return p_65884_ <= 3 ? 0 : p_65883_;
    }
}