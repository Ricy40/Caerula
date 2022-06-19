package com.ricy40.caerula.world.dimension;

import com.ricy40.caerula.Caerula;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.registries.ForgeRegistries;

public class ModDimensions {

    public static final ResourceKey<Level> CAERULA_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Caerula.MOD_ID, "caerula"));

    public static final ResourceKey<DimensionType> CAERULA_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY,
            new ResourceLocation(Caerula.MOD_ID, "caerula"));
    public static void register() {
        System.out.println("Registering ModDimensions for " + Caerula.MOD_ID);
    }
}
