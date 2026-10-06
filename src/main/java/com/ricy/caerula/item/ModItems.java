package com.ricy.caerula.item;

import com.ricy.caerula.Caerula;
import com.ricy.caerula.item.custom.CaerulaItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Caerula.MOD_ID);

    public static final DeferredItem<Item> CAERULA = ITEMS.registerItem("caerula",
            properties -> new CaerulaItem(properties.stacksTo(1).fireResistant()));
    public static final DeferredItem<Item> RAW_NIXIUM = ITEMS.registerSimpleItem("raw_nixium",
            properties -> properties);
    public static final DeferredItem<Item> NIXIUM_INGOT = ITEMS.registerSimpleItem("nixium_ingot",
            properties -> properties);
    public static final DeferredItem<Item> NIXIUM_NUGGET = ITEMS.registerSimpleItem("nixium_nugget",
            properties -> properties);


    public static void  register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
