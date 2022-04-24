package com.ricy40.caerula.datagen.client;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.ricy40.caerula.block.ModBlocks;
import com.ricy40.caerula.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ModLootTableProvider extends LootTableProvider {

    public ModLootTableProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ModBlockLoot::new, LootContextParamSets.BLOCK)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
        map.forEach((a, b) -> LootTables.validate(validationContext, a, b));
    }

    public static class ModBlockLoot extends BlockLoot {
        @Override
        protected void addTables() {

            dropSelf(ModBlocks.BUSH_CORAL.get());
            dropSelf(ModBlocks.DEAD_BUSH_CORAL.get());
            dropSelf(ModBlocks.RED_SEAGRASS.get());
            dropOther(ModBlocks.TALL_RED_SEAGRASS.get(), ModBlocks.RED_SEAGRASS.get());

            dropSelf(ModBlocks.NIXIUM_BLOCK.get());
            dropSelf(ModBlocks.RAW_NIXIUM_BLOCK.get());
            add(ModBlocks.NIXIUM_SLAB.get(), createSlabItemTable(ModBlocks.NIXIUM_SLAB.get()));
            dropSelf(ModBlocks.NIXIUM_STAIRS.get());
            add(ModBlocks.NIXIUM_ORE.get(), createOreDrop(ModBlocks.NIXIUM_ORE.get(), ModItems.RAW_NIXIUM.get()));
            add(ModBlocks.DEEPSLATE_NIXIUM_ORE.get(), createOreDrop(ModBlocks.DEEPSLATE_NIXIUM_ORE.get(), ModItems.RAW_NIXIUM.get()));

        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }
}
