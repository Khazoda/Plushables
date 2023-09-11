package com.seacroak.plushables.util;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.item.PlushableBlockItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RegistryHelper {

  // General use Identifier() maker function
  public static Identifier newID(String name) {
    return new Identifier(PlushablesMod.MOD_ID, name);
  }

  // Block Registry Helper Functions
  // *******************************
  // 1. Default BlockItem Registration Entrypoint: creates Identifier from ModID & block name
  public static <B extends Block> B registerBlock(String name, B block, FabricItemSettings itemSettings) {
    return registerBlock(newID(name), block, itemSettings);
  }

  // 2. Takes identifier and registers block and block items
  public static <B extends Block> B registerBlock(Identifier name, B block, FabricItemSettings itemSettings) {
    BlockItem item = new BlockItem(block, (itemSettings));
    item.appendBlocks(Item.BLOCK_ITEMS, item);

    Registry.register(Registries.BLOCK, name, block);
    Registry.register(Registries.ITEM, name, item);
    return block;
  }

  public static <B extends Block> B registerBlockOnly(String name, B block) {
    return registerBlockOnly(newID(name), block);
  }

  public static <B extends Block> B registerBlockOnly(Identifier name, B block) {
    Registry.register(Registries.BLOCK, name, block);
    return block;
  }

  public static <I extends BlockItem> I registerBlockItem(String name, I blockItem) {
    return registerBlockItem(newID(name), blockItem);
  }

  public static <I extends BlockItem> I registerBlockItem(Identifier name, I blockItem) {
    Registry.register(Registries.ITEM, name, blockItem);
    return blockItem;
  }

  // Block Registry Helper Functions (Variant for Plushable Blocks)
  public static <B extends Block> B registerPlushableBlock(String name, B block, FabricItemSettings itemSettings) {
    return registerPlushableBlock(newID(name), block, itemSettings);
  }

  public static <B extends Block> B registerPlushableBlock(Identifier name, B block, FabricItemSettings itemSettings) {
    BlockItem item = new PlushableBlockItem(block, (itemSettings));
    item.appendBlocks(Item.BLOCK_ITEMS, item);

    Registry.register(Registries.BLOCK, name, block);
    Registry.register(Registries.ITEM, name, item);
    return block;
  }

  // Item Registry Helper Functions
  // ******************************
  public static Item registerItem(String name, Item item) {
    return Registry.register(Registries.ITEM, newID(name), item);
  }
}
