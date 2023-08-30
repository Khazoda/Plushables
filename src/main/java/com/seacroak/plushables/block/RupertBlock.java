package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.RupertTileEntity;
import com.seacroak.plushables.registry.assets.SoundRegistry;
import com.seacroak.plushables.registry.uncommon.TileRegistry;
import com.seacroak.plushables.networking.AnimationPacketHandler;
import com.seacroak.plushables.networking.PlushablesNetworking;
import com.seacroak.plushables.networking.SoundPacketHandler;
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

public class RupertBlock extends BasePoweredPlushable {

  public RupertBlock() {
    super();
  }

  // Shift Right Click pickup code
  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos,
                            PlayerEntity player, Hand hand, BlockHitResult hit) {
    // Injects superclass method
    super.onUse(state, world, pos, player, hand, hit);
    if (!player.isSneaking()) {
      BlockEntity blockEntity = world.getBlockEntity(pos);
      if (world instanceof ServerWorld serverWorld) {
        /* Server: Send sound packet to clients*/
        SoundPacketHandler.sendPlayerPacketToClients(serverWorld, new SoundPacketHandler.PlayerSoundPacket(player, pos, SoundRegistry.RUPERT_PURR, 1f));
        /* Server: Send animation packet to clients*/
        if (blockEntity instanceof RupertTileEntity) {
          AnimationPacketHandler.sendPacketToClients(serverWorld, new AnimationPacketHandler.AnimationPacket(player, pos, true, "interaction"));
        }
        return ActionResult.CONSUME;
      } else if (world.isClient) {
        if (blockEntity instanceof RupertTileEntity) {
          /* Client: Play animation */
          PlushablesNetworking.playAnimationOnClient(true, world, pos, "interaction");
          RupertTileEntity rupertEntity = (RupertTileEntity) blockEntity;
          if (rupertEntity.shouldAnimate()
              && rupertEntity.interactionController.getAnimationState() == AnimationController.State.STOPPED) {
            PlushablesNetworking.playSoundOnClient(SoundRegistry.RUPERT_PURR, world, pos, 1f, 0.7f + randPitch.nextFloat() / 2);
          }
          return ActionResult.SUCCESS;
        }
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

