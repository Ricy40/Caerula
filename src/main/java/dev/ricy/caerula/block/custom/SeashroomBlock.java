package dev.ricy.caerula.block.custom;

import dev.ricy.caerula.tag.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SeashroomBlock extends WaterBushBlock implements BonemealableBlock {

    protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    private final ResourceKey<ConfiguredFeature<?, ?>> commonFeature;
    private final ResourceKey<ConfiguredFeature<?, ?>> uncommonFeature;
    private final ResourceKey<ConfiguredFeature<?, ?>> rareFeature;

    public SeashroomBlock(BlockBehaviour.Properties properties, ResourceKey<ConfiguredFeature<?, ?>> commonFeature, ResourceKey<ConfiguredFeature<?, ?>> uncommonFeature, ResourceKey<ConfiguredFeature<?, ?>> rareFeature) {
        super(properties);
        this.commonFeature = commonFeature;
        this.uncommonFeature = uncommonFeature;
        this.rareFeature = rareFeature;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(25) == 0) {
            int i = 5;

            for (BlockPos nearbyPos : BlockPos.betweenClosed(pos.offset(-4, -1, -4), pos.offset(4, 1, 4))) {
                if (level.getBlockState(nearbyPos).is(this)) {
                    --i;
                    if (i <= 0) {
                        return;
                    }
                }
            }

            BlockPos targetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);

            for (int k = 0; k < 4; ++k) {
                if (level.getBlockState(pos) == Blocks.WATER.defaultBlockState() && state.canSurvive(level, targetPos)) {
                    pos = targetPos;
                }

                targetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
            }

            if (level.getBlockState(pos) == Blocks.WATER.defaultBlockState() && state.canSurvive(level, targetPos)) {
                level.setBlock(targetPos, state, 2);
            }
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.isSolidRender();
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);
        if (belowState.is(ModTags.Blocks.MUSHROOM_GROW_BLOCK)) {
            return true;
        }
        return level.getRawBrightness(pos, 0) < 13 && this.mayPlaceOn(belowState, level, belowPos);
    }

    public boolean growMushroom(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        level.removeBlock(pos, false);

        if (placeRandomFeature(level, pos, random)) {
            return true;
        }
        level.setBlock(pos, state, 3);
        return false;
    }

    private boolean placeRandomFeature(ServerLevel level, BlockPos pos, RandomSource random) {
        ResourceKey<ConfiguredFeature<?, ?>> featureKey = randomFeatureKey(random);
        return level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(featureKey).value()
                .place(level, level.getChunkSource().getGenerator(), random, pos);
    }

    private ResourceKey<ConfiguredFeature<?, ?>> randomFeatureKey(RandomSource random) {
        double roll = random.nextDouble();
        if (roll < 0.65d) {
            return this.commonFeature;
        } else if (roll < 0.95d) {
            return this.uncommonFeature;
        } else {
            return this.rareFeature;
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return random.nextFloat() < 0.4F;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        this.growMushroom(level, pos, state, random);
    }
}
