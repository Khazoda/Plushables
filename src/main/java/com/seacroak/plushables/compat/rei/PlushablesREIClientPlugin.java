package com.seacroak.plushables.compat.rei;

import com.seacroak.plushables.block.screen.BuilderScreen;
import com.seacroak.plushables.recipe.BuilderRecipe;
import com.seacroak.plushables.registry.MainRegistry;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;

public class PlushablesREIClientPlugin implements REIClientPlugin {

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(BuilderRecipe.class, BuilderRecipe.Type.INSTANCE, BuilderDisplay::new);
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new BuilderCategory());
        registry.addWorkstations(PlushablesREICommonPlugin.BUILDER, EntryStacks.of(MainRegistry.BUILDER_BLOCK));
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> {
            return new Rectangle(screen.rootX() + 84, screen.rootY() + 32, 42, 9);
        }, BuilderScreen.class, PlushablesREICommonPlugin.BUILDER);
    }
}
