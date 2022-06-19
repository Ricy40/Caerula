package com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.features.utilfeatures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

public class RandomProbabilityThreeFeatureConfiguration implements FeatureConfiguration {

    public static final Codec<RandomProbabilityThreeFeatureConfiguration> CODEC = RecordCodecBuilder.create((feature) -> {
        return feature.group(PlacedFeature.CODEC.fieldOf("feature_1").forGetter((feature1) -> {
            return feature1.feature1;
        }), PlacedFeature.CODEC.fieldOf("feature_2").forGetter((feature2) -> {
            return feature2.feature2;
        }), PlacedFeature.CODEC.fieldOf("feature_3").forGetter((feature3) -> {
            return feature3.feature3;
        }), Codec.INT.fieldOf("probability1").orElse(1).forGetter((probability) -> {
            return probability.probability1;
        }), Codec.INT.fieldOf("probability2").orElse(1).forGetter((probability) -> {
            return probability.probability2;
        }), Codec.INT.fieldOf("probability3").orElse(1).forGetter((probability) -> {
            return probability.probability3;
        })).apply(feature, RandomProbabilityThreeFeatureConfiguration::new);
    });

    public final Holder<PlacedFeature> feature1;
    public final Holder<PlacedFeature> feature2;
    public final Holder<PlacedFeature> feature3;
    public final int probability1;
    public final int probability2;
    public final int probability3;

    public RandomProbabilityThreeFeatureConfiguration(Holder<PlacedFeature> feature1, Holder<PlacedFeature> feature2, Holder<PlacedFeature> feature3, int probability1, int probability2, int probability3) {
        this.feature1 = feature1;
        this.feature2 = feature2;
        this.feature3 = feature3;
        this.probability1 = probability1;
        this.probability2 = probability2;
        this.probability3 = probability3;
    }

    public Stream<ConfiguredFeature<?, ?>> getFeatures() {
        return Stream.concat(concat(this.feature1.value().getFeatures(), this.feature2.value().getFeatures()), this.feature3.value().getFeatures());
    }
}
