package dev.ricy.caerula.worldgen;

import dev.ricy.caerula.Caerula;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_NIXIUM_ORES = registerKey("add_nixium_ores");
    public static final ResourceKey<BiomeModifier> ADD_RED_SEAGRASS = registerKey("add_red_seagrass");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_NIXIUM_ORES, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OCEAN),
                HolderSet.direct(
                        placedFeatures.getOrThrow(ModPlacedFeatures.ORE_NIXIUM_UPPER_KEY),
                        placedFeatures.getOrThrow(ModPlacedFeatures.ORE_NIXIUM_MIDDLE_KEY),
                        placedFeatures.getOrThrow(ModPlacedFeatures.ORE_NIXIUM_SMALL_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_RED_SEAGRASS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OCEAN),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.RED_SEAGRASS_SIMPLE_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Identifier.fromNamespaceAndPath(Caerula.MOD_ID, name));
    }
}
