package com.khazoda.plushables.registry;

import com.khazoda.plushables.PlushablesCommon;
import com.khazoda.plushables.block.plushable.PlushableAnimatronicBlock;
import com.khazoda.plushables.block.plushable.PlushableBlahajBlock;
import com.khazoda.plushables.block.plushable.PlushablePenguinBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class MainRegistry {
  private static final Reggie<Block> BLOCK_REGISTRAR = PlushablesCommon.REGISTRARS.get(Registries.BLOCK);
  public static final Supplier<Block> PLUSHABLE_ANIMATRONIC_BLOCK = BLOCK_REGISTRAR.register("plushable_animatronic", PlushableAnimatronicBlock::new);

  /* ==========[  Block Registration  ]========== */
  public static final Supplier<Block> PLUSHABLE_PENGUIN_BLOCK = BLOCK_REGISTRAR.register("plushable_penguin", PlushablePenguinBlock::new);
  public static final Supplier<Block> PLUSHABLE_BLAHAJ_BLOCK = BLOCK_REGISTRAR.register("plushable_blahaj", PlushableBlahajBlock::new);
  private static final Reggie<Item> ITEM_REGISTRAR = PlushablesCommon.REGISTRARS.get(Registries.ITEM);

  /* ==========[  Item Registration  ]========== */
  /* ==========[  BlockItem Registration  ]========== */
  public static final Supplier<BlockItem> PLUSHABLE_PENGUIN_ITEM = registerBlockItem("plushable_penguin", PLUSHABLE_PENGUIN_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_ANIMATRONIC_ITEM = registerBlockItem("plushable_animatronic", PLUSHABLE_ANIMATRONIC_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_BLAHAJ_ITEM = registerBlockItem("plushable_blahaj", PLUSHABLE_BLAHAJ_BLOCK);


  public static void init() {
  }

  private static Supplier<BlockItem> registerBlockItem(String name, Supplier<Block> block) {
    return ITEM_REGISTRAR.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
  }
}
