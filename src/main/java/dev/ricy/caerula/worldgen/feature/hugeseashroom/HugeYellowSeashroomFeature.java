package dev.ricy.caerula.worldgen.feature.hugeseashroom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class HugeYellowSeashroomFeature extends AbstractHugeSeashroomFeature {

    public HugeYellowSeashroomFeature(Codec<HugeSeashroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected void makeCap(WorldGenLevel level, RandomSource random, BlockPos pos, int height, BlockPos.MutableBlockPos mutablePos, int radius, BlockStateProvider block) {
        for (int j = -radius; j <= radius; ++j) {
            for (int k = -radius; k <= radius; ++k) {
                boolean edgeX = j == -radius;
                boolean edgeX2 = j == radius;
                boolean edgeZ = k == -radius;
                boolean edgeZ2 = k == radius;
                boolean edge = edgeX || edgeX2;
                boolean edgeZs = edgeZ || edgeZ2;
                if (!edge || !edgeZs) {
                    int offsetHeight = j == radius || j == -radius || k == radius || k == -radius ? height + 1 : height;

                    mutablePos.setWithOffset(pos, j, offsetHeight, k);
                    if (!level.getBlockState(mutablePos).isSolidRender()) {
                        boolean west = edgeX || edgeZs && j == 1 - radius;
                        boolean east = edgeX2 || edgeZs && j == radius - 1;
                        boolean north = edgeZ || edge && k == 1 - radius;
                        boolean south = edgeZ2 || edge && k == radius - 1;
                        BlockState state = block.getState(level, random, pos);
                        if (state.hasProperty(HugeMushroomBlock.WEST) && state.hasProperty(HugeMushroomBlock.EAST) && state.hasProperty(HugeMushroomBlock.NORTH) && state.hasProperty(HugeMushroomBlock.SOUTH)) {
                            state = edgeX || edgeX2 || edgeZ || edgeZ2
                                    ? state.setValue(HugeMushroomBlock.NORTH, true).setValue(HugeMushroomBlock.SOUTH, true).setValue(HugeMushroomBlock.WEST, true).setValue(HugeMushroomBlock.EAST, true)
                                    : state.setValue(HugeMushroomBlock.WEST, west).setValue(HugeMushroomBlock.EAST, east).setValue(HugeMushroomBlock.NORTH, north).setValue(HugeMushroomBlock.SOUTH, south);
                        }
                        this.setBlock(level, mutablePos, state);
                    }
                }
            }
        }
    }

    @Override
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
