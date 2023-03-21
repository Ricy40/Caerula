package com.ricy40.caerula.entity.custom.behaviours.impaler;

import com.google.common.collect.ImmutableMap;
import com.ricy40.caerula.entity.custom.impaler.Impaler;
import com.ricy40.caerula.entity.custom.impaler.ImpalerAi;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.monster.warden.WardenAi;

public class SniffingImpaler <E extends Impaler> extends Behavior<E> {
    private static final double ANGER_FROM_SNIFFING_MAX_DISTANCE_XZ = 6.0D;
    private static final double ANGER_FROM_SNIFFING_MAX_DISTANCE_Y = 6.0D;

    public SniffingImpaler(int pDuration) {
        super(ImmutableMap.of(
                MemoryModuleType.IS_SNIFFING, MemoryStatus.VALUE_PRESENT,
                MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_ABSENT,
                MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT,
                MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED,
                MemoryModuleType.NEAREST_ATTACKABLE, MemoryStatus.REGISTERED,
                MemoryModuleType.DISTURBANCE_LOCATION, MemoryStatus.REGISTERED,
                MemoryModuleType.SNIFF_COOLDOWN, MemoryStatus.REGISTERED
        ), pDuration);
    }

    protected boolean canStillUse(ServerLevel pLevel, E impaler, long pGameTime) {
        return true;
    }

    protected void start(ServerLevel pLevel, E impaler, long pGameTime) {
        impaler.playSound(SoundEvents.WARDEN_SNIFF, 5.0F, 1.0F);
    }

    protected void stop(ServerLevel pLevel, E impaler, long pGameTime) {
        if (impaler.hasPose(Pose.SNIFFING)) {
            impaler.setPose(Pose.STANDING);
        }

        impaler.getBrain().eraseMemory(MemoryModuleType.IS_SNIFFING);
        impaler.getBrain().getMemory(MemoryModuleType.NEAREST_ATTACKABLE).filter(impaler::canTargetEntity).ifPresent((target) -> {
            if (impaler.closerThan(target, ANGER_FROM_SNIFFING_MAX_DISTANCE_XZ, ANGER_FROM_SNIFFING_MAX_DISTANCE_Y)) {
                impaler.increaseAngerAt(target);
            }

            if (!impaler.getBrain().hasMemoryValue(MemoryModuleType.DISTURBANCE_LOCATION)) {
                ImpalerAi.setDisturbanceLocation(impaler, target.blockPosition());
            }

        });
    }
}
