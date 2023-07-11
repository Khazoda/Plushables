package com.seacroak.plushables.block;

import com.seacroak.plushables.registry.SoundRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WizardBlock extends SimplePlushable {

  public WizardBlock() {
    super();
  }
  @Override
  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.25, 0.75, 0.6406, 0.625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.375, 0.9375, 0.1562, 0.625, 1, 0.5), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.3125, 0.625, 0.1562, 0.6875, 0.9375, 0.5625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.125, 0.375, 0.0937, 0.3125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.4218, 0.2968, 0.2187, 0.5781, 0.4375, 0.25), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.625, 0, 0.125, 0.75, 0.0937, 0.3125), BooleanOp.OR);

    return shape;
  }

  // Shift Right Click pickup code
  @Override
  public InteractionResult use(BlockState state, Level level, BlockPos pos,
                               Player player, InteractionHand hand, BlockHitResult hit) {
    // Injects superclass method
    super.use(state, level, pos, player, hand, hit);
    if (level.isClientSide) {
      // Custom breaking particle code
      level.addParticle(ParticleTypes.NOTE, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
      for (int i = 0; i < 5; i++) {
        level.addParticle(ParticleTypes.GLOW, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
      }
        level.playSound(player, pos, SoundRegistry.SWMG.get(), SoundSource.BLOCKS, 0.25f, 1f);

        return InteractionResult.SUCCESS;
    }
    return InteractionResult.PASS;
  }
}
