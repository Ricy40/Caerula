package com.ricy40.caerula.entity.custom.seacow.behaviors;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class GoToFoodLocation<E extends Mob> extends Behavior<E> {
    private final MemoryModuleType<BlockPos> locationMemory;
    private final int closeEnoughDist;
    private final float speedModifier;

    public GoToFoodLocation(MemoryModuleType<BlockPos> pos, int closeEnough, float speedModifier) {
        super(ImmutableMap.of(pos, MemoryStatus.VALUE_PRESENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED));
        this.locationMemory = pos;
        this.closeEnoughDist = closeEnough;
        this.speedModifier = speedModifier;
    }

    protected void start(ServerLevel level, Mob mob, long gameTime) {
        BlockPos blockpos = this.getTargetLocation(mob);
        boolean flag = blockpos.closerThan(mob.blockPosition(), (double) this.closeEnoughDist);
        if (!flag) {
            BehaviorUtils.setWalkAndLookTargetMemories(mob, getNearbyPos(mob, blockpos), this.speedModifier, this.closeEnoughDist);
        }

    }

    private static BlockPos getNearbyPos(Mob mob, BlockPos pos) {
        RandomSource randomsource = mob.level.random;
        return pos.offset(getRandomOffset(randomsource), 0, getRandomOffset(randomsource));
    }

    private static int getRandomOffset(RandomSource random) {
        return random.nextInt(3) - 1;
    }

    private BlockPos getTargetLocation(Mob p_217249_) {
        return p_217249_.getBrain().getMemory(this.locationMemory).get();
    }
}
