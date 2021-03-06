package com.ricy40.caerula.entity.client.model.seacow;

import com.google.common.collect.ImmutableList;
import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.client.model.HierarchicalAgeableModel;
import com.ricy40.caerula.entity.custom.seacow.Seacow;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class SeacowModel<E extends Seacow> extends HierarchicalAgeableModel<E> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Caerula.MOD_ID, "seacow"), "main");
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart finLeft;
	private final ModelPart finRight;
	private final ModelPart head;
	private final ModelPart nose;

	public SeacowModel(ModelPart root) {
		super(false, 1.1F, 5.0F, 2.0F, 2.5F, 34);
		this.root = root;
		this.body = root.getChild("body");
		this.tail1 = body.getChild("tail1");
		this.tail2 = tail1.getChild("tail2");
		this.tail3 = tail2.getChild("tail3");
		this.finLeft = body.getChild("finleft");
		this.finRight = body.getChild("finright");
		this.head = root.getChild("head");
		this.nose = head.getChild("nose");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -5.0F, -9.0F, 12.0F, 10.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 28).addBox(-4.0F, -3.0F, -1.0F, 8.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));
		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(37, 40).addBox(-3.0F, -2.0F, -1.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));
		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition tail3_r1 = tail3.addOrReplaceChild("tail3_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -5.0F, 22.0F, 0.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -17.0F, 0.0F, 0.0F, 1.5708F));
		PartDefinition finleft = body.addOrReplaceChild("finleft", CubeListBuilder.create().texOffs(42, 0).addBox(-1.0F, -1.0F, -1.5F, 7.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 1.0F, -3.5F));
		PartDefinition finright = body.addOrReplaceChild("finright", CubeListBuilder.create().texOffs(0, 44).addBox(-6.0F, -1.0F, -1.5F, 7.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 1.0F, -3.5F));
		PartDefinition mush1 = body.addOrReplaceChild("mush1", CubeListBuilder.create().texOffs(3, 53).addBox(-4.0F, -7.0F, 0.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -4.0F, -6.0F, 0.0F, -0.7854F, 0.0F));
		PartDefinition mush1p2_r1 = mush1.addOrReplaceChild("mush1p2_r1", CubeListBuilder.create().texOffs(3, 53).addBox(-4.0F, -7.0F, 1.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition mush2 = body.addOrReplaceChild("mush2", CubeListBuilder.create().texOffs(3, 53).addBox(-4.0F, -7.0F, 0.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -4.0F, 3.0F, 0.0F, -2.3562F, 0.0F));
		PartDefinition mush2p2_r1 = mush2.addOrReplaceChild("mush2p2_r1", CubeListBuilder.create().texOffs(3, 53).addBox(-4.0F, -7.0F, 1.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition mush3 = tail1.addOrReplaceChild("mush3", CubeListBuilder.create().texOffs(3, 53).addBox(-4.0F, -7.0F, 0.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, 5.0F, 0.0F, 2.3562F, 0.0F));
		PartDefinition mush3p2_r1 = mush3.addOrReplaceChild("mush3p2_r1", CubeListBuilder.create().texOffs(3, 53).addBox(-4.0F, -7.0F, 1.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 28).addBox(-3.0F, -2.5F, -5.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.5F, -9.0F));
		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -1.5F, -2.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -5.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public ModelPart root() {
		return this.root;
	}

	public ModelPart getHead() {
		return this.head;
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		swimAnim(ageInTicks);
		this.animate(entity.sniffleAnimationState, SeacowAnimations.SEACOW_SNIFFLE, ageInTicks);
		this.animate(entity.eatingAnimationState, SeacowAnimations.SEACOW_EATING, ageInTicks);
	}

	private void swimAnim(float ageInTicks) {
		this.tail1.xRot = (float) Math.sin(ageInTicks * 0.2f) * 0.15f - 0.05f;
		this.tail2.xRot = (float) Math.sin(ageInTicks * 0.2f) * 0.15f;
		this.tail3.xRot = (float) Math.sin(ageInTicks * 0.2f) * 0.1f;

		this.finLeft.zRot = (float) -Math.sin(ageInTicks * 0.2f) * 0.15f + 0.1f;
		this.finLeft.yRot = (float) -Math.cos(ageInTicks * 0.2f) * 0.3f;
		this.finRight.zRot = (float) Math.sin(ageInTicks * 0.2f) * 0.15f - 0.1f;
		this.finRight.yRot = (float) Math.cos(ageInTicks * 0.2f) * 0.3f;
	}

	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of(this.head);
	}

	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.body);
	}
}