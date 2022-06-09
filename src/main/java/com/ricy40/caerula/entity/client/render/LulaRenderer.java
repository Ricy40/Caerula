package com.ricy40.caerula.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.client.model.LulaModel;
import com.ricy40.caerula.entity.custom.Lula;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class LulaRenderer extends MobRenderer<Lula, LulaModel<Lula>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Caerula.MOD_ID, "textures/entity/lula/lula.png");

    public LulaRenderer(EntityRendererProvider.Context context) {
        super(context, new LulaModel<>(context.bakeLayer(LulaModel.LAYER_LOCATION)), 0.3f);
    }

    protected void setupRotations(Lula pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        float f = 0.1f * Mth.sin(pAgeInTicks * 0.7f) + 0.15f;

        if (!pEntityLiving.isInWater() && pEntityLiving.isAlive()) {
            pMatrixStack.translate((double)0.0F, (double)f, -0.05F);
            pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
        }

        float f1 = pEntityLiving.getXRot() * -3;
        pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(f1));
    }

    @Override
    public ResourceLocation getTextureLocation(Lula pEntity) {
        return TEXTURE;
    }
}
