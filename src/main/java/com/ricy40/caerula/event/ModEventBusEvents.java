package com.ricy40.caerula.event;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.ModEntityTypes;
import com.ricy40.caerula.entity.custom.Blobfish;
import com.ricy40.caerula.entity.custom.Lula;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Caerula.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.BLOBFISH.get(), Blobfish.createAttributes().build());
        event.put(ModEntityTypes.LULA.get(), Lula.createAttributes().build());
    }
}