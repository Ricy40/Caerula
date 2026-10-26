package dev.ricy.caerula.tag;

import dev.ricy.caerula.Caerula;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> MUSHROOM_GROW_BLOCK = createTag("mushroom_grow_block");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(Caerula.MOD_ID, name));
        }
    }
}
