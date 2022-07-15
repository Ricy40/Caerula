package com.ricy40.caerula.entity.custom.seacow;

import com.mojang.serialization.Dynamic;
import com.ricy40.caerula.entity.ModEntityTypes;
import com.ricy40.caerula.entity.custom.AgeableWaterAnimal;
import com.ricy40.caerula.tags.ModTags;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class Seacow extends AgeableWaterAnimal {

    private int hungerTimer;
    private boolean isHungry;
    private static final int HUNGER_COOLDOWN = 200;
    public AnimationState sniffleAnimationState = new AnimationState();
    public AnimationState eatingAnimationState = new AnimationState();
    public final Predicate<BlockState> VALID_EDIBLE_BLOCKS = (block) -> {
        if (block.is(Blocks.WATER)) {
            return false;
        } else if (block.hasProperty(BlockStateProperties.WATERLOGGED)) {
            if (block.getValue(BlockStateProperties.WATERLOGGED)) {
                return block.is(ModTags.Blocks.SEACOW_EDIBLES);
            }
        }
        return false;
    };

    public Seacow(EntityType<? extends AgeableWaterAnimal> type, Level worldIn) {
        super(type, worldIn);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.hungerTimer = 0;
        this.isHungry = false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.hungerTimer > HUNGER_COOLDOWN) {
            this.isHungry = true;
        } else {
            hungerTimer++;
        }
    }

    public void aiStep() {
        if (!this.isInWater() && this.onGround && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F, 0.4F, (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F));
            this.onGround = false;
            this.hasImpulse = true;
            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
        }

        super.aiStep();
    }

    protected void customServerAiStep() {
        this.level.getProfiler().push("seacowBrain");
        this.getBrain().tick((ServerLevel)this.level, this);
        this.level.getProfiler().pop();
        this.level.getProfiler().push("seacowActivityUpdate");
        SeacowAi.updateActivity(this);
        this.level.getProfiler().pop();
    }

    @Override
    public void travel(@NotNull Vec3 vector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.01F, vector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(vector);
        }
    }

    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> accessor) {
        if (DATA_POSE.equals(accessor)) {
            if (this.getPose() == Pose.SNIFFING) {
                this.sniffleAnimationState.start(this.tickCount);
            } else {
                this.sniffleAnimationState.stop();
            } if (this.getPose() == Pose.USING_TONGUE) {
                this.eatingAnimationState.start((this.tickCount));
            } else {
                this.eatingAnimationState.stop();
            }
        }

        super.onSyncedDataUpdated(accessor);
    }

    public boolean canBeLeashed(Player player) {
        return true;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return ModEntityTypes.SEACOW.get().create(level);
    }

    @Override
    protected Brain.Provider<Seacow> brainProvider() {
        return Brain.provider(SeacowAi.MEMORY_TYPES, SeacowAi.SENSOR_TYPES);
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return SeacowAi.makeBrain(this.brainProvider().makeBrain(dynamic));
    }

    public Brain<Seacow> getBrain() {
        return (Brain<Seacow>)super.getBrain();
    }

    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }

    public boolean isFood(ItemStack item) {
        return item.is(ModTags.Items.SEACOW_TEMPT_ITEMS);
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    protected float getStandingEyeHeight(@NotNull Pose pPose, EntityDimensions pSize) {
        return pSize.height * 0.6F;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SALMON_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SALMON_DEATH;
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return SoundEvents.SALMON_HURT;
    }

    @Override
    public int getMaxHeadXRot() {
        return 30;
    }

    @Override
    public int getMaxHeadYRot() {
        return 40;
    }

    public boolean isHungry() {
        return isHungry;
    }

    public void eat() {
        this.hungerTimer = 0;
        this.isHungry = false;
    }
}


