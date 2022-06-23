package com.seacroak.plushables.registry;

import com.seacroak.plushables.util.GenericUtils;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupRegistry {
    public static ItemGroup createItemGroup() {
        return FabricItemGroupBuilder.create(
                GenericUtils.ID("plushables"))
                .icon(() -> new ItemStack(MainRegistry.HEART_OF_GOLD))
                .appendItems(stacks -> {
                    stacks.add(new ItemStack(MainRegistry.HEART_OF_GOLD));
                    stacks.add(new ItemStack(MainRegistry.BUILDER_BLOCK));
                    stacks.add(new ItemStack(MainRegistry.CLUCKY_BLOCK));
                    stacks.add(new ItemStack(MainRegistry.FOX_PLUSHABLE));
                    stacks.add(new ItemStack(MainRegistry.PENGUIN_PLUSHABLE));
                    stacks.add(new ItemStack(MainRegistry.FROGLIN_PLUSHABLE));
                }).build();
    }

}
