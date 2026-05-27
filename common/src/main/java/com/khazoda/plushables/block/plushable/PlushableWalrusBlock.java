package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableWalrusBlock extends BasePlushable {
  public static final MapCodec<PlushableWalrusBlock> CODEC = simpleCodec(PlushableWalrusBlock::new);

  public PlushableWalrusBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableWalrusBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(39)
        .artist("@BumbleSculpts")
        .creationDate("11th September 2023")
        .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.315625, 0.0625, 0.40625, 0.684375, 0.46875, 0.71875));
    shape = Shapes.or(shape, Shapes.create(0.30625, 0.40625, 0.0625, 0.69375, 0.78125, 0.375));
    shape = Shapes.or(shape, Shapes.create(0.40625, 0.46875, 0.03125, 0.59375, 0.65625, 0.21875));
    shape = Shapes.or(shape, Shapes.create(0.309375, 0.125, 0.15625, 0.690625, 0.59375, 0.53125));
    return shape;
  }

  @Override
  protected MapCodec<PlushableWalrusBlock> codec() {
    return CODEC;
  }
}