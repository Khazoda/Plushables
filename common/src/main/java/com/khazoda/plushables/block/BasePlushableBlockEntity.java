package com.khazoda.plushables.block;

import com.khazoda.plushables.registry.MainRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.ContainerSingleItem;

public class BasePlushableBlockEntity extends BlockEntity implements ContainerSingleItem {

  private ItemStack item = ItemStack.EMPTY;

  public BasePlushableBlockEntity(BlockPos pos, BlockState blockState) {
    super(MainRegistry.PLUSHABLE_BLOCK_ENTITY.get(), pos, blockState);
  }

  @Override
  public ItemStack getTheItem() {
    return this.item;
  }

  @Override
  public void setTheItem(ItemStack itemStack) {
    this.item = itemStack;
    this.setChanged();
  }

  @Override
  public boolean stillValid(Player player) {
    return Container.stillValidBlockEntity(this, player);
  }

  @Override
  protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
    super.saveAdditional(tag, registries);

    if (!this.item.isEmpty()) {
      tag.put("Item", this.item.saveOptional(registries));
    }
  }

  @Override
  protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
    super.loadAdditional(tag, registries);
    this.item = ItemStack.parseOptional(registries, tag.getCompound("Item"));
  }
}