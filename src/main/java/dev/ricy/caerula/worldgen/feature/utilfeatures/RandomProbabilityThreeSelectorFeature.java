package dev.ricy.caerula.worldgen.feature.utilfeatures;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class RandomProbabilityThreeSelectorFeature extends Feature<RandomProbabilityThreeFeatureConfiguration> {

    public RandomProbabilityThreeSelectorFeature(Codec<RandomProbabilityThreeFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<RandomProbabilityThreeFeatureConfiguration> context) {
        RandomSource random = context.random();
        RandomProbabilityThreeFeatureConfiguration config = context.config();
        BlockPos origin = context.origin();

        double totalChance = config.probability1 + config.probability2 + config.probability3;
        double chanceFor1 = config.probability1 / totalChance;
        double chanceFor2 = (config.probability2 / totalChance) + chanceFor1;

        double roll = random.nextDouble();

        PlacedFeature feature;
        if (roll <= chanceFor1) {
            feature = config.feature1.value();
        } else if (roll <= chanceFor2) {
            feature = config.feature2.value();
        } else {
            feature = config.feature3.value();
        }

        return feature.place(context.level(), context.chunkGenerator(), random, origin);
    }
}
