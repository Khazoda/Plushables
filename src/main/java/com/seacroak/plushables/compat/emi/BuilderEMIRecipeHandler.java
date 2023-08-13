package com.seacroak.plushables.compat.emi;

import com.seacroak.plushables.block.screen.BuilderScreenHandler;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.handler.StandardRecipeHandler;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BuilderEMIRecipeHandler implements StandardRecipeHandler<BuilderScreenHandler> {
  @Override
  public List<Slot> getInputSources(BuilderScreenHandler handler) {
    List<Slot> slots = new ArrayList<>();
    /* 0 - 2 = Builder Slots || 3 - 39 = Inventory Slots, top to bottom */
    for (int i = 0; i < handler.slots.size(); i++) {
      slots.add(handler.slots.get(i));
    }
    return slots;
  }

  @Override
  public List<Slot> getCraftingSlots(BuilderScreenHandler handler) {
    List<Slot> slots = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      slots.add(handler.slots.get(i));
    }
    return slots;
  }

  @Override
  public @Nullable Slot getOutputSlot(BuilderScreenHandler handler) {
    return null;
  }

  @Override
  public boolean supportsRecipe(EmiRecipe recipe) {
    return recipe.getCategory() == PlushablesEMIPlugin.BUILDER_CATEGORY && recipe.supportsRecipeTree();
  }
}
