package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegistryHelper {

    // General use Identifier() maker function
    public static Identifier newID(String name) {
        return new Identifier(PlushablesMod.MOD_ID, name);
    }

    // Block Registry Helper Functions
    // *******************************
    // 1. Registration Entrypoint: creates Identifier from ModID & block name
    public static <B extends Block> B registerBlock(String name, B block, FabricItemSettings itemSettings) {
        return registerBlock(newID(name), block, itemSettings);
    }

    // 2. Takes identifier and registers block and block items
    public static <B extends Block> B registerBlock(Identifier name, B block, FabricItemSettings itemSettings) {
        BlockItem item = new BlockItem(block, (itemSettings));
        item.appendBlocks(Item.BLOCK_ITEMS, item);

        Registry.register(Registry.BLOCK, name, block);
        Registry.register(Registry.ITEM, name, item);
        return block;
    }

    // Item Registry Helper Functions
    // ******************************
    public static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, newID(name), item);
    };
}
