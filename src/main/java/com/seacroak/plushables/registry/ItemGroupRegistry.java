package com.seacroak.plushables.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class ItemGroupRegistry {
    public static ItemGroup createItemGroup() {
        return FabricItemGroup.builder()
                .icon(() -> new ItemStack(MainRegistry.HEART_OF_GOLD))
                .displayName(Text.translatable("itemGroup.plushables.plushables"))
                .entries((displayContext, entries) -> {
                    entries.add(new ItemStack(MainRegistry.HEART_OF_GOLD));
                    entries.add(new ItemStack(MainRegistry.BUILDER_BLOCK));
                    entries.add(new ItemStack(MainRegistry.PENGUIN_PLUSHABLE));
                    entries.add(new ItemStack(MainRegistry.FOX_PLUSHABLE));
                    entries.add(new ItemStack(MainRegistry.FROGLIN_PLUSHABLE));
                    entries.add(new ItemStack(MainRegistry.CLUCKY_BLOCK));
                    entries.add(new ItemStack(MainRegistry.PIG_PLUSHABLE));
                    entries.add(new ItemStack(MainRegistry.TRUFFLES_PLUSHABLE));
                }).build();
    }

}
