package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableWhelplingBlock extends BasePlushable {
  public static final MapCodec<PlushableWhelplingBlock> CODEC = simpleCodec(PlushableWhelplingBlock::new);

  public PlushableWhelplingBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableWhelplingBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.3375, 0, 0.271875, 0.6703125, 0.1890625, 0.690625));

    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}