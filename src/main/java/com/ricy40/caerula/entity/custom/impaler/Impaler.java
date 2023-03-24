package com.ricy40.caerula.entity.custom.impaler;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Dynamic;
import com.ricy40.caerula.entity.ModEntityTypes;
import com.ricy40.caerula.entity.custom.WaterMonster;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.StartAttacking;
import net.minecraft.world.entity.ai.behavior.warden.SonicBoom;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.monster.warden.AngerLevel;
import net.minecraft.world.entity.monster.warden.AngerManagement;
import net.minecraft.world.entity.monster.warden.WardenAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.DynamicGameEventListener;
import net.minecraft.world.level.gameevent.EntityPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.vibrations.VibrationListener;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Optional;
import java.util.function.BiConsumer;

public class Impaler extends WaterMonster implements VibrationListener.VibrationListenerConfig {

    public static final Logger LOGGER = LogUtils.getLogger();
    private static final int ANGERMANAGEMENT_TICK_DELAY = 20;
    private static final int DEFAULT_ANGER = 35;
    private static final int PROJECTILE_ANGER = 10;
    private static final int ON_HURT_ANGER_BOOST = 20;
    private static final EntityDataAccessor<Integer> CLIENT_ANGER_LEVEL = SynchedEntityData.defineId(Impaler.class, EntityDataSerializers.INT);
    private AngerManagement angerManagement = new AngerManagement(this::canTargetEntity, Collections.emptyList());
    private int tendrilAnimationLength;
    public AnimationState tendrilAnimationState = new AnimationState();
    public AnimationState attackAnimationState = new AnimationState();
    public AnimationState roarAnimationState = new AnimationState();
    public AnimationState sniffAnimationState = new AnimationState();
    public AnimationState sonicChargeAnimationState = new AnimationState();
    private final DynamicGameEventListener<VibrationListener> dynamicGameEventListener;


    public Impaler(EntityType<? extends WaterMonster> entity, Level level) {
        super(entity, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 1F, 0F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 20);
        this.dynamicGameEventListener = new DynamicGameEventListener<>(new VibrationListener(new EntityPositionSource(this, this.getEyeHeight()), 16, this, (VibrationListener.ReceivingEvent)null, 0.0F, 0));
    }
    
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 400.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.3F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 1.5D)
                .add(Attributes.ATTACK_DAMAGE, 30.0D)
                .add(Attributes.FOLLOW_RANGE, 15D);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CLIENT_ANGER_LEVEL, 0);
    }
    public int getClientAngerLevel() {
        return this.entityData.get(CLIENT_ANGER_LEVEL);
    }
    
    private void syncClientAngerLevel() {
        this.entityData.set(CLIENT_ANGER_LEVEL, this.getActiveAnger());
    }

    public void aiStep() {
        if (!this.isInWater() && this.onGround && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F, 0.4F, (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F));
            this.onGround = false;
            this.hasImpulse = true;

            //this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
        }

        super.aiStep();
    }

    protected void customServerAiStep() {
        ServerLevel serverlevel = (ServerLevel) this.level;
        serverlevel.getProfiler().push("impalerBrain");
        this.getBrain().tick((ServerLevel)this.level, this);
        this.level.getProfiler().pop();
        super.customServerAiStep();

        if (this.tickCount % 20 == 0) {
            this.angerManagement.tick(serverlevel, this::canTargetEntity);
            this.syncClientAngerLevel();
        }
        
        ImpalerAi.updateActivity(this);
    }

    public void tick() {
        Level level = this.level;
        if (level instanceof ServerLevel serverlevel) {
            this.dynamicGameEventListener.getListener().tick(serverlevel);
        }

        super.tick();
        if (this.level.isClientSide()) {

            if (this.tendrilAnimationLength > 0) {
                this.tendrilAnimationState.start(this.tendrilAnimationLength);
                this.tendrilAnimationLength -= 1F;
            } else {
                this.tendrilAnimationState.stop();
            }

        } else {
            if (!this.isInWaterOrBubble()) {
                this.setDeltaMovement(0D, 0D, 0D);
            }
            if (this.getBrain().hasMemoryValue(MemoryModuleType.ROAR_TARGET) || this.getBrain().hasMemoryValue(MemoryModuleType.ATTACK_TARGET)) {
                if (this.getTarget() instanceof Player){
                    applyDarkness(this.getTarget());
                }
            }
        }
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("anger")) {
            AngerManagement.codec(this::canTargetEntity).parse(new Dynamic<>(NbtOps.INSTANCE, tag.get("anger"))).resultOrPartial(LOGGER::error).ifPresent((p_219394_) -> {
                this.angerManagement = p_219394_;
            });
            this.syncClientAngerLevel();
        }

        if (tag.contains("listener", 10)) {
            VibrationListener.codec(this).parse(new Dynamic<>(NbtOps.INSTANCE, tag.getCompound("listener"))).resultOrPartial(LOGGER::error).ifPresent((p_219408_) -> {
                this.dynamicGameEventListener.updateListener(p_219408_, this.level);
            });
        }

    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (DATA_POSE.equals(pKey)) {
            switch (this.getPose()) {
                case ROARING:
                    this.roarAnimationState.start(this.tickCount);
                    break;
                case SNIFFING:
                    this.sniffAnimationState.start(this.tickCount);
            }
        }

        super.onSyncedDataUpdated(pKey);
    }
    
    @Override
    protected Brain.Provider<Impaler> brainProvider() {
        return Brain.provider(ImpalerAi.MEMORY_TYPES, ImpalerAi.SENSOR_TYPES);
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return ImpalerAi.makeBrain(this, this.brainProvider().makeBrain(dynamic));
    }

    public Brain<Impaler> getBrain() {
        return (Brain<Impaler>)super.getBrain();
    }

    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }

    public void updateDynamicGameEventListener(BiConsumer<DynamicGameEventListener<?>, ServerLevel> pListenerConsumer) {
        Level level = this.level;
        if (level instanceof ServerLevel serverlevel) {
            pListenerConsumer.accept(this.dynamicGameEventListener, serverlevel);
        }

    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 4) {
            this.roarAnimationState.stop();
            this.attackAnimationState.start(this.tickCount);
        } else if (pId == 61) {
            this.tendrilAnimationState.start(this.tickCount);
            this.tendrilAnimationLength = 22;
        }else if (pId == 62) {
            this.sonicChargeAnimationState.start(this.tickCount);
        } else {
            super.handleEntityEvent(pId);
        }
    }

    public static void applyDarkness(@Nullable Entity pSource) {
        MobEffectInstance mobeffectinstance = new MobEffectInstance(MobEffects.DARKNESS, 260, 0, false, false);
        if (pSource instanceof Player) {
            ((Player) pSource).addEffect(mobeffectinstance);
        }
    }

    public boolean canTriggerAvoidVibration() {
        return true;
    }
    
    public boolean canTargetEntity(Entity entity) {
        if (entity instanceof LivingEntity livingentity) {
            return this.level == entity.level && EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(entity) && !this.isAlliedTo(entity) && livingentity.getType() != EntityType.ARMOR_STAND && livingentity.getType() != ModEntityTypes.IMPALER.get() && !livingentity.isInvulnerable() && !livingentity.isDeadOrDying() && this.level.getWorldBorder().isWithinBounds(livingentity.getBoundingBox()) && entity.isInWaterOrBubble();
        }

        return false;
    }

    public AngerLevel getAngerLevel() {
        return AngerLevel.byAnger(this.getActiveAnger());
    }

    private int getActiveAnger() {
        return this.angerManagement.getActiveAnger(this.getTarget());
    }

    public void clearAnger(Entity pEntity) {
        this.angerManagement.clearAnger(pEntity);
    }

    public void increaseAngerAt(@Nullable Entity pEntity) {
        this.increaseAngerAt(pEntity, 35, true);
    }
    
    public void increaseAngerAt(Entity entity, int amount, boolean listening) {
        if (!this.isNoAi() && this.canTargetEntity(entity)) {
            WardenAi.setDigCooldown(this);
            boolean flag = !(this.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).orElse((LivingEntity)null) instanceof Player);
            int i = this.angerManagement.increaseAnger(entity, amount);
            if (entity instanceof Player && flag && AngerLevel.byAnger(i).isAngry()) {
                this.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);
            }

            if (listening) {
                //this.playListeningSound();
            }
        }

    }

    public Optional<LivingEntity> getEntityAngryAt() {
        return this.getAngerLevel().isAngry() ? this.angerManagement.getActiveEntity() : Optional.empty();
    }

    @Nullable
    public LivingEntity getTarget() {
        return this.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).orElse((LivingEntity)null);
    }

    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return false;
    }

    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        boolean flag = super.hurt(pSource, pAmount);
        if (!this.level.isClientSide && !this.isNoAi()) {
            Entity entity = pSource.getEntity();
            this.increaseAngerAt(entity, AngerLevel.ANGRY.getMinimumAnger() + 20, false);
            if (this.brain.getMemory(MemoryModuleType.ATTACK_TARGET).isEmpty() && entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity;
                if (!(pSource instanceof IndirectEntityDamageSource) || this.closerThan(livingentity, 5.0D)) {
                    this.setAttackTarget(livingentity);
                }
            }
        }

        return flag;
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        applyDarkness(pEntity);
        return super.doHurtTarget(pEntity);
    }

    public void setAttackTarget(LivingEntity pAttackTarget) {
        this.getBrain().eraseMemory(MemoryModuleType.ROAR_TARGET);
        StartAttacking.setAttackTarget(this, pAttackTarget);
        SonicBoom.setCooldown(this, 200);
    }

    protected void doPush(Entity pEntity) {
        if (!this.isNoAi() && !this.getBrain().hasMemoryValue(MemoryModuleType.TOUCH_COOLDOWN)) {
            this.getBrain().setMemoryWithExpiry(MemoryModuleType.TOUCH_COOLDOWN, Unit.INSTANCE, 20L);
            this.increaseAngerAt(pEntity);
            ImpalerAi.setDisturbanceLocation(this, pEntity.blockPosition());
        }

        super.doPush(pEntity);
    }

    public boolean shouldListen(ServerLevel pLevel, GameEventListener pListener, BlockPos pPos, GameEvent pGameEvent, GameEvent.Context pContext) {
        if (!this.isNoAi() && !this.isDeadOrDying() && !this.getBrain().hasMemoryValue(MemoryModuleType.VIBRATION_COOLDOWN) && pLevel.getWorldBorder().isWithinBounds(pPos) && !this.isRemoved() && this.level == pLevel) {
            Entity entity = pContext.sourceEntity();
            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity;
                if (!this.canTargetEntity(livingentity)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public void onSignalReceive(ServerLevel pLevel, GameEventListener pListener, BlockPos pSourcePos, GameEvent pGameEvent, @Nullable Entity pSourceEntity, @Nullable Entity pProjectileOwner, float pDistance) {
        if (!this.isDeadOrDying()) {
            this.brain.setMemoryWithExpiry(MemoryModuleType.VIBRATION_COOLDOWN, Unit.INSTANCE, 40L);
            pLevel.broadcastEntityEvent(this, (byte)61);
            this.playSound(SoundEvents.WARDEN_TENDRIL_CLICKS, 5.0F, this.getVoicePitch());
            BlockPos blockpos = pSourcePos;
            if (pProjectileOwner != null) {
                if (this.closerThan(pProjectileOwner, 30.0D)) {
                    if (this.getBrain().hasMemoryValue(MemoryModuleType.RECENT_PROJECTILE)) {
                        if (this.canTargetEntity(pProjectileOwner)) {
                            blockpos = pProjectileOwner.blockPosition();
                        }

                        this.increaseAngerAt(pProjectileOwner);
                    } else {
                        this.increaseAngerAt(pProjectileOwner, 10, true);
                    }
                }

                this.getBrain().setMemoryWithExpiry(MemoryModuleType.RECENT_PROJECTILE, Unit.INSTANCE, 100L);
            } else {
                this.increaseAngerAt(pSourceEntity);
            }

            if (!this.getAngerLevel().isAngry()) {
                Optional<LivingEntity> optional = this.angerManagement.getActiveEntity();
                if (pProjectileOwner != null || optional.isEmpty() || optional.get() == pSourceEntity) {
                    ImpalerAi.setDisturbanceLocation(this, blockpos);
                }
            }
        }
    }

    public int getMaxHeadXRot() {
        return 1;
    }

    public int getMaxHeadYRot() {
        return 10;
    }

    public AngerManagement getAngerManagement() {
        return this.angerManagement;
    }

    protected PathNavigation createNavigation(Level level) {
        return new WaterBoundPathNavigation(this, level);
    }


}
