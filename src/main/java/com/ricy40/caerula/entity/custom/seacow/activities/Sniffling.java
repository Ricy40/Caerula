package com.ricy40.caerula.entity.custom.seacow.activities;

import com.google.common.collect.ImmutableMap;
import com.ricy40.caerula.entity.custom.seacow.Seacow;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

import java.util.Random;

public class Sniffling <E extends Seacow> extends Behavior<E> {
    
    private static final int SNIFFLE_TICKS = 40;
    private static final int TIME_OUT_DURATION = 500;
    private int sniffleCounter;

    public Sniffling() {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), TIME_OUT_DURATION);
    }

    protected boolean canStillUse(ServerLevel worldIn, E seacow, long duartion) {
        return this.sniffleCounter < SNIFFLE_TICKS;
    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel pLevel, E seacow) {
        return seacow.getPose() == Pose.STANDING;
    }

    protected void start(ServerLevel worldIn, E seacow, long duration) {
        if (seacow.isInWaterOrBubble() && !seacow.isInLava()) {
            seacow.setPose(Pose.SNIFFING);
            seacow.playSound(SoundEvents.WARDEN_SNIFF, 3.0F, 1.0F);
            this.sniffleCounter = 0;
        }
    }

    protected void stop(ServerLevel worldIn, E seacow, long duration) {
        seacow.setPose(Pose.STANDING);
    }

    protected void tick(ServerLevel pLevel, E seacow, long pGameTime) {
        this.sniffleCounter++;
    }
}