package com.seacroak.plushables.block.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.seacroak.plushables.PlushablesMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BuilderScreen extends AbstractContainerScreen<BuilderScreenHandler> {
  public static final ResourceLocation TEXTURE =
      new ResourceLocation(PlushablesMod.MOD_ID,"textures/gui/builder_gui.png");

  public BuilderScreen(BuilderScreenHandler menu, Inventory inventory, Component component) {
    super(menu, inventory, component);
    /* Move "Inventory" label to the right*/
    this.imageHeight = 168;
    this.inventoryLabelY = this.imageHeight - 93;
    this.inventoryLabelX = this.imageWidth - 58;
  }

  @Override
  protected void init() {
    super.init();
    /* Center Block Name Title */
    titleLabelX = (imageWidth - font.width(title)) / 2;
  }

  @Override
  protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
    RenderSystem.setShader(GameRenderer::getPositionTexShader);
    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    int x = (width - imageWidth) / 2;
    int y = (height - imageHeight) / 2;
    pGuiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
    renderProgressArrow(pGuiGraphics, x, y);
  }

  @Override
  protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
    int x = (width - imageWidth) / 2;
    int y = (height - imageHeight) / 2;
  }

  private void renderProgressArrow(GuiGraphics pGuiGraphics, int x, int y) {
    if(menu.isCrafting()) {
      pGuiGraphics.blit(TEXTURE,x+81, y+22, 1, 169, menu.getScaledProgress(),30);
    }
  }



  @Override
  public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
    this.renderBackground(pGuiGraphics);
    super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
  }

}
