package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushablePigBlock extends BasePlushable {
  public static final MapCodec<PlushablePenguinBlock> CODEC = simpleCodec(PlushablePenguinBlock::new);

  public PlushablePigBlock(Properties settings) {
    super(settings,
        TooltipDataBuilder.create()
            .number(5)
            .artist("Khazoda")
            .creationDate("21st October 2022")
            .build(),
        InteractionEffectBuilder.create()
            .sound(SoundEvents.PIG_AMBIENT)
            .volume(0.6f)
            .pitch(1.1f)
            .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.25, 0.003125, 0.21875, 0.75, 0.315625, 0.875));
    shape = Shapes.or(shape, Shapes.create(0.5625, 0, 0.875, 0.75, 0.1875, 1));
    shape = Shapes.or(shape, Shapes.create(0.25, 0, 0.875, 0.4375, 0.1875, 1));
    shape = Shapes.or(shape, Shapes.create(0.34375, 0.003125, 0.03125, 0.65625, 0.315625, 0.21875));
    shape = Shapes.or(shape, Shapes.create(0.421875, 0.0625, -0.03125, 0.578125, 0.15625, 0.03125));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}