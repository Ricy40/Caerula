package com.ricy40.caerula.entity;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.custom.BlobfishEntity;
import com.ricy40.caerula.entity.custom.LulaEntity;
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
                    .sized(0.4f, 0.2f)
                    .build(new ResourceLocation(Caerula.MOD_ID, "blobfish").toString()));
    public static final RegistryObject<EntityType<LulaEntity>> LULA = ENTITY_TYPES.register("lula",
            () -> EntityType.Builder.of(LulaEntity::new, MobCategory.WATER_CREATURE)
                    .sized(0.4f, 0.2f)
                    .build(new ResourceLocation(Caerula.MOD_ID, "lula").toString()));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
