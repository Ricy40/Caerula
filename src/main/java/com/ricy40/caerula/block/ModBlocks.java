package com.ricy40.caerula.block;

import com.ricy40.caerula.Caerula;
import com.ricy40.caerula.block.flora.RedSeagrassBlock;
import com.ricy40.caerula.block.flora.TallRedSeagrassBlock;
import com.ricy40.caerula.item.ModCreativeModeTab;
import com.ricy40.caerula.item.ModItems;
import com.ricy40.caerula.tags.ModTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Caerula.MOD_ID);

    public static final RegistryObject<Block> RED_SEAGRASS = registerBlock("red_seagrass",
            () -> new RedSeagrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_WATER_PLANT)
                    .noCollission().instabreak().sound(SoundType.WET_GRASS)), ModCreativeModeTab.CAERULA);
    public static final RegistryObject<Block> TALL_RED_SEAGRASS = registerBlockNBI("tall_red_seagrass",
            () -> new TallRedSeagrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_WATER_PLANT)
                    .noCollission().instabreak().sound(SoundType.WET_GRASS)));
    public static final RegistryObject<Block> DEAD_BUSH_CORAL = registerBlock("dead_bush_coral",
            () -> new BaseCoralPlantBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
                    .requiresCorrectToolForDrops().noCollission().instabreak()), ModCreativeModeTab.CAERULA);
    public static final RegistryObject<Block> BUSH_CORAL = registerBlock("bush_coral",
            () -> new CoralPlantBlock(DEAD_BUSH_CORAL.get(), BlockBehaviour.Properties.of(Material.WATER_PLANT, MaterialColor.COLOR_PURPLE)
                    .noCollission().instabreak().sound(SoundType.WET_GRASS)), ModCreativeModeTab.CAERULA);

    public static final RegistryObject<Block> NIXIUM_BLOCK = registerBlock("nixium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)), ModCreativeModeTab.CAERULA);
    public static final RegistryObject<Block> RAW_NIXIUM_BLOCK = registerBlock("raw_nixium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)), ModCreativeModeTab.CAERULA);
    public static final RegistryObject<SlabBlock> NIXIUM_SLAB = registerBlock("nixium_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)), ModCreativeModeTab.CAERULA);
    public static final RegistryObject<StairBlock> NIXIUM_STAIRS = registerBlock("nixium_stairs",
            () -> new StairBlock(NIXIUM_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)), ModCreativeModeTab.CAERULA);
    public static final RegistryObject<Block> NIXIUM_ORE = registerBlock("nixium_ore",
            () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)), ModCreativeModeTab.CAERULA);
    public static final RegistryObject<Block> DEEPSLATE_NIXIUM_ORE = registerBlock("deepslate_nixium_ore",
            () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)), ModCreativeModeTab.CAERULA);



    // Methods

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlockNBI(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventbus) {
        BLOCKS.register(eventbus);
    }
}
