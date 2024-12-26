package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableWispBlock extends BasePlushable {
  public static final MapCodec<PlushableWispBlock> CODEC = simpleCodec(PlushableWispBlock::new);

  public PlushableWispBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableWispBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.3125, 0, 0.3125, 0.6875, 0.375, 0.6875));
    shape = Shapes.or(shape, Shapes.create(0.125, 0.1875, 0.75, 0.25, 0.3125, 0.875));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}