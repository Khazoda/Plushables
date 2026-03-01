package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.khazoda.plushables.registry.SoundRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableStonelingBlock extends BasePlushable {
  public static final MapCodec<PlushableStonelingBlock> CODEC = simpleCodec(PlushableStonelingBlock::new);

  public PlushableStonelingBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableStonelingBlock(Properties settings) {
    super(settings,
        TooltipDataBuilder.create()
            .number(49)
            .artist("wiiv")
            .creationDate("12th May 2019")
            .trivia("Stonelings originate from the deep dark caves of the Quark mod")
            .build(),
        InteractionEffectBuilder.create()
            .cooldown(35)
            .sound(SoundRegistry.PLUSHABLE_STONELING)
            .build()
    );
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.375, 0.75, 0.75, 0.5625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.25, 0.1875, 0.5625, 0.75, 0.75, 0.6875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.25, 0.1875, 0.25, 0.75, 0.75, 0.375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.0625, 0.5625, 0.34375, 0.25, 1, 0.59375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.75, 0.5625, 0.34375, 0.9375, 1, 0.59375), BooleanOp.OR);

    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}