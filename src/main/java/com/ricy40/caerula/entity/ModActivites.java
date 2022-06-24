package com.ricy40.caerula.entity;

import com.ricy40.caerula.Caerula;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModActivites {
    public static final DeferredRegister<Activity> ACTIVITIES = DeferredRegister.create(Registry.ACTIVITY_REGISTRY, Caerula.MOD_ID);

    public static final RegistryObject<Activity> SNIFFLING = registerActivity("sniffling", () -> new Activity("sniffling"));
    public static final RegistryObject<Activity> EATING = registerActivity("eating", () -> new Activity("eating"));
    public static final RegistryObject<Activity> FLEEING = registerActivity("fleeing", () -> new Activity("fleeing"));

    private static RegistryObject<Activity> registerActivity(String name, Supplier<Activity> activity) {
        return ACTIVITIES.register(name, activity);
    }

    public static void register(IEventBus bus) {
        ACTIVITIES.register(bus);
    }
}
