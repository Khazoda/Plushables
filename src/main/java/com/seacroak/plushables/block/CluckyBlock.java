package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.CluckyTileEntity;
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

public class CluckyBlock extends AnimatronicPlushable {
  private static Random randPitch = null;
  public CluckyBlock() {
    super();
    randPitch = new Random();
  }
  @Override
  public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    // Injects superclass method
    super.use(state, level, pos, player, hand, hit);
    if (level.isClientSide) {
      BlockEntity blockEntity = level.getBlockEntity(pos);
      if (blockEntity instanceof CluckyTileEntity) {
        CluckyTileEntity cluckyEntity = (CluckyTileEntity) blockEntity;
        cluckyEntity.setShouldLook(true);
        if (cluckyEntity.getShouldLook()
            && cluckyEntity.lookController.getAnimationState() == AnimationController.State.STOPPED) {
          level.playSound(player, pos, SoundRegistry.CLUCKY_CLUCK.get(), SoundSource.BLOCKS, 0.5f,
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
    shape = Shapes.join(shape, Shapes.box(0.375, 0.0703125, 0.375, 0.625, 0.2578125, 0.6875),BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.25, 0.0078125, 0.3125, 0.75, 0.0703125, 0.75),BooleanOp.OR);
    return shape;
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
    return TileRegistry.CLUCKY_TILE.get().create(pPos, pState);
  }
}