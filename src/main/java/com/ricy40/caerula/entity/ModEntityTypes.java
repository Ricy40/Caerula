package com.ricy40.caerula.entity;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.custom.BlobfishEntity;
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

    public static final RegistryObject<EntityType<BlobfishEntity>> BLOBFISH = ENTITY_TYPES.register("blobfish",
            () -> EntityType.Builder.of(BlobfishEntity::new, MobCategory.WATER_CREATURE)
                    .sized(0.5f, 0.3f)
                    .build(new ResourceLocation(Caerula.MOD_ID, "blobfish").toString()));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
