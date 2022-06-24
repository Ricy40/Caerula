package com.ricy40.caerula.entity.custom.seacow;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import com.ricy40.caerula.entity.ModActivites;
import com.ricy40.caerula.entity.ModMemoryModuleTypes;
import com.ricy40.caerula.entity.ModSensorTypes;
import com.ricy40.caerula.entity.custom.seacow.activities.Sniffling;
import com.ricy40.caerula.tags.ModTags;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.warden.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class SeacowAi {

    private static final float SPEED_MULTIPLIER_WHEN_FLEEING = 1.2F;
    private static final int SNIFFLING_COOLDOWN = 120;
    private static final int EATING_COOLDOWN = 1200;
    private static final int SNIFFLING_DURATION = Mth.ceil(40.0F);
    private static final List<? extends SensorType<? extends Sensor<? super Seacow>>> SENSOR_TYPES = List.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.HURT_BY,
            SensorType.IS_IN_WATER,
            ModSensorTypes.NEAREST_ADULT_WATER.get(),
            ModSensorTypes.SEACOW_TEMPTATIONS.get()
    );
    private static final List<MemoryModuleType<?>> MEMORY_TYPES = List.of(
            MemoryModuleType.NEAREST_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_PLAYER,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.TEMPTING_PLAYER,
            MemoryModuleType.TEMPTATION_COOLDOWN_TICKS,
            MemoryModuleType.IS_TEMPTED,
            MemoryModuleType.IS_PREGNANT,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.BREED_TARGET,
            MemoryModuleType.PATH,
            ModMemoryModuleTypes.IS_SNIFFLING.get(),
            ModMemoryModuleTypes.IS_EATING.get(),
            ModMemoryModuleTypes.SNIFFLING_COOLDOWN.get(),
            ModMemoryModuleTypes.EATING_COOLDOWN.get()
    );

    public static void updateActivity(Seacow seacow) {
        seacow.getBrain().setActiveActivityToFirstValid(ImmutableList.of(ModActivites.FLEEING.get(), ModActivites.SNIFFLING.get(), ModActivites.EATING.get(), Activity.IDLE));
    }

    protected static Brain<?> makeBrain(Seacow seacow, Dynamic<?> dynamic) {
        Brain.Provider<Seacow> provider = Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
        Brain<Seacow> brain = provider.makeBrain(dynamic);
        initCoreActivity(brain);
        initIdleActivity(brain);
        initSnifflingActivity(brain);
        initEatingActivity(brain);
        initFleeActivity(seacow, brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    private static void initCoreActivity(Brain<Seacow> brain) {

    }


    private static void initIdleActivity(Brain<Seacow> brain) {

    }

    private static void initEatingActivity(Brain<Seacow> brain) {

    }

    private static void initSnifflingActivity(Brain<Seacow> brain) {
        brain.addActivityAndRemoveMemoryWhenStopped(ModActivites.SNIFFLING.get(), 5, ImmutableList.of(new Sniffling<>(SNIFFLING_DURATION)), ModMemoryModuleTypes.IS_SNIFFLING.get());
    }

    private static void initFleeActivity(Seacow seacow, Brain<Seacow> brain) {

    }

    public static Ingredient getTemptations() {
        return Ingredient.of(ModTags.Items.SEACOW_TEMPT_ITEMS);
    }
}