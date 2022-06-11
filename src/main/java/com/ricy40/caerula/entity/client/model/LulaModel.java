package com.ricy40.caerula.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.custom.Lula;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class LulaModel<T extends Lula> extends EntityModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Caerula.MOD_ID, "lula_entity"), "main");
	private final ModelPart root;
	private final ModelPart main_body;
	private final ModelPart fin;
	private final ModelPart tentacle1;
	private final ModelPart tentacle2;
	private final ModelPart tentacle3;
	private final ModelPart tentacle4;
	private final ModelPart tentacle5;
	private final ModelPart tentacle6;
	private final ModelPart tentacle7;
	private final ModelPart tentacle8;
	private final ModelPart secondary1;
	private final ModelPart secondary2;
	private final ModelPart secondary3;
	private final ModelPart secondary4;
	private final ModelPart secondary5;
	private final ModelPart secondary6;
	private final ModelPart secondary7;
	private final ModelPart secondary8;

	public LulaModel(ModelPart root) {
		this.root = root;
		this.main_body = root.getChild("main_body");
		this.fin = main_body.getChild("fin");
		this.tentacle1 = main_body.getChild("tentacle1");
		this.tentacle2 = main_body.getChild("tentacle2");
		this.tentacle3 = main_body.getChild("tentacle3");
		this.tentacle4 = main_body.getChild("tentacle4");
		this.tentacle5 = main_body.getChild("tentacle5");
		this.tentacle6 = main_body.getChild("tentacle6");
		this.tentacle7 = main_body.getChild("tentacle7");
		this.tentacle8 = main_body.getChild("tentacle8");
		this.secondary1 = tentacle1.getChild("secondary1");
		this.secondary2 = tentacle2.getChild("secondary2");
		this.secondary3 = tentacle3.getChild("secondary3");
		this.secondary4 = tentacle4.getChild("secondary4");
		this.secondary5 = tentacle5.getChild("secondary5");
		this.secondary6 = tentacle6.getChild("secondary6");
		this.secondary7 = tentacle7.getChild("secondary7");
		this.secondary8 = tentacle8.getChild("secondary8");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main_body = partdefinition.addOrReplaceChild("main_body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 23.0F, 0.0F, Mth.PI * -1, Mth.HALF_PI, Mth.PI * -1));
		PartDefinition fin = main_body.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(0, 6).addBox(-2.0F, -1.5F, 0.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));
		PartDefinition tentacle1 = main_body.addOrReplaceChild("tentacle1", CubeListBuilder.create().texOffs(8, 0).addBox(0.0F, -0.5F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.5F, -2.0F));
		PartDefinition secondary1 = tentacle1.addOrReplaceChild("secondary1", CubeListBuilder.create().texOffs(12, 0).addBox(0.0F, -0.5F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));
		PartDefinition tentacle2 = main_body.addOrReplaceChild("tentacle2", CubeListBuilder.create().texOffs(8, 1).addBox(0.0F, -0.5F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -0.5F, -2.0F));
		PartDefinition secondary2 = tentacle2.addOrReplaceChild("secondary2", CubeListBuilder.create().texOffs(12, 1).addBox(0.0F, -0.5F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));
		PartDefinition tentacle3 = main_body.addOrReplaceChild("tentacle3", CubeListBuilder.create().texOffs(8, -1).addBox(0.0F, -0.5F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.5F, -2.0F));
		PartDefinition secondary3 = tentacle3.addOrReplaceChild("secondary3", CubeListBuilder.create().texOffs(12, -1).addBox(0.0F, -0.5F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));
		PartDefinition tentacle4 = main_body.addOrReplaceChild("tentacle4", CubeListBuilder.create().texOffs(8, -2).addBox(0.0F, -0.5F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -0.5F, -2.0F));
		PartDefinition secondary4 = tentacle4.addOrReplaceChild("secondary4", CubeListBuilder.create().texOffs(12, -2).addBox(0.0F, -0.5F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));
		PartDefinition tentacle5 = main_body.addOrReplaceChild("tentacle5", CubeListBuilder.create().texOffs(12, 6).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 1.0F, -2.0F));
		PartDefinition secondary5 = tentacle5.addOrReplaceChild("secondary5", CubeListBuilder.create().texOffs(12, 9).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));
		PartDefinition tentacle6 = main_body.addOrReplaceChild("tentacle6", CubeListBuilder.create().texOffs(12, 4).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.0F, -2.0F));
		PartDefinition secondary6 = tentacle6.addOrReplaceChild("secondary6", CubeListBuilder.create().texOffs(10, 4).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));
		PartDefinition tentacle7 = main_body.addOrReplaceChild("tentacle7", CubeListBuilder.create().texOffs(0, 9).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -1.0F, -2.0F));
		PartDefinition secondary7 = tentacle7.addOrReplaceChild("secondary7", CubeListBuilder.create().texOffs(0, 6).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));
		PartDefinition tentacle8 = main_body.addOrReplaceChild("tentacle8", CubeListBuilder.create().texOffs(-2, 9).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -1.0F, -2.0F));
		PartDefinition secondary8 = tentacle8.addOrReplaceChild("secondary8", CubeListBuilder.create().texOffs(-2, 6).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T lula, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!lula.isFleeing()) {
			float phaseDifference = 0.1f;
			float rot1;
			float rot1Delay;
			float rot2;
			float rot2Delay;

			float tick = lula.getClockTickSync();
			float sec1 = tick / 20 - 0.5f;

			if (sec1 >= 0) {
				float sec2 = sec1 - phaseDifference < 0 ? 0 : sec1 - phaseDifference;
				float sec1Delay = sec1 - 0.2f < 0 ? 0 : sec1 - 0.2f;
				float sec2Delay = sec2 - 0.2f < 0 ? 0 : sec2 - 0.2f;

				rot1 = sec1 < 0.5855f ? calculateSCurve(sec1) : calculateExpCurve(sec1 - 0.556f);
				rot2 = sec2 < 0.5855f ? calculateSCurve(sec2) : calculateExpCurve(sec2 - 0.556f);
				rot1Delay = sec1Delay < 0.6161f ? calculateOuterSCurve(sec1Delay) : calculateExpCurve(sec1Delay - 0.6f);
				rot2Delay = sec2Delay < 0.6161f ? calculateOuterSCurve(sec2Delay) : calculateExpCurve(sec2Delay - 0.6f);
			} else {
				rot1 = 0;
				rot1Delay = 0;
				rot2 = 0;
				rot2Delay = 0;
			}

			this.tentacle1.yRot = -rot1 * 0.7f;
			this.secondary1.yRot = -rot1Delay * 0.7f;
			this.tentacle2.yRot = -rot2 * 0.7f;
			this.secondary2.yRot = -rot2Delay * 0.7f;
			this.tentacle3.yRot = rot2 * 0.7f;
			this.secondary3.yRot = rot2Delay * 0.7f;
			this.tentacle4.yRot = rot1 * 0.7f;
			this.secondary4.yRot = rot1Delay * 0.7f;
			this.tentacle5.xRot = rot1 * 0.7f;
			this.secondary5.xRot = rot1Delay * 0.7f;
			this.tentacle6.xRot = rot2 * 0.7f;
			this.secondary6.xRot = rot2Delay * 0.7f;
			this.tentacle7.xRot = -rot1 * 0.7f;
			this.secondary7.xRot = -rot1Delay * 0.7f;
			this.tentacle8.xRot = -rot2 * 0.7f;
			this.secondary8.xRot = -rot2Delay * 0.7f;
			this.fin.yRot = (float) (Math.sin(2.513 * sec1) * 0.05f);
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main_body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	private float calculateExpCurve(float time) {
		return (float) (Math.pow(Math.E, -2.9 * time) * (Mth.cos(6.8F * time) + 0.5 * Mth.sin(6.8F * time)));
	}

	private float calculateSCurve(float time) {
		return (float) ((-0.109 * time) + (10.116 * Math.pow(time, 2)) - (12.022 * Math.pow(time, 3)));
	}

	private float calculateOuterSCurve(float time) {
		return (float) (-0.34 +(-0.109 * time) + (11.116 * Math.pow(time, 2)) - (12.022 * Math.pow(time, 3)));
	}
}