package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableOctoplushableBlock extends BasePlushable {
  public static final MapCodec<PlushableOctoplushableBlock> CODEC = simpleCodec(PlushableOctoplushableBlock::new);

  public PlushableOctoplushableBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableOctoplushableBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.28125, 0.015625, 0.28125, 0.71875, 0.46875, 0.71875));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}