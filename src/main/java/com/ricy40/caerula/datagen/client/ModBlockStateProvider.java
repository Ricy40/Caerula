package com.ricy40.caerula.datagen.client;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider<T extends ModelBuilder<T>> extends BlockStateProvider {

    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen, Caerula.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        simpleBlock(ModBlocks.NIXIUM_BLOCK.get());
        simpleBlock(ModBlocks.RAW_NIXIUM_BLOCK.get());
        simpleBlock(ModBlocks.NIXIUM_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_NIXIUM_ORE.get());
        slabBlock(ModBlocks.NIXIUM_SLAB.get(), caerula("block/nixium_block"), caerula("block/nixium_block"));
        stairsBlock(ModBlocks.NIXIUM_STAIRS.get(), "block/nixium", caerula("block/nixium_block"));
    }

    private static ResourceLocation caerula(String path) {
        return new ResourceLocation(Caerula.MOD_ID, path);
    }
}
