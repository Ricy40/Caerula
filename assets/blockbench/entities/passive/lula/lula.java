// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class lula_entity<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "lula_entity"), "main");
	private final ModelPart main_body;

	public lula_entity(ModelPart root) {
		this.main_body = root.getChild("main_body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main_body = partdefinition.addOrReplaceChild("main_body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 23.0F, 0.0F, 3.1416F, -1.5708F, -3.1416F));

		PartDefinition finn = main_body.addOrReplaceChild("finn", CubeListBuilder.create().texOffs(0, 6).addBox(-2.0F, -1.5F, 0.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

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
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main_body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}