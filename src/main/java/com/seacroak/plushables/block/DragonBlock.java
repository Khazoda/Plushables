package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.CluckyTileEntity;
import com.seacroak.plushables.block.tile.DragonTileEntity;
import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.registry.TileRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimationController;

import java.util.List;
import java.util.Random;

public class DragonBlock extends AnimatronicPlushable {
  private static Random randPitch = null;
  public DragonBlock() {
    super();
    randPitch = new Random();
  }
  @Override
  public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    super.use(state, level, pos, player, hand, hit);
    if (level.isClientSide) {
      BlockEntity blockEntity = level.getBlockEntity(pos);
      if (blockEntity instanceof DragonTileEntity) {
        DragonTileEntity dragonEntity = (DragonTileEntity) blockEntity;
        dragonEntity.setShouldLook(true);
//        Check hand is holding black dye
        if (!player.isShiftKeyDown()) {
          if (player.getMainHandItem().is(Items.BLACK_DYE)) {
            if (dragonEntity.getVariant() == 0) {
              dragonEntity.setVariant(1);
              level.playSound(player, pos, SoundRegistry.PLUSHABLE_POP.get(), SoundSource.BLOCKS, 0.5f, (float) 1f);
              // Custom breaking particle code
              for (int i = 0; i < 20; i++) {
                level.addParticle(ParticleTypes.LARGE_SMOKE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
                level.addParticle(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
              }
            }
          }
        }
//        Check Animation has finished
        if (dragonEntity.getShouldLook() && dragonEntity.lookController.getAnimationState() == AnimationController.State.STOPPED) {
          level.playSound(player, pos, SoundRegistry.LIGHTFURY.get(), SoundSource.BLOCKS, 0.5f, (float) 0.7f + randPitch.nextFloat() / 2);
        }
        return InteractionResult.SUCCESS;
      }
    }
    if (!level.isClientSide) {
      BlockEntity cblockEntity = level.getBlockEntity(pos);
      if (cblockEntity instanceof DragonTileEntity) {
        DragonTileEntity dragonEntity = (DragonTileEntity) cblockEntity;
        // Remove dye from hand
        if (!player.isShiftKeyDown()) {
          if (dragonEntity.getVariant() == 0) {
            if (player.getMainHandItem().is(Items.BLACK_DYE)) {
              player.getMainHandItem().shrink(1);
              dragonEntity.setVariant(1);
            }
          }
        }
      }
    }
    return InteractionResult.PASS;
  }

  @Override
  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.25, 0.6875, 0.5, 0.6875), BooleanOp.OR);
    return shape;
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
    return TileRegistry.DRAGON_TILE.get().create(pPos, pState);
  }

  @Override
  public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
    pTooltip.add(Component.translatable("Perhaps some temporary black dye").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
    pTooltip.add(Component.translatable("would suit this plushable well..").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
    super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
  }
}