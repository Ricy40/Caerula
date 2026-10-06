// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class impaler<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "impaler"), "main");
	private final ModelPart body;
	private final ModelPart head;

	public impaler(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -4.5F, 4.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.5F, 0.0F));

		PartDefinition leftbones = body.addOrReplaceChild("leftbones", CubeListBuilder.create(), PartPose.offset(2.0F, 2.0F, 0.0F));

		PartDefinition leftbone1 = leftbones.addOrReplaceChild("leftbone1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition leftbone1_r1 = leftbone1.addOrReplaceChild("leftbone1_r1", CubeListBuilder.create().texOffs(29, 0).addBox(-1.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition leftbone2 = leftbones.addOrReplaceChild("leftbone2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition leftbone2_r1 = leftbone2.addOrReplaceChild("leftbone2_r1", CubeListBuilder.create().texOffs(29, 1).addBox(-1.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition leftbone3 = leftbones.addOrReplaceChild("leftbone3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition leftbone3_r1 = leftbone3.addOrReplaceChild("leftbone3_r1", CubeListBuilder.create().texOffs(29, 3).addBox(-1.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition leftbone4 = leftbones.addOrReplaceChild("leftbone4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition leftbone4_r1 = leftbone4.addOrReplaceChild("leftbone4_r1", CubeListBuilder.create().texOffs(29, 1).addBox(-1.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition rightbones = body.addOrReplaceChild("rightbones", CubeListBuilder.create(), PartPose.offset(-2.0F, 2.0F, 0.0F));

		PartDefinition rightbone1 = rightbones.addOrReplaceChild("rightbone1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition rightbone1_r1 = rightbone1.addOrReplaceChild("rightbone1_r1", CubeListBuilder.create().texOffs(29, 4).addBox(-4.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition rightbone2 = rightbones.addOrReplaceChild("rightbone2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition rightbone2_r1 = rightbone2.addOrReplaceChild("rightbone2_r1", CubeListBuilder.create().texOffs(29, 5).addBox(-4.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition rightbone3 = rightbones.addOrReplaceChild("rightbone3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition rightbone3_r1 = rightbone3.addOrReplaceChild("rightbone3_r1", CubeListBuilder.create().texOffs(29, 6).addBox(-4.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition rightbone4 = rightbones.addOrReplaceChild("rightbone4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition rightbone4_r1 = rightbone4.addOrReplaceChild("rightbone4_r1", CubeListBuilder.create().texOffs(29, 7).addBox(-4.0F, 0.0F, -0.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

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

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
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

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}