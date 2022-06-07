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

public class Lula extends WaterAnimal {

    private static final EntityDataAccessor<Float> SWIM_ANIM_TIME_SYNC = SynchedEntityData.defineId(Lula.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> LAST_TIME_SYNC = SynchedEntityData.defineId(Lula.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> CLOCK_TICK_SYNC = SynchedEntityData.defineId(Lula.class, EntityDataSerializers.FLOAT);
    private float lastTime;
    private float clockTick;
    private float speedMultiplier;
    private Vec3ex tDirection;
    private boolean isFleeing;
    private float tYRot;
    private float tXRot;
    private float lerpTicks;
    private float xRotStep;
    private float yRotStep;

    public Lula(EntityType<? extends WaterAnimal> type, Level worldIn) {
        super(type, worldIn);
        this.isFleeing = false;
        this.clockTick = 0f;
        this.speedMultiplier = 0f;
        this.tDirection = new Vec3ex(0f, 0f, 0f);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new Lula.LulaRandomMovementGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SWIM_ANIM_TIME_SYNC, 0f);
        this.entityData.define(LAST_TIME_SYNC, 0f);
        this.entityData.define(CLOCK_TICK_SYNC, 0f);
    }

    public void tick() {
        super.tick();
        this.lerpingTicks();
        
        if (!this.level.isClientSide()) {
            this.setLastTimeSync(this.getSwimAnimTimeSync());
            if (this.clockTick > 49) {
                this.clockTick = 0;
            } else {
                this.clockTick++;
            }
            this.setClockTickSync(this.clockTick);
            this.setSwimAnimTimeSync(this.clockTick / 20);
        }
    }

    private void lerpingTicks() {
        if (this.getClockTickSync() > 1 && this.lerpTicks > 0 && !this.tDirection.isZero()) {
            this.setRot(this.getYRot() + this.yRotStep, this.getXRot() + this.xRotStep);
            this.lerpTicks--;
        }
    }

    private void rotateLerp(float yaw, float pitch, float ticks) {
        this.yRotStep = (yaw - this.getYRot()) / ticks;
        this.xRotStep = (pitch - this.getXRot()) / ticks;
        this.lerpTicks = ticks;
    }

    public void aiStep() {
        super.aiStep();

        if (this.isInWaterOrBubble()) {
            if (!this.isFleeing) {
                if (1.1 <= this.getSwimAnimTimeSync() && this.getSwimAnimTimeSync() < 2.6f) {
                    if (1.1 <= this.getSwimAnimTimeSync() && this.getSwimAnimTimeSync() < 1.2184f) {
                        if (this.getLastTimeSync() != this.getSwimAnimTimeSync()) {
                            this.speedMultiplier = swimPolynomial(this.getSwimAnimTimeSync() - 0.5f);
                        }
                    } else if (1.2184 <= this.getSwimAnimTimeSync() && this.getSwimAnimTimeSync() <= 2.5f) {
                        if (this.getLastTimeSync() != this.getSwimAnimTimeSync()) {
                            this.speedMultiplier = swimHyperbola(this.getSwimAnimTimeSync() - 0.5f);
                        }
                    } else {
                        this.speedMultiplier = 0f;
                    }

                    if (!this.level.isClientSide()) {
                        Vec3ex swimMovement = this.tDirection;
                        this.setDeltaMovement(swimMovement.scale(speedMultiplier * 1.3f));
                    }
                }
            }
        }
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

    public float getLastTimeSync() {
        return this.entityData.get(LAST_TIME_SYNC);
    }

    public void setLastTimeSync(float time) {
        this.entityData.set(LAST_TIME_SYNC, time);
    }

    public void setSwimAnimTimeSync(Float time) {
        this.entityData.set(SWIM_ANIM_TIME_SYNC, time);
    }

    public float getSwimAnimTimeSync() {
        return this.entityData.get(SWIM_ANIM_TIME_SYNC);
    }

    public void setClockTickSync(Float time) {
        this.entityData.set(CLOCK_TICK_SYNC, time);
    }

    public float getClockTickSync() {
        return this.entityData.get(CLOCK_TICK_SYNC);
    }

    public boolean isFleeing() {
        return this.isFleeing;
    }

    public Vec3ex getTargetDirection() {
        return this.tDirection;
    }

    class LulaRandomMovementGoal extends Goal {
        private final Lula lula;

        public LulaRandomMovementGoal(Lula lula) {
            this.lula = lula;
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public void tick() {

            if (this.lula.getClockTickSync() == 0 || this.lula.getClockTickSync() == 1f) {
                this.lula.tDirection = new Vec3ex(0, 0, 0);
            } else if (this.lula.tDirection.isZero() && this.lula.isInWaterOrBubble()) {

                float f = this.lula.getRandom().nextFloat() * Mth.TWO_PI;
                float f1 = Mth.cos(f) * 0.2F;
                float f2 = -0.1F + this.lula.getRandom().nextFloat() * 0.3F;
                float f3 = Mth.sin(f) * 0.2F;

                this.lula.tDirection = new Vec3ex(f1, f2, f3);

                this.lula.tXRot = Mth.wrapDegrees(rotateX(this.lula.tDirection));
                this.lula.tYRot = Mth.wrapDegrees(rotateY(this.lula.tDirection));

                rotateLerp(this.lula.tYRot, this.lula.tXRot, 10);
            }
        }

        private float rotateY(Vec3ex direction) {
            float amount = Vec2ex.calculateAngle(
                    new Vec2ex(1, 0    ),
                    new Vec2ex((float) direction.x, (float) direction.z));

            if ((float) direction.z != Mth.abs((float) direction.z)) {
                amount = amount * -1;
            }

            return amount * Mth.RAD_TO_DEG;
        }

        private float rotateX(Vec3ex direction) {
            float amount = Vec2ex.calculateAngle(
                    new Vec2ex(1, 0),
                    new Vec2ex( (new Vec2ex((float) direction.x, (float) direction.z)).mag , (float) direction.y));

            if ((float) direction.y != Mth.abs((float) direction.y)) {
                amount = amount * -1;
            }

            return amount * Mth.RAD_TO_DEG;
        }
    }
}