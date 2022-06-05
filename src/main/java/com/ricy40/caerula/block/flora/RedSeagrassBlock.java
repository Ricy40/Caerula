package com.ricy40.caerula.block.flora;

import com.ricy40.caerula.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SeagrassBlock;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import java.util.Random;

public class RedSeagrassBlock extends SeagrassBlock {

    public RedSeagrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {
        BlockState blockstate = ModBlocks.TALL_RED_SEAGRASS.get().defaultBlockState();
        BlockState blockstate1 = blockstate.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER);
        BlockPos blockpos = pos.above();
        if (worldIn.getBlockState(blockpos).is(Blocks.WATER)) {
            worldIn.setBlock(pos, blockstate, 2);
            worldIn.setBlock(blockpos, blockstate1, 2);
        }
    }
}
