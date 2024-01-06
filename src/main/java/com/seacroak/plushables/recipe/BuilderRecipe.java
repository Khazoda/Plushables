package com.seacroak.plushables.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.function.Function;

public class BuilderRecipe implements Recipe<SimpleInventory>  {

  public static final Codec<BuilderRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
          RecipeCodecs.CRAFTING_RESULT.fieldOf("output").forGetter(recipe -> recipe.output),
          Ingredient.DISALLOW_EMPTY_CODEC.listOf().xmap(ingredients -> DefaultedList.copyOf(Ingredient.EMPTY, ingredients.toArray(Ingredient[]::new)), Function.identity()).fieldOf("ingredients").forGetter(recipe -> recipe.recipeItems)
  ).apply(instance, BuilderRecipe::new));

  private final ItemStack output;
  private final DefaultedList<Ingredient> recipeItems;

  public BuilderRecipe(ItemStack output, DefaultedList<Ingredient> recipeItems) {
    this.output = output;
    this.recipeItems = recipeItems;
  }

  @Override
  public boolean matches(SimpleInventory inventory, World world) {
    if (!recipeItems.get(0).test(inventory.getStack(0)))
      return false;
    if (!recipeItems.get(1).test(inventory.getStack(1)))
      return false;
    if (!recipeItems.get(2).test(inventory.getStack(2)))
      return false;
    return true;
  }

  @Override
  public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager drm) {
    return output;
  }

  @Override
  public boolean fits(int width, int height) {
    return true;
  }

  public DefaultedList<Ingredient> getRecipeItems() {
    return this.recipeItems;
  }

  @Override
  public ItemStack getResult(DynamicRegistryManager drm) {
    return output.copy();
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
    private Type() {}

    public static final Type INSTANCE = new Type();
    public static final String ID = "builder";
  }

  public static class Serializer implements RecipeSerializer<BuilderRecipe> {
    public static final Serializer INSTANCE = new Serializer();
    public static final String ID = "builder";
    // this is the name given in the json file

    @Override
    public Codec<BuilderRecipe> codec() {
      return BuilderRecipe.CODEC;
    }

    @Override
    public BuilderRecipe read(PacketByteBuf buf) {
      DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

      for (int i = 0; i < inputs.size(); i++) {
        inputs.set(i, Ingredient.fromPacket(buf));
      }
      ItemStack output = buf.readItemStack();
      return new BuilderRecipe(output, inputs);
    }

    @Override
    public void write(PacketByteBuf buf, BuilderRecipe recipe) {
      buf.writeInt(recipe.getRecipeItems().size());
      for (Ingredient ing : recipe.getRecipeItems()) {
        ing.write(buf);
      }
      buf.writeItemStack(recipe.output);
    }
  }

}
