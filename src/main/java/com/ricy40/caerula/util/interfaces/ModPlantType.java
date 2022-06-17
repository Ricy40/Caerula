package com.ricy40.caerula.util.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public final class ModPlantType {

    private static final Pattern INVALID_CHARACTERS = Pattern.compile("[^a-z_]"); //Only a-z and _ are allowed, meaning names must be lower case. And use _ to separate words.
    private static final Map<String, ModPlantType> VALUES = new ConcurrentHashMap<>();

    public static final ModPlantType OCEAN = get("ocean");
    public static final ModPlantType PLAINS = get("plains");

    public static ModPlantType get(String name) {
        return VALUES.computeIfAbsent(name, e -> {
            if (INVALID_CHARACTERS.matcher(e).find())
                throw new IllegalArgumentException("ModPlantType.get() called with invalid name: " + name);
            return new ModPlantType(e);
        });
    }

    private final String name;

    private ModPlantType(String name) {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
