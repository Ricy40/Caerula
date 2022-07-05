package com.ricy40.caerula.entity.custom.seacow.behaviors;

import com.google.common.collect.ImmutableMap;
import com.ricy40.caerula.entity.ModMemoryModuleTypes;
import com.ricy40.caerula.entity.custom.seacow.Seacow;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class TryToLocateFood extends Behavior<Seacow> {
    private static final IntProvider LOCATE_FOOD_COOLDOWN = UniformInt.of(200, 300);

    public TryToLocateFood() {
        super(ImmutableMap.of(ModMemoryModuleTypes.FOOD_POS.get(), MemoryStatus.VALUE_ABSENT, ModMemoryModuleTypes.LOCATE_FOOD_COOLDOWN.get(), MemoryStatus.VALUE_ABSENT));
    }

    protected void start(ServerLevel level, Seacow seacow, long gameTime) {
        if (seacow.isHungry()) {
            Brain<Seacow> brain = seacow.getBrain();
            brain.setMemory(ModMemoryModuleTypes.IS_LOCATING_FOOD.get(), Unit.INSTANCE);
            brain.setMemoryWithExpiry(ModMemoryModuleTypes.LOCATE_FOOD_COOLDOWN.get(), Unit.INSTANCE, (long) LOCATE_FOOD_COOLDOWN.sample(level.getRandom()));
            brain.eraseMemory(MemoryModuleType.WALK_TARGET);
        }
    }
}
