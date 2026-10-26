package dev.ricy.caerula.worldgen;

import dev.ricy.caerula.Caerula;
import dev.ricy.caerula.block.ModBlocks;
import dev.ricy.caerula.worldgen.feature.ModFeatures;
import dev.ricy.caerula.worldgen.feature.hugeseashroom.HugeSeashroomFeatureConfiguration;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NIXIUM_KEY = registerKey("ore_nixium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NIXIUM_SMALL_KEY = registerKey("ore_nixium_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SEAGRASS_SIMPLE_KEY = registerKey("red_seagrass_simple");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_PURPLE_SEASHROOM_ONECAP_KEY = registerKey("huge_purple_seashroom_onecap");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_PURPLE_SEASHROOM_TWOCAP_KEY = registerKey("huge_purple_seashroom_twocap");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_PURPLE_SEASHROOM_THREECAP_KEY = registerKey("huge_purple_seashroom_threecap");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_YELLOW_SEASHROOM_ONECAP_KEY = registerKey("huge_yellow_seashroom_onecap");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_YELLOW_SEASHROOM_TWOCAP_KEY = registerKey("huge_yellow_seashroom_twocap");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_YELLOW_SEASHROOM_THREECAP_KEY = registerKey("huge_yellow_seashroom_threecap");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        List<OreConfiguration.TargetBlockState> nixiumOres = List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.NIXIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.DEEPSLATE_NIXIUM_ORE.get().defaultBlockState()));

        register(context, ORE_NIXIUM_KEY, Feature.ORE, new OreConfiguration(nixiumOres, 9));
        register(context, ORE_NIXIUM_SMALL_KEY, Feature.ORE, new OreConfiguration(nixiumOres, 4));
        register(context, RED_SEAGRASS_SIMPLE_KEY, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.RED_SEAGRASS.get())));

        register(context, HUGE_PURPLE_SEASHROOM_ONECAP_KEY, ModFeatures.HUGE_PURPLE_SEASHROOM.get(),
                new HugeSeashroomFeatureConfiguration(capProvider(ModBlocks.PURPLE_SEASHROOM_BLOCK.get()), stemProvider(), 2));
        register(context, HUGE_PURPLE_SEASHROOM_TWOCAP_KEY, ModFeatures.HUGE_PURPLE_SEASHROOM.get(),
                new HugeSeashroomFeatureConfiguration(capProvider(ModBlocks.PURPLE_SEASHROOM_BLOCK.get()), capProvider(ModBlocks.PURPLE_SEASHROOM_BLOCK.get()), stemProvider(), 2, 3));
        register(context, HUGE_PURPLE_SEASHROOM_THREECAP_KEY, ModFeatures.HUGE_PURPLE_SEASHROOM.get(),
                new HugeSeashroomFeatureConfiguration(capProvider(ModBlocks.PURPLE_SEASHROOM_BLOCK.get()), capProvider(ModBlocks.PURPLE_SEASHROOM_BLOCK.get()), capProvider(ModBlocks.PURPLE_SEASHROOM_BLOCK.get()), stemProvider(), 2, 3, 4));

        register(context, HUGE_YELLOW_SEASHROOM_ONECAP_KEY, ModFeatures.HUGE_YELLOW_SEASHROOM.get(),
                new HugeSeashroomFeatureConfiguration(capProvider(ModBlocks.YELLOW_SEASHROOM_BLOCK.get()), stemProvider(), 2));
        register(context, HUGE_YELLOW_SEASHROOM_TWOCAP_KEY, ModFeatures.HUGE_YELLOW_SEASHROOM.get(),
                new HugeSeashroomFeatureConfiguration(capProvider(ModBlocks.YELLOW_SEASHROOM_BLOCK.get()), capProvider(ModBlocks.ORANGE_SEASHROOM_BLOCK.get()), stemProvider(), 2, 3));
        register(context, HUGE_YELLOW_SEASHROOM_THREECAP_KEY, ModFeatures.HUGE_YELLOW_SEASHROOM.get(),
                new HugeSeashroomFeatureConfiguration(capProvider(ModBlocks.YELLOW_SEASHROOM_BLOCK.get()), capProvider(ModBlocks.ORANGE_SEASHROOM_BLOCK.get()), capProvider(ModBlocks.BROWN_SEASHROOM_BLOCK.get()), stemProvider(), 2, 3, 4));
    }

    private static BlockStateProvider capProvider(Block block) {
        return BlockStateProvider.simple(block.defaultBlockState().setValue(HugeMushroomBlock.UP, true).setValue(HugeMushroomBlock.DOWN, false));
    }

    private static BlockStateProvider stemProvider() {
        return BlockStateProvider.simple(ModBlocks.SEASHROOM_STEM.get().defaultBlockState().setValue(HugeMushroomBlock.UP, false).setValue(HugeMushroomBlock.DOWN, false));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Caerula.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
