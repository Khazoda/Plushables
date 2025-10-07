package com.khazoda.plushables.item;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.platform.Services;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
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

  /**
   * Revealable tooltips
   */
  @Override
  public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
    super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);

    if (!Services.PLATFORM.isClientSide()) return;
    if (!(getBlock() instanceof BasePlushable basePlushable)) return;
    if (!Minecraft.getInstance().hasControlDown()) {
      tooltipAdder.accept(Component.translatable("tooltip.plushables.holdctrl").withStyle(ChatFormatting.GRAY));
      return;
    }

    // Add basic info
    tooltipAdder.accept(Component.literal(basePlushable.getTooltipData().number()).withStyle(ChatFormatting.YELLOW));
    tooltipAdder.accept(Component.translatable("tooltip.plushables.artist").append(" \u00B7 " + basePlushable.getTooltipData().artist()).withStyle(ChatFormatting.GREEN));
    tooltipAdder.accept(Component.translatable("tooltip.plushables.created").append(" \u00B7 " + basePlushable.getTooltipData().localizeDate(Minecraft.getInstance().getLanguageManager().getSelected())).withStyle(ChatFormatting.DARK_GREEN));

    // Add trivia if available
    if (basePlushable.getTooltipData().trivia() != null) {
      tooltipAdder.accept(CommonComponents.EMPTY);
      addTrivia(tooltipAdder, basePlushable.getTooltipData().trivia());
    }
  }

  private void addTrivia(Consumer<Component> tooltipAdder, String trivia) {
    String[] words = trivia.split(" ");
    StringBuilder currentLine = new StringBuilder();

    for (String word : words) {
      if (currentLine.length() + word.length() > 35) {
        tooltipAdder.accept(Component.literal(currentLine.toString().trim()).withStyle(ChatFormatting.GRAY));
        currentLine.setLength(0);
      }
      currentLine.append(word).append(" ");
    }
    if (!currentLine.isEmpty()) {
      tooltipAdder.accept(Component.literal(currentLine.toString().trim()).withStyle(ChatFormatting.GRAY));
    }
  }
}