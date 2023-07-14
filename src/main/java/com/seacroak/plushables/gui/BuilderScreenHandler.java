package com.seacroak.plushables.gui;

import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.registry.ScreenRegistry;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;


public class BuilderScreenHandler extends AbstractContainerMenu {
//  private final Container container;
//  private final Level level;
//  private final ContainerData propertyDelegate;

  private final BuilderTileEntity blockEntity;
  private final Level level;
  private final ContainerData data;


//  public BuilderScreenHandler(int syncId, Inventory playerInventory, FriendlyByteBuf buf) {
//    this(syncId, playerInventory, new SimpleContainer(4), new SimpleContainerData(2));
//  }

  public BuilderScreenHandler(int id, Inventory inventory, FriendlyByteBuf friendlyByteBuf) {
    this(id, inventory, inventory.player.level().getBlockEntity(friendlyByteBuf.readBlockPos()), new SimpleContainerData(2));

  }

  public BuilderScreenHandler(int syncId, Inventory inventory,
                              BlockEntity entity, ContainerData delegate) {
    super(ScreenRegistry.BUILDER_SCREEN_HANDLER.get(), syncId);
    checkContainerSize(inventory, 4);
    this.blockEntity = ((BuilderTileEntity) entity);
    this.level = inventory.player.level();
    this.data = delegate;

    addPlayerInventory(inventory);
    addPlayerHotbar(inventory);

    this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
      this.addSlot(new SlotItemHandler(handler, 0, 55, 20));    // Top Slot
      this.addSlot(new SlotItemHandler(handler, 3, 55, 39));    // Heart of Gold Slot
      this.addSlot(new SlotItemHandler(handler, 1, 55, 58));    // Bottom Slot
      this.addSlot(new SlotItemHandler(handler, 2, 98, 39));    // Output Slot
    });
    addDataSlots(data);
  }

  public boolean isCrafting() {
    return data.get(0) > 0;
  }

  public int getScaledProgress() {
    int progress = this.data.get(0);
    int maxProgress = this.data.get(1); // Max Progress
    int progressArrowSize = 24; // This is the width in pixels of your arrow

    return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
  }

  @Override
  public ItemStack quickMoveStack(Player pPlayer, int invSlot) {
    ItemStack newStack = ItemStack.EMPTY;
    Slot slot = this.slots.get(invSlot);
    if (slot != null && slot.hasItem()) {
      ItemStack originalStack = slot.getItem();
      newStack = originalStack.copy();
      if (invSlot < this.blockEntity.getContainerSize()) {
        if (!this.moveItemStackTo(originalStack, this.blockEntity.getContainerSize(), this.slots.size(), true)) {
          return ItemStack.EMPTY;
        }
      } else if (!this.moveItemStackTo(originalStack, 0, this.blockEntity.getContainerSize(), false)) {
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

  @Override
  public boolean stillValid(Player pPlayer) {
    return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
        pPlayer, MainRegistry.BUILDER_BLOCK.get());
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
