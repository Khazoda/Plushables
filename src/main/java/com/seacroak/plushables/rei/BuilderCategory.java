package com.seacroak.plushables.rei;

import com.seacroak.plushables.gui.BuilderScreen;
import com.seacroak.plushables.registry.MainRegistry;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class BuilderCategory implements DisplayCategory<BuilderDisplay> {

    @Override
    public List<Widget> setupDisplay(BuilderDisplay display, Rectangle bounds) {
        final var widgets = new ArrayList<Widget>();

        int textureX = bounds.getX() + (bounds.getWidth() - 63) / 2;
        int textureY = bounds.getY() + (bounds.getHeight() - 60) / 2;

        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(BuilderScreen.TEXTURE, textureX, textureY, 52, 17, 63, 60));

        widgets.add(Widgets.createSlot(new Point(textureX + 3, textureY + 3)).entries(display.inputs().get(0))
                .disableBackground());
        widgets.add(Widgets.createSlot(new Point(textureX + 3, textureY + 41)).entries(display.inputs().get(1))
                .disableBackground());
        widgets.add(Widgets.createSlot(new Point(textureX + 3, textureY + 22)).entries(display.inputs().get(2))
                .disableBackground());

        widgets.add(Widgets.createSlot(new Point(textureX + 46, textureY + 22)).entries(display.output().get(0))
                .disableBackground());

        widgets.add(Widgets.createDrawableWidget((context, mouseX, mouseY, delta) -> {
            context.blit(BuilderScreen.TEXTURE, textureX + 20, textureY + 26, 176, 64,
                    (int) ((System.currentTimeMillis() / 150d) % 24d), 9);
        }));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return DisplayCategory.super.getDisplayHeight() + 6;
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(MainRegistry.BUILDER_BLOCK.get());
    }

    @Override
    public Component getTitle() {
        return MainRegistry.BUILDER_BLOCK.get().getName();
    }

    @Override
    public CategoryIdentifier<? extends BuilderDisplay> getCategoryIdentifier() {
        return PlushablesREICommonPlugin.BUILDER;
    }
}
