package com.seacroak.plushables.block.screen;

import com.seacroak.plushables.PlushablesMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import javax.swing.text.AttributeSet;
import javax.swing.text.Style;

public class BuilderScreen extends HandledScreen<BuilderScreenHandler> {
    public static final Identifier TEXTURE = new Identifier(PlushablesMod.MOD_ID,
            "textures/gui/builder_gui.png");

    public BuilderScreen(BuilderScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, Text.empty());
        this.backgroundHeight = 182;

        this.playerInventoryTitleY = this.backgroundHeight -17; // -93
        this.playerInventoryTitleX = this.backgroundWidth - 56; // -58
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, this.x, this.y, 0, 0, backgroundWidth, backgroundHeight);
        renderProgressArrow(context,x,y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(TEXTURE,x+81, y+22, 1, 186, handler.getScaledProgress(),30);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawText( textRenderer, Text.translatable("block.plushables.builder_block"), x + 4,y - 8, 0xffffff, true );

        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    public Text getNarratedTitle() {
        return Text.translatable("block.plushables.builder_block");
    }

    public int rootX() {
        return this.x;
    }

    public int rootY() {
        return this.y;
    }
}
