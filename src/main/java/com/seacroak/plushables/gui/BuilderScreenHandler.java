package com.seacroak.plushables.gui;

import com.seacroak.plushables.registry.ScreenRegistry;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;


public class BuilderScreenHandler extends AbstractContainerMenu {
  private final Container container;
  private final Level level;
  private final ContainerData propertyDelegate;


  public BuilderScreenHandler(int id, Inventory inventory, FriendlyByteBuf friendlyByteBuf) {
    this(id, inventory, inventory.player.level().getBlockEntity(friendlyByteBuf.readBlockPos()), new SimpleContainerData(2));
  }

//  public BuilderScreenHandler(int syncId, PlayerInventory playerInventory) {
//    this(syncId, playerInventory, new SimpleInventory(4), new ArrayPropertyDelegate(2));
//  }

  public BuilderScreenHandler(int syncId, Inventory inventory,
                              BlockEntity entity, ContainerData delegate) {
    super(ScreenRegistry.BUILDER_SCREEN_HANDLER.get(), syncId);
    checkContainerSize(inventory, 4);
    this.container = inventory;
//      this.world = playerInventory.player.method_48926();
    this.level = inventory.player.level();
    inventory.startOpen(inventory.player);
    this.propertyDelegate = delegate;

    // Top Slot
    this.addSlot(new Slot(inventory, 0, 55, 20));
    // Heart of Gold Slot
    this.addSlot(new Slot(inventory, 3, 55, 39));
    // Bottom Slot
    this.addSlot(new Slot(inventory, 1, 55, 58));
    // Output Slot
    this.addSlot(new Slot(inventory, 2, 98, 39));

    addPlayerInventory(inventory);
    addPlayerHotbar(inventory);
    addDataSlots(delegate);
  }

  public boolean isCrafting() {
    return propertyDelegate.get(0) > 0;
  }

  public int getScaledProgress() {
    int progress = this.propertyDelegate.get(0);
    int maxProgress = this.propertyDelegate.get(1); // Max Progress
    int progressArrowSize = 24; // This is the width in pixels of your arrow

    return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
  }

  @Override
  public boolean stillValid(Player pPlayer) {
    return this.container.stillValid(pPlayer);
  }

  @Override
  public ItemStack quickMoveStack(Player pPlayer, int invSlot) {
    ItemStack newStack = ItemStack.EMPTY;
    Slot slot = this.slots.get(invSlot);
    if (slot != null && slot.hasItem()) {
      ItemStack originalStack = slot.getItem();
      newStack = originalStack.copy();
      if (invSlot < this.container.getContainerSize()) {
        if (!this.moveItemStackTo(originalStack, this.container.getContainerSize(), this.slots.size(), true)) {
          return ItemStack.EMPTY;
        }
      } else if (!this.moveItemStackTo(originalStack, 0, this.container.getContainerSize(), false)) {
        return ItemStack.EMPTY;
      }
      if (originalStack.isEmpty()) {
        slot.set(ItemStack.EMPTY);
      } else {
        slot.setChanged();
      }
    }
    return newStack;
  }

  private void addPlayerInventory(Inventory playerInventory) {
    for (int i = 0; i < 3; ++i) {
      for (int l = 0; l < 9; ++l) {
        this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
      }
    }
  }

  private void addPlayerHotbar(Inventory playerInventory) {
    for (int i = 0; i < 9; ++i) {
      this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
    }
  }

}
