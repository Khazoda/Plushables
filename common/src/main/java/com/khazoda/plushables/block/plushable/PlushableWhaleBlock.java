package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableWhaleBlock extends BasePlushable {
  public static final MapCodec<PlushableWhaleBlock> CODEC = simpleCodec(PlushableWhaleBlock::new);

  public PlushableWhaleBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableWhaleBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.1875, 0.0625, 0.1875, 0.8125, 0.6875, 0.8125));
    shape = Shapes.or(shape, Shapes.create(0.25, 0.6875, 0.25, 0.75, 0.75, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.25, 0, 0.25, 0.75, 0.0625, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.8125, 0.125, 0.25, 0.875, 0.625, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.125, 0.125, 0.25, 0.1875, 0.625, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.25, 0.125, 0.125, 0.75, 0.625, 0.1875));
    shape = Shapes.or(shape, Shapes.create(0.25, 0.125, 0.8125, 0.75, 0.625, 0.875));

    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}