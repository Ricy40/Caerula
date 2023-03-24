package com.ricy40.caerula.entity.custom.behaviours;

import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.behavior.EntityTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;

import java.util.function.Function;

public class SetSwimTargetFromAttackTargetIfTargetOutOfReach extends Behavior<Mob> {
    private static final int PROJECTILE_ATTACK_RANGE_BUFFER = 1;
    private final Function<LivingEntity, Float> speedModifier;
    private final int closeEnough;

    public SetSwimTargetFromAttackTargetIfTargetOutOfReach(float pSpeedModifier, int closeEnough) {
        this((target) -> {
            return pSpeedModifier;
        }, closeEnough);
    }

    public SetSwimTargetFromAttackTargetIfTargetOutOfReach(Function<LivingEntity, Float> pSpeedModifier, int closeEnough) {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryStatus.REGISTERED));
        this.speedModifier = pSpeedModifier;
        this.closeEnough = closeEnough;
    }

    protected void start(ServerLevel pLevel, Mob pEntity, long pGameTime) {
        LivingEntity livingentity = pEntity.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).get();
        if (BehaviorUtils.canSee(pEntity, livingentity) && BehaviorUtils.isWithinAttackRange(pEntity, livingentity, 1)) {
            this.clearWalkTarget(pEntity);
        } else {
            this.setWalkAndLookTarget(pEntity, livingentity);
        }

    }

    private void setWalkAndLookTarget(LivingEntity pEntity, LivingEntity pTarget) {
        Brain<?> brain = pEntity.getBrain();
        brain.setMemory(MemoryModuleType.LOOK_TARGET, new EntityTracker(pTarget, true));
        WalkTarget walktarget = new WalkTarget(new EntityTracker(pTarget, false), this.speedModifier.apply(pEntity), this.closeEnough);
        brain.setMemory(MemoryModuleType.WALK_TARGET, walktarget);
    }

    private void clearWalkTarget(LivingEntity entity) {
        entity.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
    }
}