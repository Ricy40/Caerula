package com.ricy40.caerula.item;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.ModEntityTypes;
import com.ricy40.caerula.item.misc.CaerulaItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
            () -> new MobBucketItem(() -> ModEntityTypes.BLOBFISH.get(), () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,
                    (new Item.Properties()).stacksTo(1).tab(ModCreativeModeTab.CAERULA)));
    public static final RegistryObject<Item> BLOBFISH = ITEMS.register("blobfish",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CAERULA).food(ModFoods.BLOBFISH)));
    public static final RegistryObject<Item> COOKED_BLOBFISH = ITEMS.register("cooked_blobfish",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CAERULA).food(ModFoods.COOKED_BLOBFISH)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

