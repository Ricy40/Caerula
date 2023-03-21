package com.ricy40.caerula.entity;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.custom.seacow.SeacowAi;
import com.ricy40.caerula.entity.sensors.ImpalerEntitySensor;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.sensing.TemptingSensor;
import net.minecraft.world.entity.ai.sensing.WardenEntitySensor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.rmi.registry.Registry;
import java.util.function.Supplier;

public class ModSensorTypes {

    public static final DeferredRegister<SensorType<?>> SENSOR_TYPES = DeferredRegister.create(ForgeRegistries.SENSOR_TYPES, Caerula.MOD_ID);

    public static final RegistryObject<SensorType<TemptingSensor>> SEACOW_TEMPTATIONS = registerSensorType("seacow_temptations", () -> new SensorType<>(() -> new TemptingSensor(SeacowAi.getTemptations())));
    public static final RegistryObject<SensorType<ImpalerEntitySensor>> IMPALER_ENTITY_SENSOR = registerSensorType("impaler_entity_sensor", () -> new SensorType<>(() -> new ImpalerEntitySensor()));

    private static <U extends Sensor<?>> RegistryObject<SensorType<U>> registerSensorType(String name, Supplier<SensorType<U>> sensor) {
        return SENSOR_TYPES.register(name, sensor);
    }

    public static void register(IEventBus bus) {
        SENSOR_TYPES.register(bus);
    }
}
