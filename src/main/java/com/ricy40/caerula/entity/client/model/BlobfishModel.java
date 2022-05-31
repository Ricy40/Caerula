package com.ricy40.caerula.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ricy40.caerula.Caerula;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class BlobfishModel<T extends Entity> extends EntityModel<T> {
	
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Caerula.MOD_ID, "blobfish"), "main");
	private final ModelPart root;
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
		this.root = root;
		this.head = root.getChild("head");
		this.tail1 = head.getChild("tail1");
		this.tail2 = tail1.getChild("tail2");
		this.tail3 = tail2.getChild("tail3");
		this.frontTopFin = tail1.getChild("tail1fintop");
		this.frontBottomFin = tail1.getChild("tail1finbottom");
		this.backTopFin = tail2.getChild("tail2fintop");
		this.backBottomFin = tail2.getChild("tail2finbottom");
		this.tailFin = tail3.getChild("tail3fin");
		this.rightFin = head.getChild("mainfinright");
		this.leftFin = head.getChild("mainfinleft");
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

	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 1.0F;
		float g = 1.0F;
		if (!entity.isInWater()) {
			f = 1.5F;
			g = 0.5F;
		}

		this.tail1.yRot = f * 0.1F * Mth.sin(0.5F * ageInTicks);
		this.frontTopFin.yRot = g * 0.05F * Mth.sin(0.5F * ageInTicks);
		this.frontBottomFin.yRot = g * 00.5F * Mth.sin(0.5F * ageInTicks);

		this.tail2.yRot = f * 0.2F * Mth.sin(0.5F * ageInTicks - 1F);
		this.backTopFin.yRot = g * 0.F * Mth.sin(0.5F * ageInTicks - 1F);
		this.backBottomFin.yRot = g * 0.1F * Mth.sin(0.5F * ageInTicks - 1F);

		this.tail3.yRot = f * 0.2F * Mth.sin(0.5F * ageInTicks - 1.5F);
		this.tailFin.yRot = f * 0.1F * Mth.sin(0.F * ageInTicks - 2F);

		this.rightFin.yRot = f * 0.3F * Mth.sin(0.5F * ageInTicks - 0.2F) - 0.1F;
		this.leftFin.yRot = f * 0.3F * Mth.sin(0.5F * ageInTicks - 0.2F) + 0.1F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay);
	}
}