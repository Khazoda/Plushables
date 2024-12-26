package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableOrangutanBlock extends BasePlushable {
  public static final MapCodec<PlushableOrangutanBlock> CODEC = simpleCodec(PlushableOrangutanBlock::new);

  public PlushableOrangutanBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableOrangutanBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.125, 0, 0.3125, 0.3125, 0.375, 0.5));
    shape = Shapes.or(shape, Shapes.create(0.6875, 0, 0.3125, 0.875, 0.375, 0.5));
    shape = Shapes.or(shape, Shapes.create(0.125, 0, 0.75, 0.3125, 0.375, 0.9375));
    shape = Shapes.or(shape, Shapes.create(0.6875, 0, 0.75, 0.875, 0.375, 0.9375));
    shape = Shapes.or(shape, Shapes.create(0.125, 0.203125, 0.3125, 0.875, 0.4375, 0.5));
    shape = Shapes.or(shape, Shapes.create(0.125, 0.203125, 0.5, 0.875, 0.375, 0.9375));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}