package com.ricy40.caerula.datagen.client;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
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

        simpleItem(ModItems.NIXIUM_INGOT.get());
        simpleItem(ModItems.NIXIUM_NUGGET.get());
        simpleItem(ModItems.RAW_NIXIUM.get());
        
        simpleItem(ModItems.BLOBFISH_BUCKET.get());
        simpleItem(ModItems.BLOBFISH.get());
        simpleItem(ModItems.COOKED_BLOBFISH.get());
        basicSpawnEggItem(ModItems.BLOBFISH_SPAWN_EGG.get());

        simpleItem(ModItems.LULA_BUCKET.get());
        simpleItem(ModItems.LULA.get());
        simpleItem(ModItems.COOKED_LULA.get());
        basicSpawnEggItem(ModItems.LULA_SPAWN_EGG.get());


    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Caerula.MOD_ID,"item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder handheldItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Caerula.MOD_ID,"item/" + item.getRegistryName().getPath()));
    }

    private void basicSpawnEggItem(Item item) {
        withExistingParent(item.getRegistryName().getPath(), "item/template_spawn_egg");
    }
}
