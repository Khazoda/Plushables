package com.seacroak.plushables.gui;

import com.seacroak.plushables.PlushablesMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BuilderScreen extends AbstractContainerScreen<BuilderScreenHandler> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(PlushablesMod.MOD_ID,
            "textures/gui/builder_gui.png");

    public BuilderScreen(BuilderScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
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
        renderBg(pGuiGraphics,pPartialTick,pMouseX,pMouseY);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics,pMouseX,pMouseY);

    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        pGuiGraphics.blit(TEXTURE,this.getXSize(),this.getYSize(),0,0,this.imageWidth,this.imageHeight);
        if(menu.isCrafting()) {
            int progress = menu.getScaledProgress();
            pGuiGraphics.blit(TEXTURE,this.getXSize() + 72,this.getYSize() + 43,176,64,progress,9);
        }
    }
}
