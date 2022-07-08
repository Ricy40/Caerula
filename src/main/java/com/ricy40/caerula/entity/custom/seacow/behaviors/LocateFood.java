package com.ricy40.caerula.entity.custom.seacow.behaviors;

import com.google.common.collect.ImmutableMap;
import com.ricy40.caerula.entity.ModMemoryModuleTypes;
import com.ricy40.caerula.entity.custom.seacow.Seacow;
import com.ricy40.caerula.entity.custom.seacow.SeacowAi;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.function.Predicate;

public class LocateFood <E extends Seacow> extends Behavior<E> {

    public BlockPos found_food;
    
    public LocateFood() {
        super(ImmutableMap.of(
                ModMemoryModuleTypes.FOOD_POS.get(), MemoryStatus.VALUE_ABSENT, 
                ModMemoryModuleTypes.IS_LOCATING_FOOD.get(), MemoryStatus.VALUE_PRESENT
        ));
    }

    protected void start(ServerLevel level, Seacow seacow, long gameTime) {
        System.out.println("Is Locating Food");
        this.found_food = null;
    }

    @Override
    protected boolean canStillUse(ServerLevel pLevel, E seacow, long pGameTime) {
        if (seacow.isHungry()) {
            System.out.println("hungry");
        }
        return seacow.isHungry();
    }

    protected void tick(ServerLevel pLevel, E seacow, long pGameTime) {
        if (this.found_food == null) {
            if (seacow.isInWaterOrBubble() && !seacow.isInLava()) {
                Optional<BlockPos> food = findNearestBlock(seacow, seacow.VALID_EATDILE_BLOCKS, 7);
                if (food.isPresent()) {
                    System.out.println("Found food");
                    this.found_food = food.get();
                }
            }
        }
    }

    protected void stop(ServerLevel pLevel, E seacow, long pGameTime) {
        seacow.getBrain().eraseMemory(ModMemoryModuleTypes.IS_LOCATING_FOOD.get());
        seacow.getBrain().eraseMemory(MemoryModuleType.LOOK_TARGET);
        seacow.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
        if (this.found_food != null) {
            SeacowAi.setFoodLocation(seacow, this.found_food);
        }
    }

    private Optional<BlockPos> findNearestBlock(E seacow, Predicate<BlockState> pPredicate, double pDistance) {

        BlockPos blockpos = seacow.blockPosition();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for(int i = 0; (double)i <= pDistance; i = i > 0 ? -i : 1 - i) {
            for(int j = 0; (double)j < pDistance; ++j) {
                for(int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
                    for(int l = k < j && k > -j ? j : 0; l <= j; l = l > 0 ? -l : 1 - l) {
                        pos.setWithOffset(blockpos, k, i - 1, l);
                        if (blockpos.closerThan(pos, pDistance) && pPredicate.test(seacow.level.getBlockState(pos))) {
                            return Optional.of(pos);
                        }
                    }
                }
            }
        }

        return Optional.empty();
    }
}
