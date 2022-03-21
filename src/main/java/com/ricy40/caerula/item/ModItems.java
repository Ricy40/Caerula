package com.ricy40.caerula.item;

import com.ricy40.caerula.Caerula;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Caerula.MOD_ID);

    public static final RegistryObject<Item> CAERULA = ITEMS.register("caerula",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CAERULA)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

