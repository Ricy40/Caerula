package com.ricy40.caerula.entity.custom.impaler;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Unit;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class ImpalerAi {


    private static final UniformInt ADULT_FOLLOW_RANGE = UniformInt.of(5, 16);
    private static final float SPEED_MULTIPLIER_WHEN_IDLING_IN_WATER = 0.5F;
    private static final float SPEED_MULTIPLIER_WHEN_FLEEING = 2.0F;
    public static final List<? extends SensorType<? extends Sensor<? super Impaler>>> SENSOR_TYPES = List.of(
            SensorType.NEAREST_PLAYERS,
            SensorType.IS_IN_WATER
    );
    public static final List<MemoryModuleType<?>> MEMORY_TYPES = List.of(
            MemoryModuleType.NEAREST_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_PLAYER,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.DISTURBANCE_LOCATION,
            MemoryModuleType.PATH
    );

    protected static Brain<?> makeBrain(Brain<Impaler> brain) {
        initCoreActivity(brain);
        initIdleActivity(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    private static void initCoreActivity(Brain<Impaler> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(
                new AnimalPanic(SPEED_MULTIPLIER_WHEN_FLEEING),
                new LookAtTargetSink(35, 60),
                new MoveToTargetSink(),
                new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)
        ));

    }

    private static void initIdleActivity(Brain<Impaler> brain) {
        brain.addActivity(Activity.IDLE, 10, ImmutableList.of(
                new RunSometimes<>(new SetEntityLookTarget(EntityType.PLAYER, 6.0F), UniformInt.of(30, 60)),
                new RunOne<>(ImmutableList.of()),
                new GateBehavior<>(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), ImmutableSet.of(), GateBehavior.OrderPolicy.ORDERED, GateBehavior.RunningPolicy.TRY_ALL, ImmutableList.of(
                        Pair.of(new RandomSwim(SPEED_MULTIPLIER_WHEN_IDLING_IN_WATER), 2),
                        Pair.of(new SetWalkTargetFromLookTarget(ImpalerAi::canSetWalkTargetFromLookTarget, ImpalerAi::getSpeedModifier, 3), 3),
                        Pair.of(new RunIf<>(Entity::isInWaterOrBubble, new DoNothing(30, 60)), 5)))));
    }

    public static void updateActivity(Impaler impaler) {
        impaler.getBrain().setActiveActivityToFirstValid(ImmutableList.of(
                Activity.IDLE));
    }

    private static boolean canSetWalkTargetFromLookTarget(LivingEntity entityIn) {
        Level level = entityIn.level;
        Optional<PositionTracker> optional = entityIn.getBrain().getMemory(MemoryModuleType.LOOK_TARGET);
        if (optional.isPresent()) {
            BlockPos blockpos = optional.get().currentBlockPosition();
            return level.isWaterAt(blockpos) == entityIn.isInWaterOrBubble();
        } else {
            return false;
        }
    }

    private static float getSpeedModifier(LivingEntity entityIn) {
        return SPEED_MULTIPLIER_WHEN_IDLING_IN_WATER;
    }

    public static void setDisturbanceLocation(Impaler impaler, BlockPos pos) {
        if (impaler.level.getWorldBorder().isWithinBounds(pos) && !impaler.getEntityAngryAt().isPresent() && !impaler.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).isPresent()) {
            impaler.getBrain().setMemoryWithExpiry(MemoryModuleType.SNIFF_COOLDOWN, Unit.INSTANCE, 100L);
            impaler.getBrain().setMemoryWithExpiry(MemoryModuleType.LOOK_TARGET, new BlockPosTracker(pos), 100L);
            impaler.getBrain().setMemoryWithExpiry(MemoryModuleType.DISTURBANCE_LOCATION, pos, 100L);
            impaler.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
        }
    }
}
