package com.ricy40.caerula.world.gen.biomes;

import com.ricy40.caerula.world.gen.placedfeatures.ModAquaticPlacements;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

import javax.annotation.Nullable;

import static com.ricy40.caerula.world.gen.biomes.ModBiomeDefaultFeatures.seashroomSpawns;

public class CaerulaBiomes {

    protected static final int NORMAL_WATER_COLOR = 4159204;
    protected static final int NORMAL_WATER_FOG_COLOR = 329011;
    private static final int OVERWORLD_FOG_COLOR = 12638463;
    @Nullable
    private static final Music NORMAL_MUSIC = null;

    protected static int calculateSkyColor(float colour) {
        float $$1 = colour / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, MobSpawnSettings.Builder mobSpawnSettings, BiomeGenerationSettings.Builder genSettings, @Nullable Music music) {
        return biome(precipitation, temperature, downfall, 4159204, 329011, mobSpawnSettings, genSettings, music);
    }

    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColour, int fogColour, MobSpawnSettings.Builder mobSpawns, BiomeGenerationSettings.Builder genSettings, @Nullable Music music) {
        return (new Biome.BiomeBuilder()).precipitation(precipitation).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColour).waterFogColor(fogColour).fogColor(12638463).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(mobSpawns.build()).generationSettings(genSettings.build()).build();
    }

    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder p_194870_) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(p_194870_);
        BiomeDefaultFeatures.addDefaultCrystalFormations(p_194870_);
        BiomeDefaultFeatures.addDefaultMonsterRoom(p_194870_);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(p_194870_);
        BiomeDefaultFeatures.addDefaultSprings(p_194870_);
        BiomeDefaultFeatures.addSurfaceFreezing(p_194870_);
    }
    
    private static Biome baseOcean(MobSpawnSettings.Builder mobSpawns, int waterColour, int fogColour, BiomeGenerationSettings.Builder genSettings) {
        return biome(Biome.Precipitation.RAIN, 0.5F, 0.5F, waterColour, fogColour, mobSpawns, genSettings, NORMAL_MUSIC);
    }
    
    private static BiomeGenerationSettings.Builder baseOceanGeneration() {
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomegenerationsettings$builder);
        ModBiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addWaterTrees(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultFlowers(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultGrass(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomegenerationsettings$builder);
        return biomegenerationsettings$builder;
    }

    public static Biome redFields(boolean isDeep) {
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        if (isDeep) {
            ModBiomeDefaultFeatures.oceanSpawns(mobspawnsettings$builder, 10, 4, 8);
        } else {
            ModBiomeDefaultFeatures.oceanSpawns(mobspawnsettings$builder, 10, 2, 15);
        }

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = baseOceanGeneration();
        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, isDeep ? ModAquaticPlacements.RED_SEAGRASS_DEEP_WARM.getHolder().get() : ModAquaticPlacements.RED_SEAGRASS_WARM.getHolder().get());
        if (isDeep) {
            ModBiomeDefaultFeatures.addDefaultSeagrass(biomegenerationsettings$builder);
        }

        return baseOcean(mobspawnsettings$builder, 4566514, 267827, biomegenerationsettings$builder);
    }

    public static Biome seashroomFields() {
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        seashroomSpawns(mobspawnsettings$builder);
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = baseOceanGeneration();
        ModBiomeDefaultFeatures.addSeashroomFieldVegetation(biomegenerationsettings$builder);
        return baseOcean(mobspawnsettings$builder, 4566514, 267827, biomegenerationsettings$builder);
    }
    
    public static Biome warmOcean() {
        MobSpawnSettings.Builder mobspawnsettings$builder = (new MobSpawnSettings.Builder()).addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.PUFFERFISH, 15, 1, 3));
        BiomeDefaultFeatures.warmOceanSpawns(mobspawnsettings$builder, 10, 4);
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = baseOceanGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.WARM_OCEAN_VEGETATION).addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM).addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEA_PICKLE);
        return baseOcean(mobspawnsettings$builder, 4445678, 270131, biomegenerationsettings$builder);
    }
    
}
