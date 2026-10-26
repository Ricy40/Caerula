package dev.ricy.caerula.worldgen;

import dev.ricy.caerula.Caerula;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ORE_NIXIUM_UPPER_KEY = registerKey("ore_nixium_upper");
    public static final ResourceKey<PlacedFeature> ORE_NIXIUM_MIDDLE_KEY = registerKey("ore_nixium_middle");
    public static final ResourceKey<PlacedFeature> ORE_NIXIUM_SMALL_KEY = registerKey("ore_nixium_small");
    public static final ResourceKey<PlacedFeature> RED_SEAGRASS_SIMPLE_KEY = registerKey("red_seagrass_simple");
    public static final ResourceKey<PlacedFeature> HUGE_PURPLE_SEASHROOM_ONECAP_KEY = registerKey("huge_purple_seashroom_onecap");
    public static final ResourceKey<PlacedFeature> HUGE_PURPLE_SEASHROOM_TWOCAP_KEY = registerKey("huge_purple_seashroom_twocap");
    public static final ResourceKey<PlacedFeature> HUGE_PURPLE_SEASHROOM_THREECAP_KEY = registerKey("huge_purple_seashroom_threecap");
    public static final ResourceKey<PlacedFeature> HUGE_YELLOW_SEASHROOM_ONECAP_KEY = registerKey("huge_yellow_seashroom_onecap");
    public static final ResourceKey<PlacedFeature> HUGE_YELLOW_SEASHROOM_TWOCAP_KEY = registerKey("huge_yellow_seashroom_twocap");
    public static final ResourceKey<PlacedFeature> HUGE_YELLOW_SEASHROOM_THREECAP_KEY = registerKey("huge_yellow_seashroom_threecap");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ORE_NIXIUM_UPPER_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_NIXIUM_KEY),
                ModOrePlacements.commonOrePlacement(90,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))));
        register(context, ORE_NIXIUM_MIDDLE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_NIXIUM_KEY),
                ModOrePlacements.commonOrePlacement(10,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
        register(context, ORE_NIXIUM_SMALL_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_NIXIUM_SMALL_KEY),
                ModOrePlacements.commonOrePlacement(10,
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))));
        register(context, RED_SEAGRASS_SIMPLE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.RED_SEAGRASS_SIMPLE_KEY),
                List.of(RarityFilter.onAverageOnceEvery(10),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), Blocks.STONE),
                                BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.WATER),
                                BlockPredicate.matchesBlocks(Direction.UP.getUnitVec3i(), Blocks.WATER))),
                        BiomeFilter.biome()));

        // No placement modifiers: these aren't meant to generate naturally yet.
        // They exist so they can be tested directly with "/place feature".
        register(context, HUGE_PURPLE_SEASHROOM_ONECAP_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HUGE_PURPLE_SEASHROOM_ONECAP_KEY), List.of());
        register(context, HUGE_PURPLE_SEASHROOM_TWOCAP_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HUGE_PURPLE_SEASHROOM_TWOCAP_KEY), List.of());
        register(context, HUGE_PURPLE_SEASHROOM_THREECAP_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HUGE_PURPLE_SEASHROOM_THREECAP_KEY), List.of());
        register(context, HUGE_YELLOW_SEASHROOM_ONECAP_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HUGE_YELLOW_SEASHROOM_ONECAP_KEY), List.of());
        register(context, HUGE_YELLOW_SEASHROOM_TWOCAP_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HUGE_YELLOW_SEASHROOM_TWOCAP_KEY), List.of());
        register(context, HUGE_YELLOW_SEASHROOM_THREECAP_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HUGE_YELLOW_SEASHROOM_THREECAP_KEY), List.of());
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Caerula.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
