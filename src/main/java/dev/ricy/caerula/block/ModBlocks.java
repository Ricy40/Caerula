package dev.ricy.caerula.block;

import dev.ricy.caerula.Caerula;
import dev.ricy.caerula.block.custom.MyceliumSandBlock;
import dev.ricy.caerula.block.custom.RedSeagrassBlock;
import dev.ricy.caerula.block.custom.SeashroomBlock;
import dev.ricy.caerula.block.custom.TallRedSeagrassBlock;
import dev.ricy.caerula.item.ModItems;
import dev.ricy.caerula.worldgen.ModConfiguredFeatures;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Caerula.MOD_ID);

    public static final DeferredBlock<RedSeagrassBlock> RED_SEAGRASS = registerBlock("red_seagrass", properties -> new RedSeagrassBlock(properties.mapColor(MapColor.WATER).replaceable().noCollision().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<TallRedSeagrassBlock> TALL_RED_SEAGRASS = BLOCKS.registerBlock("tall_red_seagrass", properties -> new TallRedSeagrassBlock(properties.mapColor(MapColor.WATER).replaceable().offsetType(BlockBehaviour.OffsetType.XZ).noCollision().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<BaseCoralPlantBlock> DEAD_BUSH_CORAL = registerBlock("dead_bush_coral", properties -> new BaseCoralPlantBlock(properties.mapColor(MapColor.COLOR_GRAY).requiresCorrectToolForDrops().noCollision().instabreak()));
    public static final DeferredBlock<CoralPlantBlock> BUSH_CORAL = registerBlock("bush_coral", properties -> new CoralPlantBlock(DEAD_BUSH_CORAL.get(), properties.mapColor(MapColor.COLOR_PURPLE).noCollision().instabreak().sound(SoundType.WET_GRASS)));

    public static final DeferredBlock<Block> NIXIUM_BLOCK = registerBlock("nixium_block", properties -> new Block(properties.mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.IRON)));
    public static final DeferredBlock<Block> RAW_NIXIUM_BLOCK = registerBlock("raw_nixium_block", properties -> new Block(properties.mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<SlabBlock> NIXIUM_SLAB = registerBlock("nixium_slab", properties -> new SlabBlock(properties.mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.IRON)));
    public static final DeferredBlock<StairBlock> NIXIUM_STAIRS = registerBlock("nixium_stairs", properties -> new StairBlock(NIXIUM_BLOCK.get().defaultBlockState(), properties.mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.IRON)));
    public static final DeferredBlock<DropExperienceBlock> NIXIUM_ORE = registerBlock("nixium_ore", properties -> new DropExperienceBlock(ConstantInt.of(0), properties.mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_NIXIUM_ORE = registerBlock("deepslate_nixium_ore", properties -> new DropExperienceBlock(ConstantInt.of(0), properties.mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    public static final DeferredBlock<SeashroomBlock> PURPLE_SEASHROOM = registerBlock("purple_seashroom", properties -> new SeashroomBlock(properties.mapColor(MapColor.COLOR_PURPLE).replaceable().noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY).lightLevel(state -> 1).postProcess((state, level, pos) -> pos),
            ModConfiguredFeatures.HUGE_PURPLE_SEASHROOM_ONECAP_KEY, ModConfiguredFeatures.HUGE_PURPLE_SEASHROOM_TWOCAP_KEY, ModConfiguredFeatures.HUGE_PURPLE_SEASHROOM_THREECAP_KEY));
    public static final DeferredBlock<SeashroomBlock> YELLOW_SEASHROOM = registerBlock("yellow_seashroom", properties -> new SeashroomBlock(properties.mapColor(MapColor.COLOR_YELLOW).replaceable().noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY).lightLevel(state -> 1).postProcess((state, level, pos) -> pos),
            ModConfiguredFeatures.HUGE_YELLOW_SEASHROOM_ONECAP_KEY, ModConfiguredFeatures.HUGE_YELLOW_SEASHROOM_TWOCAP_KEY, ModConfiguredFeatures.HUGE_YELLOW_SEASHROOM_THREECAP_KEY));

    public static final DeferredBlock<HugeMushroomBlock> PURPLE_SEASHROOM_BLOCK =registerBlock("purple_seashroom_block", properties -> new HugeMushroomBlock(properties.mapColor(MapColor.DIRT).strength(0.2F).sound(SoundType.WOOD)));
    public static final DeferredBlock<HugeMushroomBlock> YELLOW_SEASHROOM_BLOCK = registerBlock("yellow_seashroom_block", properties -> new HugeMushroomBlock(properties.mapColor(MapColor.DIRT).strength(0.2F).sound(SoundType.WOOD)));
    public static final DeferredBlock<HugeMushroomBlock> ORANGE_SEASHROOM_BLOCK = registerBlock("orange_seashroom_block", properties -> new HugeMushroomBlock(properties.mapColor(MapColor.DIRT).strength(0.2F).sound(SoundType.WOOD)));
    public static final DeferredBlock<HugeMushroomBlock> BROWN_SEASHROOM_BLOCK = registerBlock("brown_seashroom_block", properties -> new HugeMushroomBlock(properties.mapColor(MapColor.DIRT).strength(0.2F).sound(SoundType.WOOD)));
    public static final DeferredBlock<HugeMushroomBlock> SEASHROOM_STEM = registerBlock("seashroom_stem", properties -> new HugeMushroomBlock(properties.mapColor(MapColor.WOOL).strength(0.2F).sound(SoundType.WOOD)));
    public static final DeferredBlock<MyceliumSandBlock> MYCELIUM_SAND = registerBlock("mycelium_sand", properties -> new MyceliumSandBlock(properties.mapColor(MapColor.COLOR_PURPLE).randomTicks().strength(0.6F).sound(SoundType.GRASS)));

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
