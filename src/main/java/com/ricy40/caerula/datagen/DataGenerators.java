package com.ricy40.caerula.datagen;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.datagen.client.ModBlockStateProvider;
import com.ricy40.caerula.datagen.client.ModItemModelProvider;
import com.ricy40.caerula.datagen.client.ModLootTableProvider;
import com.ricy40.caerula.datagen.client.ModRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Caerula.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(event.includeClient(), new ModBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(event.includeClient(), new ModItemModelProvider(gen, existingFileHelper));

        ModBlockTagsProvider blockTags = new ModBlockTagsProvider(gen, existingFileHelper);
        gen.addProvider(event.includeClient(), blockTags);
        gen.addProvider(event.includeClient(), new ModItemTagsProvider(gen, blockTags, existingFileHelper));

        gen.addProvider(event.includeClient(), new ModLootTableProvider(gen));
        gen.addProvider(event.includeClient(), new ModRecipeProvider(gen));
    }
}
