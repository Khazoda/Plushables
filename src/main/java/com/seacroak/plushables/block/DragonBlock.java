package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.DragonTileEntity;
import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.registry.TileRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimationController;

import java.util.List;

public class DragonBlock extends AnimatronicPlushable {

  public DragonBlock() {
    super();
  }

  // Shift Right Click pickup code
  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    super.onUse(state, world, pos, player, hand, hit);
    // Serverside code
    if (!world.isClient) {
      BlockEntity cblockEntity = world.getBlockEntity(pos);
      if (cblockEntity instanceof DragonTileEntity) {
        DragonTileEntity dragonEntity = (DragonTileEntity) cblockEntity;
        // Remove dye from hand
        if (!player.isSneaking()) {
          if (dragonEntity.getVariant() == 0) {
            if (player.getStackInHand(hand.MAIN_HAND).isOf(Items.BLACK_DYE)) {
              player.getStackInHand(hand.MAIN_HAND).decrement(1);
              dragonEntity.setVariant(1);
            }
          }
        }
      }
    }
    if (world.isClient) {
      BlockEntity blockEntity = world.getBlockEntity(pos);
      if (blockEntity instanceof DragonTileEntity) {
        DragonTileEntity dragonEntity = (DragonTileEntity) blockEntity;
        dragonEntity.setShouldLook(true);
//        Check hand is holding black dye
        if (!player.isSneaking()) {
          if (player.getStackInHand(hand.MAIN_HAND).isOf(Items.BLACK_DYE)) {
            if (dragonEntity.getVariant() == 0) {
              dragonEntity.setVariant(1);
              world.playSound(player, pos, SoundRegistry.PLUSHABLE_POP, SoundCategory.BLOCKS, 0.5f, (float) 1f);
              // Custom breaking particle code
              for (int i = 0; i < 20; i++) {
                world.addParticle(ParticleTypes.LARGE_SMOKE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
                world.addParticle(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
              }
            }
          }
        }
//        Check Animation has finished
        if (dragonEntity.getShouldLook() && dragonEntity.lookController.getAnimationState() == AnimationController.State.STOPPED) {
          world.playSound(player, pos, SoundRegistry.LIGHTFURY, SoundCategory.BLOCKS, 0.5f, (float) 0.7f + randPitch.nextFloat() / 2);

        }
        return ActionResult.SUCCESS;
      }
    }
    return ActionResult.PASS;
  }

  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.25, 0.6875, 0.5, 0.6875));

    return shape;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return TileRegistry.DRAGON_TILE.instantiate(pos, state);
  }

  @Override
  public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
    tooltip.add(Text.translatable("Perhaps some temporary black dye ").formatted(Formatting.ITALIC).formatted(Formatting.DARK_GRAY));
    tooltip.add(Text.translatable("would suit this plushable well..").formatted(Formatting.ITALIC).formatted(Formatting.DARK_GRAY));
    super.appendTooltip(stack, world, tooltip, options);
  }
}