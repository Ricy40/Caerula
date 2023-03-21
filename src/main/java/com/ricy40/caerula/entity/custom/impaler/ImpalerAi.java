package com.ricy40.caerula.entity.custom.impaler;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.ricy40.caerula.entity.custom.behaviours.*;
import com.ricy40.caerula.entity.custom.behaviours.impaler.*;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class ImpalerAi {

    private static final float SPEED_MULTIPLIER_WHEN_IDLING_IN_WATER = 0.7F;
    private static final float SPEED_MULTIPLIER_WHEN_FIGHTING = 1F;
    private static final int SNIFFING_DURATION = Mth.ceil(83.2F);
    public static final int ROAR_DURATION = Mth.ceil(84.0F);
    public static final List<? extends SensorType<? extends Sensor<? super Impaler>>> SENSOR_TYPES = List.of(
            SensorType.NEAREST_PLAYERS,
            SensorType.IS_IN_WATER
    );
    public static final List<MemoryModuleType<?>> MEMORY_TYPES = List.of(
            MemoryModuleType.NEAREST_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_PLAYER,
            MemoryModuleType.NEAREST_ATTACKABLE,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.ROAR_TARGET,
            MemoryModuleType.DISTURBANCE_LOCATION,
            MemoryModuleType.PATH,
            MemoryModuleType.ATTACK_TARGET,
            MemoryModuleType.ROAR_SOUND_DELAY,
            MemoryModuleType.ROAR_SOUND_COOLDOWN,
            MemoryModuleType.SONIC_BOOM_COOLDOWN,
            MemoryModuleType.SONIC_BOOM_SOUND_COOLDOWN,
            MemoryModuleType.SONIC_BOOM_SOUND_DELAY,
            MemoryModuleType.IS_SNIFFING,
            MemoryModuleType.SNIFF_COOLDOWN
    );

    protected static Brain<?> makeBrain(Impaler impaler, Brain<Impaler> brain) {
        initCoreActivity(brain);
        initIdleActivity(brain);
        initRoarActivity(brain);
        initFightActivity(impaler, brain);
        initInvestigateActivity(brain);
        initSniffingActivity(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    private static void initCoreActivity(Brain<Impaler> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(
                new SetImpalerLookTarget(),
                new LookAtTargetSink(35, 60),
                new MoveToTargetSink()
        ));

    }

    private static void initIdleActivity(Brain<Impaler> brain) {
        brain.addActivity(Activity.IDLE, 10, ImmutableList.of(
                new SetRoarTargetImpaler<>(Impaler::getEntityAngryAt),
                new TryToSniffImpaler(),
                new RunSometimes<>(new SetEntityLookTarget(EntityType.PLAYER, 6.0F), UniformInt.of(30, 60)),
                new RunOne<>(ImmutableList.of()),
                new GateBehavior<>(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), ImmutableSet.of(), GateBehavior.OrderPolicy.ORDERED, GateBehavior.RunningPolicy.TRY_ALL, ImmutableList.of(
                        Pair.of(new CloseRandomSwim(SPEED_MULTIPLIER_WHEN_IDLING_IN_WATER, 3), 2),
                        Pair.of(new SetWalkTargetFromLookTarget(ImpalerAi::canSetWalkTargetFromLookTarget, ImpalerAi::getSpeedModifier, 3), 3),
                        Pair.of(new RunIf<>(Entity::isInWaterOrBubble, new DoNothing(30, 60)), 5)))));
    }

    private static void initRoarActivity(Brain<Impaler> brain) {
        brain.addActivityAndRemoveMemoryWhenStopped(Activity.ROAR, 10, ImmutableList.of(
                new RoarImpaler()
        ), MemoryModuleType.ROAR_TARGET);
    }

    private static void initFightActivity(Impaler impaler, Brain<Impaler> brain) {
        brain.addActivityAndRemoveMemoryWhenStopped(Activity.FIGHT, 10, ImmutableList.of(
                new StopAttackingIfTargetInvalid<>((target) -> {
                        return !impaler.getAngerLevel().isAngry() || !impaler.canTargetEntity(target);
                    }, ImpalerAi::onTargetInvalid, false),
                new SetEntityLookTarget((target) -> {
                    return isTarget(impaler, target);
                    }, (float)impaler.getAttributeValue(Attributes.FOLLOW_RANGE)),
                new SetWalkTargetFromAttackTargetIfTargetOutOfReach(SPEED_MULTIPLIER_WHEN_FIGHTING),
                new SonicCharge()
        ), MemoryModuleType.ATTACK_TARGET);
    }

    private static void initInvestigateActivity(Brain<Impaler> brain) {
        brain.addActivityAndRemoveMemoryWhenStopped(Activity.INVESTIGATE, 5, ImmutableList.of(
                new SetRoarTargetImpaler<>(Impaler::getEntityAngryAt),
                new GoToTargetLocation<>(MemoryModuleType.DISTURBANCE_LOCATION, 2, 0.7F)
        ), MemoryModuleType.DISTURBANCE_LOCATION);
    }

    private static void initSniffingActivity(Brain<Impaler> brain) {
        brain.addActivityAndRemoveMemoryWhenStopped(Activity.SNIFF, 5, ImmutableList.of(
                new SetRoarTargetImpaler<>(Impaler::getEntityAngryAt),
                new SniffingImpaler<>(SNIFFING_DURATION)
        ), MemoryModuleType.IS_SNIFFING);
    }

    public static void updateActivity(Impaler impaler) {
        impaler.getBrain().setActiveActivityToFirstValid(ImmutableList.of(
                Activity.ROAR,
                Activity.FIGHT,
                Activity.INVESTIGATE,
                Activity.SNIFF,
                Activity.IDLE
        ));
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

    private static boolean isTarget(Impaler impaler, LivingEntity target) {
        return impaler.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).filter((targetX) -> {
            return target == targetX;
        }).isPresent();
    }
    
    private static void onTargetInvalid(Impaler impaler, LivingEntity target) {
        if (!impaler.canTargetEntity(target)) {
            impaler.clearAnger(target);
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
