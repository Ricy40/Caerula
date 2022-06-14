package com.ricy40.caerula.world.gen.configuredfeatures;

import com.ricy40.caerula.entity.ModEntityTypes;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomeDefaultFeatures {

    public static void addDefaultSeagrass(BiomeGenerationSettings.Builder pBuilder) {
        pBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModAquaticPlacements.RED_SEAGRASS_SIMPLE.getHolder().get());
    }

    public static void oceanSpawns(MobSpawnSettings.Builder pBuilder, int pLulaWeight, int pLulaMaxCount, int pBlobfishWeight) {
        pBuilder.addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.LULA.get(), pLulaWeight, 1, pLulaMaxCount));
        pBuilder.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(ModEntityTypes.BLOBFISH.get(), pBlobfishWeight, 3, 6));
    }

}
