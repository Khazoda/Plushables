package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableTraterBlock extends BasePlushable {
  public static final MapCodec<PlushableTraterBlock> CODEC = simpleCodec(PlushableTraterBlock::new);

  public PlushableTraterBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableTraterBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.5625, 0, 0.4375, 0.79375, 0.3125, 0.6875));
    shape = Shapes.or(shape, Shapes.create(0.2078125, 0.3125, 0.26875, 0.7953125, 1.0625, 0.803125));
    shape = Shapes.or(shape, Shapes.create(0.21875, 0.703125, 0.28125, 0.78125, 1.328125, 0.78125));
    shape = Shapes.or(shape, Shapes.create(0.2046875, 0, 0.4375, 0.4375, 0.3125, 0.6875));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}