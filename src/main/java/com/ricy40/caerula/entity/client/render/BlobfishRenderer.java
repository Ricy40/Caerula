package com.ricy40.caerula.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.client.model.BlobfishModel;
import com.ricy40.caerula.entity.custom.Blobfish;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BlobfishRenderer extends MobRenderer<Blobfish, BlobfishModel<Blobfish>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Caerula.MOD_ID, "textures/entity/blobfish/blobfish.png");

    public BlobfishRenderer(EntityRendererProvider.Context context) {
        super(context, new BlobfishModel<>(context.bakeLayer(BlobfishModel.LAYER_LOCATION)), 0.3f);
    }

    protected void setupRotations(Blobfish pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        float f = 4.3F * Mth.sin(0.5F * pAgeInTicks);
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!pEntityLiving.isInWater()) {
            pMatrixStack.translate((double) 0.1F, (double) 0.1F, (double) -0.1F);
            pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }

    @Override
    public ResourceLocation getTextureLocation(Blobfish pEntity) {
        return TEXTURE;
    }
}
