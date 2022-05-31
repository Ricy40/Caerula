package com.ricy40.caerula.entity.client;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.ModEntityTypes;
import com.ricy40.caerula.entity.client.model.BlobfishModel;
import com.ricy40.caerula.entity.client.model.LulaModel;
import com.ricy40.caerula.entity.client.render.BlobfishRenderer;
import com.ricy40.caerula.entity.client.render.LulaRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Caerula.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RendererRegistry {

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.BLOBFISH.get(), BlobfishRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.LULA.get(), LulaRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerLocation(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BlobfishModel.LAYER_LOCATION, BlobfishModel::createBodyLayer);
        event.registerLayerDefinition(LulaModel.LAYER_LOCATION, LulaModel::createBodyLayer);
    }

}