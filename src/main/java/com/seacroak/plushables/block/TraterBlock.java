package com.seacroak.plushables.block;

import com.seacroak.plushables.PlushablesMod;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TraterBlock extends BasePlushable {
  public TraterBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5625, 0, 0.4375, 0.79375, 0.3125, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.2078125, 0.3125, 0.26875, 0.7953125, 1.0625, 0.803125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.21875, 0.703125, 0.28125, 0.78125, 1.328125, 0.78125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.2046875, 0, 0.4375, 0.4375, 0.3125, 0.6875));

    return shape;
  }

  @Override
  public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
    tooltip.add(Text.translatable("block." + PlushablesMod.MOD_ID + ".trater.tooltip"));
    super.appendTooltip(stack, world, tooltip, options);
  }
}