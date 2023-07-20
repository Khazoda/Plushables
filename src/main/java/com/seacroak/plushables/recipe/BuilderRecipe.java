package com.seacroak.plushables.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class BuilderRecipe implements Recipe<SimpleContainer> {

  private final ResourceLocation id;
  private final ItemStack output;
  private final NonNullList<Ingredient> recipeItems;

  public BuilderRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
    this.id = id;
    this.output = output;
    this.recipeItems = recipeItems;
  }

  @Override
  public boolean matches(SimpleContainer inventory, Level level) {
    if (!recipeItems.get(0).test(inventory.getItem(0))) return false;
    if (!recipeItems.get(1).test(inventory.getItem(1))) return false;
    if (!recipeItems.get(2).test(inventory.getItem(2))) return false;
    return true;
  }

  @Override
  public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
    return output;
  }

  @Override
  public boolean canCraftInDimensions(int pWidth, int pHeight) {
    return true;
  }

  public NonNullList<Ingredient> getRecipeItems() {
    return this.recipeItems;
  }

  @Override
  public ItemStack getResultItem(RegistryAccess drm) {
    return output.copy();
  }

  @Override
  public ResourceLocation getId() {
    return id;
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return Serializer.INSTANCE;
  }

  @Override
  public RecipeType<?> getType() {
    return Type.INSTANCE;
  }

  public static class Type implements RecipeType<BuilderRecipe> {
    private Type() {
    }

    public static final Type INSTANCE = new Type();
    public static final String ID = "builder";
  }

  public static class Serializer implements RecipeSerializer<BuilderRecipe> {
    public static final Serializer INSTANCE = new Serializer();
    public static final String ID = "builder";

    @Override
    public BuilderRecipe fromJson(ResourceLocation id, JsonObject json) {
      ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

      JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
      NonNullList<Ingredient> inputs = NonNullList.withSize(3, Ingredient.EMPTY);

      for (int i = 0; i < inputs.size(); i++) {
        inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
      }
      return new BuilderRecipe(id, output, inputs);
    }

    @Override
    public @Nullable BuilderRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
      NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
      for (int i = 0; i < inputs.size(); i++) {
        inputs.set(i, Ingredient.fromNetwork(buf));
      }
      ItemStack output = buf.readItem();
      return new BuilderRecipe(id, output, inputs);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, BuilderRecipe recipe) {
      buf.writeInt(recipe.getRecipeItems().size());
      for (Ingredient ing : recipe.getRecipeItems()) {
        ing.toNetwork(buf);
      }
      buf.writeItem(recipe.output);
    }
  }
}
