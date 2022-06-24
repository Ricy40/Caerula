package com.ricy40.caerula.entity;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.custom.Blobfish;
import com.ricy40.caerula.entity.custom.Lula;
import com.ricy40.caerula.entity.custom.seacow.Seacow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, Caerula.MOD_ID);

    public static final RegistryObject<EntityType<Blobfish>> BLOBFISH = ENTITY_TYPES.register("blobfish",
            () -> EntityType.Builder.of(Blobfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.4f, 0.2f)
                    .build(new ResourceLocation(Caerula.MOD_ID, "blobfish").toString()));

    public static final RegistryObject<EntityType<Lula>> LULA = ENTITY_TYPES.register("lula",
            () -> EntityType.Builder.of(Lula::new, MobCategory.WATER_CREATURE)
                    .sized(0.4f, 0.2f)
                    .build(new ResourceLocation(Caerula.MOD_ID, "lula").toString()));

    public static final RegistryObject<EntityType<Seacow>> SEACOW = ENTITY_TYPES.register("seacow",
            () -> EntityType.Builder.of(Seacow::new, MobCategory.WATER_CREATURE)
                    .sized(1f, 0.625f)
                    .build(new ResourceLocation(Caerula.MOD_ID, "seacow").toString()));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
