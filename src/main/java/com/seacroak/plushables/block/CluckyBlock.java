package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.CluckyTileEntity;
import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.registry.TileRegistry;
import com.seacroak.plushables.util.networking.PlushablesNetworking;
import com.seacroak.plushables.util.networking.SoundPacketHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimationController;

public class CluckyBlock extends AnimatronicPlushable {

  public CluckyBlock() {
    super();
  }

  // Shift Right Click pickup code
  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos,
                            PlayerEntity player, Hand hand, BlockHitResult hit) {
    super.onUse(state, world, pos, player, hand, hit);
    float randomPitch = (float) 0.7f + randPitch.nextFloat() / 2;
    // Send packets to server
    if (!player.isSneaking()) {
      if (world instanceof ServerWorld serverWorld) {
        SoundPacketHandler.sendPlayerPacketToClients(serverWorld, new SoundPacketHandler.PlayerSoundPacket(player, pos, SoundRegistry.CLUCKY_CLUCK, randomPitch));
        return ActionResult.CONSUME;
      } else if (world.isClient) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof CluckyTileEntity) {
          CluckyTileEntity cluckyEntity = (CluckyTileEntity) blockEntity;
          cluckyEntity.shouldAnimate(true);
          if (cluckyEntity.shouldAnimate()
              && cluckyEntity.interactionController.getAnimationState() == AnimationController.State.STOPPED) {
            PlushablesNetworking.playSoundOnClient(SoundRegistry.CLUCKY_CLUCK, world, pos, 1f, randomPitch);
          }
          return ActionResult.SUCCESS;
        }
      }
    }
    return ActionResult.PASS;
  }

  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape,
        VoxelShapes.cuboid(0.375, 0.0703125, 0.375, 0.625, 0.2578125, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0078125, 0.3125, 0.75, 0.0703125, 0.75));

    return shape;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return TileRegistry.CLUCKY_TILE.instantiate(pos, state);
  }
}