package com.ricy40.caerula.data;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.tags.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator generatorIn, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(generatorIn, blockTagsProvider, Caerula.MOD_ID, existingFileHelper);
    }

    protected void addTags() {
        copy(ModTags.Blocks.WALLS, ModTags.Items.WALLS);
        copy(ModTags.Blocks.FENCES, ModTags.Items.FENCES);
        copy(ModTags.Blocks.FENCE_GATES, ModTags.Items.FENCE_GATES);
    }
}
