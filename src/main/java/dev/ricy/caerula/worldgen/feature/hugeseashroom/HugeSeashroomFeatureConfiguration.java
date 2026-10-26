package dev.ricy.caerula.worldgen.feature.hugeseashroom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class HugeSeashroomFeatureConfiguration implements FeatureConfiguration {
    public static final Codec<HugeSeashroomFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("top_cap_block").forGetter(config -> config.topCapProvider),
            BlockStateProvider.CODEC.fieldOf("mid_cap_block").forGetter(config -> config.midCapProvider),
            BlockStateProvider.CODEC.fieldOf("bottom_cap_block").forGetter(config -> config.bottomCapProvider),
            BlockStateProvider.CODEC.fieldOf("stem_provider").forGetter(config -> config.stemProvider),
            Codec.INT.fieldOf("top_foliage_radius").orElse(2).forGetter(config -> config.topFoliageRadius),
            Codec.INT.fieldOf("mid_foliage_radius").orElse(2).forGetter(config -> config.midFoliageRadius),
            Codec.INT.fieldOf("bottom_foliage_radius").orElse(2).forGetter(config -> config.bottomFoliageRadius)
    ).apply(instance, HugeSeashroomFeatureConfiguration::new));

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
        this.topFoliageRadius = 0;
        this.midFoliageRadius = 0;
        this.bottomFoliageRadius = bottomFoliageRadius;
        this.layers = HugeSeashroomLayers.ONE;
    }

    public enum HugeSeashroomLayers {
        THREE(11, 6),
        TWO(8, 4),
        ONE(5, 2);

        private final int maxHeight;
        private final int minHeight;

        HugeSeashroomLayers(int maxHeight, int minHeight) {
            this.maxHeight = maxHeight;
            this.minHeight = minHeight;
        }

        public int getMaxHeight() {
            return maxHeight;
        }

        public int getMinHeight() {
            return minHeight;
        }
    }
}
