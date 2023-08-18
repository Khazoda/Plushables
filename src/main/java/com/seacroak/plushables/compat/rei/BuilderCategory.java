package com.seacroak.plushables.compat.rei;

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
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BuilderCategory implements DisplayCategory<BuilderDisplay> {

    @Override
    public List<Widget> setupDisplay(BuilderDisplay display, Rectangle bounds) {
        final var widgets = new ArrayList<Widget>();

        int textureX = bounds.getX() + (bounds.getWidth() - 140) / 2;
        int textureY = bounds.getY() + (bounds.getHeight() - 63) / 2;

        widgets.add(Widgets.createTexturedWidget(BuilderScreen.TEXTURE, textureX - 18, textureY -5, 0, 0, 176, 74));

        /* Input Slots */
        widgets.add(Widgets.createSlot(new Point(textureX + 14, textureY + 9)).entries(display.inputs().get(0))
            .disableBackground());
        widgets.add(Widgets.createSlot(new Point(textureX + 14, textureY + 39)).entries(display.inputs().get(1))
            .disableBackground());
        widgets.add(Widgets.createSlot(new Point(textureX + 44, textureY + 24)).entries(display.inputs().get(2))
            .disableBackground());

        /* Output Slot */
        widgets.add(Widgets.createSlot(new Point(textureX + 113, textureY + 24)).entries(display.output().get(0))
            .disableBackground());

        /* Progress Arrow */
        widgets.add(Widgets.createDrawableWidget((context, mouseX, mouseY, delta) -> {
            context.drawTexture(BuilderScreen.TEXTURE, textureX + 63, textureY + 17, 1, 186,
                (int) ((System.currentTimeMillis() / 150d) % 86d), 30);
        }));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return DisplayCategory.super.getDisplayHeight() + 12;
    }

    @Override
    public int getDisplayWidth(BuilderDisplay display) {
        return DisplayCategory.super.getDisplayWidth(display) + 20;
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(MainRegistry.BUILDER_BLOCK);
    }

    @Override
    public Text getTitle() {
        return MainRegistry.BUILDER_BLOCK.getName();
    }

    @Override
    public CategoryIdentifier<? extends BuilderDisplay> getCategoryIdentifier() {
        return PlushablesREICommonPlugin.BUILDER;
    }
}
