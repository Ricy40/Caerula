package dev.ricy.caerula.block.custom;

import dev.ricy.caerula.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SeagrassBlock;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class RedSeagrassBlock extends SeagrassBlock {

    public RedSeagrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockState tallState = ModBlocks.TALL_RED_SEAGRASS.get().defaultBlockState();
        BlockState upperState = tallState.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER);
        BlockPos abovePos = pos.above();
        if (level.getBlockState(abovePos).is(Blocks.WATER)) {
            level.setBlock(pos, tallState, 2);
            level.setBlock(abovePos, upperState, 2);
        }
    }
}
