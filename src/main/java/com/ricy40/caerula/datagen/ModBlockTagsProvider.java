package com.ricy40.caerula.datagen;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, Caerula.MOD_ID, existingFileHelper);
    }

    protected void addTags() {
        tag(BlockTags.WALLS)
                ;

        tag(BlockTags.FENCES)
                ;

        tag(BlockTags.FENCE_GATES)
                ;

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.PURPLE_SEASHROOM_BLOCK.get())
                .add(ModBlocks.PURPLE_SEASHROOM.get())
                ;

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.NIXIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_NIXIUM_ORE.get())
                .add(ModBlocks.RAW_NIXIUM_BLOCK.get())
                .add(ModBlocks.NIXIUM_BLOCK.get())
                .add(ModBlocks.NIXIUM_STAIRS.get())
                .add(ModBlocks.NIXIUM_SLAB.get())
                .add(ModBlocks.BUSH_CORAL.get())
                .add(ModBlocks.DEAD_BUSH_CORAL.get())
                ;

        tag(BlockTags.MINEABLE_WITH_HOE)
            ;

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.MYCELIUM_SAND.get())
                ;

        tag(BlockTags.MUSHROOM_GROW_BLOCK)
                .add(ModBlocks.MYCELIUM_SAND.get())
                ;
    }
}
