package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.PenguinBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class MainRegistry {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PlushablesMod.MOD_ID);
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PlushablesMod.MOD_ID);


  // Complex Plushables

  //  Simple Plushables
  public static final RegistryObject<Block> PENGUIN_PLUSHABLE = registerBlock("penguin_plushable", () -> new PenguinBlock());

  // Item
  public static final RegistryObject<Item> HEART_OF_GOLD = ITEMS.register("heart_of_gold", () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> POWERED_HEART = ITEMS.register("powered_heart", () -> new Item(new Item.Properties()));


  //  Block Register Generic Helper Function
  private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
    RegistryObject<T> toReturn = BLOCKS.register(name, block);
    registerBlockItem(name, toReturn);
    return toReturn;
  }


  //  Block Item Register Generic Helper Function
  private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
    return ITEMS.register(name, () -> new BlockItem(block.get(),
        new Item.Properties()));
  }

  //  Register All
  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
    BLOCKS.register(eventBus);
  }

}
