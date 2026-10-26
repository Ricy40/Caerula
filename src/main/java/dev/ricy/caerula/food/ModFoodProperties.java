package dev.ricy.caerula.food;

import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties BLOBFISH = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).build();
    public static final FoodProperties COOKED_BLOBFISH = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6F).build();

    public static final FoodProperties RAW_LULA = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).build();
    public static final FoodProperties COOKED_LULA = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6F).build();
}
