package com.seacroak.plushables.registry.uncommon;

import com.seacroak.plushables.recipe.BuilderRecipe;
import com.seacroak.plushables.util.GenericUtils;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public final class RecipeRegistry {

    public static void init() {
        register("builder", BuilderRecipe.Serializer.INSTANCE);
        register("builder", BuilderRecipe.Type.INSTANCE);
    }

    private static void register(String name, RecipeSerializer<?> serializer) {
        Registry.register(Registries.RECIPE_SERIALIZER, GenericUtils.ID(name), serializer);
    }

    private static void register(String name, RecipeType<?> type) {
        Registry.register(Registries.RECIPE_TYPE, GenericUtils.ID(name), type);
    }

}