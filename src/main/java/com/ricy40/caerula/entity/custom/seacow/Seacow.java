package com.ricy40.caerula.entity.custom.seacow;

import com.mojang.math.Vector3f;
import com.mojang.serialization.Dynamic;
import com.ricy40.caerula.entity.ModEntityTypes;
import com.ricy40.caerula.entity.custom.AgeableWaterAnimal;
import com.ricy40.caerula.item.ModItems;
import com.ricy40.caerula.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.axolotl.AxolotlAi;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.monster.warden.WardenAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class Seacow extends AgeableWaterAnimal {

    public AnimationState sniffleAnimationState = new AnimationState();
    public AnimationState eatingAnimationState = new AnimationState();
    public final Predicate<BlockState> VALID_EATDILE_BLOCKS = (block) -> {
        if (!block.hasProperty(BlockStateProperties.WATERLOGGED) && !block.getValue(BlockStateProperties.WATERLOGGED)) {
            return false;
        } else if (block.is(ModTags.Blocks.SEACOW_EDIBLES)) {
            return true;
        } else {
            return false;
        }
    };

    public Seacow(EntityType<? extends AgeableWaterAnimal> type, Level worldIn) {
        super(type, worldIn);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.0D);
    }

    @Override
    public void tick() {
        super.tick();
    }

    public void aiStep() {
        if (!this.isInWater() && this.onGround && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), (double)0.4F, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
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
    public void travel(Vec3 vector) {
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

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
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

    protected boolean canRandomSwim() {
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
    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return pSize.height * 0.6F;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SALMON_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SALMON_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
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

}


