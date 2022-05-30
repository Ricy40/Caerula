package com.ricy40.caerula.item;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.item.misc.CaerulaItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Caerula.MOD_ID);

    public static final RegistryObject<Item> CAERULA = ITEMS.register("caerula",
            () -> new CaerulaItem(new Item.Properties().tab(ModCreativeModeTab.CAERULA)));

    public static final RegistryObject<Item> NIXIUM_INGOT = ITEMS.register("nixium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CAERULA)));
    public static final RegistryObject<Item> NIXIUM_NUGGET = ITEMS.register("nixium_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CAERULA)));
    public static final RegistryObject<Item> RAW_NIXIUM = ITEMS.register("raw_nixium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CAERULA)));

    public static final RegistryObject<Item> BLOBFISH_BUCKET = ITEMS.register("blobfish_bucket",
            () -> new MobBucketItem(EntityType.SALMON, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH,
                    (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

