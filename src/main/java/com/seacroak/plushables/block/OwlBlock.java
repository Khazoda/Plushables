package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.OwlTileEntity;
import com.seacroak.plushables.networking.AnimationPacketHandler;
import com.seacroak.plushables.networking.PlushablesNetworking;
import com.seacroak.plushables.networking.SoundPacketHandler;
import com.seacroak.plushables.registry.assets.SoundRegistry;
import com.seacroak.plushables.registry.uncommon.TileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimationController;

public class OwlBlock extends BasePoweredPlushable {

  public OwlBlock() {
    super();
  }

  // Shift Right Click pickup code
  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (!player.isSneaking()) {
      BlockEntity blockEntity = world.getBlockEntity(pos);

      if (world instanceof ServerWorld serverWorld) {
        if (!(blockEntity instanceof OwlTileEntity)) return ActionResult.CONSUME;
        Direction hitSide = hit.getSide();
        Direction beFacing = state.get(Properties.HORIZONTAL_FACING);
        /* Server: Send sound & animation packets to clients*/
        if (hitSide == beFacing.getOpposite()) {
          SoundPacketHandler.sendPlayerPacketToClients(serverWorld, new SoundPacketHandler.PlayerSoundPacket(player, pos, SoundRegistry.OWL, 1f));
          AnimationPacketHandler.sendPacketToClients(serverWorld, new AnimationPacketHandler.AnimationPacket(player, pos, true, "interaction"));
          return ActionResult.CONSUME;
        } else {
          return ActionResult.PASS;
        }
      } else if (world.isClient) {
        if (!(blockEntity instanceof OwlTileEntity)) return ActionResult.CONSUME;
        OwlTileEntity owlEntity = (OwlTileEntity) blockEntity;
        /* Owl Special -> Only play animation of back of owl is right clicked */
        Direction hitSide = hit.getSide();
        Direction beFacing = state.get(Properties.HORIZONTAL_FACING);
        if (hitSide == beFacing.getOpposite()) {
          /* Client: Play animation */
          PlushablesNetworking.playAnimationOnClient(true, world, pos, "interaction");
          if (owlEntity.shouldAnimate()
              && owlEntity.interactionController.getAnimationState() == AnimationController.State.STOPPED) {
            PlushablesNetworking.playSoundOnClient(SoundRegistry.OWL, world, pos, 1f, 1f);
          }
          return ActionResult.SUCCESS;
        } else {
          return ActionResult.PASS;
        }

      }
    }
    return super.onUse(state, world, pos, player, hand, hit);
  }

  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.1875, 0.1875, 0.8125, 0.625, 0.8125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.125, 0.25, 0.25, 0.6875, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.75, 0.125, 0.25, 0.875, 0.6875, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0, 0.3125, 0.625, 0.1875, 0.6875));

    return shape;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return TileRegistry.OWL_TILE.instantiate(pos, state);
  }
}