package com.ricy40.caerula.world.gen.configuredfeatures;

import com.ricy40.caerula.Caerula;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModAquaticPlacements {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Caerula.MOD_ID);

    public static final RegistryObject<PlacedFeature> RED_SEAGRASS_WARM = registerPlacedFeature("red_seagrass_warm", () -> new PlacedFeature(ModAquaticFeatures.RED_SEAGRASS_SHORT.getHolder().get(), seagrassPlacement(80)));
    public static final RegistryObject<PlacedFeature> RED_SEAGRASS_NORMAL = registerPlacedFeature("red_seagrass_normal", () -> new PlacedFeature(ModAquaticFeatures.RED_SEAGRASS_SHORT.getHolder().get(), seagrassPlacement(48)));
    public static final RegistryObject<PlacedFeature> RED_SEAGRASS_COLD = registerPlacedFeature("red_seagrass_cold", () -> new PlacedFeature(ModAquaticFeatures.RED_SEAGRASS_SHORT.getHolder().get(), seagrassPlacement(32)));
    public static final RegistryObject<PlacedFeature> RED_SEAGRASS_RIVER = registerPlacedFeature("red_seagrass_river", () -> new PlacedFeature(ModAquaticFeatures.RED_SEAGRASS_SLIGHTLY_LESS_SHORT.getHolder().get(), seagrassPlacement(48)));
    public static final RegistryObject<PlacedFeature> RED_SEAGRASS_SWAMP = registerPlacedFeature("red_seagrass_swamp", () -> new PlacedFeature(ModAquaticFeatures.RED_SEAGRASS_MID.getHolder().get(), seagrassPlacement(64)));
    public static final RegistryObject<PlacedFeature> RED_SEAGRASS_DEEP_WARM = registerPlacedFeature("red_seagrass_deep_warm", () -> new PlacedFeature(ModAquaticFeatures.RED_SEAGRASS_TALL.getHolder().get(), seagrassPlacement(80)));
    public static final RegistryObject<PlacedFeature> RED_SEAGRASS_DEEP = registerPlacedFeature("red_seagrass_deep", () -> new PlacedFeature(ModAquaticFeatures.RED_SEAGRASS_TALL.getHolder().get(), seagrassPlacement(48)));
    public static final RegistryObject<PlacedFeature> RED_SEAGRASS_DEEP_COLD = registerPlacedFeature("red_seagrass_deep_cold", () -> new PlacedFeature(ModAquaticFeatures.RED_SEAGRASS_TALL.getHolder().get(), seagrassPlacement(40)));
    public static final RegistryObject<PlacedFeature> RED_SEAGRASS_SIMPLE = registerPlacedFeature("red_seagrass_simple", () -> new PlacedFeature(ModAquaticFeatures.RED_SEAGRASS_SIMPLE.getHolder().get(), seagrassPlacement(CarvingMaskPlacement.forStep(GenerationStep.Carving.LIQUID), RarityFilter.onAverageOnceEvery(10), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.STONE), BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.WATER), BlockPredicate.matchesBlocks(Direction.UP.getNormal(), Blocks.WATER))), BiomeFilter.biome())));

    public static List<PlacementModifier> seagrassPlacement(int spread) {
        return List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, CountPlacement.of(spread), BiomeFilter.biome());
    }
    
    public static void register(IEventBus bus) {
        PLACED_FEATURES.register(bus);
    }
    
    private static RegistryObject<PlacedFeature> registerPlacedFeature(String name, Supplier<PlacedFeature> feature) {
        return PLACED_FEATURES.register(name, feature);
    }

    public static List<PlacementModifier> seagrassPlacement(PlacementModifier... modifiers) {
        return List.of(modifiers);
    }
}
