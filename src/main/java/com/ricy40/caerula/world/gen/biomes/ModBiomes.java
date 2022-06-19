package com.ricy40.caerula.world.gen.biomes;

import com.ricy40.caerula.Caerula;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Caerula.MOD_ID);
    public static List<String> biomeList = new ArrayList<>();

    public static final RegistryObject<Biome> RED_FIELDS = registerBiome("red_fields",
            () -> CaerulaBiomes.redFields(false));
    public static final RegistryObject<Biome> DEEP_RED_FIELDS = registerBiome("deep_red_fields",
            () -> CaerulaBiomes.redFields(true));
    public static final RegistryObject<Biome> SEASHROOM_FIELDS = registerBiome("seashroom_fields",
            () -> CaerulaBiomes.seashroomFields());

    public static void register(IEventBus bus) {
        BIOMES.register(bus);
    }

    public static <T extends Biome> RegistryObject<T> registerBiome(String name, Supplier<T> biome) {
        biomeList.add(name);
        return BIOMES.register(name, biome);
    }
}
