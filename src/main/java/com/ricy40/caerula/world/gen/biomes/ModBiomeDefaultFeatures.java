package com.ricy40.caerula.world.gen.biomes;

import com.ricy40.caerula.entity.ModEntityTypes;
import com.ricy40.caerula.world.gen.placedfeatures.ModAquaticPlacements;
import com.ricy40.caerula.world.gen.placedfeatures.ModOrePlacements;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
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

    public static void seashroomSpawns(MobSpawnSettings.Builder pBuilder) {
        //pBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SEASHROOM_MOB, 8, 4, 8));
    }

    public static void addSeashroomFieldVegetation(BiomeGenerationSettings.Builder pBuilder) {
        pBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModAquaticPlacements.SEASHROOM_FIELDS_TREES.getHolder().get());
        pBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModAquaticPlacements.PURPLE_SEASHROOM_FIELDS.getHolder().get());
    }
    
    public static void addDefaultOres(BiomeGenerationSettings.Builder pBuilder) {
        addDefaultOres(pBuilder, false);
    }
    
    public static void addDefaultOres(BiomeGenerationSettings.Builder pBuilder, boolean large) {
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOrePlacements.ORE_NIXIUM_UPPER.getHolder().get());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOrePlacements.ORE_NIXIUM_MIDDLE.getHolder().get());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOrePlacements.ORE_NIXIUM_SMALL.getHolder().get());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COAL_UPPER);
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COAL_LOWER);
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_UPPER);
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_MIDDLE);
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_SMALL);
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GOLD);
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GOLD_LOWER);
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, large ? OrePlacements.ORE_COPPER_LARGE : OrePlacements.ORE_COPPER);
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CavePlacements.UNDERWATER_MAGMA);
    }

}
