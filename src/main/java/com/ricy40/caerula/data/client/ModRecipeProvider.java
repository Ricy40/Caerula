package com.ricy40.caerula.data.client;

import com.google.common.collect.ImmutableList;
import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.block.ModBlocks;
import com.ricy40.caerula.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator gen) {
        super(gen);
    }

    private static final ImmutableList<ItemLike> NIXIUM_SMELTABLES = ImmutableList.of(ModBlocks.NIXIUM_ORE.get(), ModBlocks.DEEPSLATE_NIXIUM_ORE.get(), ModItems.RAW_NIXIUM.get());


    protected void buildShapelessRecipes(Consumer<FinishedRecipe> consumer) {

        //ShapelessRecipeBuilder.shapeless(ModItems.NIXIUM_INGOT.get(), 9).requires(ModBlocks.NIXIUM_BLOCK.get()).unlockedBy("has_nixium_ingot", has(ModItems.NIXIUM_INGOT.get())).save(consumer);
        //ShapelessRecipeBuilder.shapeless(ModItems.NIXIUM_NUGGET.get(), 9).requires(ModItems.NIXIUM_INGOT.get()).unlockedBy("has_nixium_nugget", has(ModItems.NIXIUM_NUGGET.get())).save(consumer);
        oreSmelting(consumer, NIXIUM_SMELTABLES, ModItems.NIXIUM_INGOT.get(), 0.7F, 200, "nixium_ingot");
        oreBlasting(consumer, NIXIUM_SMELTABLES, ModItems.NIXIUM_INGOT.get(), 0.7F, 100, "nixium_ingot");
        nineBlockStorageRecipes(consumer, ModItems.RAW_NIXIUM.get(), ModBlocks.RAW_NIXIUM_BLOCK.get());
        nineBlockStorageRecipesRecipesWithCustomUnpacking(consumer, ModItems.NIXIUM_INGOT.get(), ModBlocks.NIXIUM_BLOCK.get(), "nixium_ingot_from_nixium_block", "nixium_ingot");
        nineBlockStorageRecipesWithCustomPacking(consumer, ModItems.NIXIUM_NUGGET.get(), ModItems.NIXIUM_INGOT.get(), "nixium_ingot_from_nuggets", "nixium_ingot");
        slabBuilder(ModBlocks.NIXIUM_SLAB.get(), Ingredient.of(ModBlocks.NIXIUM_BLOCK.get())).unlockedBy("has_nixium_block", has(ModBlocks.NIXIUM_BLOCK.get())).save(consumer);
        stairBuilder(ModBlocks.NIXIUM_STAIRS.get(), Ingredient.of(ModBlocks.NIXIUM_BLOCK.get())).unlockedBy("has_nixium_block", has(ModBlocks.NIXIUM_BLOCK.get())).save(consumer);

        //Check RecipeProvider

    }

    private ResourceLocation modId(String path) {
        return new ResourceLocation(Caerula.MOD_ID, path);
    }

    private static void oneToOneConversionRecipe(Consumer<FinishedRecipe> consumer, ItemLike pResult, ItemLike pIngredient, @Nullable String pGroup) {
        oneToOneConversionRecipe(consumer, pResult, pIngredient, pGroup, 1);
    }

    private static void oneToOneConversionRecipe(Consumer<FinishedRecipe> consumer, ItemLike pResult, ItemLike pIngredient, @Nullable String pGroup, int pResultCount) {
        ShapelessRecipeBuilder.shapeless(pResult, pResultCount).requires(pIngredient).group(pGroup).unlockedBy(getHasName(pIngredient), has(pIngredient)).save(consumer, getConversionRecipeName(pResult, pIngredient));
    }

    private static void oreSmelting(Consumer<FinishedRecipe> consumer, List<ItemLike> pIngredients, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(consumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    private static void oreBlasting(Consumer<FinishedRecipe> consumer, List<ItemLike> pIngredients, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(consumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    private static void oreCooking(Consumer<FinishedRecipe> consumer, SimpleCookingSerializer<?> pCookingSerializer, List<ItemLike> pIngredients, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.cooking(Ingredient.of(itemlike), pResult, pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(consumer, getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }

    private static void nineBlockStorageRecipes(Consumer<FinishedRecipe> consumer, ItemLike pUnpacked, ItemLike pPacked) {
        nineBlockStorageRecipes(consumer, pUnpacked, pPacked, getSimpleRecipeName(pPacked), (String)null, getSimpleRecipeName(pUnpacked), (String)null);
    }

    private static void nineBlockStorageRecipesWithCustomPacking(Consumer<FinishedRecipe> consumer, ItemLike pUnpacked, ItemLike pPacked, String pPackingRecipeName, String pPackingRecipeGroup) {
        nineBlockStorageRecipes(consumer, pUnpacked, pPacked, pPackingRecipeName, pPackingRecipeGroup, getSimpleRecipeName(pUnpacked), (String)null);
    }

    private static void nineBlockStorageRecipesRecipesWithCustomUnpacking(Consumer<FinishedRecipe> consumer, ItemLike pUnpacked, ItemLike pPacked, String pUnpackingRecipeName, String pUnpackingRecipeGroup) {
        nineBlockStorageRecipes(consumer, pUnpacked, pPacked, getSimpleRecipeName(pPacked), (String)null, pUnpackingRecipeName, pUnpackingRecipeGroup);
    }

    private static void nineBlockStorageRecipes(Consumer<FinishedRecipe> consumer, ItemLike pUnpacked, ItemLike pPacked, String pPackingRecipeName, @Nullable String pPackingRecipeGroup, String pUnpackingRecipeName, @Nullable String pUnpackingRecipeGroup) {
        ShapelessRecipeBuilder.shapeless(pUnpacked, 9).requires(pPacked).group(pUnpackingRecipeGroup).unlockedBy(getHasName(pPacked), has(pPacked)).save(consumer, new ResourceLocation(pUnpackingRecipeName));
        ShapedRecipeBuilder.shaped(pPacked).define('#', pUnpacked).pattern("###").pattern("###").pattern("###").group(pPackingRecipeGroup).unlockedBy(getHasName(pUnpacked), has(pUnpacked)).save(consumer, new ResourceLocation(pPackingRecipeName));
    }

    private static String getHasName(ItemLike pItemLike) {
        return "has_" + getItemName(pItemLike);
    }

    private static String getSimpleRecipeName(ItemLike pItemLike) {
        return getItemName(pItemLike);
    }

    private static String getItemName(ItemLike pItemLike) {
        return Registry.ITEM.getKey(pItemLike.asItem()).getPath();
    }

    private static String getConversionRecipeName(ItemLike pResult, ItemLike pIngredient) {
        return getItemName(pResult) + "_from_" + getItemName(pIngredient);
    }

    private static RecipeBuilder slabBuilder(ItemLike pSlab, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(pSlab, 6).define('#', pMaterial).pattern("###");
    }

    private static RecipeBuilder stairBuilder(ItemLike pStairs, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(pStairs, 4).define('#', pMaterial).pattern("#  ").pattern("## ").pattern("###");
    }
}
