package com.ricy40.caerula.entity.client.model.impaler;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.custom.impaler.Impaler;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class ImpalerModel<E extends Impaler> extends HierarchicalModel<E> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Caerula.MOD_ID, "impaler"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart jaw;
    private final ModelPart fin1;
    private final ModelPart fin2;
    private final ModelPart tail1;
    private final ModelPart fin3;
    private final ModelPart fin4;
    private final ModelPart tail2;
    private final ModelPart fin5;
    private final ModelPart fin6;
    private final ModelPart tail3;
    private final ModelPart rightBones;
    private final ModelPart leftBones;
    private final ModelPart lance1;
    private final ModelPart lance2;
    private final ModelPart stinger;
    private final List<ModelPart> tendrilsLayerModelParts;
    private final List<ModelPart> stingerLayerModelParts;
    private final List<ModelPart> bioluminescentLayerModelParts;
    private final List<ModelPart> pulsatingSpotsLayerModelParts;

    public ImpalerModel(ModelPart root) {
        this.root = root;
        this.head = this.root.getChild("head");
        this.jaw = this.head.getChild("jaw");
        this.lance1 = this.head.getChild("lance1");
        this.lance2 = this.lance1.getChild("lance2");
        this.stinger = this.lance2.getChild("stinger");
        this.body = this.root.getChild("body");
        this.rightBones = this.body.getChild("rightbones");
        this.leftBones = this.body.getChild("leftbones");
        this.fin1 = this.body.getChild("fin1");
        this.fin2 = this.body.getChild("fin2");
        this.tail1 = this.body.getChild("tail1");
        this.fin3 = this.tail1.getChild("fin3");
        this.fin4 = this.tail1.getChild("fin4");
        this.tail2 = this.tail1.getChild("tail2");
        this.fin5 = this.tail2.getChild("fin5");
        this.fin6 = this.tail2.getChild("fin6");
        this.tail3 = this.tail2.getChild("tail3");
        this.tendrilsLayerModelParts = List.of(this.fin1, this.fin2, this. fin3, this.fin4, this.fin5, this.fin6);
        this.stingerLayerModelParts = List.of(this.lance2, this.stinger);
        this.bioluminescentLayerModelParts = List.of(this.head, this.body);
        this.pulsatingSpotsLayerModelParts = List.of(this.head, this.body);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -4.5F, 4.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.5F, 0.0F));
        PartDefinition leftbones = body.addOrReplaceChild("leftbones", CubeListBuilder.create(), PartPose.offset(2.0F, 2.0F, 0.0F));
        PartDefinition leftbone1 = leftbones.addOrReplaceChild("leftbone1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));
        PartDefinition leftbone1_c = leftbone1.addOrReplaceChild("leftbone1_c", CubeListBuilder.create().texOffs(29, 0).addBox(-1.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));
        PartDefinition leftbone2 = leftbones.addOrReplaceChild("leftbone2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));
        PartDefinition leftbone2_c = leftbone2.addOrReplaceChild("leftbone2_c", CubeListBuilder.create().texOffs(29, 1).addBox(-1.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));
        PartDefinition leftbone3 = leftbones.addOrReplaceChild("leftbone3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));
        PartDefinition leftbone3_c = leftbone3.addOrReplaceChild("leftbone3_c", CubeListBuilder.create().texOffs(29, 3).addBox(-1.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));
        PartDefinition leftbone4 = leftbones.addOrReplaceChild("leftbone4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 3.0F));
        PartDefinition leftbone4_c = leftbone4.addOrReplaceChild("leftbone4_c", CubeListBuilder.create().texOffs(29, 1).addBox(-1.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));
        PartDefinition rightbones = body.addOrReplaceChild("rightbones", CubeListBuilder.create(), PartPose.offset(-2.0F, 2.0F, 0.0F));
        PartDefinition rightbone1 = rightbones.addOrReplaceChild("rightbone1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));
        PartDefinition rightbone1_c = rightbone1.addOrReplaceChild("rightbone1_c", CubeListBuilder.create().texOffs(29, 4).addBox(-4.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));
        PartDefinition rightbone2 = rightbones.addOrReplaceChild("rightbone2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));
        PartDefinition rightbone2_c = rightbone2.addOrReplaceChild("rightbone2_c", CubeListBuilder.create().texOffs(29, 5).addBox(-4.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));
        PartDefinition rightbone3 = rightbones.addOrReplaceChild("rightbone3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));
        PartDefinition rightbone3_c = rightbone3.addOrReplaceChild("rightbone3_c", CubeListBuilder.create().texOffs(29, 6).addBox(-4.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));
        PartDefinition rightbone4 = rightbones.addOrReplaceChild("rightbone4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 3.0F));
        PartDefinition rightbone4_c = rightbone4.addOrReplaceChild("rightbone4_c", CubeListBuilder.create().texOffs(29, 7).addBox(-4.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));
        PartDefinition fin1 = body.addOrReplaceChild("fin1", CubeListBuilder.create().texOffs(10, 33).addBox(-0.5F, -4.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 26).addBox(0.0F, -4.0F, 0.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -3.0F));
        PartDefinition fin2 = body.addOrReplaceChild("fin2", CubeListBuilder.create().texOffs(28, 10).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.0F, -4.0F, -1.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 2.0F));
        PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(12, 14).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 5.5F));
        PartDefinition fin3 = tail1.addOrReplaceChild("fin3", CubeListBuilder.create().texOffs(35, 10).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 22).addBox(0.0F, -3.0F, -1.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 1.0F));
        PartDefinition fin4 = tail1.addOrReplaceChild("fin4", CubeListBuilder.create().texOffs(34, 22).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 21).addBox(0.0F, -3.0F, -1.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 5.0F));
        PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));
        PartDefinition fin5 = tail2.addOrReplaceChild("fin5", CubeListBuilder.create().texOffs(18, 33).addBox(-0.5F, -3.5F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 0).addBox(0.0F, -3.5F, -1.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 1.0F));
        PartDefinition fin6 = tail2.addOrReplaceChild("fin6", CubeListBuilder.create().texOffs(14, 33).addBox(-0.5F, -3.5F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 18).addBox(0.0F, -3.5F, -1.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 5.0F));
        PartDefinition tail4 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(0.0F, -4.0F, 1.0F, 0.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 28).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.5F, -4.5F));
        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(26, 18).addBox(-1.0F, -0.26F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 1).addBox(-0.99F, -1.25F, -2.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.99F, -1.25F, -2.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(6, 4).addBox(-1.01F, -1.25F, -1.99F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -4.0F));
        PartDefinition lance1 = head.addOrReplaceChild("lance1", CubeListBuilder.create().texOffs(11, 25).addBox(-1.0F, -0.75F, -6.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(6, 3).addBox(-1.01F, 0.25F, -1.99F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));
        PartDefinition lance2 = lance1.addOrReplaceChild("lance2", CubeListBuilder.create().texOffs(26, 10).addBox(-0.5F, -0.5F, -7.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.25F, -6.0F));
        PartDefinition stinger = lance2.addOrReplaceChild("stinger", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0.0F, -8.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(22, 25).addBox(0.0F, -1.5F, -8.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void setupAnim(E impaler, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(impaler.tendrilAnimationState, ImpalerAnimations.IMPALER_TENDRILS, ageInTicks);
        //this.animate(impaler.sonicChargeAnimationState, ImpalerAnimations.SONIC_CHARGE, ageInTicks);
        
        animateHeadLookTarget(netHeadYaw, headPitch);
    }

    private void animateHeadLookTarget(float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
    }

    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
    
    public ModelPart root() {
        return this.root;
    }

    public List<ModelPart> getTendrilsLayerModelParts() {
        return this.tendrilsLayerModelParts;
    }

    public List<ModelPart> getStingerLayerModelParts() {
        return this.stingerLayerModelParts;
    }

    public List<ModelPart> getBioluminescentLayerModelParts() {
        return this.bioluminescentLayerModelParts;
    }

    public List<ModelPart> getPulsatingSpotsLayerModelParts() {
        return this.pulsatingSpotsLayerModelParts;
    }
    
}
