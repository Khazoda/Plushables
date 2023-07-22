package com.seacroak.plushables.rei;

import com.seacroak.plushables.block.screen.BuilderScreen;
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

        int textureX = bounds.getX() + (bounds.getWidth() - 140) / 2;
        int textureY = bounds.getY() + (bounds.getHeight() - 63) / 2;

//        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(BuilderScreen.TEXTURE, textureX - 18, textureY + 0, 0, 0, 176, 74));
//        widgets.add(Widgets.createTexturedWidget(BuilderScreen.TEXTURE, textureX + 3, textureY + 4, 25, 9, 134, 56));

        /* Input Slots */
        widgets.add(Widgets.createSlot(new Point(textureX + 14, textureY + 14)).entries(display.inputs().get(0))
                .disableBackground());
        widgets.add(Widgets.createSlot(new Point(textureX + 14, textureY + 44)).entries(display.inputs().get(1))
                .disableBackground());
        widgets.add(Widgets.createSlot(new Point(textureX + 44, textureY + 29)).entries(display.inputs().get(2))
                .disableBackground());

        /* Output Slot */
        widgets.add(Widgets.createSlot(new Point(textureX + 113, textureY + 29)).entries(display.output().get(0))
                .disableBackground());

        /* Progress Arrow */
        widgets.add(Widgets.createDrawableWidget((context, mouseX, mouseY, delta) -> {
            context.blit(BuilderScreen.TEXTURE, textureX + 63, textureY + 22, 1, 169,
                    (int) ((System.currentTimeMillis() / 150d) % 86d), 30);
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
