package com.khazoda.plushables.datagen.provider;

import com.khazoda.plushables.registry.MainRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class PlushablesRecipeProvider extends FabricRecipeProvider {

  public PlushablesRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
    super(output, registriesFuture);
  }

  @Override
  public void buildRecipes(RecipeOutput output) {
    /* ==========[ Heart of Gold ]========== */
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MainRegistry.HEART_OF_GOLD_ITEM.get())
        .pattern("X#X")
        .pattern("#^#")
        .pattern(" # ")
        .define('#', Items.GOLD_NUGGET)
        .define('^', Items.HONEY_BOTTLE)
        .define('X', ItemTags.FLOWERS)
        .unlockedBy("has_gold_nugget", has(Items.GOLD_NUGGET))
        .unlockedBy("has_flowers", has(ItemTags.FLOWERS))
        .unlockedBy("has_honey_bottle", has(Items.HONEY_BOTTLE))
        .save(output);

    /* ==========[ Plushables ]========== */
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_ANIMATRONIC_ITEM.get(), Items.REDSTONE, Items.BLUE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_WISP_ITEM.get(), Items.ECHO_SHARD, Items.LIGHT_BLUE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_WIZARD_ITEM.get(), Items.GOLD_INGOT, Items.PURPLE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_ZIGGY_ITEM.get(), ItemTags.FISHES, Items.WHITE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_WHALE_ITEM.get(), Items.KELP, Items.BLUE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_WHELPLING_ITEM.get(), Items.BLAZE_POWDER, Items.RED_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_TRUFFLES_ITEM.get(), Items.LILY_OF_THE_VALLEY, Items.PINK_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_UNICORN_ITEM.get(), Items.CHERRY_LEAVES, Items.WHITE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_WALRUS_ITEM.get(), Items.SEA_PICKLE, Items.BROWN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_TIGER_ITEM.get(), Items.PORKCHOP, Items.ORANGE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_TRATER_ITEM.get(), Items.POISONOUS_POTATO, Items.CYAN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_TRICERATOPS_ITEM.get(), Items.FERN, Items.GREEN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_SHRUMP_ITEM.get(), Items.RED_MUSHROOM, Items.WHITE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_SNAIL_ITEM.get(), Items.SLIME_BALL, Items.CYAN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_STATUETTE_ITEM.get(), Items.TERRACOTTA, Items.BROWN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_RUPERT_ITEM.get(), ItemTags.FISHES, Items.BLACK_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_SEA_BUNNY_ITEM.get(), Items.SEAGRASS, Items.WHITE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_POTSY_ITEM.get(), Items.CAULDRON, Items.BLACK_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_RAPTOR_ITEM.get(), Items.FLINT, Items.BLUE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_RATTIAM_ITEM.get(), Items.WHEAT, Items.LIGHT_GRAY_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_OWL_ITEM.get(), Items.GLOW_BERRIES, Items.BROWN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_PENGUIN_ITEM.get(), Items.SNOWBALL, Items.GRAY_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_PIG_ITEM.get(), Items.MUD, Items.PINK_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_ORANGUTAN_ITEM.get(), Items.VINE, Items.ORANGE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_OTTER_ITEM.get(), ItemTags.FISHES, Items.BROWN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_MOOBLOOM_ITEM.get(), Items.SUNFLOWER, Items.YELLOW_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_OCTOPLUSHABLE_ITEM.get(), Items.INK_SAC, Items.LIGHT_BLUE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_MAMMOTH_ITEM.get(), Items.POINTED_DRIPSTONE, Items.BROWN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_GOBLIN_ITEM.get(), Items.RED_MUSHROOM, Items.LIME_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_GOLDFISH_ITEM.get(), Items.GOLD_INGOT, Items.ORANGE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_HAMSTER_ITEM.get(), Items.CARROT, Items.ORANGE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_FOX_ITEM.get(), Items.PUMPKIN, Items.ORANGE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_FROGE_ITEM.get(), Items.LILY_PAD, Items.YELLOW_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_FROGLIN_ITEM.get(), Items.LILY_PAD, Items.GREEN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_DJUNGELSKOG_ITEM.get(), Items.HONEYCOMB, Items.BROWN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_DORMOUSE_ITEM.get(), Items.OAK_LEAVES, Items.BROWN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_DRAGON_ITEM.get(), Items.BLAZE_ROD, Items.WHITE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_CONDUCTOR_ITEM.get(), Items.RAIL, Items.GRAY_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_COOPER_ITEM.get(), Items.BONE, Items.BLACK_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_CLUCKY_ITEM.get(), Items.EGG, Items.WHITE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_BIG_IRRITATER_ITEM.get(), Items.POTATO, Items.RED_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_BIG_TATER_ITEM.get(), Items.POTATO, Items.PINK_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_BLAHAJ_ITEM.get(), Items.PINK_PETALS, Items.LIGHT_BLUE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_BEAUX_ITEM.get(), Items.BONE, Items.GRAY_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_SNOWIE_ITEM.get(), Items.SNOWBALL, Items.WHITE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_RIBBIT_ITEM.get(), Items.FISHING_ROD, Items.LIME_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_CREAKY_ITEM.get(), Items.CHERRY_SAPLING, Items.BLACK_WOOL);

  }

  private void createPlushableRecipe(RecipeOutput output, Item result, TagKey<Item> decorativeTag, Item wool) {
    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result)
        .requires(decorativeTag)
        .requires(wool)
        .requires(MainRegistry.HEART_OF_GOLD_ITEM.get())
        .unlockedBy("has_heart_of_gold", has(MainRegistry.HEART_OF_GOLD_ITEM.get()))
        .save(output);
  }

  private void createPlushableRecipe(RecipeOutput output, Item result, Item decorative, Item wool) {
    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result)
        .requires(decorative)
        .requires(wool)
        .requires(MainRegistry.HEART_OF_GOLD_ITEM.get())
        .unlockedBy("has_heart_of_gold", has(MainRegistry.HEART_OF_GOLD_ITEM.get()))
        .save(output);
  }
}