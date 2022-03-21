package com.ricy40.caerula.block.flora;

import com.ricy40.caerula.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TallRedSeagrassBlock extends TallSeagrassBlock {

    public TallRedSeagrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter blockReader, BlockPos pos, BlockState state) {
        return new ItemStack(ModBlocks.RED_SEAGRASS.get());
    }
}
