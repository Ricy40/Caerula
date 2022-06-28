package com.ricy40.caerula.entity;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.custom.seacow.SeacowAi;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.sensing.TemptingSensor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModSensorTypes {

    public static final DeferredRegister<SensorType<?>> SENSOR_TYPES = DeferredRegister.create(ForgeRegistries.SENSOR_TYPES, Caerula.MOD_ID);

    public static final RegistryObject<SensorType<TemptingSensor>> SEACOW_TEMPTATIONS = registerSensorType("seacow_temptations", () -> {return new TemptingSensor(SeacowAi.getTemptations());});

    private static <U extends Sensor<?>> RegistryObject<SensorType<U>> registerSensorType(String name, Supplier<U> sensor) {
        return SENSOR_TYPES.register(name, () -> new SensorType<>(sensor));
    }

    public static void register(IEventBus bus) {
        SENSOR_TYPES.register(bus);
    }
}
