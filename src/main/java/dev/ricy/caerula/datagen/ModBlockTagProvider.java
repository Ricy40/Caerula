package dev.ricy.caerula.datagen;

import dev.ricy.caerula.Caerula;
import dev.ricy.caerula.block.ModBlocks;
import dev.ricy.caerula.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
                .add(ModBlocks.NIXIUM_STAIRS.get())
                .add(ModBlocks.NIXIUM_SLAB.get())
                .add(ModBlocks.NIXIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_NIXIUM_ORE.get())
                .add(ModBlocks.BUSH_CORAL.get())
                .add(ModBlocks.DEAD_BUSH_CORAL.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.NIXIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_NIXIUM_ORE.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.PURPLE_SEASHROOM_BLOCK.get())
                .add(ModBlocks.YELLOW_SEASHROOM_BLOCK.get())
                .add(ModBlocks.ORANGE_SEASHROOM_BLOCK.get())
                .add(ModBlocks.BROWN_SEASHROOM_BLOCK.get())
                .add(ModBlocks.SEASHROOM_STEM.get());

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.MYCELIUM_SAND.get());

        tag(ModTags.Blocks.MUSHROOM_GROW_BLOCK)
                .add(ModBlocks.MYCELIUM_SAND.get())
                .add(Blocks.SAND)
                .addTag(BlockTags.DIRT);
    }
}
