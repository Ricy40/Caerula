package com.ricy40.caerula.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab CAERULA = new CreativeModeTab("caerula") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.CAERULA.get());
        }
    };
}
