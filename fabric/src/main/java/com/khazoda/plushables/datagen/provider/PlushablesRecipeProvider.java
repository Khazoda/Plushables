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
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_ANIMATRONIC_BLOCK.getItem(), Items.REDSTONE, Items.BLUE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_WISP_BLOCK.getItem(), Items.ECHO_SHARD, Items.LIGHT_BLUE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_WIZARD_BLOCK.getItem(), Items.GOLD_INGOT, Items.PURPLE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_ZIGGY_BLOCK.getItem(), ItemTags.FISHES, Items.WHITE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_WHALE_BLOCK.getItem(), Items.KELP, Items.BLUE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_WHELPLING_BLOCK.getItem(), Items.BLAZE_POWDER, Items.RED_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_TRUFFLES_BLOCK.getItem(), Items.LILY_OF_THE_VALLEY, Items.PINK_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_UNICORN_BLOCK.getItem(), Items.CHERRY_LEAVES, Items.WHITE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_WALRUS_BLOCK.getItem(), Items.SEA_PICKLE, Items.BROWN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_TIGER_BLOCK.getItem(), Items.PORKCHOP, Items.ORANGE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_TRATER_BLOCK.getItem(), Items.POISONOUS_POTATO, Items.CYAN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_TRICERATOPS_BLOCK.getItem(), Items.FERN, Items.GREEN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_SHRUMP_BLOCK.getItem(), Items.RED_MUSHROOM, Items.WHITE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_SNAIL_BLOCK.getItem(), Items.SLIME_BALL, Items.CYAN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_STATUETTE_BLOCK.getItem(), Items.TERRACOTTA, Items.BROWN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_RUPERT_BLOCK.getItem(), ItemTags.FISHES, Items.BLACK_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_SEA_BUNNY_BLOCK.getItem(), Items.SEAGRASS, Items.WHITE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_POTSY_BLOCK.getItem(), Items.CAULDRON, Items.BLACK_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_RAPTOR_BLOCK.getItem(), Items.FLINT, Items.BLUE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_RATTIAM_BLOCK.getItem(), Items.WHEAT, Items.LIGHT_GRAY_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_OWL_BLOCK.getItem(), Items.GLOW_BERRIES, Items.BROWN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_PENGUIN_BLOCK.getItem(), Items.SNOWBALL, Items.GRAY_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_PIG_BLOCK.getItem(), Items.MUD, Items.PINK_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_ORANGUTAN_BLOCK.getItem(), Items.VINE, Items.ORANGE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_OTTER_BLOCK.getItem(), ItemTags.FISHES, Items.BROWN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_MOOBLOOM_BLOCK.getItem(), Items.SUNFLOWER, Items.YELLOW_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_OCTOPLUSHABLE_BLOCK.getItem(), Items.INK_SAC, Items.LIGHT_BLUE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_MAMMOTH_BLOCK.getItem(), Items.POINTED_DRIPSTONE, Items.BROWN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_GOBLIN_BLOCK.getItem(), Items.RED_MUSHROOM, Items.LIME_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_GOLDFISH_BLOCK.getItem(), Items.GOLD_INGOT, Items.ORANGE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_HAMSTER_BLOCK.getItem(), Items.CARROT, Items.ORANGE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_FOX_BLOCK.getItem(), Items.PUMPKIN, Items.ORANGE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_FROGE_BLOCK.getItem(), Items.LILY_PAD, Items.YELLOW_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_FROGLIN_BLOCK.getItem(), Items.LILY_PAD, Items.GREEN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_DJUNGELSKOG_BLOCK.getItem(), Items.HONEYCOMB, Items.BROWN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_DORMOUSE_BLOCK.getItem(), Items.OAK_LEAVES, Items.BROWN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_DRAGON_BLOCK.getItem(), Items.BLAZE_ROD, Items.WHITE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_CONDUCTOR_BLOCK.getItem(), Items.RAIL, Items.GRAY_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_COOPER_BLOCK.getItem(), Items.BONE, Items.BLACK_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_CLUCKY_BLOCK.getItem(), Items.EGG, Items.WHITE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_BIG_IRRITATER_BLOCK.getItem(), Items.POTATO, Items.RED_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_BIG_TATER_BLOCK.getItem(), Items.POTATO, Items.PINK_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_BLAHAJ_BLOCK.getItem(), Items.PINK_PETALS, Items.LIGHT_BLUE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_BEAUX_BLOCK.getItem(), Items.BONE, Items.GRAY_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_SNOWIE_BLOCK.getItem(), Items.SNOWBALL, Items.WHITE_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_RIBBIT_BLOCK.getItem(), Items.FISHING_ROD, Items.LIME_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_CREAKY_BLOCK.getItem(), Items.CHERRY_SAPLING, Items.BLACK_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_KWEEBEC_BLOCK.getItem(), Items.OAK_LEAVES, Items.BROWN_WOOL);
    createPlushableRecipe(output, MainRegistry.PLUSHABLE_STONELING_BLOCK.getItem(), Items.STONE, Items.GRAY_WOOL);

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