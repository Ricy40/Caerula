package com.ricy40.caerula.entity.custom.seacow.behaviors;

import com.google.common.collect.ImmutableMap;
import com.ricy40.caerula.entity.ModMemoryModuleTypes;
import com.ricy40.caerula.entity.custom.seacow.Seacow;
import com.ricy40.caerula.entity.custom.seacow.SeacowAi;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.function.Predicate;


public class LocateFood <E extends Seacow> extends Behavior<E> {

    public BlockPos found_food;
    
    public LocateFood() {
        super(ImmutableMap.of(ModMemoryModuleTypes.IS_LOCATING_FOOD.get(), MemoryStatus.VALUE_PRESENT,
                ModMemoryModuleTypes.FOOD_POS.get(), MemoryStatus.REGISTERED,
                MemoryModuleType.WALK_TARGET,  MemoryStatus.VALUE_ABSENT, 
                MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, 
                MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT, 
                MemoryModuleType.IS_TEMPTED, MemoryStatus.VALUE_ABSENT,
                ModMemoryModuleTypes.LOCATE_FOOD_COOLDOWN.get(), MemoryStatus.REGISTERED), 20);
    }
    
    protected boolean canStillUse(ServerLevel pLevel, E pEntity, long pGameTime) {
        return true;
    }
    
    protected void start(ServerLevel pLevel, E seacow, long pGameTime) {
        this.found_food = null;
    }
    
    protected void tick(ServerLevel pLevel, E seacow, long pGameTime) {
        if (this.found_food == null) {
            if (seacow.isInWaterOrBubble() && !seacow.isInLava()) {
                Optional<BlockPos> food = findNearestBlock(seacow, seacow.VALID_EATDILE_BLOCKS, 7);
                if (food.isPresent()) {
                    this.found_food = food.get();
                }
            }
        }
    }

    protected void stop(ServerLevel pLevel, E seacow, long pGameTime) {
        seacow.getBrain().eraseMemory(ModMemoryModuleTypes.IS_LOCATING_FOOD.get());
        if (this.found_food != null) {
            SeacowAi.setFoodLocation(seacow, this.found_food);
        }
    }

    private Optional<BlockPos> findNearestBlock(E seacow, Predicate<BlockState> pPredicate, double pDistance) {
        
        BlockPos blockpos = seacow.blockPosition();
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int i = 0; (double)i <= pDistance; i = i > 0 ? -i : 1 - i) {
            for(int j = 0; (double)j < pDistance; ++j) {
                for(int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
                    for(int l = k < j && k > -j ? j : 0; l <= j; l = l > 0 ? -l : 1 - l) {
                        blockpos$mutableblockpos.setWithOffset(blockpos, k, i - 1, l);
                        if (blockpos.closerThan(blockpos$mutableblockpos, pDistance) && pPredicate.test(seacow.level.getBlockState(blockpos$mutableblockpos))) {
                            return Optional.of(blockpos$mutableblockpos);
                        }
                    }
                }
            }
        }

        return Optional.empty();
    }
}
