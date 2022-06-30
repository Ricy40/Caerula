package com.ricy40.caerula.entity.custom.seacow;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import com.ricy40.caerula.entity.ModActivites;
import com.ricy40.caerula.entity.ModEntityTypes;
import com.ricy40.caerula.entity.ModMemoryModuleTypes;
import com.ricy40.caerula.entity.ModSensorTypes;
import com.ricy40.caerula.entity.custom.seacow.activities.Sniffling;
import com.ricy40.caerula.entity.custom.seacow.activities.TryToSniffle;
import com.ricy40.caerula.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
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
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class SeacowAi {

    private static final UniformInt ADULT_FOLLOW_RANGE = UniformInt.of(5, 16);
    private static final float SPEED_MULTIPLIER_WHEN_MAKING_LOVE = 0.2F;
    private static final float SPEED_MULTIPLIER_WHEN_IDLING_IN_WATER = 0.5F;
    private static final float SPEED_MULTIPLIER_WHEN_FOLLOWING_ADULT_IN_WATER = 0.6F;
    private static final float SPEED_MULTIPLIER_WHEN_FLEEING = 1.2F;
    private static final int EATING_COOLDOWN = 1200;
    private static final int SNIFFLING_DURATION = Mth.ceil(40.0F);
    public static final List<? extends SensorType<? extends Sensor<? super Seacow>>> SENSOR_TYPES = List.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.HURT_BY,
            SensorType.IS_IN_WATER,
            SensorType.NEAREST_ADULT,
            ModSensorTypes.SEACOW_TEMPTATIONS.get()
    );
    public static final List<MemoryModuleType<?>> MEMORY_TYPES = List.of(
            MemoryModuleType.NEAREST_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_PLAYER,
            MemoryModuleType.NEAREST_VISIBLE_ADULT,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.TEMPTING_PLAYER,
            MemoryModuleType.TEMPTATION_COOLDOWN_TICKS,
            MemoryModuleType.IS_TEMPTED,
            MemoryModuleType.IS_PREGNANT,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.BREED_TARGET,
            MemoryModuleType.IS_PANICKING,
            MemoryModuleType.PATH,
            ModMemoryModuleTypes.IS_SNIFFLING.get(),
            ModMemoryModuleTypes.IS_EATING.get(),
            ModMemoryModuleTypes.SNIFFLING_COOLDOWN.get(),
            ModMemoryModuleTypes.EATING_COOLDOWN.get()
    );

    protected static Brain<?> makeBrain(Brain<Seacow> brain) {
        initCoreActivity(brain);
        initIdleActivity(brain);
        initSnifflingActivity(brain);
        //initEatingActivity(brain);
        //initFleeActivity(seacow, brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    private static void initCoreActivity(Brain<Seacow> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(
                new LookAtTargetSink(45, 90),
                new MoveToTargetSink(),
                new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS),
                new TryToSniffle()
        ));

    }

    private static void initIdleActivity(Brain<Seacow> brain) {
        brain.addActivity(Activity.IDLE, ImmutableList.of(
                Pair.of(0, new RunSometimes<>(new SetEntityLookTarget(EntityType.PLAYER, 6.0F), UniformInt.of(30, 60))),
                Pair.of(1, new AnimalMakeLove(ModEntityTypes.SEACOW.get(), 0.2F)),
                Pair.of(2, new RunOne<>(ImmutableList.of(
                        Pair.of(new FollowTemptation(SeacowAi::getSpeedModifier), 1),
                        Pair.of(new BabyFollowAdult<>(ADULT_FOLLOW_RANGE, SeacowAi::getSpeedModifierFollowingAdult), 1)))),
                Pair.of(3, new GateBehavior<>(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), ImmutableSet.of(), GateBehavior.OrderPolicy.ORDERED, GateBehavior.RunningPolicy.TRY_ALL, ImmutableList.of(
                        Pair.of(new RandomSwim(0.5F), 2),
                        Pair.of(new SetWalkTargetFromLookTarget(SeacowAi::canSetWalkTargetFromLookTarget, SeacowAi::getSpeedModifier, 3), 3),
                        Pair.of(new RunIf<>(Entity::isInWaterOrBubble, new DoNothing(30, 60)), 5))))));
    }

    private static void initEatingActivity(Brain<Seacow> brain) {

    }

    private static void initSnifflingActivity(Brain<Seacow> brain) {
        brain.addActivityAndRemoveMemoryWhenStopped(ModActivites.SNIFFLING.get(), 1, ImmutableList.of(new Sniffling<>(SNIFFLING_DURATION)), ModMemoryModuleTypes.IS_SNIFFLING.get());
    }

    private static void initFleeActivity(Seacow seacow, Brain<Seacow> brain) {

    }

    public static void updateActivity(Seacow seacow) {
        seacow.getBrain().setActiveActivityToFirstValid(ImmutableList.of(
                //ModActivites.FLEEING.get(),
                //ModActivites.EATING.get(),
                ModActivites.SNIFFLING.get(),
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

    private static float getSpeedModifierFollowingAdult(LivingEntity entityIn) {
        return entityIn.isInWaterOrBubble() ? 0.6F : 0.15F;
    }

    private static float getSpeedModifier(LivingEntity entityIn) {
        return entityIn.isInWaterOrBubble() ? 0.5F : 0.15F;
    }

    public static Ingredient getTemptations() {
        return Ingredient.of(ModTags.Items.SEACOW_TEMPT_ITEMS);
    }
}