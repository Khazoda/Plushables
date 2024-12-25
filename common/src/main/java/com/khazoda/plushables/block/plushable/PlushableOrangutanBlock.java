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
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.375, 0, 0, 0.625, 0.25, 0.5625));
    shape = Shapes.or(shape, Shapes.create(0.4375, 0.0625, 0.5625, 0.5625, 0.1875, 1));
    shape = Shapes.or(shape, Shapes.create(0.40625, 0.03125, 0.5625, 0.59375, 0.21875, 0.6875));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}