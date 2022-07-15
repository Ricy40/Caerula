package com.ricy40.caerula.entity.client.render.impaler;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ricy40.caerula.entity.client.model.impaler.ImpalerModel;
import com.ricy40.caerula.entity.custom.impaler.Impaler;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ImpalerEmissiveLayer<T extends Impaler, M extends ImpalerModel<T>> extends RenderLayer<T, M> {
    private final ResourceLocation texture;
    private final ImpalerEmissiveLayer.AlphaFunction<T> alphaFunction;
    private final ImpalerEmissiveLayer.DrawSelector<T, M> drawSelector;

    public ImpalerEmissiveLayer(RenderLayerParent<T, M> renderLayerParent, ResourceLocation resourceLocation, ImpalerEmissiveLayer.AlphaFunction<T> alphaFunction, ImpalerEmissiveLayer.DrawSelector<T, M> drawSelector) {
        super(renderLayerParent);
        this.texture = resourceLocation;
        this.alphaFunction = alphaFunction;
        this.drawSelector = drawSelector;
    }

    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, T impaler, float pLimbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch) {
        if (!impaler.isInvisible()) {
            this.onlyDrawSelectedParts();
            VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RenderType.entityTranslucentEmissive(this.texture));
            this.getParentModel().renderToBuffer(poseStack, vertexconsumer, packedLight, LivingEntityRenderer.getOverlayCoords(impaler, 0.0F), 1.0F, 1.0F, 1.0F, this.alphaFunction.apply(impaler, partialTicks, ageInTicks));
            this.resetDrawForAllParts();
        }
    }

    private void onlyDrawSelectedParts() {
        List<ModelPart> list = this.drawSelector.getPartsToDraw(this.getParentModel());
        this.getParentModel().root().getAllParts().forEach((part) -> {
            part.skipDraw = true;
        });
        list.forEach((part) -> {
            part.skipDraw = false;
        });
    }

    private void resetDrawForAllParts() {
        this.getParentModel().root().getAllParts().forEach((part) -> {
            part.skipDraw = false;
        });
    }

    @OnlyIn(Dist.CLIENT)
    public interface AlphaFunction<T extends Impaler> {
        float apply(T impaler, float partialTicks, float ageInTicks);
    }

    @OnlyIn(Dist.CLIENT)
    public interface DrawSelector<T extends Impaler, M extends EntityModel<T>> {
        List<ModelPart> getPartsToDraw(M model);
    }
}
