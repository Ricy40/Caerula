package com.ricy40.caerula.entity.client.render;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.entity.client.model.SeacowModel;
import com.ricy40.caerula.entity.custom.seacow.Mershroom;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class MershroomRenderer extends MobRenderer<Mershroom, SeacowModel<Mershroom>> {

    private static final Map<Mershroom.MushroomType, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (type) -> {
        type.put(Mershroom.MushroomType.YELLOW, new ResourceLocation(Caerula.MOD_ID, "textures/entity/seacow/purple_mershroom.png"));
        type.put(Mershroom.MushroomType.PURPLE, new ResourceLocation(Caerula.MOD_ID,"textures/entity/seacow/yellow_mershroom.png"));
    });

    public MershroomRenderer(EntityRendererProvider.Context context) {
        super(context, new SeacowModel<>(context.bakeLayer(SeacowModel.LAYER_LOCATION)), 0.8f);
    }

    protected void setupRotations(Mershroom pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        //float f = 4.3F * Mth.sin(0.5F * pAgeInTicks);
        //pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!pEntityLiving.isInWater()) {
        }
    }

    @Override
    public ResourceLocation getTextureLocation(Mershroom pEntity) {
        return TEXTURES.get(pEntity.getMushroomType());
    }
}
