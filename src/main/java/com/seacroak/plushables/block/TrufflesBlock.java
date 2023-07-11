package com.seacroak.plushables.block;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TrufflesBlock extends SimplePlushable {
  public TrufflesBlock() {
    super();
  }
  @Override
  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.25, 0.8125, 0.5, 0.75), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.0625, 0.3125, 0.0625, 0.125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.6875, 0, 0.0625, 0.75, 0.0625, 0.125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.25, 0.46875, 0.125, 0.375, 0.59375, 0.125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.375, 0.125, 0.0625, 0.625, 0.25, 0.125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.625, 0.46875, 0.125, 0.75, 0.59375, 0.125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.4375, 0.25, 0.890625, 0.5625, 0.375, 0.890625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.21875, 0, 0.75, 0.78125, 0.46875, 0.875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.71875, 0, 0.875, 0.78125, 0.0625, 0.9375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.21875, 0, 0.875, 0.28125, 0.0625, 0.9375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.21875, 0, 0.125, 0.78125, 0.46875, 0.25), BooleanOp.OR);

    return shape;
  }

  @Override
  public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
    pTooltip.add(Component.translatable("With a certain white flower, even").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
    pTooltip.add(Component.translatable("a pig could look this cute...").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
    super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
  }
}