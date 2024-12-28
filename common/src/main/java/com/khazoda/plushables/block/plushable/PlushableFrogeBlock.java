package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableFrogeBlock extends BasePlushable {
  public static final MapCodec<PlushableFrogeBlock> CODEC = simpleCodec(PlushableFrogeBlock::new);

  public PlushableFrogeBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableFrogeBlock(Properties settings) {
    super(settings, new InteractionEffectBuilder().lightLevel(8).build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.1875, 0, 0.25, 0.8125, 0.1875, 0.4375));
    shape = Shapes.or(shape, Shapes.create(0.25, 0.0625, 0.21875, 0.75, 0.4375, 0.78125));
    shape = Shapes.or(shape, Shapes.create(0.5625, 0.4375, 0.28125, 0.75, 0.5625, 0.46875));
    shape = Shapes.or(shape, Shapes.create(0.25, 0.4375, 0.28125, 0.4375, 0.5625, 0.46875));
    shape = Shapes.or(shape, Shapes.create(0.125, 0, 0.5625, 0.875, 0.1875, 0.75));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}