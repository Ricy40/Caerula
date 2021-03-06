package com.ricy40.caerula.util;

import com.mojang.math.Vector3f;
import net.minecraft.world.phys.Vec3;

public class Vec3ex extends Vec3 {

    public float mag;
    public static final Vec3ex ZEROex = new Vec3ex(0.0D, 0.0D, 0.0D);

    public Vec3ex(double pX, double pY, double pZ) {
        super(pX, pY, pZ);
        calculateMag();
    }

    public Vec3ex(Vector3f pFloatVector) {
        super(pFloatVector);
        calculateMag();
    }

    public float calculateAngle(Vec3ex v2) {
        return (float) Math.acos(this.normalize().dot(v2.normalize()));
    }

    public float calculateAngle(Vec3ex v1, Vec3ex v2) {
        return (float) Math.acos(v1.normalize().dot(v2.normalize()));
    }

    public Vec3 getVec3() {
        return new Vec3(this.x, this.y, this.z);
    }

    public boolean isZero() {
        return this.x == 0 && this.y == 0 && this.z == 0;
    }

    private void calculateMag() {
        this.mag =  (float) (Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2)));
    }

    public Vec3ex normalizeEx() {
        double d0 = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        return d0 < 1.0E-4D ? ZEROex : new Vec3ex(this.x / d0, this.y / d0, this.z / d0);
    }
}
