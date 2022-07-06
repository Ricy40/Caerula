package com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.features;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.features.hugeseashroom.HugeYellowSeashroomFeature;
import com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.features.utilfeatures.RandomProbabilityThreeFeatureConfiguration;
import com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.features.hugeseashroom.HugePurpleSeashroomFeature;
import com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.features.hugeseashroom.HugeSeashroomFeatureConfiguration;
import com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.features.utilfeatures.RandomProbabilityThreeSelectorFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class ModFeatures<FC extends FeatureConfiguration> {
    
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Caerula.MOD_ID);

    public static final RegistryObject<Feature<RandomProbabilityThreeFeatureConfiguration>> RANDOM_PROBABILITY_THREE_SECLECTOR = registerFeature("random_probability_selector_three", () -> new RandomProbabilityThreeSelectorFeature(RandomProbabilityThreeFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> RED_SEAGRASS = registerFeature("red_seagrass", () -> new RedSeagrassFeature(ProbabilityFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<HugeSeashroomFeatureConfiguration>> HUGE_PURPLE_SEASHROOM = registerFeature("huge_purple_seashroom", () -> new HugePurpleSeashroomFeature(HugeSeashroomFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<HugeSeashroomFeatureConfiguration>> HUGE_YELLOW_SEASHROOM = registerFeature("huge_yellow_seashroom", () -> new HugeYellowSeashroomFeature(HugeSeashroomFeatureConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }

    public static <F extends FeatureConfiguration> RegistryObject<Feature<F>> registerFeature(String name, Supplier<Feature<F>> feature) {
        return FEATURES.register(name, feature);
    }

    protected void setBlock(LevelWriter pLevel, BlockPos pPos, BlockState pState) {
        pLevel.setBlock(pPos, pState, 3);
    }

    public static Predicate<BlockState> isReplaceable(TagKey<Block> pBlockTag) {
        return (pState) -> {
            return !pState.is(pBlockTag);
        };
    }

    protected void safeSetBlock(WorldGenLevel pLevel, BlockPos pPos, BlockState pState, Predicate<BlockState> pOldState) {
        if (pOldState.test(pLevel.getBlockState(pPos))) {
            pLevel.setBlock(pPos, pState, 2);
        }

    }

    public abstract boolean place(FeaturePlaceContext<FC> pContext);

    public boolean place(FC featureConfig, WorldGenLevel gen, ChunkGenerator chunkGen, RandomSource random, BlockPos pos) {
        return gen.ensureCanWrite(pos) && this.place(new FeaturePlaceContext<>(Optional.empty(), gen, chunkGen, random, pos, featureConfig));
    }
    protected static boolean isStone(BlockState pState) {
        return pState.is(net.minecraftforge.common.Tags.Blocks.STONE);
    }

    public static boolean isDirt(BlockState pState) {
        return pState.is(BlockTags.DIRT);
    }

    public static boolean isGrassOrDirt(LevelSimulatedReader pLevel, BlockPos pPos) {
        return pLevel.isStateAtPosition(pPos, Feature::isDirt);
    }

    public static boolean checkNeighbors(Function<BlockPos, BlockState> pAdjacentStateAccessor, BlockPos pPos, Predicate<BlockState> pFilter) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(pPos, direction);
            if (pFilter.test(pAdjacentStateAccessor.apply(blockpos$mutableblockpos))) {
                return true;
            }
        }

        return false;
    }
    
    public static boolean isAdjacentToAir(Function<BlockPos, BlockState> pAdjacentStateAccessor, BlockPos pPos) {
        return checkNeighbors(pAdjacentStateAccessor, pPos, BlockBehaviour.BlockStateBase::isAir);
    }

    protected void markAboveForPostProcessing(WorldGenLevel pLevel, BlockPos pBasePos) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pBasePos.mutable();

        for(int i = 0; i < 2; ++i) {
            blockpos$mutableblockpos.move(Direction.UP);
            if (pLevel.getBlockState(blockpos$mutableblockpos).isAir()) {
                return;
            }

            pLevel.getChunk(blockpos$mutableblockpos).markPosForPostprocessing(blockpos$mutableblockpos);
        }

    }
}
