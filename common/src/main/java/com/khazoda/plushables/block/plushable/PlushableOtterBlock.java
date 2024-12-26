package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableOtterBlock extends BasePlushable {
  public static final MapCodec<PlushableOtterBlock> CODEC = simpleCodec(PlushableOtterBlock::new);

  public PlushableOtterBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableOtterBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.4375, 0.15625, 0, 0.5625, 0.25, 0.0625));
    shape = Shapes.or(shape, Shapes.create(0.375, 0.15625, 0.0625, 0.625, 0.40625, 0.3125));
    shape = Shapes.or(shape, Shapes.create(0.390625, 0.03125, 0.15625, 0.609375, 0.28125, 0.65625));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}