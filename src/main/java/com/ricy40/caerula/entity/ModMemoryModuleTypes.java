package com.ricy40.caerula.entity;

import com.mojang.serialization.Codec;
import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.custom.AgeableWaterAnimal;
import net.minecraft.core.Registry;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;
import java.util.function.Supplier;

public class ModMemoryModuleTypes {

    public static final DeferredRegister<MemoryModuleType<?>> MEMORY_MODULE_TYPES = DeferredRegister.create(ForgeRegistries.MEMORY_MODULE_TYPES, Caerula.MOD_ID);

    public static final RegistryObject<MemoryModuleType<Unit>> IS_SNIFFLING = registerMemoryModuleType("is_sniffling", () -> new MemoryModuleType<>(Optional.of(Codec.unit(Unit.INSTANCE))));
    public static final RegistryObject<MemoryModuleType<Unit>> SNIFFLING_COOLDOWN = registerMemoryModuleType("sniffling_cooldown", () -> new MemoryModuleType<>(Optional.of(Codec.unit(Unit.INSTANCE))));
    public static final RegistryObject<MemoryModuleType<Unit>> IS_EATING = registerMemoryModuleType("is_eating", () -> new MemoryModuleType<>(Optional.of(Codec.unit(Unit.INSTANCE))));
    public static final RegistryObject<MemoryModuleType<Unit>> EATING_COOLDOWN = registerMemoryModuleType("eating_cooldown", () -> new MemoryModuleType<>(Optional.of(Codec.unit(Unit.INSTANCE))));
    
    private static <T> RegistryObject<MemoryModuleType<T>> registerMemoryModuleType(String name, Supplier<MemoryModuleType<T>> module) {
        return MEMORY_MODULE_TYPES.register(name, module);
    }

    public static void register(IEventBus bus) {
        MEMORY_MODULE_TYPES.register(bus);
    }
}
