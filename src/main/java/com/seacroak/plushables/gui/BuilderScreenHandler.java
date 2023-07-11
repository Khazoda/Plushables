package com.seacroak.plushables.gui;

import com.seacroak.plushables.registry.ScreenRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

public class BuilderScreenHandler extends ScreenHandler {
  private final Inventory inventory;
  private final World world;
  private final PropertyDelegate propertyDelegate;

  public BuilderScreenHandler(int syncId, PlayerInventory playerInventory) {
    this(syncId, playerInventory, new SimpleInventory(4), new ArrayPropertyDelegate(2));
  }

  public BuilderScreenHandler(int syncId, PlayerInventory playerInventory,
                              Inventory inventory, PropertyDelegate delegate) {
    super(ScreenRegistry.BUILDER_SCREEN_HANDLER, syncId);
    checkSize(inventory, 4);
    this.inventory = inventory;
//      this.world = playerInventory.player.method_48926();
    this.world = playerInventory.player.getWorld();
    inventory.onOpen(playerInventory.player);
    this.propertyDelegate = delegate;

    // Top Slot
    this.addSlot(new Slot(inventory, 0, 55, 20));
    // Heart of Gold Slot
    this.addSlot(new Slot(inventory, 3, 55, 39));
    // Bottom Slot
    this.addSlot(new Slot(inventory, 1, 55, 58));

    // Output Slot
    this.addSlot(new Slot(inventory, 2, 98, 39));

    addPlayerInventory(playerInventory);
    addPlayerHotbar(playerInventory);

    addProperties(delegate);
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

  public boolean isLightningStorm() {
    return world.isThundering();
  }

  @Override
  public boolean canUse(PlayerEntity player) {
    return this.inventory.canPlayerUse(player);
  }

  @Override
  public ItemStack quickMove(PlayerEntity player, int invSlot) {
    ItemStack newStack = ItemStack.EMPTY;
    Slot slot = this.slots.get(invSlot);
    if (slot != null && slot.hasStack()) {
      ItemStack originalStack = slot.getStack();
      newStack = originalStack.copy();
      if (invSlot < this.inventory.size()) {
        if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
          return ItemStack.EMPTY;
        }
      } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
        return ItemStack.EMPTY;
      }

      if (originalStack.isEmpty()) {
        slot.setStack(ItemStack.EMPTY);
      } else {
        slot.markDirty();
      }
    }

    return newStack;
  }

  private void addPlayerInventory(PlayerInventory playerInventory) {
    for (int i = 0; i < 3; ++i) {
      for (int l = 0; l < 9; ++l) {
        this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
      }
    }
  }

  private void addPlayerHotbar(PlayerInventory playerInventory) {
    for (int i = 0; i < 9; ++i) {
      this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
    }
  }
}