package dev.ricy.caerula.block.custom;

import dev.ricy.caerula.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TallRedSeagrassBlock extends TallSeagrassBlock {

    public TallRedSeagrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(ModBlocks.RED_SEAGRASS.get());
    }
}
