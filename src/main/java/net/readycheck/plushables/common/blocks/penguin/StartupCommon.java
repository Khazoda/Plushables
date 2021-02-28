package net.readycheck.plushables.common.blocks.penguin;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.readycheck.plushables.common.Plushables;

/**
 * User: The Grey Ghost
 * Date: 24/12/2014
 *
 * The Startup classes for this example are called during startup, in the following order:
 * onBlocksRegistration then onItemsRegistration
 *  See MinecraftByExample class for more information
 */
public class StartupCommon
{
//  public static Penguin penguin;  // this holds the unique instance of your block
//  public static BlockItem itemPenguin;  // this holds the unique instance of the ItemBlock corresponding to your block
//
//  @SubscribeEvent
//  public static void onBlocksRegistration(final RegistryEvent.Register<Block> blockRegisterEvent) {
//    penguin = (Penguin) (new Penguin().setRegistryName("plushables", "penguin"));
//    blockRegisterEvent.getRegistry().register(penguin);
//  }
//
//  @SubscribeEvent
//  public static void onItemsRegistration(final RegistryEvent.Register<Item> itemRegisterEvent) {
//    // We need to create a BlockItem so the player can carry this block in their hand and it can appear in the inventory
//
//    Item.Properties itemPenguinProperties = new Item.Properties()
//                                                   .maxStackSize(64)
//                                                   .group(Plushables.PlushablesGroup);  // which inventory tab?
//    itemPenguin = new BlockItem(penguin, itemPenguinProperties);
//    itemPenguin.setRegistryName(penguin.getRegistryName());
//    itemRegisterEvent.getRegistry().register(itemPenguin);
//  }



  @SubscribeEvent
  public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
    // not actually required for this example....
  }
}
