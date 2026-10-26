package dev.ricy.caerula.worldgen.feature;

import dev.ricy.caerula.Caerula;
import dev.ricy.caerula.worldgen.feature.hugeseashroom.HugePurpleSeashroomFeature;
import dev.ricy.caerula.worldgen.feature.hugeseashroom.HugeSeashroomFeatureConfiguration;
import dev.ricy.caerula.worldgen.feature.hugeseashroom.HugeYellowSeashroomFeature;
import dev.ricy.caerula.worldgen.feature.utilfeatures.RandomProbabilityThreeFeatureConfiguration;
import dev.ricy.caerula.worldgen.feature.utilfeatures.RandomProbabilityThreeSelectorFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Caerula.MOD_ID);

    public static final DeferredHolder<Feature<?>, Feature<RandomProbabilityThreeFeatureConfiguration>> RANDOM_PROBABILITY_THREE_SELECTOR =
            FEATURES.register("random_probability_selector_three", () -> new RandomProbabilityThreeSelectorFeature(RandomProbabilityThreeFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<ProbabilityFeatureConfiguration>> RED_SEAGRASS =
            FEATURES.register("red_seagrass", () -> new RedSeagrassFeature(ProbabilityFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<HugeSeashroomFeatureConfiguration>> HUGE_PURPLE_SEASHROOM =
            FEATURES.register("huge_purple_seashroom", () -> new HugePurpleSeashroomFeature(HugeSeashroomFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<HugeSeashroomFeatureConfiguration>> HUGE_YELLOW_SEASHROOM =
            FEATURES.register("huge_yellow_seashroom", () -> new HugeYellowSeashroomFeature(HugeSeashroomFeatureConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
