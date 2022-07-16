package com.ricy40.caerula.entity.custom.impaler;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Dynamic;
import com.ricy40.caerula.entity.custom.WaterMonster;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
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
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.Optional;

public class Impaler extends WaterMonster implements VibrationListener.VibrationListenerConfig {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int ANGERMANAGEMENT_TICK_DELAY = 20;
    private static final int DEFAULT_ANGER = 35;
    private static final int PROJECTILE_ANGER = 10;
    private static final int ON_HURT_ANGER_BOOST = 20;
    private static final EntityDataAccessor<Integer> CLIENT_ANGER_LEVEL = SynchedEntityData.defineId(Impaler.class, EntityDataSerializers.INT);
    private AngerManagement angerManagement = new AngerManagement(this::canTargetEntity, Collections.emptyList());
    private int tendrilAnimationLength;
    public AnimationState tendrilAnimationState = new AnimationState();
    private final DynamicGameEventListener<VibrationListener> dynamicGameEventListener;


    public Impaler(EntityType<? extends WaterMonster> entity, Level level) {
        super(entity, level);
        this.dynamicGameEventListener = new DynamicGameEventListener<>(new VibrationListener(new EntityPositionSource(this, this.getEyeHeight()), 16, this, (VibrationListener.ReceivingEvent)null, 0.0F, 0));

    }
    
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 400.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.3F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 1.5D)
                .add(Attributes.ATTACK_DAMAGE, 30.0D);
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
        super.tick();
        if (this.level.isClientSide()) {

            if (this.tendrilAnimationLength > 0) {
                --this.tendrilAnimationLength;
            } else {
                this.tendrilAnimationState.stop();
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
    
    @Override
    protected Brain.Provider<Impaler> brainProvider() {
        return Brain.provider(ImpalerAi.MEMORY_TYPES, ImpalerAi.SENSOR_TYPES);
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return ImpalerAi.makeBrain(this.brainProvider().makeBrain(dynamic));
    }

    public Brain<Impaler> getBrain() {
        return (Brain<Impaler>)super.getBrain();
    }

    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 61) {
            this.tendrilAnimationState.start(this.tickCount);
            this.tendrilAnimationLength = 22;
        } else {
            super.handleEntityEvent(pId);
        }
    }

    public boolean isPushable() {
        return super.isPushable();
    }

    protected void doPush(Entity entity) {
        if (!this.isNoAi() && !this.getBrain().hasMemoryValue(MemoryModuleType.TOUCH_COOLDOWN)) {
            this.getBrain().setMemoryWithExpiry(MemoryModuleType.TOUCH_COOLDOWN, Unit.INSTANCE, 20L);
            this.increaseAngerAt(entity);
            ImpalerAi.setDisturbanceLocation(this, entity.blockPosition());
        }

        super.doPush(entity);
    }
    
    public boolean shouldListen(ServerLevel level, GameEventListener listener, BlockPos pos, GameEvent event, GameEvent.Context context) {
        if (!this.isNoAi() && !this.isDeadOrDying() && !this.getBrain().hasMemoryValue(MemoryModuleType.VIBRATION_COOLDOWN) && level.getWorldBorder().isWithinBounds(pos) && !this.isRemoved() && this.level == level) {
            Entity entity = context.sourceEntity();
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

    @Override
    public void onSignalReceive(ServerLevel level, GameEventListener listener, BlockPos pos, GameEvent event, @Nullable Entity entity, @Nullable Entity noiseMaker, float p_223871_) {

    }

    public boolean canTriggerAvoidVibration() {
        return true;
    }

    @Contract("null->false")
    public boolean canTargetEntity(@Nullable Entity entity) {
        if (entity instanceof LivingEntity livingentity) {
            return this.level == entity.level && EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(entity) && !this.isAlliedTo(entity) && livingentity.getType() != EntityType.ARMOR_STAND && livingentity.getType() != EntityType.WARDEN && !livingentity.isInvulnerable() && !livingentity.isDeadOrDying() && this.level.getWorldBorder().isWithinBounds(livingentity.getBoundingBox());
        }

        return false;
    }

    public AngerLevel getAngerLevel() {
        return AngerLevel.byAnger(this.getActiveAnger());
    }

    private int getActiveAnger() {
        return this.angerManagement.getActiveAnger(this.getTarget());
    }

    public void clearAnger(Entity entity) {
        this.angerManagement.clearAnger(entity);
    }

    public void increaseAngerAt(@Nullable Entity entity) {
        this.increaseAngerAt(entity, 35, true);
    }

    @VisibleForTesting
    public void increaseAngerAt(@Nullable Entity entity, int amount, boolean listening) {
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

    @VisibleForTesting
    public AngerManagement getAngerManagement() {
        return this.angerManagement;
    }

    protected PathNavigation createNavigation(Level level) {
        return new GroundPathNavigation(this, level) {
            protected PathFinder createPathFinder(int distance) {
                this.nodeEvaluator = new WalkNodeEvaluator();
                this.nodeEvaluator.setCanPassDoors(true);
                return new PathFinder(this.nodeEvaluator, distance) {
                    protected float distance(Node node1, Node node2) {
                        return node1.distanceToXZ(node2);
                    }
                };
            }
        };
    }
}
