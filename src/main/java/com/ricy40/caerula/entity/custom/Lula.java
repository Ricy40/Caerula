package com.ricy40.caerula.entity.custom;

import com.ricy40.caerula.item.ModItems;
import com.ricy40.caerula.util.Vec2ex;
import com.ricy40.caerula.util.Vec3ex;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Lula extends WaterAnimal implements Bucketable {

    private static final EntityDataAccessor<Float> SWIM_ANIM_TIME_SYNC = SynchedEntityData.defineId(Lula.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> CLOCK_TICK_SYNC = SynchedEntityData.defineId(Lula.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Float> XROT_SYNC = SynchedEntityData.defineId(Lula.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Float> YROT_SYNC = SynchedEntityData.defineId(Lula.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Lula.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> TICK_OFFSET = SynchedEntityData.defineId(Lula.class, EntityDataSerializers.INT);

    private float speedMultiplier;
    private Vec3ex tDirection;
    private boolean isFleeing;
    private float tYRot;
    private float tXRot;
    private float lerpTicks;
    private float xRotStep;
    private float yRotStep;
    public float xFlee;
    public float yFlee;
    public float zFlee;
    private float fleeTime;

    public Lula(EntityType<? extends WaterAnimal> type, Level worldIn) {
        super(type, worldIn);
        this.isFleeing = false;
        this.speedMultiplier = 0f;
        this.fleeTime = 0f;
        this.tDirection = new Vec3ex(0f, 0f, 0f);

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new Lula.LulaFleeGoal(this));
        this.goalSelector.addGoal(1, new Lula.LulaRandomMovementGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SWIM_ANIM_TIME_SYNC, 0f);
        this.entityData.define(CLOCK_TICK_SYNC, 0f);
        this.entityData.define(XROT_SYNC, 0f);
        this.entityData.define(YROT_SYNC, 0f);
        this.entityData.define(TICK_OFFSET, Mth.floor(this.getRandom().nextFloat() * 50));
        this.entityData.define(FROM_BUCKET, false);
    }

    public void tick() {
        super.tick();
        this.lerpingTicks();
        
        if (!this.level.isClientSide()) {
            float correctedTick = this.tickCount - this.getIntSync(TICK_OFFSET);
            float clockTick = correctedTick < 0 ? 0f : (correctedTick > 50 ? correctedTick % 50 : correctedTick);
            this.setClockTickSync(clockTick);
            this.setSwimAnimTimeSync(clockTick / 20);
        }
    }
    
    private void lerpingTicks() {
        if (!this.level.isClientSide()) {
            if (this.getClockTickSync() > 1 && this.lerpTicks > 0 && !this.tDirection.isZero()) {
                this.setRot(this.getYRot() + this.yRotStep, this.getXRot() + this.xRotStep);
                this.lerpTicks--;
            }
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
            if (!this.isFleeing()) {
                if (1.1 <= this.getSwimAnimTimeSync() && this.getSwimAnimTimeSync() < 2.6f) {
                    if (1.1 <= this.getSwimAnimTimeSync() && this.getSwimAnimTimeSync() < 1.2184f) {
                        this.speedMultiplier = swimPolynomial(this.getSwimAnimTimeSync() - 0.5f);
                    } else if (1.2184 <= this.getSwimAnimTimeSync() && this.getSwimAnimTimeSync() <= 2.5f) {
                        this.speedMultiplier = swimHyperbola(this.getSwimAnimTimeSync() - 0.5f);
                    } else {
                        this.speedMultiplier = 0f;
                    }

                    if (!this.level.isClientSide()) {
                        Vec3ex swimMovement = (this.tDirection);
                        this.setDeltaMovement(swimMovement.scale(speedMultiplier * 1.3f).subtract(0, 0.01, 0));
                    }
                }
            } else {

                if (this.fleeTime > 30) {
                    this.speedMultiplier = 0f;
                    this.fleeTime = 0f;
                } else {
                    this.speedMultiplier = swimHyperbola((this.fleeTime / 20) + 0.5f);
                    if (!this.level.isClientSide()) {
                        this.setDeltaMovement(this.tDirection.scale(speedMultiplier * 0.3f).subtract(0, 0.01, 0));
                    }
                    this.fleeTime++;
                    if (this.fleeTime > 30) {
                        this.speedMultiplier = 0f;
                        this.tDirection = new Vec3ex(0, 0, 0);
                        this.isFleeing = false;
                    }
                }
            }
        } else if (this.speedMultiplier != 0f) {
            this.speedMultiplier = 0f;
            this.tDirection = new Vec3ex(0, 0, 0);
            if (this.fleeTime > 0) {
                this.fleeTime--;
            }
        }
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {

        if (super.hurt(pSource, pAmount) && this.getLastHurtByMob() != null) {
            if (!this.level.isClientSide) {
                this.spawnInk();
            }
            this.xFlee = (float) pSource.getEntity().getX();
            this.yFlee = (float) pSource.getEntity().getY() + pSource.getEntity().getEyeHeight();
            this.zFlee = (float) pSource.getEntity().getZ();
            this.isFleeing = true;

            return true;
        } else {
            return false;
        }
    }

    private void spawnInk() {
        this.playSound(this.getSquirtSound(), this.getSoundVolume(), this.getVoicePitch());
        for(int i = 0; i < 30; ++i) {
            Vec3 vec3 = new Vec3(this.random.nextFloat() * 0.2 - 0.1, this.random.nextFloat() * 0.2 - 0.1, this.random.nextFloat() * 0.2 - 0.1);
            ((ServerLevel)this.level).sendParticles(this.getInkParticle(), this.getX(), this.getY(), this.getZ(), 0, vec3.x, vec3.y, vec3.z, 0.1F);
        }
    }

    protected ParticleOptions getInkParticle() {
        return ParticleTypes.SQUID_INK;
    }

    private float swimPolynomial(float time) {
        float o = 10 * time - 7.35f;
        return (float) (0.207 * (2 * Math.pow(o, 4) + 3.5 * Math.pow(0, 3) + -0.47 * Math.pow(0, 2) + -0.7 * o + 1.8824));
    }

    private float swimHyperbola(float time) {
        return (float) (0.1 * Math.cosh(1.7 * time - 3.5297) - 0.1);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("FromBucket", this.fromBucket());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFromBucket(pCompound.getBoolean("FromBucket"));
    }

    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        return Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
    }

    public void saveToBucketTag(ItemStack stack) {
        Bucketable.saveDefaultDataToBucketTag(this, stack);
    }

    public void loadFromBucketTag(CompoundTag tag) {
        Bucketable.loadDefaultDataFromBucketTag(this, tag);
    }

    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }
    
    public void setFromBucket(boolean fromBucket) {
        this.entityData.set(FROM_BUCKET, fromBucket);
    }
    
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.LULA_BUCKET.get());
    }

    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
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

    public int getIntSync(EntityDataAccessor<Integer> sync) {
        return this.entityData.get(sync);
    }



    public boolean isFleeing() {
        return this.isFleeing;
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

                this.lula.tDirection = new Vec3ex(f1, f2 + 0.02, f3);

                this.lula.tXRot = Mth.wrapDegrees(rotateX(this.lula.tDirection));
                this.lula.tYRot = Mth.wrapDegrees(rotateY(this.lula.tDirection));

                rotateLerp(this.lula.tYRot, this.lula.tXRot, 10);
            }
        }
    }

    class LulaFleeGoal extends Goal {
        private final Lula lula;

        public LulaFleeGoal(Lula lula) {
            this.lula = lula;
        }

        @Override
        public boolean canUse() {
            LivingEntity livingentity = Lula.this.getLastHurtByMob();
            if (Lula.this.isInWater() && livingentity != null && this.lula.isFleeing()) {
                return Lula.this.distanceToSqr(livingentity) < 100.0D;
            } else {
                return false;
            }
        }

        @Override
        public void tick() {
            if (this.lula.isInWaterOrBubble()) {
                this.lula.tDirection = new Vec3ex(this.lula.getX() - this.lula.xFlee, this.lula.getY() - this.lula.yFlee, this.lula.getZ() - this.lula.zFlee);
                this.lula.tXRot = Mth.wrapDegrees(rotateX(this.lula.tDirection));
                this.lula.tYRot = Mth.wrapDegrees(rotateY(this.lula.tDirection));
                rotateLerp(this.lula.tYRot, this.lula.tXRot, 2);
            } else {
                this.lula.isFleeing = false;
            }
        }
    }
}