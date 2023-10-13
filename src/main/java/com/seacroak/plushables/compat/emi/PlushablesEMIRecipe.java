package com.seacroak.plushables.compat.emi;

import com.seacroak.plushables.recipe.BuilderRecipe;
import com.seacroak.plushables.util.GenericUtils;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PlushablesEMIRecipe implements EmiRecipe {
  private final Identifier id;
  static final EmiTexture GUI_TEXTURE = new EmiTexture(GenericUtils.ID("textures/gui/builder_gui.png"), 0, 0, 176, 74);
  static final EmiTexture PROGRESS_TEXTURE = new EmiTexture(GenericUtils.ID("textures/gui/builder_gui.png"), 1, 186, 100, 30);

  static final int bounds_x = 0;
  static final int bounds_y = 0;

  private final List<EmiIngredient> inputs = new ArrayList<>();
  private final List<EmiStack> outputs = new ArrayList<>();

  public PlushablesEMIRecipe(BuilderRecipe recipe) {
    this.id = recipe.getId();
    this.inputs.add(EmiIngredient.of(recipe.getRecipeItems().get(0)));
    this.inputs.add(EmiIngredient.of(recipe.getRecipeItems().get(1)));
    this.inputs.add(EmiIngredient.of(recipe.getRecipeItems().get(2)));
    this.outputs.add(EmiStack.of(recipe.getResult(null)));
  }

  @Override
  public void addWidgets(WidgetHolder widgets) {
    int textureX = bounds_x + (getDisplayWidth() - 140) / 2;
    int textureY = bounds_y + (getDisplayHeight() - 63) / 2;

    widgets.addTexture(GUI_TEXTURE, 0, 0);

    /* Add Input Slots */
    widgets.addSlot(inputs.get(0), textureX + 13, textureY + 8).drawBack(false);
    widgets.addSlot(inputs.get(1), textureX + 13, textureY + 38).drawBack(false);
    widgets.addSlot(inputs.get(2), textureX + 43, textureY + 23).drawBack(false);

    /* Add Output Slots */
    widgets.addSlot(outputs.get(0), textureX + 112, textureY + 23).recipeContext(this).drawBack(false);

    /* Progress Arrow */
    widgets.addAnimatedTexture(PROGRESS_TEXTURE, textureX + 63, textureY + 17,5000 * 20/ 20 , true, false, false);
  }

  @Override
  public EmiRecipeCategory getCategory() {
    return PlushablesEMIPlugin.BUILDER_CATEGORY;
  }

  @Override
  public @Nullable Identifier getId() {
    return id;
  }

  @Override
  public List<EmiIngredient> getInputs() {
    return inputs;
  }

  @Override
  public List<EmiStack> getOutputs() {
    return outputs;
  }

  @Override
  public int getDisplayWidth() {
    return 176;
  }

  @Override
  public int getDisplayHeight() {
    return 74;
  }
}
