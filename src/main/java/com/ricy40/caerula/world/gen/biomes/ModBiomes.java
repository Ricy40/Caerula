package com.ricy40.caerula.world.gen.biomes;

import com.ricy40.caerula.Caerula;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Caerula.MOD_ID);

    public static final RegistryObject<Biome> RED_FIELDS = registerBiome("red_fields",
            () -> CaerulaBiomes.redFields(false));

    public static void register(IEventBus bus) {
        BIOMES.register(bus);
    }

    public static <T extends Biome> RegistryObject<T> registerBiome(String name, Supplier<T> biome) {
        return BIOMES.register(name, biome);
    }
}
