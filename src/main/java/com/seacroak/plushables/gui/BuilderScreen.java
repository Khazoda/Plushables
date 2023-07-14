package com.seacroak.plushables.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.seacroak.plushables.PlushablesMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BuilderScreen extends AbstractContainerScreen<BuilderScreenHandler> {
  public static final ResourceLocation TEXTURE = new ResourceLocation(PlushablesMod.MOD_ID,
      "textures/gui/builder_gui.png");

  public BuilderScreen(BuilderScreenHandler handler, Inventory inventory, Component title) {
    super(handler, inventory, title);

    // Move Inventory label to the right
    this.imageHeight = 168;
    this.inventoryLabelY = this.imageHeight - 93;
    this.inventoryLabelX = this.imageWidth - 58;
  }

  @Override
  protected void init() {
    super.init();
    // Center the title
    titleLabelX = (imageWidth - font.width(title)) / 2;
  }

  @Override
  public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
    this.renderBackground(pGuiGraphics);
    super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
  }

  @Override
  protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
    RenderSystem.setShader(GameRenderer::getPositionTexShader);
    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

    int x = (width - imageWidth) / 2;
    int y = (height - imageHeight) / 2;
    pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

    if (menu.isCrafting()) {
      int progress = menu.getScaledProgress();
      pGuiGraphics.blit(TEXTURE, this.getXSize() + 72, this.getYSize() + 43, 176, 64, progress, 9);
    }
  }
}
