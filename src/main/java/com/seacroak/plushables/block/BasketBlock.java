package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.BasketBlockEntity;
import com.seacroak.plushables.registry.TileRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BasketBlock extends BlockWithEntity {

  public BasketBlock() {
    super(FabricBlockSettings.create().sounds(BlockSoundGroup.WOOD).strength(1f).nonOpaque());
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
    return checkType(type, TileRegistry.BASKET_TILE, BasketBlockEntity::tick);
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (world.isClient) return ActionResult.CONSUME;

    BasketBlockEntity be = (BasketBlockEntity) world.getBlockEntity(pos);
    if (be == null) return ActionResult.FAIL;
    if (!player.isSneaking()) {
      if (player.getMainHandStack().isOf(Items.HEART_OF_THE_SEA)) {
//        player.sendMessage(Text.literal(String.valueOf(be.getPlushStack().get(0))));
//        player.sendMessage(Text.literal(String.valueOf(be.getPlushStack().get(1))));
//        player.sendMessage(Text.literal(String.valueOf(be.getPlushStack().get(2))));
//        player.sendMessage(Text.literal(String.valueOf(be.getPlushStack().get(3))));

        player.sendMessage(Text.literal(String.valueOf(be.getPlushStack()[0])));
        player.sendMessage(Text.literal(String.valueOf(be.getPlushStack()[1])));
        player.sendMessage(Text.literal(String.valueOf(be.getPlushStack()[2])));
        player.sendMessage(Text.literal(String.valueOf(be.getPlushStack()[3])));

        return ActionResult.SUCCESS;
      }
      if (be.pushPlush(player.getEquippedStack(EquipmentSlot.MAINHAND)))
        return ActionResult.SUCCESS;
    }
    if (player.isSneaking()) {
      if (be.popPlush())
        return ActionResult.SUCCESS;
    }
    return ActionResult.SUCCESS;
  }


  /* Rendering fluff */
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.0625, 0.875, 0.9375, 0.125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.875, 0.875, 0.9375, 0.9375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0, 0.125, 0.9375, 0.9375, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0, 0.125, 0.125, 0.9375, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.125, 0.875, 0.0625, 0.875));
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

}
