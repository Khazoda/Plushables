package com.seacroak.plushables.block;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.tile.BasketBlockEntity;
import com.seacroak.plushables.config.ClientConfigValues;
import com.seacroak.plushables.item.PlushableBlockItem;
import com.seacroak.plushables.networking.PlushablesNetworking;
import com.seacroak.plushables.networking.SoundPacketHandler;
import com.seacroak.plushables.registry.assets.SoundRegistry;
import com.seacroak.plushables.registry.uncommon.TileRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.LocalRandom;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.seacroak.plushables.config.PlushablesConfig.allow_all_block_items_in_baskets;
import static com.seacroak.plushables.config.PlushablesConfig.enable_baskets;

public class BasketBlock extends BlockWithEntity {
  public static LocalRandom random;

  public BasketBlock() {
    super(FabricBlockSettings.create().sounds(BlockSoundGroup.WOOD).strength(1f).nonOpaque());
    random = new LocalRandom(100);

  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
    return validateTicker(type, TileRegistry.BASKET_TILE, BasketBlockEntity::tick);
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (!player.canModifyBlocks()) return ActionResult.CONSUME;
    BasketBlockEntity be = (BasketBlockEntity) world.getBlockEntity(pos);
    float randomPitch = 0.85f + random.nextFloat() / 4;

    if (be == null) return ActionResult.FAIL;
    /* Add ItemStack to basket */
    if (!player.isSneaking()) {
      ItemStack heldItem = player.getEquippedStack(EquipmentSlot.MAINHAND);
      /* Return early if player's hand is empty or doesn't contain a plushie */
      if (heldItem.isOf(Items.AIR)) return ActionResult.CONSUME;
      /* TODO (Someday extract this client server config checking functionality to a helper function) */
      /* Check client synced config */
      if (world.isClient) {
        if (!ClientConfigValues.allow_all_block_items_in_baskets) {
          if (!(heldItem.getItem() instanceof PlushableBlockItem)) return ActionResult.CONSUME;
        }
        /* Check server config */
      } else if (world instanceof ServerWorld serverWorld) {
        if (!allow_all_block_items_in_baskets) {
          if (!(heldItem.getItem() instanceof PlushableBlockItem)) return ActionResult.CONSUME;
        }
      }
      /* Adding an item is only executed if both the server and client allow it */
      if (be.pushPlush(player)) {
        if (world instanceof ServerWorld serverWorld)
          SoundPacketHandler.sendPlayerPacketToClients(serverWorld, new SoundPacketHandler.PlayerSoundPacket(player, pos, SoundRegistry.BASKET_IN, randomPitch));
        else if (world.isClient)
          PlushablesNetworking.playSoundOnClient(SoundRegistry.BASKET_IN, world, pos, 1f, randomPitch);
        return ActionResult.SUCCESS;
      } else {
        return ActionResult.CONSUME;
      }
    }
    /* Remove ItemStack from basket*/
    if (player.isSneaking()) {
      ItemStack heldStack = player.getEquippedStack(EquipmentSlot.MAINHAND);
      if ((heldStack.isOf(Items.AIR))) {
        if (be.popPlush(player)) {
          if (world instanceof ServerWorld serverWorld) {
            SoundPacketHandler.sendPlayerPacketToClients(serverWorld, new SoundPacketHandler.PlayerSoundPacket(player, pos, SoundRegistry.BASKET_OUT, randomPitch));
          } else if (world.isClient)
            PlushablesNetworking.playSoundOnClient(SoundRegistry.BASKET_OUT, world, pos, 1f, randomPitch);
          return ActionResult.SUCCESS;
        } else {
          return ActionResult.CONSUME;
        }
      }
    }
    return ActionResult.SUCCESS;
  }

  @Override
  public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
    if (!player.canModifyBlocks()) return;
    float randomPitch = 0.85f + random.nextFloat() / 4;
    BlockEntity be = world.getBlockEntity(pos);
    if (!(be instanceof BasketBlockEntity)) return;
    ItemStack[] poppedStack = ((BasketBlockEntity) be).popAll(player);
    /* Checks whether the lowest index in the basket's stack isn't empty */
    if (poppedStack[0].isOf(Items.AIR)) return;
    if (world instanceof ServerWorld serverWorld) {
      SoundPacketHandler.sendPlayerPacketToClients(serverWorld, new SoundPacketHandler.PlayerSoundPacket(player, pos, SoundRegistry.BASKET_ATTACK, randomPitch));
      for (ItemStack plush : poppedStack
      ) {
        ItemScatterer.spawn(world, pos.getX() + 0.5 + (0.5 * ((2 * random.nextFloat()) - 1)), pos.getY() + 0.5 + random.nextFloat(), pos.getZ() + 0.5 + (0.5 * ((2 * random.nextFloat()) - 1)), plush);
      }
    } else if (world.isClient) {
      PlushablesNetworking.playSoundOnClient(SoundRegistry.BASKET_ATTACK, world, pos, 1f, randomPitch);
    }
    super.onBlockBreakStart(state, world, pos, player);
  }

  @Override
  public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
    if (state.isOf(newState.getBlock())) {
      return;
    }
    BlockEntity blockEntity = world.getBlockEntity(pos);
    if (blockEntity instanceof BasketBlockEntity) {
      world.updateComparators(pos, this);
    }
    super.onStateReplaced(state, world, pos, newState, moved);
  }

  /* Rendering fluff */
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.00625, 0.125, 0.875, 0.06875, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0, 0.125, 0.9375, 0.9375, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0, 0.125, 0.125, 0.9375, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.0625, 0.875, 0.9375, 0.125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.875, 0.875, 0.9375, 0.9375));
    return shape;
  }

  final VoxelShape blockShape = getShape();

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return blockShape;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return TileRegistry.BASKET_TILE.instantiate(pos, state);
  }

  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.MODEL;
  }

  @Override
  public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
    /* TODO Someday extract this client server config checking functionality to a helper function */
    if (!ClientConfigValues.enable_baskets) {
      tooltip.add(Text.translatable("block." + PlushablesMod.MOD_ID + ".disabled"));
    } else {
      tooltip.add(Text.translatable("block." + PlushablesMod.MOD_ID + ".basket.tooltip.line_1"));
      tooltip.add(Text.translatable("block." + PlushablesMod.MOD_ID + ".basket.tooltip.line_2"));
      tooltip.add(Text.translatable("block." + PlushablesMod.MOD_ID + ".basket.tooltip.line_3"));
    }
    if(ClientConfigValues.allow_all_block_items_in_baskets) {
      tooltip.add(Text.translatable("block." + PlushablesMod.MOD_ID + ".basket.tooltip.experimental_enabled"));
    }
    super.appendTooltip(stack, world, tooltip, options);
  }

  @Override
  public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
    super.onDestroyedByExplosion(world, pos, explosion);
    if (world instanceof ServerWorld serverworld) {
      ItemScatterer.spawn(world, pos.getX() + 2, pos.getY(), pos.getZ() + 2, new ItemStack(Items.STICK));
      ItemScatterer.spawn(world, pos.getX() - 2, pos.getY(), pos.getZ() + 2, new ItemStack(Items.STICK));
      ItemScatterer.spawn(world, pos.getX() + 2, pos.getY(), pos.getZ() - 2, new ItemStack(Items.STICK));
      ItemScatterer.spawn(world, pos.getX() - 2, pos.getY(), pos.getZ() - 2, new ItemStack(Items.STICK));
    }
  }

  @Override
  public boolean shouldDropItemsOnExplosion(Explosion explosion) {
    return false;
  }

  /* Check whether block is enabled in config */
  @Override
  public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    super.onPlaced(world, pos, state, placer, itemStack);

    /* TODO Someday extract this client server config checking functionality to a helper function */
    if (world.isClient) {
      if (!ClientConfigValues.enable_baskets) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
      }
      /* Check server config */
    } else if (world instanceof ServerWorld serverWorld) {
      if (!enable_baskets) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
      }
    }
    if (!(ClientConfigValues.enable_baskets || enable_baskets)) {
      assert placer != null;
      if (world.isClient) placer.sendMessage(Text.translatable("block." + PlushablesMod.MOD_ID + ".disabled"));
    }

  }


}
