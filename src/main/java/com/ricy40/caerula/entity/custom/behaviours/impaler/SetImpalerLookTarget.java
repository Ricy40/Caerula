package com.ricy40.caerula.entity.custom.behaviours.impaler;

import com.google.common.collect.ImmutableMap;
import com.ricy40.caerula.entity.custom.impaler.Impaler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BlockPosTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.monster.warden.Warden;

public class SetImpalerLookTarget extends Behavior<Impaler> {
    public SetImpalerLookTarget() {
        super(ImmutableMap.of(MemoryModuleType.DISTURBANCE_LOCATION, MemoryStatus.REGISTERED, MemoryModuleType.ROAR_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_ABSENT));
    }

    protected boolean checkExtraStartConditions(ServerLevel level, Impaler impaler) {
        return impaler.getBrain().hasMemoryValue(MemoryModuleType.DISTURBANCE_LOCATION) || impaler.getBrain().hasMemoryValue(MemoryModuleType.ROAR_TARGET);
    }

    protected void start(ServerLevel level, Impaler impaler, long p_217641_) {
        BlockPos blockpos = impaler.getBrain().getMemory(MemoryModuleType.ROAR_TARGET).map(Entity::blockPosition).or(() -> {
            return impaler.getBrain().getMemory(MemoryModuleType.DISTURBANCE_LOCATION);
        }).get();
        impaler.getBrain().setMemory(MemoryModuleType.LOOK_TARGET, new BlockPosTracker(blockpos));
    }
}
