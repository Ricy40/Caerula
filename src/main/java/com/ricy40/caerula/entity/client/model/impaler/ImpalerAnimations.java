package com.ricy40.caerula.entity.client.model.impaler;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ImpalerAnimations {
    public static final AnimationDefinition IMPALER_TENDRILS = AnimationDefinition.Builder.withLength(1.08F)
            .addAnimation("fin1",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.16F, KeyframeAnimations.degreeVec(-2.5F, 10F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.33F, KeyframeAnimations.degreeVec(-5F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.50F, KeyframeAnimations.degreeVec(-2.5F, -10F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.66F, KeyframeAnimations.degreeVec(-0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("fin2",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.08F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25F, KeyframeAnimations.degreeVec(-2.5F, 10F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.41F, KeyframeAnimations.degreeVec(-5F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.58F, KeyframeAnimations.degreeVec(-2.5F, -10F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75F, KeyframeAnimations.degreeVec(-0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("fin3",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.16F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.33F, KeyframeAnimations.degreeVec(-2.5F, 10F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.50F, KeyframeAnimations.degreeVec(-5F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.66F, KeyframeAnimations.degreeVec(-2.5F, -10F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.83F, KeyframeAnimations.degreeVec(-0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("fin4",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.25F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.41F, KeyframeAnimations.degreeVec(-2.5F, 10F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.58F, KeyframeAnimations.degreeVec(-5F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.75F, KeyframeAnimations.degreeVec(-2.5F, -10F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.91F, KeyframeAnimations.degreeVec(-0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("fin5",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.33F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.50F, KeyframeAnimations.degreeVec(-2.5F, 10F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.66F, KeyframeAnimations.degreeVec(-5F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.83F, KeyframeAnimations.degreeVec(-2.5F, -10F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.00F, KeyframeAnimations.degreeVec(-0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("fin6",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.41F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.58F, KeyframeAnimations.degreeVec(-2.5F, 10F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75F, KeyframeAnimations.degreeVec(-5F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.91F, KeyframeAnimations.degreeVec(-2.5F, -10F, 0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.08F, KeyframeAnimations.degreeVec(-0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)))
            .build();
}
