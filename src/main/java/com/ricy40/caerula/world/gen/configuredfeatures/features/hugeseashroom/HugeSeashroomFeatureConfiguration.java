package com.ricy40.caerula.world.gen.configuredfeatures.features.hugeseashroom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import javax.annotation.Nullable;

public class HugeSeashroomFeatureConfiguration  implements FeatureConfiguration {
    public static final Codec<HugeSeashroomFeatureConfiguration> CODEC = RecordCodecBuilder.create((seashroom) -> {
        return seashroom.group(BlockStateProvider.CODEC.fieldOf("top_cap_block").forGetter((cap) -> {
            return cap.topCapProvider;
        }), BlockStateProvider.CODEC.fieldOf("mid_cap_block").forGetter((stem) -> {
            return stem.midCapProvider;
        }), BlockStateProvider.CODEC.fieldOf("bottom_cap_block").forGetter((stem) -> {
            return stem.bottomCapProvider;
        }), BlockStateProvider.CODEC.fieldOf("stem_provider").forGetter((stem) -> {
            return stem.stemProvider;
        }), Codec.INT.fieldOf("top_foliage_radius").orElse(2).forGetter((foliage) -> {
            return foliage.topFoliageRadius;
        }), Codec.INT.fieldOf("mid_foliage_radius").orElse(2).forGetter((foliage) -> {
            return foliage.midFoliageRadius;
        }), Codec.INT.fieldOf("bottom_foliage_radius").orElse(2).forGetter((foliage) -> {
            return foliage.bottomFoliageRadius;
        })).apply(seashroom, HugeSeashroomFeatureConfiguration::new);
    });

    public final BlockStateProvider topCapProvider;
    public final BlockStateProvider midCapProvider;
    public final BlockStateProvider bottomCapProvider;
    public final BlockStateProvider stemProvider;
    public final int topFoliageRadius;
    public final int midFoliageRadius;
    public final int bottomFoliageRadius;
    public final HugeSeashroomLayers layers;

    public HugeSeashroomFeatureConfiguration(BlockStateProvider topCapBlock, BlockStateProvider midCapBlock, BlockStateProvider bottomCapBlock, BlockStateProvider stemBlock, int topFoliageRadius, int midFoliageRadius, int bottomFoliageRadius) {
        this.topCapProvider = topCapBlock;
        this.midCapProvider = midCapBlock;
        this.bottomCapProvider = bottomCapBlock;
        this.stemProvider = stemBlock;
        this.topFoliageRadius = topFoliageRadius;
        this.midFoliageRadius = midFoliageRadius;
        this.bottomFoliageRadius = bottomFoliageRadius;
        this.layers = HugeSeashroomLayers.THREE;
    }

    public HugeSeashroomFeatureConfiguration(BlockStateProvider midCapBlock, BlockStateProvider bottomCapBlock, BlockStateProvider stemBlock, int midFoliageRadius, int bottomFoliageRadius) {
        this.topCapProvider = midCapBlock;
        this.midCapProvider = midCapBlock;
        this.bottomCapProvider = bottomCapBlock;
        this.stemProvider = stemBlock;
        this.topFoliageRadius = 0;
        this.midFoliageRadius = midFoliageRadius;
        this.bottomFoliageRadius = bottomFoliageRadius;
        this.layers = HugeSeashroomLayers.TWO;
    }

    public HugeSeashroomFeatureConfiguration(BlockStateProvider bottomCapBlock, BlockStateProvider stemBlock, int bottomFoliageRadius) {
        this.topCapProvider = bottomCapBlock;
        this.midCapProvider = bottomCapBlock;
        this.bottomCapProvider = bottomCapBlock;
        this.stemProvider = stemBlock;
        this.topFoliageRadius = bottomFoliageRadius;
        this.midFoliageRadius = bottomFoliageRadius;
        this.bottomFoliageRadius = bottomFoliageRadius;
        this.layers = HugeSeashroomLayers.THREE;
    }

    public enum HugeSeashroomLayers {

        THREE(11, 6),
        TWO(8, 4),
        ONE(5, 2);

        HugeSeashroomLayers(int maxHeight, int minHeight) {
            this.maxHeight = maxHeight;
            this.minHeight = minHeight;
        }

        private int maxHeight;
        private int minHeight;

        public int getMaxHeight() {
            return maxHeight;
        }
        public int getMinHeight() {
            return minHeight;
        }
    }
}