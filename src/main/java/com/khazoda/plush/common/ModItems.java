package com.khazoda.plush.common;

import com.khazoda.plush.common.block.Block_PenguinBaby;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

    public static final CreativeTabs tabPlushables = new CreativeTabs("tabPlushables") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(penguinBaby);
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }
    }.setBackgroundImageName("item_search.png");

    @GameRegistry.ObjectHolder("plush:penguin_baby")
    public static Block_PenguinBaby penguinBaby;

}
