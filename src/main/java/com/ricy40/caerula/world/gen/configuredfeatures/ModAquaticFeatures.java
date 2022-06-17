package com.ricy40.caerula.world.gen.configuredfeatures;


import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModAquaticFeatures {
    
    public static DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Caerula.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> RED_SEAGRASS_SHORT = registerConfiguredFeature("red_seagrass_short", () -> new ConfiguredFeature<>(ModFeatures.RED_SEAGRASS.get(), new ProbabilityFeatureConfiguration(0.3F)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> RED_SEAGRASS_SLIGHTLY_LESS_SHORT = registerConfiguredFeature("red_seagrass_slightly_less_short", () -> new ConfiguredFeature<>(ModFeatures.RED_SEAGRASS.get(), new ProbabilityFeatureConfiguration(0.4F)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> RED_SEAGRASS_MID = registerConfiguredFeature("red_seagrass_mid", () -> new ConfiguredFeature<>(ModFeatures.RED_SEAGRASS.get(), new ProbabilityFeatureConfiguration(0.6F)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> RED_SEAGRASS_TALL = registerConfiguredFeature("red_seagrass_tall", () -> new ConfiguredFeature<>(ModFeatures.RED_SEAGRASS.get(), new ProbabilityFeatureConfiguration(0.8F)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> RED_SEAGRASS_SIMPLE = registerConfiguredFeature("red_seagrass_simple", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.RED_SEAGRASS.get()))));

    public static void register(IEventBus bus) {
        CONFIGURED_FEATURES.register(bus);
    }

    public static RegistryObject<ConfiguredFeature<?, ?>> registerConfiguredFeature(String name, Supplier<ConfiguredFeature<?, ?>> feature) {
        return CONFIGURED_FEATURES.register(name, feature);
    }


}
