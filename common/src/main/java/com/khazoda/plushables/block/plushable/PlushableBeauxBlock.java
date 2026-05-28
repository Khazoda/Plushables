package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableBeauxBlock extends BasePlushable {
  public static final MapCodec<PlushableBeauxBlock> CODEC = simpleCodec(PlushableBeauxBlock::new);

  public PlushableBeauxBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableBeauxBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(16)
        .artist("@BumbleSculpts")
        .creationDate("18th June 2023")
        .build(),
        InteractionEffectBuilder.create()
            .sound(SoundEvents.WOLF_AMBIENT)
            .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.40625, 0, 0.07812, 0.59375, 0.0625, 0.14062));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0, 0.125, 0.6875, 0.28125, 0.375));
    shape = Shapes.or(shape, Shapes.create(0.34375, 0, 0.375, 0.65625, 0.21875, 0.875));
    shape = Shapes.or(shape, Shapes.create(0.4375, 0.0625, 0.07812, 0.5625, 0.125, 0.14062));
    return shape;
  }

  @Override
  protected MapCodec<PlushableBeauxBlock> codec() {
    return CODEC;
  }
}