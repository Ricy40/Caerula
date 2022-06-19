package com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures;

import com.ricy40.caerula.Caerula;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModOreFeatures {

    public static DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Caerula.MOD_ID);
    public static List<String> configuredOreFeatureList = new ArrayList<>();

    public static final RuleTest STONE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    public static final RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    public static final List<OreConfiguration.TargetBlockState> ORE_NIXIUM_TARGET_LIST = List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, Blocks.IRON_ORE.defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_IRON_ORE.defaultBlockState()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_NIXIUM = registerConfiguredFeature("ore_nixium", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ORE_NIXIUM_TARGET_LIST, 9)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_NIXIUM_SMALL = registerConfiguredFeature("ore_nixium_small", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ORE_NIXIUM_TARGET_LIST, 4)));

    public static void register(IEventBus bus) {
        CONFIGURED_FEATURES.register(bus);
    }

    public static RegistryObject<ConfiguredFeature<?, ?>> registerConfiguredFeature(String name, Supplier<ConfiguredFeature<?, ?>> feature) {
        configuredOreFeatureList.add(name);
        return CONFIGURED_FEATURES.register(name, feature);
    }


}
