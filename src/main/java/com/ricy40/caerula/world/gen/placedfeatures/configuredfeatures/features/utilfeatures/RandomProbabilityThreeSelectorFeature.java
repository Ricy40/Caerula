package com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.features.utilfeatures;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class RandomProbabilityThreeSelectorFeature extends Feature<RandomProbabilityThreeFeatureConfiguration> {
    public RandomProbabilityThreeSelectorFeature(Codec<RandomProbabilityThreeFeatureConfiguration> codec) {
        super(codec);
    }
    
    public boolean place(FeaturePlaceContext<RandomProbabilityThreeFeatureConfiguration> context) {
        RandomSource randomsource = context.random();
        RandomProbabilityThreeFeatureConfiguration config = context.config();
        WorldGenLevel worldgenlevel = context.level();
        ChunkGenerator chunkgenerator = context.chunkGenerator();
        BlockPos blockpos = context.origin();

        int totalChance = config.probability1 + config.probability2 + config.probability3;
        double chanceFor1 = config.probability1 / totalChance;
        double chanceFor2 = (config.probability2 / totalChance) + chanceFor1;

        double chance = randomsource.nextDouble();

        PlacedFeature feature;

        if (chance <= chanceFor1) {
            feature = config.feature1.value();
        } else if (chance <= chanceFor2) {
            feature = config.feature2.value();
        } else {
            feature = config.feature3.value();
        }
        
        return (feature).place(worldgenlevel, chunkgenerator, randomsource, blockpos);
    }
}
