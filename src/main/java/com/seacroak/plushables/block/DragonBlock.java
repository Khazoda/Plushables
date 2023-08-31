package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.DragonTileEntity;
import com.seacroak.plushables.networking.AnimationPacketHandler;
import com.seacroak.plushables.networking.PlushablesNetworking;
import com.seacroak.plushables.networking.SoundPacketHandler;
import com.seacroak.plushables.registry.assets.SoundRegistry;
import com.seacroak.plushables.registry.uncommon.TileRegistry;
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

public class DragonBlock extends BasePoweredPlushable {

  public DragonBlock() {
    super();
  }

  // Shift Right Click pickup code
  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (!player.isSneaking()) {
      BlockEntity blockEntity = world.getBlockEntity(pos);

      if (world instanceof ServerWorld serverWorld) {
        if (!(blockEntity instanceof DragonTileEntity)) return ActionResult.CONSUME;
        /* Server: Send sound & animation packets to clients*/
        SoundPacketHandler.sendPlayerPacketToClients(serverWorld, new SoundPacketHandler.PlayerSoundPacket(player, pos, SoundRegistry.LIGHTFURY, 1f));
        AnimationPacketHandler.sendPacketToClients(serverWorld, new AnimationPacketHandler.AnimationPacket(player, pos, true, "interaction"));
        return ActionResult.CONSUME;

      } else if (world.isClient) {
        if (!(blockEntity instanceof DragonTileEntity)) return ActionResult.CONSUME;
        DragonTileEntity dragonEntity = (DragonTileEntity) blockEntity;
        /* Client: Play animation */
        PlushablesNetworking.playAnimationOnClient(true, world, pos, "interaction");
        if (dragonEntity.shouldAnimate()
            && dragonEntity.interactionController.getAnimationState() == AnimationController.State.STOPPED) {
          PlushablesNetworking.playSoundOnClient(SoundRegistry.LIGHTFURY, world, pos, 1f, 1f);
        }
        return ActionResult.SUCCESS;
      }
    }
    /* Run base plushable shift click behaviour */
    return super.onUse(state, world, pos, player, hand, hit);
  }

  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.1875, 0.40625, 0.125, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0, 0.1875, 0.59375, 0.125, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.3125, 0.59375, 0.4375, 0.5625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.3125, 0.171875, 0.59375, 0.5, 0.421875));

    return shape;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return TileRegistry.DRAGON_TILE.instantiate(pos, state);
  }
}