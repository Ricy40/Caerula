package com.ricy40.caerula.entity.custom.impaler;

import com.ricy40.caerula.entity.custom.WaterMonster;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.vibrations.VibrationListener;
import org.jetbrains.annotations.Nullable;

public class Impaler extends WaterMonster implements VibrationListener.VibrationListenerConfig {

    public AnimationState tendrilAnimationState = new AnimationState();
    private int tendrilAnimationLength;
    private int stingerAnimationO;
    private int stingerAnimation;

    protected Impaler(EntityType<? extends WaterMonster> entity, Level level) {
        super(entity, level);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level.isClientSide()) {
            if (this.tickCount % 15 == 0) {
                this.stingerAnimation = 10;
                if (!this.isSilent()) {
                    this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.WARDEN_HEARTBEAT, this.getSoundSource(), 5.0F, this.getVoicePitch(), false);
                }
            }

            if (this.tendrilAnimationLength > 0) {
                --this.tendrilAnimationLength;
            } else {
                this.tendrilAnimationState.stop();
            }

            this.stingerAnimationO = this.stingerAnimation;
            if (this.stingerAnimation > 0) {
                --this.stingerAnimation;
            }
        }
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

    @Override
    public boolean shouldListen(ServerLevel level, GameEventListener listener, BlockPos pos, GameEvent event, GameEvent.Context context) {
        return true;
    }

    @Override
    public void onSignalReceive(ServerLevel level, GameEventListener listener, BlockPos pos, GameEvent event, @Nullable Entity entity, @Nullable Entity noiseMaker, float p_223871_) {

    }

    public float getStingerAnimation(float partialTicks) {
        return Mth.lerp(partialTicks, (float)this.stingerAnimationO, (float)this.stingerAnimation) / 10.0F;
    }
}
