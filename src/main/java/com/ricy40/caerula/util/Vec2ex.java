package com.ricy40.caerula.util;

import net.minecraft.world.phys.Vec2;

public class Vec2ex extends Vec2 {

    public float mag;

    public Vec2ex(float pX, float pY) {
        super(pX, pY);
        calculateMag();
    }

    public float calculateAngle(Vec2ex v2) {
        return (float) Math.acos(this.normalized().dot(v2.normalized()));
    }

    public static float calculateAngle(Vec2ex v1, Vec2ex v2) {
        float rot;

        if ((-1 * v1.x) > v2.x && (-1 * v1.y) > v2.y) {
            rot = -1f;
        } else if (v1.x < v2.x && v1.y > v2.y) {
            rot = -1f;
        } else {
            rot = 1f;
        }

        return (float) Math.acos(v1.normalized().dot(v2.normalized()));
    }

    public Vec2 getVec2() {
        return new Vec2(this.x, this.y);
    }

    public boolean isZero() {
        return this.x == 0f && this.y == 0f;
    }

    private void calculateMag() {
        this.mag = (float) (Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2)));
    }
}
