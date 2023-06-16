package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.CluckyTileEntity;
import com.seacroak.plushables.block.tile.DragonTileEntity;
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

public class DragonBlock extends AnimatronicPlushable {

  public DragonBlock() {
    super();
  }

  // Shift Right Click pickup code
  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos,
                            PlayerEntity player, Hand hand, BlockHitResult hit) {
    super.onUse(state, world, pos, player, hand, hit);
    // Serverside code
    if (world.isClient) {
      BlockEntity blockEntity = world.getBlockEntity(pos);
      if (blockEntity instanceof DragonTileEntity) {
        DragonTileEntity dragonEntity = (DragonTileEntity) blockEntity;
        dragonEntity.setShouldLook(true);
        if (dragonEntity.getShouldLook()
          && dragonEntity.lookController.getAnimationState() == AnimationController.State.STOPPED) {
          System.out.println((float) randPitch.nextFloat());
          world.playSound(player, pos, SoundRegistry.CLUCKY_CLUCK, SoundCategory.BLOCKS, 0.5f,
            (float) 0.7f + randPitch.nextFloat() / 2);
        }
        return ActionResult.SUCCESS;
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
    return TileRegistry.DRAGON_TILE.instantiate(pos, state);
  }
}