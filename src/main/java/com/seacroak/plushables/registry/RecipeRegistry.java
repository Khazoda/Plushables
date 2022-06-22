package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.recipe.BuilderRecipe;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RecipeRegistry {
    public void registerRecipes() {
        // Builder block recipe type
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(PlushablesMod.ModID, BuilderRecipe.Serializer.ID),
                BuilderRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(PlushablesMod.ModID, BuilderRecipe.Type.ID),
                BuilderRecipe.Type.INSTANCE);
    }
}
