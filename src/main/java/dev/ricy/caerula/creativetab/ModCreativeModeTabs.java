package dev.ricy.caerula.creativetab;

import dev.ricy.caerula.Caerula;
import dev.ricy.caerula.block.ModBlocks;
import dev.ricy.caerula.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Caerula.MOD_ID);

    public static final Supplier<CreativeModeTab> CAERULA_ITEMS_TAB = CREATIVE_MODE_TABS.register("caerula_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CAERULA.get()))
                    .title(Component.translatable("creativetab.caerula.items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.CAERULA.get());
                        output.accept(ModItems.RAW_NIXIUM.get());
                        output.accept(ModItems.NIXIUM_INGOT.get());
                        output.accept(ModItems.NIXIUM_NUGGET.get());
                    }).build());

    public static final Supplier<CreativeModeTab> CAERULA_BLOCKS_TAB = CREATIVE_MODE_TABS.register("caerula_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CAERULA.get()))
                    .title(Component.translatable("creativetab.caerula.blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.NIXIUM_BLOCK.get());
                        output.accept(ModBlocks.RAW_NIXIUM_BLOCK.get());
                        output.accept(ModBlocks.NIXIUM_SLAB.get());
                        output.accept(ModBlocks.NIXIUM_STAIRS.get());
                        output.accept(ModBlocks.NIXIUM_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_NIXIUM_ORE.get());
                        output.accept(ModBlocks.RED_SEAGRASS.get());
                        output.accept(ModBlocks.BUSH_CORAL.get());
                        output.accept(ModBlocks.DEAD_BUSH_CORAL.get());
                        output.accept(ModBlocks.MYCELIUM_SAND.get());
                        output.accept(ModBlocks.PURPLE_SEASHROOM.get());
                        output.accept(ModBlocks.YELLOW_SEASHROOM.get());
                        output.accept(ModBlocks.PURPLE_SEASHROOM_BLOCK.get());
                        output.accept(ModBlocks.YELLOW_SEASHROOM_BLOCK.get());
                        output.accept(ModBlocks.ORANGE_SEASHROOM_BLOCK.get());
                        output.accept(ModBlocks.BROWN_SEASHROOM_BLOCK.get());
                        output.accept(ModBlocks.SEASHROOM_STEM.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
