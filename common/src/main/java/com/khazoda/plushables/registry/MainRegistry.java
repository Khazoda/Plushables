package com.khazoda.plushables.registry;

import com.khazoda.plushables.PlushablesCommon;
import com.khazoda.plushables.block.plushables.PlushablePenguinBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class MainRegistry {
  private static final Reggie<Block> BLOCK_REGISTRAR = PlushablesCommon.REGISTRARS.get(Registries.BLOCK);
  /* ==========[  Block Registration  ]========== */
  public static final Supplier<Block> PLUSHABLE_PENGUIN_BLOCK = BLOCK_REGISTRAR.register("plushable_penguin", PlushablePenguinBlock::new);

  /* ==========[  Item Registration  ]========== */
  private static final Reggie<Item> ITEM_REGISTRAR = PlushablesCommon.REGISTRARS.get(Registries.ITEM);
  /* ==========[  BlockItem Registration  ]========== */
  public static final Supplier<BlockItem> PLUSHABLE_PENGUIN_ITEM = ITEM_REGISTRAR.register("plushable_penguin",
      () -> new BlockItem(PLUSHABLE_PENGUIN_BLOCK.get(), new Item.Properties()));


  public static void init() {
  }
}
