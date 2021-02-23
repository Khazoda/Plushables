package net.readycheck.plushables.common.blocks.recycler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.readycheck.plushables.common.Plushables;

import javax.annotation.Nullable;

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
  public static Recycler recycler;  // this holds the unique instance of your block
  public static BlockItem itemRecycler;  // this holds the unique instance of the ItemBlock corresponding to your block

  @SubscribeEvent
  public static void onBlocksRegistration(final RegistryEvent.Register<Block> blockRegisterEvent) {
    recycler = (Recycler) (new Recycler().setRegistryName("plushables", "recycler"));
    blockRegisterEvent.getRegistry().register(recycler);
  }

  @SubscribeEvent
  public static void onItemsRegistration(final RegistryEvent.Register<Item> itemRegisterEvent) {
    // We need to create a BlockItem so the player can carry this block in their hand and it can appear in the inventory

    Item.Properties itemRecyclerProperties = new Item.Properties()
                                                   .maxStackSize(64)
                                                   .group(Plushables.PlushablesGroup);  // which inventory tab?
    itemRecycler = new BlockItem(recycler, itemRecyclerProperties);
    itemRecycler.setRegistryName(recycler.getRegistryName());
    itemRegisterEvent.getRegistry().register(itemRecycler);
  }



  @SubscribeEvent
  public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
    // not actually required for this example....
  }
}
