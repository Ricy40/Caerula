package com.ricy40.caerula.entity.custom.seacow;

import com.mojang.serialization.Dynamic;
import com.ricy40.caerula.entity.ModEntityTypes;
import com.ricy40.caerula.entity.custom.AgeableWaterAnimal;
import com.ricy40.caerula.item.ModItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.monster.warden.WardenAi;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class Seacow extends AgeableWaterAnimal {

    public AnimationState sniffleAnimationState = new AnimationState();
    private int coolDownTicks;


    public Seacow(EntityType<? extends AbstractFish> type, Level worldIn) {
        super(type, worldIn);
        this.coolDownTicks = 0;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D);
    }

    @Override
    public void tick() {
        super.tick();

        double rand = this.random.nextFloat();

        if (this.coolDownTicks != 0 ) {
            this.coolDownTicks--;
        }

        if (rand > 0.99f && this.coolDownTicks == 0) {
            this.setPose(Pose.SNIFFING);
            this.coolDownTicks = 80;
        } else if (this.getPose() == Pose.SNIFFING) {
            this.setPose(Pose.SWIMMING);
        }
    }

    @Override
    public AgeableWaterAnimal getBreedOffspring(ServerLevel level, AgeableWaterAnimal mob) {
        return ModEntityTypes.SEACOW.get().create(level);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        if (DATA_POSE.equals(accessor)) {
            switch (this.getPose()) {
                case SNIFFING:
                    this.sniffleAnimationState.start(this.tickCount);
            }
        }

        super.onSyncedDataUpdated(accessor);
    }

    protected Brain<?> makeBrain(Dynamic<?> p_219406_) {
        return SeacowAi.makeBrain(this, p_219406_);
    }

    public Brain<Seacow> getBrain() {
        return (Brain<Seacow>)super.getBrain();
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

}

