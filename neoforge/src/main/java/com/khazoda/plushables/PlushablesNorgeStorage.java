package com.khazoda.plushables;

import com.google.common.collect.MapMaker;
import com.khazoda.plushables.block.BasePlushableBlockEntity;
import com.khazoda.plushables.registry.MainRegistry;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.Map;

public final class PlushablesNorgeStorage {
  private static final Map<BasePlushableBlockEntity, IItemHandler> HANDLERS = new MapMaker().weakKeys().weakValues().makeMap();

  private PlushablesNorgeStorage() {
  }

  public static void registerCapabilities(RegisterCapabilitiesEvent event) {
    event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, MainRegistry.PLUSHABLE_BLOCK_ENTITY.get(), (blockEntity, direction) -> handlerFor(blockEntity));
  }

  private static IItemHandler handlerFor(BasePlushableBlockEntity blockEntity) {
    return HANDLERS.computeIfAbsent(blockEntity, PlushableItemHandler::new);
  }

  private static final class PlushableItemHandler implements IItemHandler {
    private final BasePlushableBlockEntity blockEntity;

    private PlushableItemHandler(BasePlushableBlockEntity blockEntity) {
      this.blockEntity = blockEntity;
    }

    @Override
    public int getSlots() {
      return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
      if (slot != 0) return ItemStack.EMPTY;
      return this.blockEntity.getTheItem().copy();
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
      if (slot != 0 || stack.isEmpty() || !this.blockEntity.canTransferInsert(stack)) return stack;

      ItemStack remainder = stack.copy();
      remainder.shrink(1);

      if (!simulate) {
        this.blockEntity.setTheItem(stack.copyWithCount(1));
      }

      return remainder.isEmpty() ? ItemStack.EMPTY : remainder;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
      ItemStack storedItem = this.blockEntity.getTheItem();
      if (slot != 0 || amount <= 0 || !this.blockEntity.canTransferExtract(storedItem)) return ItemStack.EMPTY;

      ItemStack extracted = storedItem.copyWithCount(Math.min(amount, storedItem.getCount()));
      if (!simulate) {
        this.blockEntity.splitTheItem(extracted.getCount());
      }

      return extracted;
    }

    @Override
    public int getSlotLimit(int slot) {
      return slot == 0 ? this.blockEntity.getMaxStackSize() : 0;
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
      return slot == 0 && this.blockEntity.canTransferAccept(stack);
    }
  }
}