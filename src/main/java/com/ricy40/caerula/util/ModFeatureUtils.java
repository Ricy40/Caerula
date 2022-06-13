package com.ricy40.caerula.util;

import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class ModFeatureUtils extends FeatureUtils {
    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String name, F modFeature, FC configFeature) {
        return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_FEATURE, name, new ConfiguredFeature<>(modFeature, configFeature));
    }
}
