package com.ricy40.caerula.world.gen.configuredfeatures.features;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.block.ModBlocks;
import com.ricy40.caerula.world.gen.configuredfeatures.ModFeatures;
import com.ricy40.caerula.world.gen.configuredfeatures.features.hugeseashroom.HugeSeashroomFeatureConfiguration;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModTreeFeatures {

    public static DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Caerula.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<HugeSeashroomFeatureConfiguration, ?>> HUGE_PURPLE_SEASHROOM_ONECAP
            = CONFIGURED_FEATURES.register("huge_purple_seashroom_onecap", () -> new ConfiguredFeature<>(ModFeatures.HUGE_PURPLE_SEASHROOM.get(),
            new HugeSeashroomFeatureConfiguration(
                    BlockStateProvider.simple(ModBlocks.PURPLE_SEASHROOM_BLOCK.get().defaultBlockState()
                            .setValue(HugeMushroomBlock.UP, Boolean.TRUE)
                            .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                    BlockStateProvider.simple(Blocks.MUSHROOM_STEM.defaultBlockState()
                            .setValue(HugeMushroomBlock.UP, Boolean.FALSE)
                            .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)), 3)));

    public static final RegistryObject<ConfiguredFeature<HugeSeashroomFeatureConfiguration, ?>> HUGE_PURPLE_SEASHROOM_TWOCAP
            = CONFIGURED_FEATURES.register("huge_purple_seashroom_twocap", () -> new ConfiguredFeature<>(ModFeatures.HUGE_PURPLE_SEASHROOM.get(),
            new HugeSeashroomFeatureConfiguration(
                    BlockStateProvider.simple(ModBlocks.PURPLE_SEASHROOM_BLOCK.get().defaultBlockState()
                            .setValue(HugeMushroomBlock.UP, Boolean.TRUE)
                            .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                    BlockStateProvider.simple(ModBlocks.PURPLE_SEASHROOM_BLOCK.get().defaultBlockState()
                            .setValue(HugeMushroomBlock.UP, Boolean.TRUE)
                            .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                    BlockStateProvider.simple(Blocks.MUSHROOM_STEM.defaultBlockState()
                            .setValue(HugeMushroomBlock.UP, Boolean.FALSE)
                            .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)), 2, 3)));

    public static final RegistryObject<ConfiguredFeature<HugeSeashroomFeatureConfiguration, ?>> HUGE_PURPLE_SEASHROOM_THREECAP
            = CONFIGURED_FEATURES.register("huge_purple_seashroom_threecap", () -> new ConfiguredFeature<>(ModFeatures.HUGE_PURPLE_SEASHROOM.get(),
            new HugeSeashroomFeatureConfiguration(
                    BlockStateProvider.simple(ModBlocks.PURPLE_SEASHROOM_BLOCK.get().defaultBlockState()
                            .setValue(HugeMushroomBlock.UP, Boolean.TRUE)
                            .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                    BlockStateProvider.simple(ModBlocks.PURPLE_SEASHROOM_BLOCK.get().defaultBlockState()
                            .setValue(HugeMushroomBlock.UP, Boolean.TRUE)
                            .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                    BlockStateProvider.simple(ModBlocks.PURPLE_SEASHROOM_BLOCK.get().defaultBlockState()
                            .setValue(HugeMushroomBlock.UP, Boolean.TRUE)
                            .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                    BlockStateProvider.simple(Blocks.MUSHROOM_STEM.defaultBlockState()
                            .setValue(HugeMushroomBlock.UP, Boolean.FALSE)
                            .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)), 2, 3,4)));

    public static void register(IEventBus bus) {
        CONFIGURED_FEATURES.register(bus);
    }

    public static RegistryObject<ConfiguredFeature<?, ?>> registerConfiguredFeature(String name, Supplier<ConfiguredFeature<?, ?>> feature) {
        return CONFIGURED_FEATURES.register(name, feature);
    }
}
