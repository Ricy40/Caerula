package com.ricy40.caerula.data.client;

import com.ricy40.caerula.Caerula;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Caerula.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        withExistingParent("nixium_block", modLoc("block/nixium_block"));
        withExistingParent("raw_nixium_block", modLoc("block/raw_nixium_block"));
        withExistingParent("nixium_ore", modLoc("block/nixium_ore"));
        withExistingParent("deepslate_nixium_ore", modLoc("block/deepslate_nixium_ore"));
        withExistingParent("nixium_slab", modLoc("block/nixium_slab"));
        withExistingParent("nixium_stairs", modLoc("block/nixium_stairs"));

        //ModelFile itemGenerated = getExistingFile(mcLoc("block/some_block"));
        //builder(itemGenerated, "someblock");

    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
