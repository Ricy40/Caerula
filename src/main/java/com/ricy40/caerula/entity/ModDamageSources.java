package com.ricy40.caerula.entity;


import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;

public class ModDamageSources {

    public static DamageSource sonicCharge(Entity entity) {
        return (new EntityDamageSource("sonic_charge", entity)).bypassEnchantments().setMagic();
    }

}
