package com.ricy40.caerula.entity.custom.behaviours.impaler;

import com.google.common.collect.ImmutableMap;
import com.ricy40.caerula.entity.custom.impaler.Impaler;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class SonicCharge extends Behavior<Impaler> {

    private static final int DISTANCE_XZ = 15;
    private static final int DISTANCE_Y = 15;
    private static final double KNOCKBACK_VERTICAL = 0.5D;
    private static final double KNOCKBACK_HORIZONTAL = 2.5D;
    public static final int COOLDOWN = 5;
    private static final int TICKS_BEFORE_PLAYING_SOUND = Mth.ceil(34.0D);
    private static final int DURATION = Mth.ceil(60.0F);
    
    public SonicCharge() {
        super(ImmutableMap.of(
                MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT, 
                MemoryModuleType.SONIC_BOOM_COOLDOWN, MemoryStatus.VALUE_ABSENT, 
                MemoryModuleType.SONIC_BOOM_SOUND_COOLDOWN, MemoryStatus.REGISTERED, 
                MemoryModuleType.SONIC_BOOM_SOUND_DELAY, MemoryStatus.REGISTERED
        ), DURATION);
    }

    protected boolean checkExtraStartConditions(ServerLevel level, Impaler impaler) {
        return impaler.closerThan(impaler.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).get(), DISTANCE_XZ, DISTANCE_Y);
    }

    protected boolean canStillUse(ServerLevel level, Impaler impaler, long pGameTime) {
        return true;
    }
    
    protected void start(ServerLevel level, Impaler impaler, long pGameTime) {
        impaler.LOGGER.debug("Impaler now charging");
        impaler.getBrain().setMemoryWithExpiry(MemoryModuleType.ATTACK_COOLING_DOWN, true, (long)DURATION);
        impaler.getBrain().setMemoryWithExpiry(MemoryModuleType.SONIC_BOOM_SOUND_DELAY, Unit.INSTANCE, (long)TICKS_BEFORE_PLAYING_SOUND);
        level.broadcastEntityEvent(impaler, (byte)62);
        impaler.playSound(SoundEvents.WARDEN_SONIC_CHARGE, 3.0F, 1.0F);

        impaler.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).ifPresent((target) -> {
            impaler.getLookControl().setLookAt(target.position());
        });
    }

    protected void tick(ServerLevel level, Impaler impaler, long pGameTime) {
        
        if (!impaler.getBrain().hasMemoryValue(MemoryModuleType.SONIC_BOOM_SOUND_DELAY) && !impaler.getBrain().hasMemoryValue(MemoryModuleType.SONIC_BOOM_SOUND_COOLDOWN)) {
            impaler.getBrain().setMemoryWithExpiry(MemoryModuleType.SONIC_BOOM_SOUND_COOLDOWN, Unit.INSTANCE, (long)(DURATION - TICKS_BEFORE_PLAYING_SOUND));
            impaler.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).filter(impaler::canTargetEntity).filter((target) -> {
                return impaler.closerThan(target, DISTANCE_XZ, DISTANCE_Y);
            }).ifPresent((target) -> {
                Vec3 vec3 = impaler.position().add(0.0D, (double) 0.4F, 0.0D);
                Vec3 vec31 = target.getEyePosition().subtract(vec3);
                Vec3 vec32 = vec31.normalize();

                impaler.setDeltaMovement(vec32.scale(1D));
                for (int i = 1; i < Mth.floor(vec31.length()) + 7; ++i) {
                    Vec3 vec33 = vec3.add(vec32.scale((double) i));
                    level.sendParticles(ParticleTypes.SONIC_BOOM, vec33.x, vec33.y, vec33.z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
                }

                impaler.playSound(SoundEvents.WARDEN_SONIC_BOOM, 3.0F, 1.0F);
                if (impaler.isColliding(target.getOnPos(), Blocks.WATER.defaultBlockState())) {
                    target.hurt(DamageSource.sonicBoom(impaler), 7.0F);
                    double d1 = KNOCKBACK_VERTICAL * (1.0D - target.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                    double d0 = KNOCKBACK_HORIZONTAL * (1.0D - target.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                    target.push(vec32.x() * d0, vec32.y() * d1, vec32.z() * d0);
                }
            });
        }
    }
    
    protected void stop(ServerLevel level, Impaler impaler, long pGameTime) {
        setCooldown(impaler, COOLDOWN);
    }

    public static void setCooldown(LivingEntity impaler, int pCooldown) {
        impaler.getBrain().setMemoryWithExpiry(MemoryModuleType.SONIC_BOOM_COOLDOWN, Unit.INSTANCE, (long)pCooldown);
    }
}
