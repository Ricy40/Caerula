package com.ricy40.caerula.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import java.util.function.Function;

public abstract class HierarchicalAgeableModel<E extends Entity> extends HierarchicalModel<E> {

    private final boolean scaleHead;
    private final float babyYHeadOffset;
    private final float babyZHeadOffset;
    private final float babyHeadScale;
    private final float babyBodyScale;
    private final float bodyYOffset;

    public HierarchicalAgeableModel(boolean pScaleHead, float pBabyYHeadOffset, float pBabyZHeadOffset) {
        this(pScaleHead, pBabyYHeadOffset, pBabyZHeadOffset, 2.0F, 2.0F, 24.0F);
    }

    public HierarchicalAgeableModel(boolean pScaleHead, float pBabyYHeadOffset, float pBabyZHeadOffset, float pBabyHeadScale, float pBabyBodyScale, float pBodyYOffset) {
        this(RenderType::entityCutoutNoCull, pScaleHead, pBabyYHeadOffset, pBabyZHeadOffset, pBabyHeadScale, pBabyBodyScale, pBodyYOffset);
    }

    public HierarchicalAgeableModel(Function<ResourceLocation, RenderType> pRenderType, boolean pScaleHead, float pBabyYHeadOffset, float pBabyZHeadOffset, float pBabyHeadScale, float pBabyBodyScale, float pBodyYOffset) {
        super(pRenderType);
        this.scaleHead = pScaleHead;
        this.babyYHeadOffset = pBabyYHeadOffset;
        this.babyZHeadOffset = pBabyZHeadOffset;
        this.babyHeadScale = pBabyHeadScale;
        this.babyBodyScale = pBabyBodyScale;
        this.bodyYOffset = pBodyYOffset;
    }

    public HierarchicalAgeableModel() {
        this(false, 5.0F, 2.0F);
    }

    public void setupAnim(E pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
    }

    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        if (this.young) {
            pPoseStack.pushPose();
            if (this.scaleHead) {
                float f = 1.5F / this.babyHeadScale;
                pPoseStack.scale(f, f, f);
            }

            pPoseStack.translate(0.0D, (double) (this.babyYHeadOffset / 16.0F), (double) (this.babyZHeadOffset / 16.0F));
            this.headParts().forEach((part) -> {
                part.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
            });
            pPoseStack.popPose();
            pPoseStack.pushPose();
            float f1 = 1.0F / this.babyBodyScale;
            pPoseStack.scale(f1, f1, f1);
            pPoseStack.translate(0.0D, (double) (this.bodyYOffset / 16.0F), 0.0D);
            this.bodyParts().forEach((part) -> {
                part.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
            });
            pPoseStack.popPose();
        } else {
            this.headParts().forEach((part) -> {
                part.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
            });
            this.bodyParts().forEach((part) -> {
                part.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
            });
        }

    }

    protected abstract Iterable<ModelPart> headParts();

    protected abstract Iterable<ModelPart> bodyParts();

}