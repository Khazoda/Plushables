package com.khazoda.plushables.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

/**
 * Middleman class between a Plushable and a standard Item.
 * All plushable BlockItems extend this class in order for the rendering mixin to be able to know what items to apply player transformations to.
 */
public class PlushableBlockItem extends BlockItem implements Equipable {
  public PlushableBlockItem(Block block, Properties properties) {
    super(block, properties);
  }

  @Override
  public EquipmentSlot getEquipmentSlot() {
    return EquipmentSlot.HEAD;
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
    return this.swapWithEquipmentSlot(this, level, player, usedHand);
  }
}
