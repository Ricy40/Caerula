package com.ricy.caerula.datagen;

import com.ricy.caerula.block.ModBlocks;
import com.ricy.caerula.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.Iterator;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.NIXIUM_BLOCK.get());
        dropSelf(ModBlocks.RAW_NIXIUM_BLOCK.get());
        //dropSelf(ModBlocks.NIXIUM_STAIRS.get());
        //dropSelf(ModBlocks.NIXIUM_SLAB.get());

        add(ModBlocks.NIXIUM_ORE.get(), block -> createOreDrop(block, ModItems.RAW_NIXIUM.get()));
        add(ModBlocks.DEEPSLATE_NIXIUM_ORE.get(), block -> createOreDrop(block, ModItems.RAW_NIXIUM.get()));

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
