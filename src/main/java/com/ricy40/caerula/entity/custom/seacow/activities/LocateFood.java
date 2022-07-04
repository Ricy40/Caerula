package com.ricy40.caerula.entity.custom.seacow.activities;

import com.google.common.collect.ImmutableMap;
import com.ricy40.caerula.entity.ModMemoryModuleTypes;
import com.ricy40.caerula.entity.custom.seacow.Seacow;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.function.Predicate;


public class LocateFood <E extends Seacow> extends Behavior<E> {

    private static final int HUNGER_COOLDOWN = 4000;

    public LocateFood() {
        super(ImmutableMap.of(ModMemoryModuleTypes.FOOD_POS.get(), MemoryStatus.VALUE_ABSENT, MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT, MemoryModuleType.IS_TEMPTED, MemoryStatus.VALUE_ABSENT, MemoryModuleType.BREED_TARGET, MemoryStatus.VALUE_ABSENT));
    }

    protected boolean checkExtraStartConditions(ServerLevel pLevel, E seacow) {
        return seacow.getPose() == Pose.STANDING || seacow.getPose() == Pose.SWIMMING;
    }

    @Override
    protected void start(ServerLevel pLevel, E seacow, long pGameTime) {
        if (seacow.isInWaterOrBubble() && !seacow.isInLava()) {
            Optional<BlockPos> food = findNearestBlock(seacow, seacow.VALID_EATDILE_BLOCKS, 7);
            if (food.isPresent()) {
                System.out.println("Found food!");
                seacow.getBrain().setMemory(ModMemoryModuleTypes.FOOD_POS.get(), food);
            }
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
