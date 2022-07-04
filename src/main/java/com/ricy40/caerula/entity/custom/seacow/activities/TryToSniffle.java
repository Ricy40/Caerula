package com.ricy40.caerula.entity.custom.seacow.activities;

import com.google.common.collect.ImmutableMap;
import com.ricy40.caerula.entity.ModMemoryModuleTypes;
import com.ricy40.caerula.entity.custom.seacow.Seacow;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class TryToSniffle extends Behavior<Seacow> {
    
    private static final IntProvider SNIFFLE_COOLDOWN = UniformInt.of(300, 400);

    public TryToSniffle() {
        super(ImmutableMap.of(ModMemoryModuleTypes.SNIFFLING_COOLDOWN.get(), MemoryStatus.VALUE_ABSENT));
    }

    protected void start(ServerLevel level, Seacow seacow, long duration) {
        Brain<Seacow> brain = seacow.getBrain();
        brain.setMemory(ModMemoryModuleTypes.IS_SNIFFLING.get(), Unit.INSTANCE);
        brain.setMemoryWithExpiry(ModMemoryModuleTypes.SNIFFLING_COOLDOWN.get(), Unit.INSTANCE, (long)SNIFFLE_COOLDOWN.sample(level.getRandom()));
        seacow.setPose(Pose.SNIFFING);
    }
}