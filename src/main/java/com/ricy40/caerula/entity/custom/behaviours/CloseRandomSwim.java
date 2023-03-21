package com.ricy40.caerula.entity.custom.behaviours;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;

public class CloseRandomSwim extends Behavior<PathfinderMob> {
    private static final int MAX_XZ_DIST = 10;
    private static final int MAX_Y_DIST = 7;
    public static final int[][] XY_DISTANCE_TIERS = new int[][]{{1, 1}, {3, 3}, {5, 5}, {6, 5}, {7, 7}, {10, 7}};
    private final float speedModifier;
    protected final int maxHorizontalDistance;
    protected final int maxVerticalDistance;
    private final boolean mayStrollFromWater;
    private final int closeEnough;

    public CloseRandomSwim(float pSpeedModifier, int closeEnough) {
        this(pSpeedModifier, true, closeEnough);
    }

    public CloseRandomSwim(float pSpeedModifier, boolean pMayStrollFromWater, int closeEnough) {
        this(pSpeedModifier, 10, 7, pMayStrollFromWater, closeEnough);
    }

    public CloseRandomSwim(float pSpeedModifier, int pMaxHorizontalDistance, int pMaxVerticalDistance, int closeEnough) {
        this(pSpeedModifier, pMaxHorizontalDistance, pMaxVerticalDistance, true, closeEnough);
    }

    public CloseRandomSwim(float pSpeedModifier, int pMaxHorizontalDistance, int pMaxVerticalDistance, boolean pMayStrollFromWater, int closeEnough) {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT));
        this.speedModifier = pSpeedModifier;
        this.maxHorizontalDistance = pMaxHorizontalDistance;
        this.maxVerticalDistance = pMaxVerticalDistance;
        this.mayStrollFromWater = pMayStrollFromWater;
        this.closeEnough = closeEnough;
    }

    protected boolean checkExtraStartConditions(ServerLevel pLevel, PathfinderMob pOwner) {
        return pOwner.isInWaterOrBubble();
    }

    protected void start(ServerLevel pLevel, PathfinderMob pEntity, long pGameTime) {
        Optional<Vec3> optional = Optional.ofNullable(this.getTargetPos(pEntity));
        pEntity.getBrain().setMemory(MemoryModuleType.WALK_TARGET, optional.map((target) -> {
            return new WalkTarget(target, this.speedModifier, this.closeEnough);
        }));
    }

    @Nullable
    protected Vec3 getTargetPos(PathfinderMob p_147861_) {
        Vec3 vec3 = null;
        Vec3 vec31 = null;

        for(int[] aint : XY_DISTANCE_TIERS) {
            if (vec3 == null) {
                vec31 = BehaviorUtils.getRandomSwimmablePos(p_147861_, aint[0], aint[1]);
            } else {
                vec31 = p_147861_.position().add(p_147861_.position().vectorTo(vec3).normalize().multiply((double)aint[0], (double)aint[1], (double)aint[0]));
            }

            if (vec31 == null || p_147861_.level.getFluidState(new BlockPos(vec31)).isEmpty()) {
                return vec3;
            }

            vec3 = vec31;
        }

        return vec31;
    }
}