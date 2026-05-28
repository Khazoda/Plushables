package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableGoblinBlock extends BasePlushable {
  public static final MapCodec<PlushableGoblinBlock> CODEC = simpleCodec(PlushableGoblinBlock::new);

  public PlushableGoblinBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableGoblinBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(17)
        .artist("@BumbleSculpts")
        .creationDate("19th June 2023")
        .build(),
        InteractionEffectBuilder.create()
            .sound(SoundEvents.VILLAGER_YES)
            .pitch(1.4f)
            .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.375, 0, 0.375, 0.625, 0.375, 0.625));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.375, 0.3125, 0.6875, 0.6875, 0.6875));
    shape = Shapes.or(shape, Shapes.create(-0.0625, 0.5625, 0.5, 0.3125, 0.625, 0.5625));
    shape = Shapes.or(shape, Shapes.create(0.6875, 0.5625, 0.5, 1.0625, 0.625, 0.5625));

    return shape;
  }

  @Override
  protected MapCodec<PlushableGoblinBlock> codec() {
    return CODEC;
  }
}