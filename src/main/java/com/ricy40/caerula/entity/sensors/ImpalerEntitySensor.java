package com.ricy40.caerula.entity.sensors;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.ricy40.caerula.entity.custom.impaler.Impaler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.NearestLivingEntitySensor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class ImpalerEntitySensor extends NearestLivingEntitySensor<Impaler> {

    public Set<MemoryModuleType<?>> requires() {
        return ImmutableSet.copyOf(Iterables.concat(super.requires(), List.of(MemoryModuleType.NEAREST_ATTACKABLE)));
    }

    protected void doTick(ServerLevel level, Impaler impaler) {
        super.doTick(level, impaler);
        getClosest(impaler, (p_217847_) -> {
            return p_217847_.getType() == EntityType.PLAYER;
        }).or(() -> {
            return getClosest(impaler, (entity) -> {
                return entity.getType() != EntityType.PLAYER;
            });
        }).ifPresentOrElse((entity) -> {
            impaler.getBrain().setMemory(MemoryModuleType.NEAREST_ATTACKABLE, entity);
        }, () -> {
            impaler.getBrain().eraseMemory(MemoryModuleType.NEAREST_ATTACKABLE);
        });
    }

    private static Optional<LivingEntity> getClosest(Impaler p_217843_, Predicate<LivingEntity> p_217844_) {
        return p_217843_.getBrain().getMemory(MemoryModuleType.NEAREST_LIVING_ENTITIES).stream().flatMap(Collection::stream).filter(p_217843_::canTargetEntity).filter(p_217844_).findFirst();
    }

    protected int radiusXZ() {
        return 24;
    }

    protected int radiusY() {
        return 24;
    }

}
