package dev.ricy.caerula.datagen;

import dev.ricy.caerula.block.ModBlocks;
import dev.ricy.caerula.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
        dropSelf(ModBlocks.NIXIUM_STAIRS.get());
        add(ModBlocks.NIXIUM_SLAB.get(), this::createSlabItemTable);

        add(ModBlocks.NIXIUM_ORE.get(), block -> createOreDrop(block, ModItems.RAW_NIXIUM.get()));
        add(ModBlocks.DEEPSLATE_NIXIUM_ORE.get(), block -> createOreDrop(block, ModItems.RAW_NIXIUM.get()));

        add(ModBlocks.RED_SEAGRASS.get(), createShearsOrSilkTouchOnlyDrop(ModBlocks.RED_SEAGRASS.get()));
        add(ModBlocks.TALL_RED_SEAGRASS.get(), createShearsOrSilkTouchOnlyDrop(ModBlocks.RED_SEAGRASS.get()));
        dropSelf(ModBlocks.BUSH_CORAL.get());
        dropSelf(ModBlocks.DEAD_BUSH_CORAL.get());
        add(ModBlocks.MYCELIUM_SAND.get(), block -> createSingleItemTableWithSilkTouch(block, Blocks.SAND));

        dropSelf(ModBlocks.PURPLE_SEASHROOM.get());
        dropSelf(ModBlocks.YELLOW_SEASHROOM.get());

        add(ModBlocks.PURPLE_SEASHROOM_BLOCK.get(), block -> createMushroomBlockDrop(block, ModBlocks.PURPLE_SEASHROOM.get()));
        add(ModBlocks.YELLOW_SEASHROOM_BLOCK.get(), block -> createMushroomBlockDrop(block, ModBlocks.YELLOW_SEASHROOM.get()));
        add(ModBlocks.ORANGE_SEASHROOM_BLOCK.get(), block -> createMushroomBlockDrop(block, ModBlocks.YELLOW_SEASHROOM.get()));
        add(ModBlocks.BROWN_SEASHROOM_BLOCK.get(), block -> createMushroomBlockDrop(block, ModBlocks.YELLOW_SEASHROOM.get()));
        add(ModBlocks.SEASHROOM_STEM.get(), this::createSilkTouchOnlyTable);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
