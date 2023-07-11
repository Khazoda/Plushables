package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.RupertTileEntity;
import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimationController;

import java.util.Random;

public class RupertBlock extends AnimatronicPlushable {
  private static Random randPitch = null;
  public RupertBlock() {
    super();
    randPitch = new Random();
  }

  @Override
  public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    // Injects superclass method
    super.use(state, level, pos, player, hand, hit);
    if (level.isClientSide) {
      BlockEntity blockEntity = level.getBlockEntity(pos);
      if (blockEntity instanceof RupertTileEntity) {
        RupertTileEntity rupertEntity = (RupertTileEntity) blockEntity;
        rupertEntity.setShouldLook(true);
        if (rupertEntity.getShouldLook()
            && rupertEntity.lookController.getAnimationState() == AnimationController.State.STOPPED) {
          level.playSound(player, pos, SoundRegistry.RUPERT_PURR.get(), SoundSource.BLOCKS, 0.5f,
              (float) 0.7f + randPitch.nextFloat() / 2);
        }
        return InteractionResult.SUCCESS;
      }
    }
    return InteractionResult.PASS;
  }

  @Override
  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.1875, 0.5625, 0.125, 0.5), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.4062, 0, 0.4843, 0.5937, 0.125, 0.7343), BooleanOp.OR);
    return shape;
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
    return TileRegistry.RUPERT_TILE.get().create(pPos, pState);
  }
}

