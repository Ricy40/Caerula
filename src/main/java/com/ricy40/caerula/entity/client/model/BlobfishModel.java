package com.ricy40.caerula.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BlobfishModel<T extends Entity> extends EntityModel<T> {
	
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "blobfish"), "main");
	private final ModelPart head;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart frontTopFin;
	private final ModelPart frontBottomFin;
	private final ModelPart backTopFin;
	private final ModelPart backBottomFin;
	private final ModelPart tailFin;
	private final ModelPart rightFin;
	private final ModelPart leftFin;

	public BlobfishModel(ModelPart root) {
		this.head = root.getChild("head");
		this.tail1 = root.getChild("tail1");
		this.tail2 = root.getChild("tail2");
		this.tail3 = root.getChild("tail3");
		this.frontTopFin = root.getChild("tail1fintop");
		this.frontBottomFin = root.getChild("tail1finbottom");
		this.backTopFin = root.getChild("tail2fintop");
		this.backBottomFin = root.getChild("tail2finbottom");
		this.tailFin = root.getChild("tail3fin");
		this.rightFin = root.getChild("mainfinright");
		this.leftFin = root.getChild("mainfinleft");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -4.0F, -4.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition tail1 = head.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(18, 0).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));
		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));
		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(0, 17).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));
		PartDefinition tail3fin = tail3.addOrReplaceChild("tail3fin", CubeListBuilder.create().texOffs(8, 8).addBox(0.0F, -1.0F, 1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition tail2fintop = tail2.addOrReplaceChild("tail2fintop", CubeListBuilder.create().texOffs(12, 13).addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));
		PartDefinition tail2finbottom = tail2.addOrReplaceChild("tail2finbottom", CubeListBuilder.create().texOffs(14, 8).addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));
		PartDefinition tail1fintop = tail1.addOrReplaceChild("tail1fintop", CubeListBuilder.create().texOffs(6, 13).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 0.0F));
		PartDefinition tail1finbottom = tail1.addOrReplaceChild("tail1finbottom", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, 0.0F));
		PartDefinition mainfinleft = head.addOrReplaceChild("mainfinleft", CubeListBuilder.create().texOffs(10, 17).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -2.0F, 0.0F));
		PartDefinition mainfinright = head.addOrReplaceChild("mainfinright", CubeListBuilder.create().texOffs(6, 17).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -2.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}