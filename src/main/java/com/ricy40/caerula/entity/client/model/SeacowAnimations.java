package com.ricy40.caerula.entity.client.model;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SeacowAnimations {
    public static final AnimationDefinition SEACOW_SNIFFLE = AnimationDefinition.Builder.withLength(1.5F)
            .addAnimation("nose",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.16F, KeyframeAnimations.degreeVec(-4.8821f, 0, 10F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5F, KeyframeAnimations.degreeVec(-14.91F, 0, -10F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75F, KeyframeAnimations.degreeVec(-22.5F, 0, 5F), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.83F, KeyframeAnimations.degreeVec(-20F, 0, 10F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.16F, KeyframeAnimations.degreeVec(-10F, 0, -10F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5F, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.CATMULLROM)
                    )).build();

    public static final AnimationDefinition SEACOW_EATING = AnimationDefinition.Builder.withLength(1F).looping()
            .addAnimation("nose",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0, 0, 5), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(-22.5f, 0, 0), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0, 0, -5), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.75f, KeyframeAnimations.degreeVec(-22.5f, 0, 0), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0, 0, 5), AnimationChannel.Interpolations.CATMULLROM)
                    ))
            .addAnimation("head",
                    new AnimationChannel(
                            AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.25f, KeyframeAnimations.posVec(0, 0, 0.4f), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0, 0, 0.4f), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    )).build();
}
