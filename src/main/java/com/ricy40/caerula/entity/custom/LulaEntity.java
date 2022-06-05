package com.ricy40.caerula.entity.custom;

import com.ricy40.caerula.util.Vec2ex;
import com.ricy40.caerula.util.Vec3ex;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;

public class LulaEntity extends WaterAnimal {

    private static final EntityDataAccessor<Float> SWIM_ANIM_TIME_SYNC = SynchedEntityData.defineId(LulaEntity.class, EntityDataSerializers.FLOAT);
    private float swimAnimTime;
    private float lastTime;
    private float swimAnimTick;
    private float speedMultiplier;
    private Vec3ex target;
    private boolean isFleeing;

    public LulaEntity(EntityType<? extends WaterAnimal> type, Level worldIn) {
        super(type, worldIn);
        this.isFleeing = false;
        this.swimAnimTick = 0f;
        this.lastTime = 0f;
        this.speedMultiplier = 0f;
        this.target = new Vec3ex(0f, 0f, 0f);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new LulaEntity.LulaRandomMovementGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SWIM_ANIM_TIME_SYNC, 0f);
    }

    public void tick() {
        super.tick();
        if (!this.level.isClientSide()) {
            if (!this.isFleeing) {
                float swimAnimLength = 50f;

                if (this.swimAnimTick <= swimAnimLength) {
                    this.swimAnimTick++;
                } else {
                    this.swimAnimTick = 0;
                }
                System.out.println("tick: " + this.swimAnimTick + " time: " + this.swimAnimTime);
                this.swimAnimTime = this.swimAnimTick / 20;
                this.setSwimAnimTimeSync(this.swimAnimTime);
            }

            if (this.swimAnimTick > 39.5 && this.swimAnimTick < 41) {
                this.lookAtPos(this.target);
            }
        }
    }

    public void aiStep() {
        super.aiStep();

        if (!this.level.isClientSide() && this.isInWaterOrBubble()) {

            if (!this.isFleeing) {

                if (0.6 <= this.swimAnimTime && this.swimAnimTime < 2.1f) {
                    if (0.6 <= this.swimAnimTime && this.swimAnimTime < 0.7184f) {
                        if (this.lastTime != swimAnimTime) {
                            this.speedMultiplier = swimPolynomial(this.swimAnimTime);
                        }
                    } else if (0.7184 <= this.swimAnimTime && this.swimAnimTime <= 2f) {
                        if (this.lastTime != swimAnimTime) {
                            this.speedMultiplier = swimHyperbola(this.swimAnimTime);
                        }
                    } else {
                        this.speedMultiplier = 0f;
                    }
                    this.lastTime = this.swimAnimTime;

                    Vec3ex swimMovement = this.target;
                    this.setDeltaMovement(swimMovement.scale(speedMultiplier * 2.1f));
                }
            }
        }
    }

    public void lookAtPos(Vec3ex position) {
        double d0 = position.x -this.xo;
        double d2 = position.z;
        double d1 = position.y;
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
        float f1 = (float)(-(Mth.atan2(d1, d3) * (double)(180F / (float)Math.PI)));
        this.setXRot(this.getXRot() + f1);
        this.setYRot(this.getYRot() + f);
    }

    private float swimPolynomial(float time) {
        float o = 10 * time - 7.35f;
        return (float) (0.207 * (2 * Math.pow(o, 4) + 3.5 * Math.pow(0, 3) + -0.47 * Math.pow(0, 2) + -0.7 * o + 1.8824));
    }

    private float swimHyperbola(float time) {
        return (float) (0.1 * Math.cosh(1.7 * time - 3.5297) - 0.1);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SQUID_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SQUID_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SQUID_DEATH;
    }

    protected SoundEvent getSquirtSound() {
        return SoundEvents.SQUID_SQUIRT;
    }

    public float getLastTime() {
        return this.lastTime;
    }

    public void setSwimAnimTimeSync(Float time) {
        this.entityData.set(SWIM_ANIM_TIME_SYNC, time);
    }

    public float getSwimAnimTimeSync() {
        return this.entityData.get(SWIM_ANIM_TIME_SYNC);
    }

    public boolean isFleeing() {
        return this.isFleeing;
    }

    public Vec3ex getTargetDirection() {
        return this.target;
    }

    class LulaRandomMovementGoal extends Goal {
        private final LulaEntity lula;

        public LulaRandomMovementGoal(LulaEntity lula) {
            this.lula = lula;
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public void tick() {

            if (!this.lula.level.isClientSide()) {
                if (this.lula.getSwimAnimTimeSync() == 2.0f || this.lula.getSwimAnimTimeSync() == 2.05f) {
                    this.lula.target = new Vec3ex(0, 0, 0);
                } else if (this.lula.target.isZero() || !this.lula.wasTouchingWater) {

                    float f = this.lula.getRandom().nextFloat() * ((float) Math.PI * 2F);
                    float f1 = Mth.cos(f) * 0.2F;
                    float f2 = -0.1F + this.lula.getRandom().nextFloat() * 0.25F;
                    float f3 = Mth.sin(f) * 0.2F;

                    this.lula.target = new Vec3ex(f1, f2, f3);
                }
            }
        }
    }

}
