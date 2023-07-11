package com.seacroak.plushables.block;

import com.seacroak.plushables.registry.SoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static com.seacroak.plushables.registry.MainRegistry.TRUFFLES_PLUSHABLE;


public class PigBlock extends SimplePlushable {

  public PigBlock() {
    super();
  }

  @Override
  public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    //Serverside Code
    if (!level.isClientSide) {
      if (player.isShiftKeyDown()) {
        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(this));
        level.updateNeighbourForOutputSignal(pos, this);
        level.removeBlock(pos, false);
        return InteractionResult.CONSUME;

      } else {
        //Turn into Truffles
        if (hand == hand.MAIN_HAND && player.getMainHandItem().is(Items.LILY_OF_THE_VALLEY)) {
          level.removeBlock(pos, false);
          Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(TRUFFLES_PLUSHABLE.get()));
          player.getMainHandItem().shrink(1);
        }
        return InteractionResult.SUCCESS;
      }

    }
    //Clientside Code
    if (level.isClientSide) {
      if (player.isShiftKeyDown()) {
        level.playSound(player, pos, SoundRegistry.PLUSHABLE_POP.get(), SoundSource.BLOCKS, 0.5f, 1f);
        level.playSound(player, pos, SoundEvents.WOOL_HIT, SoundSource.BLOCKS, 0.5f, 1f);

        // Custom breaking particle code
        for (int i = 0; i < 5; i++) {
          level.addParticle(ParticleTypes.POOF, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
          level.addParticle(ParticleTypes.GLOW, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
        }

      } else {
        // Turn into Truffles
        if (hand == hand.MAIN_HAND && player.getMainHandItem().is(Items.LILY_OF_THE_VALLEY)) {
          level.playSound(player, pos, SoundRegistry.PLUSHABLE_POP.get(), SoundSource.BLOCKS, 0.5f, 0.2f);
          level.playSound(player, pos, SoundEvents.WOOL_HIT, SoundSource.BLOCKS, 0.5f, 0.2f);

          // Custom breaking particle code
          for (int i = 0; i < 20; i++) {
            level.addParticle(ParticleTypes.SCULK_SOUL, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
            level.addParticle(ParticleTypes.LARGE_SMOKE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
          }
        }
      }
    }
    return InteractionResult.PASS;
  }

  @Override
  public VoxelShape buildShape() {
    VoxelShape s = Shapes.empty();
    s = Shapes.join(s, Shapes.box(0.125, 0, 0.125, 0.3125, 0.1875, 0.5), BooleanOp.OR);
    s = Shapes.join(s, Shapes.box(0.59375, 0, 0.1875, 0.96875, 0.1875, 0.375), BooleanOp.OR);
    s = Shapes.join(s, Shapes.box(0.25, 0.003125, 0.21875, 0.75, 0.315625, 0.875), BooleanOp.OR);
    s = Shapes.join(s, Shapes.box(0.5625, 0, 0.875, 0.75, 0.1875, 1), BooleanOp.OR);
    s = Shapes.join(s, Shapes.box(0.25, 0, 0.875, 0.4375, 0.1875, 1), BooleanOp.OR);
    s = Shapes.join(s, Shapes.box(0.34375, 0, 0.03125, 0.65625, 0.3125, 0.21875), BooleanOp.OR);
    s = Shapes.join(s, Shapes.box(0.421875, 0.0625, -0.03125, 0.578125, 0.15625, 0.03125), BooleanOp.OR);
    s = Shapes.join(s, Shapes.box(0.375, 0.3125, 0.0625, 0.5, 0.328125, 0.1875), BooleanOp.OR);
    s = Shapes.join(s, Shapes.box(0.375, 0.328125, 0.125, 0.5, 0.515625, 0.125), BooleanOp.OR);
    return s;
  }

}