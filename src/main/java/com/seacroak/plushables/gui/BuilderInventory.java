package com.seacroak.plushables.gui;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * A simple {@code Inventory} implementation with only default methods + an item
 * list getter.
 *
 * Originally by Juuz
 */
public interface BuilderInventory extends Container {

    /**
     * Retrieves the item list of this inventory.
     * Must return the same instance every time it's called.
     */
    NonNullList<ItemStack> getItems();

    /**
     * Creates an inventory from the item list.
     */
    static BuilderInventory of(NonNullList<ItemStack> items) {
        return () -> items;
    }

    /**
     * Creates a new inventory with the specified size.
     */
    static BuilderInventory ofSize(int size) {
        return of(NonNullList.withSize(size, ItemStack.EMPTY));
    }

    /**
     * Returns the inventory size.
     */
    @Override
    default int getContainerSize() {
        return getItems().size();
    };

    /**
     * Checks if the inventory is empty.
     * 
     * @return true if this inventory has only empty stacks, false otherwise.
     */
    @Override
    default boolean isEmpty() {
        for (int i = 0; i < getContainerSize(); i++) {
            ItemStack stack = getItem(i);
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves the item in the slot.
     */
    @Override
    default ItemStack getItem(int pSlot) {return getItems().get(pSlot);}

    /**
     * Removes items from an inventory slot.
     * 
     * @param pSlot  The slot to remove from.
     * @param pAmount How many items to remove. If there are less items in the slot
     *              than what are requested,
     *              takes all items in that slot.
     */
    @Override
    default ItemStack removeItem(int pSlot, int pAmount) {
        ItemStack result = ContainerHelper.removeItem(getItems(), pSlot, pAmount);
        if (!result.isEmpty()) {
            setChanged();
        }
        return result;
    };

    /**
     * Removes all items from an inventory slot.
     * 
     * @param pSlot The slot to remove from.
     */

//    64 might be incorrect here, I don't know how to remove all items from a slot.
    @Override
    default ItemStack removeItemNoUpdate(int pSlot) {
        return ContainerHelper.removeItem(getItems(), pSlot,64);
    };


    /**
     * Replaces the current stack in an inventory slot with the provided stack.
     * 
     * @param pSlot  The inventory slot of which to replace the itemstack.
     * @param pStack The replacing itemstack. If the stack is too big for
     *              this inventory ({@link Container#getContainerSize()}),
     *              it gets resized to this inventory's maximum amount.
     */
    @Override
    default void setItem(int pSlot, ItemStack pStack) {
        getItems().set(pSlot, pStack);
        if (pStack.getCount() > getMaxStackSize()) {
            pStack.setCount(getMaxStackSize());
        }
    };

    /**
     * Clears the inventory.
     */
    @Override
    default void clearContent() {
        getItems().clear();
    };

    /**
     * Marks the state as dirty.
     * Must be called after changes in the inventory, so that the game can properly
     * save
     * the inventory contents and notify neighboring blocks of inventory changes.
     */
    @Override
    default void setChanged() {
        // Override if you want behavior.
    };

    /**
     * @return true if the player can use the inventory, false otherwise.
     */
    @Override
    default boolean stillValid(Player pPlayer) {return true;}
}
