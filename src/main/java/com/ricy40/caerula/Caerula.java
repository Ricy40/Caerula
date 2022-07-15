package com.ricy40.caerula;

import com.mojang.logging.LogUtils;
import com.ricy40.caerula.block.ModBlocks;
import com.ricy40.caerula.entity.ModActivites;
import com.ricy40.caerula.entity.ModEntityTypes;
import com.ricy40.caerula.entity.ModMemoryModuleTypes;
import com.ricy40.caerula.entity.ModSensorTypes;
import com.ricy40.caerula.entity.client.render.blobfish.BlobfishRenderer;
import com.ricy40.caerula.entity.client.render.lula.LulaRenderer;
import com.ricy40.caerula.entity.client.render.seacow.MershroomRenderer;
import com.ricy40.caerula.entity.client.render.seacow.SeacowRenderer;
import com.ricy40.caerula.item.ModItems;
import com.ricy40.caerula.world.dimension.ModDimensions;
import com.ricy40.caerula.world.gen.biomes.ModBiomes;
import com.ricy40.caerula.world.gen.placedfeatures.ModOrePlacements;
import com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.ModAquaticFeatures;
import com.ricy40.caerula.world.gen.placedfeatures.ModAquaticPlacements;
import com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.ModOreFeatures;
import com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.features.ModFeatures;
import com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures.ModTreeFeatures;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
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

        ModActivites.register(bus);
        ModSensorTypes.register(bus);
        ModMemoryModuleTypes.register(bus);
        ModEntityTypes.register(bus);

        ModFeatures.register(bus);
        ModAquaticFeatures.register(bus);
        ModTreeFeatures.register(bus);
        ModOreFeatures.register(bus);
        ModAquaticPlacements.register(bus);
        ModOrePlacements.register(bus);

        ModBiomes.register(bus);
        ModDimensions.register();

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
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PURPLE_SEASHROOM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.YELLOW_SEASHROOM.get(), RenderType.cutout());

        EntityRenderers.register(ModEntityTypes.BLOBFISH.get(), BlobfishRenderer::new);
        EntityRenderers.register(ModEntityTypes.LULA.get(), LulaRenderer::new);
        EntityRenderers.register(ModEntityTypes.SEACOW.get(), SeacowRenderer::new);
        EntityRenderers.register(ModEntityTypes.MERSHROOM.get(), MershroomRenderer::new);
    }
}
