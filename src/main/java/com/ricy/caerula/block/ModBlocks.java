package com.ricy.caerula.block;

import com.ricy.caerula.Caerula;
import com.ricy.caerula.item.ModItems;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Caerula.MOD_ID);

    public static final DeferredBlock<Block> NIXIUM_BLOCK = registerBlock("nixium_block", properties -> new Block(properties.mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.IRON)));
    public static final DeferredBlock<Block> RAW_NIXIUM_BLOCK = registerBlock("raw_nixium_block", properties -> new Block(properties.mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    //public static final DeferredBlock<SlabBlock> NIXIUM_SLAB = registerBlock("nixium_slab", properties -> new SlabBlock(properties.mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    //public static final DeferredBlock<Block> NIXIUM_STAIRS = registerBlock("nixium_stairs", properties -> new StairBlock(NIXIUM_BLOCK.get().defaultBlockState(), properties.mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<DropExperienceBlock> NIXIUM_ORE = registerBlock("nixium_ore", properties -> new DropExperienceBlock(ConstantInt.of(0), properties.mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_NIXIUM_ORE = registerBlock("deepslate_nixium_ore", properties -> new DropExperienceBlock(ConstantInt.of(0), properties.mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
