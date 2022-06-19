package com.ricy40.caerula.world.gen.placedfeatures;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.ModAquaticFeatures;
import com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.ModOreFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModOrePlacements {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Caerula.MOD_ID);
    public static List<String> placedOreFeatureList = new ArrayList<>();

    public static final  RegistryObject<PlacedFeature> ORE_NIXIUM_UPPER = registerPlacedFeature("ore_nixium_upper", () -> new PlacedFeature(ModOreFeatures.ORE_NIXIUM.getHolder().get(), commonOrePlacement(90, HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384)))));
    public static final  RegistryObject<PlacedFeature> ORE_NIXIUM_MIDDLE = registerPlacedFeature("ore_nixium_middle", () -> new PlacedFeature(ModOreFeatures.ORE_NIXIUM.getHolder().get(), commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)))));
    public static final  RegistryObject<PlacedFeature> ORE_NIXIUM_SMALL = registerPlacedFeature("ore_nixium_small", () -> new PlacedFeature(ModOreFeatures.ORE_NIXIUM_SMALL.getHolder().get(), commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72)))));

    public static void register(IEventBus bus) {
        PLACED_FEATURES.register(bus);
    }

    private static RegistryObject<PlacedFeature> registerPlacedFeature(String name, Supplier<PlacedFeature> feature) {
        placedOreFeatureList.add(name);
        return PLACED_FEATURES.register(name, feature);
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier modifier1, PlacementModifier modifier2) {
        return List.of(modifier1, InSquarePlacement.spread(), modifier2, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(count), modifier);
    }

    private static List<PlacementModifier> rareOrePlacement(int rarity, PlacementModifier modifer) {
        return orePlacement(RarityFilter.onAverageOnceEvery(rarity), modifer);
    }
}
