package com.seacroak.plushables.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.seacroak.plushables.PlushablesMod;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BuilderScreen extends HandledScreen<BuilderScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(PlushablesMod.MOD_ID,
            "textures/gui/builder_gui.png");

    public BuilderScreen(BuilderScreenHandler handler, PlayerInventory inventory, Text title) {

        super(handler, inventory, title);
        this.playerInventoryTitleY = this.backgroundHeight - 92;
        this.playerInventoryTitleX = this.backgroundWidth - 58;

    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        if (handler.isLightningStorm()) {
            this.drawTexture(matrices, x + 29, y + 29, 176, 0, 44, 44);
        }

        if (handler.isCrafting()) {
            int progress = handler.getScaledProgress();
            this.drawTexture(matrices, x + 72, y + 45, 176, 64, progress, 9);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
