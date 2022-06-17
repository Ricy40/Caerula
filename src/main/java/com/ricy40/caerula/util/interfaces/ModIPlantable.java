package com.ricy40.caerula.util.interfaces;

import com.ricy40.caerula.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.PlantType;

public interface ModIPlantable
{
    default ModPlantType getPlantType(BlockGetter level, BlockPos pos) {
        if (this == ModBlocks.PURPLE_SEASHROOM.get())       return ModPlantType.OCEAN;
        return ModPlantType.PLAINS;
    }

    BlockState getPlant(BlockGetter level, BlockPos pos);
}

