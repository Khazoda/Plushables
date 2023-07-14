package com.seacroak.plushables.block.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.seacroak.plushables.PlushablesMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BuilderScreen extends AbstractContainerScreen<BuilderScreenHandler> {
  private static final ResourceLocation TEXTURE =
      new ResourceLocation(PlushablesMod.MOD_ID,"textures/gui/builder_gui.png");


  public BuilderScreen(BuilderScreenHandler menu, Inventory inventory, Component component) {
    super(menu, inventory, component);
  }

  @Override
  protected void init() {
    super.init();
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
      pGuiGraphics.blit(TEXTURE,x+72, y, 177, -20, menu.getScaledProgress(),this.imageHeight);
    }
  }

  @Override
  public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
    renderBackground(pGuiGraphics);
    super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    renderTooltip(pGuiGraphics, pMouseX, pMouseY);
  }

}
