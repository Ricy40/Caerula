package com.ricy40.caerula.entity.custom.seacow.activities;

import com.google.common.collect.ImmutableMap;
import com.ricy40.caerula.entity.ModMemoryModuleTypes;
import com.ricy40.caerula.entity.custom.seacow.Seacow;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BlockPosTracker;
import net.minecraft.world.entity.ai.behavior.PositionTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class Eating <E extends Seacow> extends Behavior<E> {

    private BlockPos foodLocation;
    private int eatingTicks;

    public Eating() {
        super(ImmutableMap.of(ModMemoryModuleTypes.FOOD_POS.get(), MemoryStatus.VALUE_PRESENT));
        this.eatingTicks = 0;
    }

    protected void start(ServerLevel pLevel, E seacow, long pGameTime) {
        this.foodLocation = seacow.getBrain().getMemory(ModMemoryModuleTypes.FOOD_POS.get()).get();

        WalkTarget target = new WalkTarget(this.foodLocation, 1.5f, 8);
        seacow.getBrain().setMemory(MemoryModuleType.WALK_TARGET, target);
        seacow.getBrain().setMemory(MemoryModuleType.LOOK_TARGET, new BlockPosTracker(this.foodLocation));
    }

    protected boolean canStillUse(ServerLevel pLevel, E pEntity, long pGameTime) {
        return this.eatingTicks < 40f;
    }

    protected void tick(ServerLevel pLevel, E seacow, long pGameTime) {
        super.tick(pLevel, seacow, pGameTime);

        if (seacow.position().equals(new Vec3(this.foodLocation.getX(), this.foodLocation.getY(), this.foodLocation.getZ()))) {
            seacow.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
            seacow.getBrain().setMemory(MemoryModuleType.LOOK_TARGET, new BlockPosTracker(this.foodLocation));
            seacow.setPose(Pose.USING_TONGUE);
            if (this.eatingTicks == 39) {
                System.out.println("Destroying block!");
                pLevel.destroyBlock(this.foodLocation, true, seacow);
            }
            this.eatingTicks++;
        }
    }

    protected void stop(ServerLevel pLevel, E seacow, long pGameTime) {
        seacow.getBrain().eraseMemory(ModMemoryModuleTypes.FOOD_POS.get());
        seacow.getBrain().eraseMemory(MemoryModuleType.LOOK_TARGET);
        seacow.setPose(Pose.STANDING);
    }
}
