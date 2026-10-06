package com.ricy.caerula.datagen;

import com.ricy.caerula.Caerula;
import com.ricy.caerula.block.ModBlocks;
import com.ricy.caerula.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        List<ItemLike> NIXIUM_SMELTABLES = List.of(ModItems.RAW_NIXIUM, ModBlocks.NIXIUM_ORE, ModBlocks.DEEPSLATE_NIXIUM_ORE);

        oreSmelting(NIXIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.NIXIUM_INGOT.get(), 0.25f, 200, "nixium");
        oreBlasting(NIXIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.NIXIUM_INGOT.get(), 0.25f, 100, "nixium");
        oreSmelting(List.of(ModBlocks.RAW_NIXIUM_BLOCK), RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModBlocks.NIXIUM_BLOCK.get(), 2.25f, 1800, "nixium");
        oreBlasting(List.of(ModBlocks.RAW_NIXIUM_BLOCK), RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModBlocks.NIXIUM_BLOCK.get(), 2.25f, 900, "nixium");

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NIXIUM_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.NIXIUM_INGOT.get())
                .unlockedBy(getHasName(ModItems.NIXIUM_INGOT.get()), has(ModItems.NIXIUM_INGOT.get()))
                .save(output, Caerula.MOD_ID + ":" + "nixium_block_from_ingots");

        shapeless(RecipeCategory.MISC, ModItems.NIXIUM_INGOT.get(), 9)
                .requires(ModBlocks.NIXIUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.NIXIUM_BLOCK.get()), has(ModBlocks.NIXIUM_BLOCK.get()))
                .save(output, Caerula.MOD_ID + ":" + "nixium_ingots_from_block");

        shaped(RecipeCategory.MISC, ModItems.NIXIUM_INGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.NIXIUM_NUGGET.get())
                .unlockedBy(getHasName(ModItems.NIXIUM_NUGGET.get()), has(ModItems.NIXIUM_NUGGET.get()))
                .save(output, Caerula.MOD_ID + ":" + "nixium_ingot_from_nuggets");

        shapeless(RecipeCategory.MISC, ModItems.NIXIUM_NUGGET.get(), 9)
                .requires(ModItems.NIXIUM_INGOT.get())
                .unlockedBy(getHasName(ModItems.NIXIUM_INGOT.get()), has(ModItems.NIXIUM_INGOT.get()))
                .save(output, Caerula.MOD_ID + ":" + "nixium_nuggets_from_ingot");

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_NIXIUM_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.RAW_NIXIUM.get())
                .unlockedBy(getHasName(ModItems.RAW_NIXIUM.get()), has(ModItems.RAW_NIXIUM.get()))
                .save(output, Caerula.MOD_ID + ":" + "raw_nixium_block_from_raw_nixium");

        shapeless(RecipeCategory.MISC, ModItems.RAW_NIXIUM.get(), 9)
                .requires(ModBlocks.RAW_NIXIUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RAW_NIXIUM_BLOCK.get()), has(ModBlocks.RAW_NIXIUM_BLOCK.get()))
                .save(output, Caerula.MOD_ID + ":" + "raw_nixium_from_raw_nixium_block");
    }

    @Override
    protected <T extends AbstractCookingRecipe> void oreCooking(AbstractCookingRecipe.Factory<T> factory, List<ItemLike> smeltables, RecipeCategory craftingCategory, CookingBookCategory cookingCategory, ItemLike result, float experience, int cookingTime, String group, String fromDesc) {
        for (ItemLike item : smeltables) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(item), craftingCategory, cookingCategory, result, experience, cookingTime, factory)
                    .group(group)
                    .unlockedBy(getHasName(item), this.has(item))
                    .save(this.output, Caerula.MOD_ID + ":" + getItemName(result) + fromDesc + "_" + getItemName(item));
        }
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "Caerula Recipes";
        }
    }
}
