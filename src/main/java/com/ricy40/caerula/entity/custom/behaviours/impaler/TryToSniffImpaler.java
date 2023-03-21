package com.ricy40.caerula.entity.custom.behaviours.impaler;

import com.google.common.collect.ImmutableMap;
import com.ricy40.caerula.entity.custom.impaler.Impaler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class TryToSniffImpaler extends Behavior<Impaler> {
    private static final IntProvider SNIFF_COOLDOWN = UniformInt.of(100, 200);

    public TryToSniffImpaler() {
        super(ImmutableMap.of(MemoryModuleType.SNIFF_COOLDOWN, MemoryStatus.VALUE_ABSENT, MemoryModuleType.NEAREST_ATTACKABLE, MemoryStatus.VALUE_PRESENT, MemoryModuleType.DISTURBANCE_LOCATION, MemoryStatus.VALUE_ABSENT));
    }

    protected void start(ServerLevel level, Impaler impaler, long gameTime) {
        Brain<Impaler> brain = impaler.getBrain();
        brain.setMemory(MemoryModuleType.IS_SNIFFING, Unit.INSTANCE);
        brain.setMemoryWithExpiry(MemoryModuleType.SNIFF_COOLDOWN, Unit.INSTANCE, (long)SNIFF_COOLDOWN.sample(level.getRandom()));
        brain.eraseMemory(MemoryModuleType.WALK_TARGET);
        impaler.setPose(Pose.SNIFFING);
    }
}
