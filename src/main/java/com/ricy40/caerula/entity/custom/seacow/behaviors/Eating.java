package com.ricy40.caerula.entity.custom.seacow.behaviors;

import com.google.common.collect.ImmutableMap;
import com.ricy40.caerula.entity.ModMemoryModuleTypes;
import com.ricy40.caerula.entity.custom.seacow.Seacow;
import com.ricy40.caerula.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.behavior.BlockPosTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.phys.Vec3;


public class Eating <E extends Seacow> extends Behavior<E> {

    private BlockPos foodLocation;
    private int eatingTicks;

    public Eating() {
        super(ImmutableMap.of(ModMemoryModuleTypes.FOOD_POS.get(), MemoryStatus.VALUE_PRESENT), 120);
    }

    protected void start(ServerLevel pLevel, E seacow, long pGameTime) {
        seacow.playSound(SoundEvents.GENERIC_EAT, 1, 1);
        this.eatingTicks = 0;
        this.foodLocation = seacow.getBrain().getMemory(ModMemoryModuleTypes.FOOD_POS.get()).get();
        seacow.getBrain().setMemory(MemoryModuleType.LOOK_TARGET, new BlockPosTracker(this.foodLocation));
    }

    protected boolean canStillUse(ServerLevel pLevel, E pEntity, long pGameTime) {
        if (!pLevel.getBlockState(this.foodLocation).is(ModTags.Blocks.SEACOW_EDIBLES)) {
            return false;
        }
        return this.eatingTicks < 40f;
    }

    protected void tick(ServerLevel pLevel, E seacow, long pGameTime) {
        BehaviorUtils.setWalkAndLookTargetMemories(seacow, getNearbyPos(seacow, this.foodLocation), 1.2F, 1);

        if (seacow.position().distanceTo(new Vec3(this.foodLocation.getX(), this.foodLocation.getY(), this.foodLocation.getZ())) < 2) {
            seacow.setPose(Pose.USING_TONGUE);
            if (this.eatingTicks == 39) {
                pLevel.destroyBlock(this.foodLocation, false, seacow);
                seacow.eat();
            }
            this.eatingTicks++;
        } else {
            seacow.setPose(Pose.STANDING);
        }
    }

    private static BlockPos getNearbyPos(Mob mob, BlockPos pos) {
        RandomSource randomsource = mob.level.random;
        return pos.offset(getRandomOffset(randomsource), 0, getRandomOffset(randomsource));
    }

    private static int getRandomOffset(RandomSource random) {
        return random.nextInt(3) - 1;
    }


    protected void stop(ServerLevel pLevel, E seacow, long pGameTime) {
        seacow.getBrain().eraseMemory(ModMemoryModuleTypes.FOOD_POS.get());
        seacow.getBrain().eraseMemory(MemoryModuleType.LOOK_TARGET);
        seacow.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
        seacow.setPose(Pose.STANDING);
    }
}
