package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableRaptorBlock extends BasePlushable {
  public static final MapCodec<PlushableRaptorBlock> CODEC = simpleCodec(PlushableRaptorBlock::new);

  public PlushableRaptorBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableRaptorBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.3125, 0, 0.078125, 0.6875, 0.734375, 0.59375));
    shape = Shapes.or(shape, Shapes.create(0.421875, 0.59375, -0.0625, 0.59375, 0.703125, 0.078125));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}