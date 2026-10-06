// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class seacow<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "seacow"), "main");
	private final ModelPart body;
	private final ModelPart head;

	public seacow(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -5.0F, -9.0F, 12.0F, 10.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));

		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 28).addBox(-4.0F, -3.0F, -1.0F, 8.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(37, 40).addBox(-3.0F, -2.0F, -1.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail3_r1 = tail3.addOrReplaceChild("tail3_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -5.0F, 22.0F, 0.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -17.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition mush3 = tail1.addOrReplaceChild("mush3", CubeListBuilder.create().texOffs(3, 53).addBox(-4.0F, -7.0F, 0.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, 5.0F, 0.0F, 2.3562F, 0.0F));

		PartDefinition mush3p2_r1 = mush3.addOrReplaceChild("mush3p2_r1", CubeListBuilder.create().texOffs(3, 53).addBox(-4.0F, -7.0F, 1.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition finleft = body.addOrReplaceChild("finleft", CubeListBuilder.create().texOffs(42, 0).addBox(-1.0F, -1.0F, -1.5F, 7.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 1.0F, -3.5F));

		PartDefinition finright = body.addOrReplaceChild("finright", CubeListBuilder.create().texOffs(0, 44).addBox(-6.0F, -1.0F, -1.5F, 7.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 1.0F, -3.5F));

		PartDefinition mush1 = body.addOrReplaceChild("mush1", CubeListBuilder.create().texOffs(3, 53).addBox(-4.0F, -7.0F, 0.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -4.0F, -6.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition mush1p2_r1 = mush1.addOrReplaceChild("mush1p2_r1", CubeListBuilder.create().texOffs(3, 53).addBox(-4.0F, -7.0F, 1.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition mush2 = body.addOrReplaceChild("mush2", CubeListBuilder.create().texOffs(3, 53).addBox(-4.0F, -7.0F, 0.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -4.0F, 3.0F, 0.0F, -2.3562F, 0.0F));

		PartDefinition mush2p2_r1 = mush2.addOrReplaceChild("mush2p2_r1", CubeListBuilder.create().texOffs(3, 53).addBox(-4.0F, -7.0F, 1.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 28).addBox(-3.0F, -2.5F, -5.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.5F, -9.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -1.5F, -2.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -5.0F));

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