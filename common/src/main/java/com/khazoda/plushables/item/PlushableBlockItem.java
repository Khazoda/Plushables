package com.khazoda.plushables.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

/**
 * Middleman class between a Plushable and a standard Item.
 * All plushable BlockItems extend this class in order for the rendering mixin to be able to know what items to apply player transformations to.
 */
public class PlushableBlockItem extends BlockItem {
  public PlushableBlockItem(Block block, Properties properties) {
    super(block, properties);
  }
}
