package com.seacroak.plushables.gui;

import com.seacroak.plushables.PlushablesMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BuilderScreen extends HandledScreen<BuilderScreenHandler> {
    public static final Identifier TEXTURE = new Identifier(PlushablesMod.MOD_ID,
            "textures/gui/builder_gui.png");

    public BuilderScreen(BuilderScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 168;

        this.playerInventoryTitleY = this.backgroundHeight - 93;
        this.playerInventoryTitleX = this.backgroundWidth - 58;
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(TEXTURE, this.x, this.y, 0, 0, backgroundWidth, backgroundHeight);

        // if (handler.isLightningStorm()) {
        // this.drawTexture(matrices, x + 29, y + 29, 176, 0, 44, 44);
        // }

        if (handler.isCrafting()) {
            int progress = handler.getScaledProgress();
            context.drawTexture(TEXTURE, x + 72, y + 43, 176, 64, progress, 9);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    public int rootX() {
        return this.x;
    }

    public int rootY() {
        return this.y;
    }
}
