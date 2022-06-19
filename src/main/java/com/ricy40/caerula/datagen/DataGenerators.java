package com.ricy40.caerula.datagen;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.datagen.client.*;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import com.ricy40.caerula.world.gen.biomes.ModBiomes;
import com.ricy40.caerula.world.gen.placedfeatures.ModOrePlacements;
import com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.ModAquaticFeatures;
import com.ricy40.caerula.world.gen.placedfeatures.ModAquaticPlacements;
import com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.ModOreFeatures;
import com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.ModTreeFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Caerula.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {

    public static final String MODID = Caerula.MOD_ID;

    public static final String TEST = "test";
    public static final ResourceLocation ADD_FEATURES_TO_BIOMES_RL = new ResourceLocation(MODID, TEST);

    public DataGenerators() {

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Serializer types can be registered via deferred register.
        final DeferredRegister<Codec<? extends BiomeModifier>> serializers = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MODID);
        serializers.register(modBus);
        serializers.register(TEST, TestModifier::makeCodec);

    }

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new ModBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(generator, existingFileHelper));

        ModBlockTagsProvider blockTags = new ModBlockTagsProvider(generator, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(generator, blockTags, existingFileHelper));

        generator.addProvider(event.includeServer(), new ModLootTableProvider(generator));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(generator));

        // Any reference holders our objects have must come from this same RegistryAccess instance,
        // or encoding our objects will fail.
        final RegistryAccess registries = RegistryAccess.builtinCopy();
        final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, registries);

        // Create and add our data providers.
        final DataProvider configuredFeatureProvider = JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper, MODID, ops, Registry.CONFIGURED_FEATURE_REGISTRY, getConfiguredFeatures(registries));
        generator.addProvider(event.includeServer(), configuredFeatureProvider);

        final DataProvider placedFeatureProvider = JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper, MODID, ops, Registry.PLACED_FEATURE_REGISTRY, getPlacedFeatures(registries));
        generator.addProvider(event.includeServer(), placedFeatureProvider);

        final DataProvider biomeProvider = JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper, MODID, ops, Registry.BIOME_REGISTRY, getBiomes(registries));
        generator.addProvider(event.includeServer(), biomeProvider);

    }

    public static Map<ResourceLocation, ConfiguredFeature<?, ?>> getConfiguredFeatures(RegistryAccess registries) {
        Map<ResourceLocation, ConfiguredFeature<?, ?>> map = new HashMap<>();

        for (int i = 0; i < ModAquaticFeatures.configuredFeatureList.size(); i++) {
            ResourceLocation RL = new ResourceLocation(MODID, ModAquaticFeatures.configuredFeatureList.get(i));
            Registry<ConfiguredFeature<?, ?>> configuredFeatures = registries.registryOrThrow(Registry.CONFIGURED_FEATURE_REGISTRY);
            ConfiguredFeature<?, ?> PF = configuredFeatures.get(RL);
            map.put(RL, PF);
        }
        for (int i = 0; i < ModTreeFeatures.configuredTreeFeatureList.size(); i++) {
            ResourceLocation RL = new ResourceLocation(MODID, ModTreeFeatures.configuredTreeFeatureList.get(i));
            Registry<ConfiguredFeature<?, ?>> configuredFeatures = registries.registryOrThrow(Registry.CONFIGURED_FEATURE_REGISTRY);
            ConfiguredFeature<?, ?> PF = configuredFeatures.get(RL);
            map.put(RL, PF);
        }
        for (int i = 0; i < ModOreFeatures.configuredOreFeatureList.size(); i++) {
            ResourceLocation RL = new ResourceLocation(MODID, ModOreFeatures.configuredOreFeatureList.get(i));
            Registry<ConfiguredFeature<?, ?>> configuredFeatures = registries.registryOrThrow(Registry.CONFIGURED_FEATURE_REGISTRY);
            ConfiguredFeature<?, ?> PF = configuredFeatures.get(RL);
            map.put(RL, PF);
        }

        return map;
    }

    public static Map<ResourceLocation, PlacedFeature> getPlacedFeatures(RegistryAccess registries) {
        Map<ResourceLocation, PlacedFeature> map = new HashMap<>();

        for (int i = 0; i < ModAquaticPlacements.placedFeatureList.size(); i++) {
            ResourceLocation RL = new ResourceLocation(MODID, ModAquaticPlacements.placedFeatureList.get(i));
            Registry<PlacedFeature> placedFeatures = registries.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY);
            PlacedFeature PF = placedFeatures.get(RL);
            map.put(RL, PF);
        }
        for (int i = 0; i < ModOrePlacements.placedOreFeatureList.size(); i++) {
            ResourceLocation RL = new ResourceLocation(MODID, ModOrePlacements.placedOreFeatureList.get(i));
            Registry<PlacedFeature> placedFeatures = registries.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY);
            PlacedFeature PF = placedFeatures.get(RL);
            map.put(RL, PF);
        }
        return map;
    }

    public static Map<ResourceLocation, Biome> getBiomes(RegistryAccess registries) {
        Map<ResourceLocation, Biome> map = new HashMap<>();

        for (int i = 0; i < ModBiomes.biomeList.size(); i++) {
            ResourceLocation RL = new ResourceLocation(MODID, ModBiomes.biomeList.get(i));
            Registry<Biome> biomes = registries.registryOrThrow(Registry.BIOME_REGISTRY);
            Biome B = biomes.get(RL);
            map.put(RL, B);
        }
        return map;
    }

    public record TestModifier(HolderSet<Biome> biomes, Decoration generationStage, HolderSet<PlacedFeature> features, SpawnerData spawn, Precipitation precipitation, int waterColor) implements BiomeModifier {
        private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(ADD_FEATURES_TO_BIOMES_RL, ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MODID);

        @Override
        public void modify(Holder<Biome> biome, Phase phase, Builder builder)
        {
            if (phase == Phase.ADD && this.biomes.contains(biome))
            {
                BiomeGenerationSettingsBuilder generation = builder.getGenerationSettings();
                this.features.forEach(holder -> generation.addFeature(this.generationStage, holder));
                builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), this.spawn);
            }
            else if (phase == Phase.MODIFY && this.biomes.contains(biome))
            {
                builder.getClimateSettings().setPrecipitation(this.precipitation);
                builder.getEffects().waterColor(this.waterColor);
                if (this.precipitation == Precipitation.SNOW)
                    builder.getClimateSettings().setTemperature(0F);
            }
        }

        @Override
        public Codec<? extends BiomeModifier> codec()
        {
            return SERIALIZER.get();
        }

        private static Codec<TestModifier> makeCodec()
        {
            return RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(TestModifier::biomes),
                    Codec.STRING.comapFlatMap(TestModifier::generationStageFromString, Decoration::toString).fieldOf("generation_stage").forGetter(TestModifier::generationStage),
                    PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(TestModifier::features),
                    SpawnerData.CODEC.fieldOf("spawn").forGetter(TestModifier::spawn),
                    Precipitation.CODEC.fieldOf("precipitation").forGetter(TestModifier::precipitation),
                    Codec.INT.fieldOf("water_color").forGetter(TestModifier::waterColor)
            ).apply(builder, TestModifier::new));
        }

        private static DataResult<Decoration> generationStageFromString(String name)
        {
            try
            {
                return DataResult.success(Decoration.valueOf(name));
            }
            catch (Exception e)
            {
                return DataResult.error("Not a decoration stage: " + name);
            }
        }
    }
}
