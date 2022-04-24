package com.ricy40.caerula.data;

import com.ricy40.caerula.Caerula;
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
                //.add(ModBlocks.WALLBLOCK)
                ;

        tag(BlockTags.FENCES)
                //.add(ModBlocks.FENCEBLOCK)
                ;

        tag(BlockTags.FENCE_GATES)
                //.add(ModBlocks.FENCEGATEBLOCK)
                ;
    }
}
