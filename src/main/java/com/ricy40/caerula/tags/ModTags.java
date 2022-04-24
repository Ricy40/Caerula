package com.ricy40.caerula.tags;


import com.ricy40.caerula.Caerula;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> WALLS = tag("walls");
        public static final TagKey<Block> FENCES = tag("fences");
        public static final TagKey<Block> FENCE_GATES = tag("fence_gates");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Caerula.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> WALLS = tag("walls");
        public static final TagKey<Item> FENCES = tag("fences");
        public static final TagKey<Item> FENCE_GATES = tag("fence_gates");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Caerula.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
