package com.ricy.caerula.datagen;

import com.ricy.caerula.Caerula;
import com.ricy.caerula.block.ModBlocks;
import com.ricy.caerula.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTab;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, Caerula.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

        /* ITEMS */
        itemModels.generateFlatItem(ModItems.CAERULA.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_NIXIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.NIXIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.NIXIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);




        /* BLOCKS */
        blockModels.createTrivialCube(ModBlocks.NIXIUM_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RAW_NIXIUM_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.NIXIUM_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_NIXIUM_ORE.get());
        //blockModels.createSlab(ModBlocks.NIXIUM_SLAB.get(), caerula("block/nixium_block"), caerula("block/nixium_block"));
        //blockModels.createStairs(ModBlocks.NIXIUM_STAIRS.get(), "block/nixium", caerula("block/nixium_block"));

    }
}
