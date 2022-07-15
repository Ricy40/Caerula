package com.ricy40.caerula.entity.client.render.impaler;

import com.ricy40.caerula.entity.client.model.impaler.ImpalerModel;
import com.ricy40.caerula.entity.custom.impaler.Impaler;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ImpalerRenderer extends MobRenderer<Impaler, ImpalerModel<Impaler>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/impaler/impaler.png");
    private static final ResourceLocation BIOLUMINESCENT_LAYER_TEXTURE = new ResourceLocation("textures/entity/impaler/impaler_bioluminescent_layer.png");
    private static final ResourceLocation STINGER_TEXTURE = new ResourceLocation("textures/entity/impaler/impaler_stinger.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_1 = new ResourceLocation("textures/entity/impaler/impaler_pulsating_spots_1.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_2 = new ResourceLocation("textures/entity/impaler/impaler_pulsating_spots_2.png");

    public ImpalerRenderer(EntityRendererProvider.Context context) {
        super(context, new ImpalerModel<>(context.bakeLayer(ImpalerModel.LAYER_LOCATION)), 0.9f);
        this.addLayer(new ImpalerEmissiveLayer<>(this, BIOLUMINESCENT_LAYER_TEXTURE, (impaler, min, ageInTicks) -> 1.0F, ImpalerModel::getBioluminescentLayerModelParts));
        this.addLayer(new ImpalerEmissiveLayer<>(this, PULSATING_SPOTS_TEXTURE_1, (impaler, partialTicks, ageInTicks) -> Math.max(0.0F, Mth.cos(ageInTicks * 0.045F) * 0.25F), ImpalerModel::getPulsatingSpotsLayerModelParts));
        this.addLayer(new ImpalerEmissiveLayer<>(this, PULSATING_SPOTS_TEXTURE_2, (impaler, partialTicks, ageInTicks) -> Math.max(0.0F, Mth.cos(ageInTicks * 0.045F + (float)Math.PI) * 0.25F), ImpalerModel::getPulsatingSpotsLayerModelParts));
        this.addLayer(new ImpalerEmissiveLayer<>(this, TEXTURE, (impaler, partialTicks, ageInTicks) -> impaler.tendrilAnimationState.getAccumulatedTime(), ImpalerModel::getTendrilsLayerModelParts));
        this.addLayer(new ImpalerEmissiveLayer<>(this, STINGER_TEXTURE, (impaler, partialTicks, ageInTicks) -> impaler.getStingerAnimation(partialTicks), ImpalerModel::getStingerLayerModelParts));
    }

    @Override
    public ResourceLocation getTextureLocation(Impaler pEntity) {
        return TEXTURE;
    }
    
}
