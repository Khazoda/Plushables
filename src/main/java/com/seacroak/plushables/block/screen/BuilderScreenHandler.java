package com.seacroak.plushables.block.screen;

import com.seacroak.plushables.registry.client.ScreenRegistry;
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
    this.inventory = inventory;
    this.world = playerInventory.player.getWorld();
    this.propertyDelegate = delegate;

    checkSize(inventory, 4);
    inventory.onOpen(playerInventory.player);

    this.addSlot(new Slot(inventory, 0, 32, 14));    // Item Slot
    this.addSlot(new Slot(inventory, 1, 32, 44));    // Wool Slot
    this.addSlot(new Slot(inventory, 2, 62, 29));    // Heart of Gold Slot
    this.addSlot(new Slot(inventory, 3, 131, 29));   // Output Slot

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
    int progressArrowSize = 74; // This is the width in pixels of your arrow

    return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
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
