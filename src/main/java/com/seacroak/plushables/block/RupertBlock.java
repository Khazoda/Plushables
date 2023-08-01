package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.RupertTileEntity;
import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.registry.TileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimationController;

public class RupertBlock extends AnimatronicPlushable {

  public RupertBlock() {
    super();
  }

  // Shift Right Click pickup code
  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos,
                            PlayerEntity player, Hand hand, BlockHitResult hit) {
    // Injects superclass method
    super.onUse(state, world, pos, player, hand, hit);
    if (world.isClient) {
      BlockEntity blockEntity = world.getBlockEntity(pos);
      if (blockEntity instanceof RupertTileEntity) {
        RupertTileEntity rupertEntity = (RupertTileEntity) blockEntity;
        rupertEntity.shouldAnimate(true);
        if (rupertEntity.shouldAnimate()
            && rupertEntity.interactionController.getAnimationState() == AnimationController.State.STOPPED) {
          world.playSound(player, pos, SoundRegistry.RUPERT_PURR, SoundCategory.BLOCKS, 0.5f,
              (float) 0.7f + randPitch.nextFloat() / 2);
        }
        return ActionResult.SUCCESS;
      }
    }
    return ActionResult.PASS;
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.5, 0.75, 0.125, 0.6875));

    return shape;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return TileRegistry.RUPERT_TILE.instantiate(pos, state);
  }
}

