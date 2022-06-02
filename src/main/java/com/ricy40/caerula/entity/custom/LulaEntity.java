package com.ricy40.caerula.entity.custom;

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
import net.minecraft.world.phys.Vec3;

public class LulaEntity extends WaterAnimal {

    private float swimAnimTime;
    private float lastTime;
    private float swimAnimTick;
    private float speedMultiplier;
    private float tx;
    private float ty;
    private float tz;
    public boolean isFleeing;

    public LulaEntity(EntityType<? extends WaterAnimal> type, Level worldIn) {
        super(type, worldIn);
        this.isFleeing = false;
        this.swimAnimTick = 0f;
        this.lastTime = 0f;
        this.speedMultiplier = 0f;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new LulaEntity.LulaRandomMovementGoal(this));

    }

    public void tick() {
        super.tick();
        if (!this.isFleeing) {
            float swimAnimLength = 50f;

            if (this.swimAnimTick <= swimAnimLength) {
                this.swimAnimTick++;
            } else {
                this.swimAnimTick = 0;
            }
            this.swimAnimTime = this.swimAnimTick / 20;
        }
    }

    public void aiStep() {
        super.aiStep();

        System.out.println(this.tx + " , " + this.ty + " , " + this.tz);

        if (!this.isFleeing) {
            //float delay = this.swimAnimTime - 0.5f < 0 ? 0f : this.swimAnimTime - 0.5f;
            if (0.6 <= this.swimAnimTime && this.swimAnimTime < 0.7184f) {
                if (this.lastTime != swimAnimTime) {
                    this.speedMultiplier = swimPolynomial(this.swimAnimTime);
                }
            } else if (0.7184 <= this.swimAnimTime && this.swimAnimTime <= 2.5f) {
                if (this.lastTime != swimAnimTime) {
                    this.speedMultiplier = swimHyperbola(this.swimAnimTime);
                }
            } else {
                this.speedMultiplier = 0f;
            }
            this.lastTime = this.swimAnimTime;

            if (!this.level.isClientSide) {
                Vec3 swimMovement = new Vec3(this.tx * this.speedMultiplier, this.ty * this.speedMultiplier, this.tz * this.speedMultiplier);
                this.setDeltaMovement(swimMovement.scale(0.4f));
            }
        }

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

    public float getSwimAnimTime() {
        return this.swimAnimTime;
    }

    public float getLastTime() {
        return this.lastTime;
    }

    public void setMovementVector(float pRandomMotionVecX, float pRandomMotionVecY, float pRandomMotionVecZ) {
        this.tx = pRandomMotionVecX;
        this.ty = pRandomMotionVecY;
        this.tz = pRandomMotionVecZ;
    }

    public boolean hasMovementVector() {
        return this.tx != 0.0F || this.ty != 0.0F || this.tz != 0.0F;
    }

    private float swimPolynomial(float time) {
        float o = 10 * time - 7.35f;
        return (float) (0.5 * (2 * Math.pow(o, 4) + 3.5 * Math.pow(0, 3) + -0.47 * Math.pow(0, 2) + -0.7 * o +1.8824));
    }

    private float swimHyperbola(float time) {
        return (float) (0.1 * Math.cosh(1.7 * time - 4.297) - 0.1);
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
            float swimTime = this.lula.swimAnimTime;
            if (swimTime == 2.5) {
                this.lula.setMovementVector(0.0F, 0.0F, 0.0F);
            } else if (!this.lula.hasMovementVector() || !this.lula.wasTouchingWater) {
                float f = this.lula.getRandom().nextFloat() * ((float)Math.PI * 2F);
                float f1 = Mth.cos(f) * 0.2F;
                float f2 = -0.1F + this.lula.getRandom().nextFloat() * 0.2F;
                float f3 = Mth.sin(f) * 0.2F;
                this.lula.setMovementVector(f1, f2, f3);

            }
        }
    }

}
