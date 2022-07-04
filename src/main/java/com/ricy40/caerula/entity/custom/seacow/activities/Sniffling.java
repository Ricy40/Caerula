package com.ricy40.caerula.entity.custom.seacow.activities;

import com.google.common.collect.ImmutableMap;
import com.ricy40.caerula.entity.ModMemoryModuleTypes;
import com.ricy40.caerula.entity.custom.seacow.Seacow;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class Sniffling <E extends Seacow> extends Behavior<E> {

    public Sniffling(int duration) {
        super(ImmutableMap.of(
                ModMemoryModuleTypes.IS_SNIFFLING.get(), MemoryStatus.VALUE_PRESENT,
                ModMemoryModuleTypes.SNIFFLING_COOLDOWN.get(), MemoryStatus.REGISTERED), duration);
    }

    protected boolean canStillUse(ServerLevel worldIn, E seacow, long duartion) {
        return true;
    }

    protected void start(ServerLevel worldIn, E seacow, long duration) {
        seacow.playSound(SoundEvents.WARDEN_SNIFF, 3.0F, 1.0F);
    }

    protected void stop(ServerLevel worldIn, E seacow, long duration) {
        if (seacow.hasPose(Pose.SNIFFING)) {
            seacow.setPose(Pose.STANDING);
        }

        seacow.getBrain().eraseMemory(ModMemoryModuleTypes.IS_SNIFFLING.get());
    }
}