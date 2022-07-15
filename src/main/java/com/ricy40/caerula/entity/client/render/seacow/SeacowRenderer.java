package com.ricy40.caerula.entity.client.render.seacow;

import com.mojang.blaze3d.vertex.PoseStack;
import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.client.model.seacow.SeacowModel;
import com.ricy40.caerula.entity.custom.seacow.Seacow;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SeacowRenderer extends MobRenderer<Seacow, SeacowModel<Seacow>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Caerula.MOD_ID, "textures/entity/seacow/seacow.png");

    public SeacowRenderer(EntityRendererProvider.Context context) {
        super(context, new SeacowModel<>(context.bakeLayer(SeacowModel.LAYER_LOCATION)), 0.8f);
    }

    protected void setupRotations(Seacow pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        //float f = 4.3F * Mth.sin(0.5F * pAgeInTicks);
        //pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!pEntityLiving.isInWater()) {
        }
    }

    @Override
    public ResourceLocation getTextureLocation(Seacow pEntity) {
        return TEXTURE;
    }
}