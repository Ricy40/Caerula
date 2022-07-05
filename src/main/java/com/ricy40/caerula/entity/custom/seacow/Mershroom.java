package com.ricy40.caerula.entity.custom.seacow;

import com.ricy40.caerula.block.ModBlocks;
import com.ricy40.caerula.entity.ModEntityTypes;
import com.ricy40.caerula.entity.custom.AgeableWaterAnimal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.IForgeShearable;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

public class Mershroom extends Seacow implements Shearable, IForgeShearable {

    private static final EntityDataAccessor<String> DATA_TYPE = SynchedEntityData.defineId(Mershroom.class, EntityDataSerializers.STRING);
    private static final int MUTATE_CHANCE = 1024;
    @Nullable
    private MobEffect effect;
    private int effectDuration;
    @Nullable
    private UUID lastLightningBoltUUID;
    
    public Mershroom(EntityType<? extends AgeableWaterAnimal> type, Level worldIn) {
        super(type, worldIn);
    }

    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return pLevel.getBlockState(pPos.below()).is(Blocks.MYCELIUM) ? 10.0F : pLevel.getPathfindingCostFromLightLevels(pPos);
    }

    public static boolean checkMushroomSpawnRules(EntityType<Mershroom> mershroom, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).is(BlockTags.MUSHROOM_GROW_BLOCK) && isBrightEnoughToSpawn(level, pos);
    }

    public void thunderHit(ServerLevel pLevel, LightningBolt pLightning) {
        UUID uuid = pLightning.getUUID();
        if (!uuid.equals(this.lastLightningBoltUUID)) {
            this.setMushroomType(this.getMushroomType() == MushroomType.PURPLE ? MushroomType.YELLOW : MushroomType.PURPLE);
            this.lastLightningBoltUUID = uuid;
            this.playSound(SoundEvents.MOOSHROOM_CONVERT, 2.0F, 1.0F);
        }

    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE, MushroomType.PURPLE.type);
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.is(Items.BOWL) && !this.isBaby()) {
            boolean flag = false;
            ItemStack itemstack1;
            if (this.effect != null) {
                flag = true;
                itemstack1 = new ItemStack(Items.SUSPICIOUS_STEW);
                SuspiciousStewItem.saveMobEffect(itemstack1, this.effect, this.effectDuration);
                this.effect = null;
                this.effectDuration = 0;
            } else {
                itemstack1 = new ItemStack(Items.MUSHROOM_STEW);
            }

            ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, pPlayer, itemstack1, false);
            pPlayer.setItemInHand(pHand, itemstack2);
            SoundEvent soundevent;
            if (flag) {
                soundevent = SoundEvents.MOOSHROOM_MILK_SUSPICIOUSLY;
            } else {
                soundevent = SoundEvents.MOOSHROOM_MILK;
            }

            this.playSound(soundevent, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else if (false && itemstack.getItem() == Items.SHEARS && this.readyForShearing()) { //Forge: Moved to onSheared
            this.shear(SoundSource.PLAYERS);
            this.gameEvent(GameEvent.SHEAR, pPlayer);
            if (!this.level.isClientSide) {
                itemstack.hurtAndBreak(1, pPlayer, (item) -> {
                    item.broadcastBreakEvent(pHand);
                });
            }

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else if (this.getMushroomType() == MushroomType.YELLOW && itemstack.is(ItemTags.SMALL_FLOWERS)) {
            if (this.effect != null) {
                for(int i = 0; i < 2; ++i) {
                    this.level.addParticle(ParticleTypes.SMOKE, this.getX() + this.random.nextDouble() / 2.0D, this.getY(0.5D), this.getZ() + this.random.nextDouble() / 2.0D, 0.0D, this.random.nextDouble() / 5.0D, 0.0D);
                }
            } else {
                Optional<Pair<MobEffect, Integer>> optional = this.getEffectFromItemStack(itemstack);
                if (!optional.isPresent()) {
                    return InteractionResult.PASS;
                }

                Pair<MobEffect, Integer> pair = optional.get();
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                for(int j = 0; j < 4; ++j) {
                    this.level.addParticle(ParticleTypes.EFFECT, this.getX() + this.random.nextDouble() / 2.0D, this.getY(0.5D), this.getZ() + this.random.nextDouble() / 2.0D, 0.0D, this.random.nextDouble() / 5.0D, 0.0D);
                }

                this.effect = pair.getLeft();
                this.effectDuration = pair.getRight();
                this.playSound(SoundEvents.MOOSHROOM_EAT, 2.0F, 1.0F);
            }

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }

    @Override
    public java.util.List<ItemStack> onSheared(@Nullable Player player, @NotNull ItemStack item, Level world, BlockPos pos, int fortune) {
        this.gameEvent(GameEvent.SHEAR, player);
        return shearInternal(player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS);
    }

    public void shear(SoundSource pCategory) {
        shearInternal(pCategory).forEach(s -> this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(1.0D), this.getZ(), s)));
    }

    private java.util.List<ItemStack> shearInternal(SoundSource pCategory) {
        this.level.playSound((Player)null, this, SoundEvents.MOOSHROOM_SHEAR, pCategory, 1.0F, 1.0F);
        if (!this.level.isClientSide()) {
            ((ServerLevel)this.level).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
            this.discard();
            Seacow seacow = ModEntityTypes.SEACOW.get().create(this.level);
            seacow.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
            seacow.setHealth(this.getHealth());
            seacow.yBodyRot = this.yBodyRot;
            if (this.hasCustomName()) {
                seacow.setCustomName(this.getCustomName());
                seacow.setCustomNameVisible(this.isCustomNameVisible());
            }

            if (this.isPersistenceRequired()) {
                seacow.setPersistenceRequired();
            }

            seacow.setInvulnerable(this.isInvulnerable());
            this.level.addFreshEntity(seacow);

            java.util.List<ItemStack> items = new java.util.ArrayList<>();
            for(int i = 0; i < 5; ++i) {
                items.add(new ItemStack(this.getMushroomType().blockState.getBlock()));
            }
            return items;
        }
        return Collections.emptyList();

    }

    public boolean readyForShearing() {
        return this.isAlive() && !this.isBaby();
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putString("Type", this.getMushroomType().type);
        if (this.effect != null) {
            pCompound.putInt("EffectId", MobEffect.getId(this.effect));
            net.minecraftforge.common.ForgeHooks.saveMobEffect(pCompound, "forge:effect_id", this.effect);
            pCompound.putInt("EffectDuration", this.effectDuration);
        }

    }
    
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setMushroomType(Mershroom.MushroomType.byType(pCompound.getString("Type")));
        if (pCompound.contains("EffectId", 1)) {
            this.effect = MobEffect.byId(pCompound.getInt("EffectId"));
            this.effect = net.minecraftforge.common.ForgeHooks.loadMobEffect(pCompound, "forge:effect_id", this.effect);
        }

        if (pCompound.contains("EffectDuration", 3)) {
            this.effectDuration = pCompound.getInt("EffectDuration");
        }

    }

    private Optional<Pair<MobEffect, Integer>> getEffectFromItemStack(ItemStack pStack) {
        Item item = pStack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem)item).getBlock();
            if (block instanceof FlowerBlock) {
                FlowerBlock flowerblock = (FlowerBlock)block;
                return Optional.of(Pair.of(flowerblock.getSuspiciousStewEffect(), flowerblock.getEffectDuration()));
            }
        }

        return Optional.empty();
    }

    private void setMushroomType(Mershroom.MushroomType pType) {
        this.entityData.set(DATA_TYPE, pType.type);
    }

    public Mershroom.MushroomType getMushroomType() {
        return Mershroom.MushroomType.byType(this.entityData.get(DATA_TYPE));
    }
    
    public Mershroom getBreedOffspring(ServerLevel level, AgeableMob mob) {
        Mershroom mershroom = ModEntityTypes.MERSHROOM.get().create(level);
        mershroom.setMushroomType(this.getOffspringType((Mershroom)mob));
        return mershroom;
    }

    private Mershroom.MushroomType getOffspringType(Mershroom pMate) {
        Mershroom.MushroomType mershroom$mushroomtype = this.getMushroomType();
        Mershroom.MushroomType mershroom$mushroomtype1 = pMate.getMushroomType();
        Mershroom.MushroomType mershroom$mushroomtype2;
        if (mershroom$mushroomtype == mershroom$mushroomtype1 && this.random.nextInt(1024) == 0) {
            mershroom$mushroomtype2 = mershroom$mushroomtype == MushroomType.YELLOW ? MushroomType.PURPLE : MushroomType.YELLOW;
        } else {
            mershroom$mushroomtype2 = this.random.nextBoolean() ? mershroom$mushroomtype : mershroom$mushroomtype1;
        }

        return mershroom$mushroomtype2;
    }

    
    public boolean isShearable(ItemStack item, Level world, BlockPos pos) {
        return readyForShearing();
    }

    public static enum MushroomType {
        PURPLE("purple", ModBlocks.PURPLE_SEASHROOM.get().defaultBlockState()),
        YELLOW("yellow", ModBlocks.RED_SEAGRASS.get().defaultBlockState());

        final String type;
        final BlockState blockState;

        private MushroomType(String pType, BlockState state) {
            this.type = pType;
            this.blockState = state;
        }

        public BlockState getBlockState() {
            return this.blockState;
        }

        static Mershroom.MushroomType byType(String pName) {
            for(Mershroom.MushroomType mershroom$mushroomtype : values()) {
                if (mershroom$mushroomtype.type.equals(pName)) {
                    return mershroom$mushroomtype;
                }
            }

            return PURPLE;
        }
    }
}
