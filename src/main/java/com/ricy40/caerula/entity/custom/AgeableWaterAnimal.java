package com.ricy40.caerula.entity.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public abstract class AgeableWaterAnimal extends WaterAnimal {

    private static final EntityDataAccessor<Boolean> DATA_BABY_ID = SynchedEntityData.defineId(AgeableWaterAnimal.class, EntityDataSerializers.BOOLEAN);
    public static final int BABY_START_AGE = -24000;
    private static final int FORCED_AGE_PARTICLE_TICKS = 40;
    protected int age;
    protected int ageAge;
    protected int ageAgeTimer;
    
    protected AgeableWaterAnimal(EntityType<? extends WaterAnimal> entity, Level level) {
        super(entity, level);
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag tag) {
        if (spawnGroupData == null) {
            spawnGroupData = new AgeableWaterAnimal.AgeableWaterAnimalGroupData(true);
        }

        AgeableWaterAnimal.AgeableWaterAnimalGroupData ageablemob$ageablemobgroupdata = (AgeableWaterAnimal.AgeableWaterAnimalGroupData)spawnGroupData;
        if (ageablemob$ageablemobgroupdata.isShouldSpawnBaby() && ageablemob$ageablemobgroupdata.getGroupSize() > 0 && serverLevelAccessor.getRandom().nextFloat() <= ageablemob$ageablemobgroupdata.getBabySpawnChance()) {
            this.setAge(-24000);
        }

        ageablemob$ageablemobgroupdata.increaseGroupSizeByOne();
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, spawnType, spawnGroupData, tag);
    }

    @Nullable
    public abstract AgeableWaterAnimal getBreedOffspring(ServerLevel level, AgeableWaterAnimal mob);

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BABY_ID, false);
    }

    public boolean canBreed() {
        return false;
    }

    public int getAge() {
        if (this.level.isClientSide) {
            return this.entityData.get(DATA_BABY_ID) ? -1 : 1;
        } else {
            return this.age;
        }
    }

    public void ageUp(int age, boolean forced) {
        int i = this.getAge();
        i += age * 20;
        if (i > 0) {
            i = 0;
        }

        int j = i - i;
        this.setAge(i);
        if (forced) {
            this.ageAge += j;
            if (this.ageAgeTimer == 0) {
                this.ageAgeTimer = 40;
            }
        }

        if (this.getAge() == 0) {
            this.setAge(this.ageAge);
        }

    }

    public void ageUp(int age) {
        this.ageUp(age, false);
    }

    public void setAge(int age) {
        int i = this.getAge();
        this.age = age;
        if (i < 0 && age >= 0 || i >= 0 && age < 0) {
            this.entityData.set(DATA_BABY_ID, age < 0);
            this.ageBoundaryReached();
        }

    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Age", this.getAge());
        tag.putInt("ForcedAge", this.ageAge);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setAge(tag.getInt("Age"));
        this.ageAge = tag.getInt("ForcedAge");
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        if (DATA_BABY_ID.equals(accessor)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(accessor);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void aiStep() {
        super.aiStep();
        if (this.level.isClientSide) {
            if (this.ageAgeTimer > 0) {
                if (this.ageAgeTimer % 4 == 0) {
                    this.level.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D);
                }

                --this.ageAgeTimer;
            }
        } else if (this.isAlive()) {
            int i = this.getAge();
            if (i < 0) {
                ++i;
                this.setAge(i);
            } else if (i > 0) {
                --i;
                this.setAge(i);
            }
        }

    }

    protected void ageBoundaryReached() {
    }
    
    public boolean isBaby() {
        return this.getAge() < 0;
    }
    
    public void setBaby(boolean p_146756_) {
        this.setAge(p_146756_ ? -24000 : 0);
    }

    public static int getSpeedUpSecondsWhenFeeding(int p_216968_) {
        return (int)((float)(p_216968_ / 20) * 0.1F);
    }

    public static class AgeableWaterAnimalGroupData implements SpawnGroupData {
        private int groupSize;
        private final boolean shouldSpawnBaby;
        private final float babySpawnChance;

        private AgeableWaterAnimalGroupData(boolean p_146775_, float p_146776_) {
            this.shouldSpawnBaby = p_146775_;
            this.babySpawnChance = p_146776_;
        }

        public AgeableWaterAnimalGroupData(boolean p_146773_) {
            this(p_146773_, 0.05F);
        }

        public AgeableWaterAnimalGroupData(float p_146771_) {
            this(true, p_146771_);
        }

        public int getGroupSize() {
            return this.groupSize;
        }

        public void increaseGroupSizeByOne() {
            ++this.groupSize;
        }

        public boolean isShouldSpawnBaby() {
            return this.shouldSpawnBaby;
        }

        public float getBabySpawnChance() {
            return this.babySpawnChance;
        }
    }
}
