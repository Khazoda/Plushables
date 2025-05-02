package com.khazoda.plushables.item;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.platform.Services;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

/**
 * Middleman class between a Plushable and a standard Item.
 * All plushable BlockItems extend this class in order for the rendering mixin to be able to know what items to apply player transformations to.
 */
public class PlushableBlockItem extends BlockItem {
  public PlushableBlockItem(Block block, Properties properties) {
    super(block, properties);
  }

  private boolean wasControlDown = false;  // Used for tooltip sound logic

  /**
   * Revealable tooltips
   */
  @Override
  public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
    super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);

    // Only call tooltip code on client
    if (!Services.PLATFORM.isClientSide()) return;

    if (!(getBlock() instanceof BasePlushable basePlushable)) {
      return;
    }

    boolean isControlDown = Screen.hasControlDown();
    // Play sound effect when CTRL is initially pressed
    if (isControlDown && !wasControlDown) {
      Minecraft minecraft = Minecraft.getInstance();
      if (minecraft.player != null) {
        minecraft.player.playSound(SoundEvents.CAKE_ADD_CANDLE, 1.0F, 1.0F);
      }
    }
    wasControlDown = isControlDown; // Update state so sound effect doesn't play again

    if (isControlDown) {
      tooltipAdder.accept(Component.literal(basePlushable.getTooltipData().number()).withStyle(ChatFormatting.YELLOW));
      tooltipAdder.accept(Component.translatable("tooltip.plushables.artist").append(" \u00B7 " + basePlushable.getTooltipData().artist()).withStyle(ChatFormatting.GREEN));
      tooltipAdder.accept(Component.translatable("tooltip.plushables.created").append(" \u00B7 " + basePlushable.getTooltipData().localizeDate(Minecraft.getInstance().getLanguageManager().getSelected())).withStyle(ChatFormatting.DARK_GREEN));
      if (basePlushable.getTooltipData().trivia() != null) {
        tooltipAdder.accept(CommonComponents.EMPTY);
        // Wraps trivia string input so the tooltip doesn't go on one line forever
        String[] words = basePlushable.getTooltipData().trivia().split(" ");
        StringBuilder currentLine = new StringBuilder();
        for (String word : words) {
          if (currentLine.length() + word.length() > 35) {  // 35 characters per line
            tooltipAdder.accept(Component.literal(currentLine.toString().trim()).withStyle(ChatFormatting.GRAY));
            currentLine = new StringBuilder();
          }
          currentLine.append(word).append(" ");
        }
        if (!currentLine.isEmpty()) {
          tooltipAdder.accept(Component.literal(currentLine.toString().trim()).withStyle(ChatFormatting.GRAY));
        }
      }
    } else {
      tooltipAdder.accept(Component.translatable("tooltip.plushables.holdctrl").withStyle(ChatFormatting.GRAY));
    }
  }
}
