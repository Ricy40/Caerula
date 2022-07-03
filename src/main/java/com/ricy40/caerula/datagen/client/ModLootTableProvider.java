package com.ricy40.caerula.datagen.client;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.ricy40.caerula.block.ModBlocks;
import com.ricy40.caerula.entity.ModEntityTypes;
import com.ricy40.caerula.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.Tags;
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
                Pair.of(ModBlockLoot::new, LootContextParamSets.BLOCK),
                Pair.of(ModEntityLoot::new, LootContextParamSets.ENTITY)
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

            dropSelf(ModBlocks.PURPLE_SEASHROOM.get());
            add(ModBlocks.PURPLE_SEASHROOM_BLOCK.get(), createMushroomBlockDrop(ModBlocks.PURPLE_SEASHROOM_BLOCK.get(), ModBlocks.PURPLE_SEASHROOM.get()));
            add(ModBlocks.MYCELIUM_SAND.get(), createSingleItemTableWithSilkTouch(ModBlocks.MYCELIUM_SAND.get(), Blocks.SAND));

            //Check BlockLoot

        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }

    public class ModEntityLoot extends EntityLoot {

        @Override
        protected void addTables() {

            add(ModEntityTypes.BLOBFISH.get(),
                    LootTable.lootTable().withPool(
                    LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(ModItems.BLOBFISH.get())
                            .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))))
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(Items.BONE_MEAL))
                            .when(LootItemRandomChanceCondition.randomChance(0.05F))));

            add(ModEntityTypes.LULA.get(),
                    LootTable.lootTable().withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(LootItem.lootTableItem(ModItems.LULA.get())
                                            .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))));

          //  add(ModEntityTypes.SEACOW.get(),
            //        LootTable.lootTable().withPool(
              //              LootPool.lootPool()
                //                    .setRolls(ConstantValue.exactly(1F))
                  //                  .add(LootItem.lootTableItem(Items.BEEF))
                    //));

        }

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return ModEntityTypes.ENTITY_TYPES.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }
}
