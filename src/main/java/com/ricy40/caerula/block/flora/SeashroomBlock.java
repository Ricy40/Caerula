package com.ricy40.caerula.block.flora;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SeashroomBlock extends WaterBushBlock implements BonemealableBlock {

    protected static final float AABB_OFFSET = 3.0F;
    protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    private final Supplier<Holder<? extends ConfiguredFeature<?, ?>>> featureSupplierCommon;
    private final Supplier<Holder<? extends ConfiguredFeature<?, ?>>> featureSupplierUncommon;
    private final Supplier<Holder<? extends ConfiguredFeature<?, ?>>> featureSupplierRare;

    public SeashroomBlock(BlockBehaviour.Properties properties, Supplier<Holder<? extends ConfiguredFeature<?, ?>>> feature) {
        super(properties);
        this.featureSupplierCommon = feature;
        this.featureSupplierUncommon = feature;
        this.featureSupplierRare = feature;
    }

    public SeashroomBlock(BlockBehaviour.Properties properties, Supplier<Holder<? extends ConfiguredFeature<?, ?>>> feature, Supplier<Holder<? extends ConfiguredFeature<?, ?>>> feature1, Supplier<Holder<? extends ConfiguredFeature<?, ?>>> feature2) {
        super(properties);

        this.featureSupplierCommon = feature;
        this.featureSupplierUncommon = feature1;
        this.featureSupplierRare = feature2;
    }



    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(25) == 0) {
            int i = 5;
            int j = 4;

            for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, -1, -4), pos.offset(4, 1, 4))) {
                if (level.getBlockState(blockpos).is(this)) {
                    --i;
                    if (i <= 0) {
                        return;
                    }
                }
            }

            BlockPos blockpos1 = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);

            for(int k = 0; k < 4; ++k) {
                if (level.getBlockState(pos) == Blocks.WATER.defaultBlockState() && state.canSurvive(level, blockpos1)) {
                    pos = blockpos1;
                }

                blockpos1 = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
            }

            if (level.getBlockState(pos) == Blocks.WATER.defaultBlockState() && state.canSurvive(level, blockpos1)) {
                level.setBlock(blockpos1, state, 2);
            }
        }

    }

    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.isSolidRender(pLevel, pPos);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        BlockState blockstate = pLevel.getBlockState(blockpos);
        if (blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
            return true;
        } else {
            return pLevel.getRawBrightness(pPos, 0) < 13 && blockstate.canSustainPlant(pLevel, blockpos, Direction.UP, this);
        }
    }

    public boolean growMushroom(ServerLevel server, BlockPos pos, BlockState state, RandomSource random) {
        server.removeBlock(pos, false);

        if (randType().get().value().place(server, server.getChunkSource().getGenerator(), random, pos)) {
            return true;
        } else {
            server.setBlock(pos, state, 3);
            return false;
        }
    }

    public Supplier<Holder<? extends ConfiguredFeature<?, ?>>> randType() {
        double num = RandomSource.create().nextDouble();
        if (num < 0.65d) {
            return this.featureSupplierCommon;
        } else if (num < 0.95d) {
            return this.featureSupplierUncommon;
        } else {
            return this.featureSupplierRare;
        }
    }
    
    public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return true;
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return (double)random.nextFloat() < 0.4D;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        this.growMushroom(level, pos, state, random);
    }
}
