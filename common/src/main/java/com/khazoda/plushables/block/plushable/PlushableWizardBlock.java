package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.registry.SoundRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableWizardBlock extends BasePlushable {
  public static final MapCodec<PlushableWizardBlock> CODEC = simpleCodec(PlushableWizardBlock::new);

  public PlushableWizardBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableWizardBlock(Properties settings) {
    super(settings);
    this.interactionSound = SoundRegistry.PLUSHABLE_WIZARD.get();
    this.cooldownPeriod = 80;
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.25, 0, 0.25, 0.75, 0.640625, 0.625));
    shape = Shapes.or(shape, Shapes.create(0.375, 0.9375, 0.15625, 0.625, 1, 0.5));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.625, 0.15625, 0.6875, 0.9375, 0.5625));
    shape = Shapes.or(shape, Shapes.create(0.421875, 0.296875, 0.21875, 0.578125, 0.4375, 0.25));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}