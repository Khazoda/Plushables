package com.khazoda.plushables;

import com.google.common.collect.MapMaker;
import com.khazoda.plushables.block.BasePlushableBlockEntity;
import com.khazoda.plushables.registry.MainRegistry;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.item.base.SingleStackStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public final class PlushablesFabricStorage {
  private static final Map<BasePlushableBlockEntity, Storage<ItemVariant>> STORAGES = new MapMaker().weakKeys().weakValues().makeMap();

  private PlushablesFabricStorage() {
  }

  public static void init() {
    ItemStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> storageFor(blockEntity), MainRegistry.PLUSHABLE_BLOCK_ENTITY.get());
  }

  private static Storage<ItemVariant> storageFor(BasePlushableBlockEntity blockEntity) {
    return STORAGES.computeIfAbsent(blockEntity, PlushablesFabricStorage::createStorage);
  }

  private static Storage<ItemVariant> createStorage(BasePlushableBlockEntity blockEntity) {
    return new SingleStackStorage() {
      @Override
      protected ItemStack getStack() {
        return blockEntity.getTheItem();
      }

      @Override
      protected void setStack(ItemStack stack) {
        blockEntity.setTheItemTransactionally(stack.isEmpty() ? ItemStack.EMPTY : stack);
      }

      @Override
      protected boolean canInsert(ItemVariant itemVariant) {
        return blockEntity.canTransferInsert(itemVariant.toStack());
      }

      @Override
      protected boolean canExtract(ItemVariant itemVariant) {
        return blockEntity.canTransferExtract(itemVariant.toStack());
      }

      @Override
      protected int getCapacity(ItemVariant itemVariant) {
        return blockEntity.getMaxStackSize();
      }

      @Override
      protected void onFinalCommit() {
        blockEntity.commitTheItemTransfer();
      }
    };
  }
}