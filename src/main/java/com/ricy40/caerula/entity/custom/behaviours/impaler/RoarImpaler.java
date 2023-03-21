package com.ricy40.caerula.entity.custom.behaviours.impaler;

import com.google.common.collect.ImmutableMap;
import com.ricy40.caerula.entity.custom.impaler.Impaler;
import com.ricy40.caerula.entity.custom.impaler.ImpalerAi;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.monster.warden.WardenAi;

public class RoarImpaler extends Behavior<Impaler> {
    private static final int TICKS_BEFORE_PLAYING_ROAR_SOUND = 25;
    private static final int ROAR_ANGER_INCREASE = 20;

    public RoarImpaler() {
        super(ImmutableMap.of(
                MemoryModuleType.ROAR_TARGET, MemoryStatus.VALUE_PRESENT,
                MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_ABSENT,
                MemoryModuleType.ROAR_SOUND_COOLDOWN, MemoryStatus.REGISTERED,
                MemoryModuleType.ROAR_SOUND_DELAY, MemoryStatus.REGISTERED
        ), ImpalerAi.ROAR_DURATION);
    }

    protected void start(ServerLevel level, Impaler impaler, long gameTime) {
        Brain<Impaler> brain = impaler.getBrain();
        brain.setMemoryWithExpiry(MemoryModuleType.ROAR_SOUND_DELAY, Unit.INSTANCE, 25L);
        brain.eraseMemory(MemoryModuleType.WALK_TARGET);
        LivingEntity livingentity = impaler.getBrain().getMemory(MemoryModuleType.ROAR_TARGET).get();
        BehaviorUtils.lookAtEntity(impaler, livingentity);
        impaler.setPose(Pose.ROARING);
        impaler.increaseAngerAt(livingentity, ROAR_ANGER_INCREASE, false);
    }

    protected boolean canStillUse(ServerLevel level, Impaler impaler, long gameTime) {
        return true;
    }

    protected void tick(ServerLevel level, Impaler impaler, long gameTime) {
        if (!impaler.getBrain().hasMemoryValue(MemoryModuleType.ROAR_SOUND_DELAY) && !impaler.getBrain().hasMemoryValue(MemoryModuleType.ROAR_SOUND_COOLDOWN)) {
            impaler.getBrain().setMemoryWithExpiry(MemoryModuleType.ROAR_SOUND_COOLDOWN, Unit.INSTANCE, (long)(WardenAi.ROAR_DURATION - TICKS_BEFORE_PLAYING_ROAR_SOUND));
            impaler.playSound(SoundEvents.WARDEN_ROAR, 3.0F, 1.0F);
        }
    }

    protected void stop(ServerLevel level, Impaler impaler, long gameTime) {
        if (impaler.hasPose(Pose.ROARING)) {
            impaler.setPose(Pose.STANDING);
        }

        impaler.getBrain().getMemory(MemoryModuleType.ROAR_TARGET).ifPresent(impaler::setAttackTarget);
        impaler.getBrain().eraseMemory(MemoryModuleType.ROAR_TARGET);
    }
}
