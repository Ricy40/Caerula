package com.ricy40.caerula.entity.sensors;

import com.google.common.collect.ImmutableSet;
import com.ricy40.caerula.entity.ModMemoryModuleTypes;
import com.ricy40.caerula.entity.custom.AgeableWaterAnimal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.NearestVisibleLivingEntities;
import net.minecraft.world.entity.ai.sensing.Sensor;

import java.util.Optional;
import java.util.Set;

public class WaterAdultSensor extends Sensor<AgeableWaterAnimal> {
    public Set<MemoryModuleType<?>> requires() {
        return ImmutableSet.of(ModMemoryModuleTypes.NEAREST_VISIBLE_ADULT_WATER.get(), MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES);
    }

    protected void doTick(ServerLevel pLevel, AgeableWaterAnimal pAttacker) {
        pAttacker.getBrain().getMemory(MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES).ifPresent((adult) -> {
            this.setNearestVisibleAdult(pAttacker, adult);
        });
    }

    private void setNearestVisibleAdult(AgeableWaterAnimal pMob, NearestVisibleLivingEntities pNearbyEntities) {
        Optional<AgeableWaterAnimal> optional = pNearbyEntities.findClosest((adult) -> {
            return adult.getType() == pMob.getType() && !adult.isBaby();
        }).map(AgeableWaterAnimal.class::cast);
        pMob.getBrain().setMemory(ModMemoryModuleTypes.NEAREST_VISIBLE_ADULT_WATER.get(), optional);
    }
}