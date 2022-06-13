package com.ricy40.caerula.world.gen.configuredfeatures;

import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomeDefaultFeatures {

    public static void addDefaultSeagrass(BiomeGenerationSettings.Builder pBuilder) {
        pBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModAquaticPlacements.RED_SEAGRASS_SIMPLE.getHolder().get());
    }

}
