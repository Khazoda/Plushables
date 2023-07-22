package com.seacroak.plushables.rei;

import com.seacroak.plushables.block.screen.BuilderScreen;
import com.seacroak.plushables.recipe.BuilderRecipe;
import com.seacroak.plushables.registry.MainRegistry;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;

@REIPluginClient
public class PlushablesREIClientPlugin implements REIClientPlugin {

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(BuilderRecipe.class, BuilderDisplay::new);
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new BuilderCategory());
        registry.addWorkstations(PlushablesREICommonPlugin.BUILDER, EntryStacks.of(MainRegistry.BUILDER_BLOCK.get()));
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> {
            return new Rectangle(screen.getGuiLeft() + 72, screen.getGuiTop() + 43, 24, 9);
        }, BuilderScreen.class, PlushablesREICommonPlugin.BUILDER);
    }
}
