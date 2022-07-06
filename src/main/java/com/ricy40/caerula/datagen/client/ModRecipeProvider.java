package com.ricy40.caerula.datagen.client;

import com.google.common.collect.ImmutableList;
import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.block.ModBlocks;
import com.ricy40.caerula.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator gen) {
        super(gen);
    }

    private static final ImmutableList<ItemLike> NIXIUM_SMELTABLES = ImmutableList.of(ModBlocks.NIXIUM_ORE.get(), ModBlocks.DEEPSLATE_NIXIUM_ORE.get(), ModItems.RAW_NIXIUM.get());


    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        oreSmelting(consumer, NIXIUM_SMELTABLES, ModItems.NIXIUM_INGOT.get(), 0.7F, 200, "nixium_ingot");
        oreBlasting(consumer, NIXIUM_SMELTABLES, ModItems.NIXIUM_INGOT.get(), 0.7F, 100, "nixium_ingot");
        nineBlockStorageRecipes(consumer, ModItems.RAW_NIXIUM.get(), ModBlocks.RAW_NIXIUM_BLOCK.get());
        nineBlockStorageRecipesRecipesWithCustomUnpacking(consumer, ModItems.NIXIUM_INGOT.get(), ModBlocks.NIXIUM_BLOCK.get(), "nixium_ingot_from_nixium_block", "nixium_ingot");
        nineBlockStorageRecipesWithCustomPacking(consumer, ModItems.NIXIUM_NUGGET.get(), ModItems.NIXIUM_INGOT.get(), "nixium_ingot_from_nuggets", "nixium_ingot");
        slabBuilder(ModBlocks.NIXIUM_SLAB.get(), Ingredient.of(ModBlocks.NIXIUM_BLOCK.get())).unlockedBy("has_nixium_block", has(ModBlocks.NIXIUM_BLOCK.get())).save(consumer);
        stairBuilder(ModBlocks.NIXIUM_STAIRS.get(), Ingredient.of(ModBlocks.NIXIUM_BLOCK.get())).unlockedBy("has_nixium_block", has(ModBlocks.NIXIUM_BLOCK.get())).save(consumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.BLOBFISH.get()), ModItems.COOKED_BLOBFISH.get(), 0.35F, 200).unlockedBy("has_blobfish", has(ModItems.BLOBFISH.get())).save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_LULA.get()), ModItems.COOKED_LULA.get(), 0.35F, 200).unlockedBy("has_blobfish", has(ModItems.RAW_LULA.get())).save(consumer);

        cookRecipes(consumer, "smoking", RecipeSerializer.SMOKING_RECIPE, 100);
        cookRecipes(consumer, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600);

        ShapelessRecipeBuilder.shapeless(Items.MUSHROOM_STEW).requires(Blocks.BROWN_MUSHROOM).requires(ModBlocks.PURPLE_SEASHROOM.get()).requires(Items.BOWL).unlockedBy("has_mushroom_stew", has(Items.MUSHROOM_STEW)).unlockedBy("has_bowl", has(Items.BOWL)).unlockedBy("has_brown_mushroom", has(Blocks.BROWN_MUSHROOM)).unlockedBy("has_purple_seashroom", has(ModBlocks.PURPLE_SEASHROOM.get())).save(consumer, "mushroom_stew_purple_and_brown");
        ShapelessRecipeBuilder.shapeless(Items.MUSHROOM_STEW).requires(Blocks.RED_MUSHROOM).requires(ModBlocks.PURPLE_SEASHROOM.get()).requires(Items.BOWL).unlockedBy("has_mushroom_stew", has(Items.MUSHROOM_STEW)).unlockedBy("has_bowl", has(Items.BOWL)).unlockedBy("has_red_mushroom", has(Blocks.RED_MUSHROOM)).unlockedBy("has_purple_seashroom", has(ModBlocks.PURPLE_SEASHROOM.get())).save(consumer, "mushroom_stew_purple_and_red");
        ShapelessRecipeBuilder.shapeless(Items.MUSHROOM_STEW).requires(Blocks.BROWN_MUSHROOM).requires(ModBlocks.YELLOW_SEASHROOM.get()).requires(Items.BOWL).unlockedBy("has_mushroom_stew", has(Items.MUSHROOM_STEW)).unlockedBy("has_bowl", has(Items.BOWL)).unlockedBy("has_brown_mushroom", has(Blocks.BROWN_MUSHROOM)).unlockedBy("has_yellow_seashroom", has(ModBlocks.YELLOW_SEASHROOM.get())).save(consumer, "mushroom_stew_yellow_and_brown");
        ShapelessRecipeBuilder.shapeless(Items.MUSHROOM_STEW).requires(Blocks.RED_MUSHROOM).requires(ModBlocks.YELLOW_SEASHROOM.get()).requires(Items.BOWL).unlockedBy("has_mushroom_stew", has(Items.MUSHROOM_STEW)).unlockedBy("has_bowl", has(Items.BOWL)).unlockedBy("has_red_mushroom", has(Blocks.RED_MUSHROOM)).unlockedBy("has_yellow_seashroom", has(ModBlocks.YELLOW_SEASHROOM.get())).save(consumer, "mushroom_stew_yellow_and_red");
        ShapelessRecipeBuilder.shapeless(Items.MUSHROOM_STEW).requires(ModBlocks.PURPLE_SEASHROOM.get()).requires(ModBlocks.YELLOW_SEASHROOM.get()).requires(Items.BOWL).unlockedBy("has_mushroom_stew", has(Items.MUSHROOM_STEW)).unlockedBy("has_bowl", has(Items.BOWL)).unlockedBy("has_purple_seashroom", has(ModBlocks.PURPLE_SEASHROOM.get())).unlockedBy("has_yellow_seashroom", has(ModBlocks.YELLOW_SEASHROOM.get())).save(consumer, "mushroom_stew_yellow_and_purple");




        //Check RecipeProvider

    }

    private ResourceLocation modId(String path) {
        return new ResourceLocation(Caerula.MOD_ID, path);
    }

}
