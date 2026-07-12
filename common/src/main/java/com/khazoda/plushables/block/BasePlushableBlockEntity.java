package com.khazoda.plushables.block;

import com.khazoda.plushables.registry.MainRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.ContainerSingleItem;

import java.util.List;

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
    this.setTheItemTransactionally(itemStack);
    this.commitTheItemTransfer();
  }

  public void setTheItemTransactionally(ItemStack itemStack) {
    this.item = itemStack;
  }

  public void commitTheItemTransfer() {
    this.setChanged();

    if (this.level != null) {
      this.level.updateNeighbourForOutputSignal(this.worldPosition, this.getBlockState().getBlock());
    }

    if (this.level instanceof ServerLevel serverLevel) {
      BasePlushable.tryExplodeStoredTnt(serverLevel, this.worldPosition);
    }
  }

  @Override
  public ItemStack splitTheItem(int amount) {
    if (this.item.isEmpty()) return ItemStack.EMPTY;

    ItemStack removedItem = this.item.split(amount);
    this.setTheItem(this.item.isEmpty() ? ItemStack.EMPTY : this.item);
    return removedItem;
  }

  @Override
  public boolean stillValid(Player player) {
    return Container.stillValidBlockEntity(this, player);
  }

  @Override
  public int getMaxStackSize() {
    return 1;
  }

  @Override
  public boolean canPlaceItem(int slot, ItemStack itemStack) {
    return slot == 0 && this.item.isEmpty() && BasePlushable.canStoreInPlushable(itemStack);
  }

  public boolean canTransferAccept(ItemStack itemStack) {
    return !itemStack.isEmpty() && BasePlushable.canStoreInPlushable(itemStack);
  }

  public boolean canTransferInsert(ItemStack itemStack) {
    return !itemStack.isEmpty() && this.canPlaceItem(0, itemStack);
  }

  public boolean canTransferExtract(ItemStack itemStack) {
    return !itemStack.isEmpty();
  }

  @Override
  protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
    super.saveAdditional(tag, registries);

    if (!this.item.isEmpty()) {
      tag.put("item", this.item.saveOptional(registries));
    }
  }

  @Override
  protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
    super.loadAdditional(tag, registries);
    this.item = ItemStack.parseOptional(registries, tag.getCompound("item"));
  }

  @Override
  protected void collectImplicitComponents(DataComponentMap.Builder componentMapBuilder) {
    super.collectImplicitComponents(componentMapBuilder);
    if (!this.item.isEmpty()) {
      componentMapBuilder.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(List.of(this.item.copy())));
    }
  }

  @Override
  protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
    super.applyImplicitComponents(componentInput);
    ItemStack storedItem = componentInput.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyOne();
    if (this.item.isEmpty() && !storedItem.isEmpty()) {
      this.setTheItem(storedItem);
    }
  }

  @Override
  @SuppressWarnings("deprecation")
  public void removeComponentsFromTag(CompoundTag tag) {
    tag.remove("item");
  }
}
