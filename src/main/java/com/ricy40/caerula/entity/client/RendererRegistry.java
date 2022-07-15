package com.ricy40.caerula.entity.client;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.ModEntityTypes;
import com.ricy40.caerula.entity.client.model.blobfish.BlobfishModel;
import com.ricy40.caerula.entity.client.model.impaler.ImpalerModel;
import com.ricy40.caerula.entity.client.model.lula.LulaModel;
import com.ricy40.caerula.entity.client.model.seacow.SeacowModel;
import com.ricy40.caerula.entity.client.render.blobfish.BlobfishRenderer;
import com.ricy40.caerula.entity.client.render.impaler.ImpalerRenderer;
import com.ricy40.caerula.entity.client.render.lula.LulaRenderer;
import com.ricy40.caerula.entity.client.render.seacow.MershroomRenderer;
import com.ricy40.caerula.entity.client.render.seacow.SeacowRenderer;
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
        event.registerEntityRenderer(ModEntityTypes.SEACOW.get(), SeacowRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.MERSHROOM.get(), MershroomRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.IMPALER.get(), ImpalerRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerLocation(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BlobfishModel.LAYER_LOCATION, BlobfishModel::createBodyLayer);
        event.registerLayerDefinition(LulaModel.LAYER_LOCATION, LulaModel::createBodyLayer);
        event.registerLayerDefinition(SeacowModel.LAYER_LOCATION, SeacowModel::createBodyLayer);
        event.registerLayerDefinition(ImpalerModel.LAYER_LOCATION, ImpalerModel::createBodyLayer);
    }

}