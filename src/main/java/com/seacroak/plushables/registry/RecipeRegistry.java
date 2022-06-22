package com.seacroak.plushables.registry;

import com.seacroak.plushables.recipe.BuilderRecipe;
import com.seacroak.plushables.util.GenericUtils;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.registry.Registry;

public final class RecipeRegistry {

    public static void init() {
        register("builder", BuilderRecipe.Serializer.INSTANCE);
        register("builder", BuilderRecipe.Type.INSTANCE);
    }

    private static void register(String name, RecipeSerializer<?> serializer) {
        Registry.register(Registry.RECIPE_SERIALIZER, GenericUtils.ID(name), serializer);
    }

    private static void register(String name, RecipeType<?> type) {
        Registry.register(Registry.RECIPE_TYPE, GenericUtils.ID(name), type);
    }

}