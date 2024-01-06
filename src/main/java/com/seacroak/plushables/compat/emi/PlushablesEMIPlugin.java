package com.seacroak.plushables.compat.emi;

import com.seacroak.plushables.recipe.BuilderRecipe;
import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.registry.client.ScreenRegistry;
import com.seacroak.plushables.util.GenericUtils;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.util.Identifier;

public class PlushablesEMIPlugin implements EmiPlugin {
  private static final Identifier ID = GenericUtils.ID("plushable_builder");
  public static final EmiStack BUILDER_BLOCK = EmiStack.of(MainRegistry.BUILDER_BLOCK);
  public static final EmiRecipeCategory BUILDER_CATEGORY = new EmiRecipeCategory(ID, BUILDER_BLOCK);

  @Override
  public void register(EmiRegistry registry) {
    registry.addCategory(BUILDER_CATEGORY);
    registry.addWorkstation(BUILDER_CATEGORY, BUILDER_BLOCK);

    for (RecipeEntry<BuilderRecipe> recipe : registry.getRecipeManager().listAllOfType(BuilderRecipe.Type.INSTANCE)) {
      registry.addRecipe(new PlushablesEMIRecipe(recipe));
    }

    registry.addRecipeHandler(ScreenRegistry.BUILDER_SCREEN_HANDLER, new BuilderEMIRecipeHandler());
  }
}
