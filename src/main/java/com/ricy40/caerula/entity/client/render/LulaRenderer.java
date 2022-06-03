package com.ricy40.caerula.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.client.model.LulaModel;
import com.ricy40.caerula.entity.custom.LulaEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class LulaRenderer extends MobRenderer<LulaEntity, LulaModel<LulaEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Caerula.MOD_ID, "textures/entity/lula/lula.png");

    public LulaRenderer(EntityRendererProvider.Context context) {
        super(context, new LulaModel<>(context.bakeLayer(LulaModel.LAYER_LOCATION)), 0.3f);
    }

    protected void setupRotations(LulaEntity pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        if (!pEntityLiving.isInWater()) {
            pMatrixStack.translate((double) 0.1F, (double) 0.1F, (double) -0.1F);
            pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }

    @Override
    public ResourceLocation getTextureLocation(LulaEntity pEntity) {
        return TEXTURE;
    }
}
