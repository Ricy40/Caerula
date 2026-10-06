package com.ricy.caerula.datagen;

import com.ricy.caerula.Caerula;
import com.ricy.caerula.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Caerula.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.NIXIUM_BLOCK.get())
                .add(ModBlocks.RAW_NIXIUM_BLOCK.get())
                //.add(ModBlocks.NIXIUM_STAIRS.get())
                //.add(ModBlocks.NIXIUM_SLAB.get())
                .add(ModBlocks.NIXIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_NIXIUM_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.NIXIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_NIXIUM_ORE.get());
    }
}
