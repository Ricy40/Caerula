package com.ricy40.caerula;

import com.mojang.logging.LogUtils;
import com.ricy40.caerula.block.ModBlocks;
import com.ricy40.caerula.entity.ModEntityTypes;
import com.ricy40.caerula.entity.client.render.BlobfishRenderer;
import com.ricy40.caerula.item.ModItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("caerula")
public class Caerula {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "caerula";

    public Caerula() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(bus);
        ModBlocks.register(bus);

        ModEntityTypes.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM PREINIT");
    }

    private void clientSetup(final InterModEnqueueEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_SEAGRASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TALL_RED_SEAGRASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BUSH_CORAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DEAD_BUSH_CORAL.get(), RenderType.cutout());

        EntityRenderers.register(ModEntityTypes.BLOBFISH.get(), BlobfishRenderer::new);
    }
}
