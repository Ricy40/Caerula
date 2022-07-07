package com.ricy40.caerula.item;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.ModEntityTypes;
import com.ricy40.caerula.item.misc.CaerulaItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
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
            () -> new MobBucketItem(() -> ModEntityTypes.BLOBFISH.get(), () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,
                    (new Item.Properties()).stacksTo(1).tab(ModCreativeModeTab.CAERULA)));
    public static final RegistryObject<Item> BLOBFISH = ITEMS.register("blobfish",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CAERULA).food(ModFoods.BLOBFISH)));
    public static final RegistryObject<Item> COOKED_BLOBFISH = ITEMS.register("cooked_blobfish",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CAERULA).food(ModFoods.COOKED_BLOBFISH)));
    public static final RegistryObject<Item> BLOBFISH_SPAWN_EGG = ITEMS.register("blobfish_spawn_egg",
            () -> new ForgeSpawnEggItem(() -> ModEntityTypes.BLOBFISH.get(), 14202297, 7356750, (new Item.Properties()).tab(ModCreativeModeTab.CAERULA)));

    public static final RegistryObject<Item> LULA_BUCKET = ITEMS.register("lula_bucket",
            () -> new MobBucketItem(() -> ModEntityTypes.LULA.get(), () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,
                    (new Item.Properties()).stacksTo(1).tab(ModCreativeModeTab.CAERULA)));
    public static final RegistryObject<Item> RAW_LULA = ITEMS.register("raw_lula",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CAERULA).food(ModFoods.RAW_LULA)));
    public static final RegistryObject<Item> COOKED_LULA = ITEMS.register("cooked_lula",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CAERULA).food(ModFoods.COOKED_LULA)));
    public static final RegistryObject<Item> LULA_SPAWN_EGG = ITEMS.register("lula_spawn_egg",
            () -> new ForgeSpawnEggItem(() -> ModEntityTypes.LULA.get(), 16754062, 16435377, (new Item.Properties()).tab(ModCreativeModeTab.CAERULA)));

    public static final RegistryObject<Item> SEACOW_SPAWN_EGG = ITEMS.register("seacow_spawn_egg",
            () -> new ForgeSpawnEggItem(() -> ModEntityTypes.SEACOW.get(), 5197647, 7039851, (new Item.Properties()).tab(ModCreativeModeTab.CAERULA)));
    public static final RegistryObject<Item> MERSHROOM_SPAWN_EGG = ITEMS.register("mershroom_spawn_egg",
            () -> new ForgeSpawnEggItem(() -> ModEntityTypes.MERSHROOM.get(), 6114396, 13880516, (new Item.Properties()).tab(ModCreativeModeTab.CAERULA)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

