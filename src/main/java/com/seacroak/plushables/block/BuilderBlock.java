package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BuilderBlock extends BaseEntityBlock {

  public BuilderBlock() {
    super(BlockBehaviour.Properties
        .copy(Blocks.OAK_WOOD)
        .sound(SoundType.WOOD)
        .strength(2.5f)
        .requiresCorrectToolForDrops());
    registerDefaultState(this.defaultBlockState());
  }

  @Override
  public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
    return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
  }

  @Override
  public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
    if (pState.is(pNewState.getBlock())) {
      return;
    }
    BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
//    if (blockEntity instanceof Inventory) {
      if (blockEntity instanceof Container) {
//      Containers.dropItemStack(pLevel, pPos.getX(),pPos.getY(),pPos.getZ(), (Container) blockEntity);
      pLevel.updateNeighbourForOutputSignal(pPos, this);
    }
    super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
  }

  //Functional Code
  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
    return new BuilderTileEntity(pPos,pState);
  }

  @Override
  public @NotNull VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.0625, 0.1875, 0.625, 0.1875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.8125, 0.1875, 0.625, 0.9375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.8125, 0, 0.8125, 0.9375, 0.625, 0.9375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.8125, 0, 0.0625, 0.9375, 0.625, 0.1875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.078125, 0.3125, 0.078125, 0.921875, 0.4375, 0.921875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.1875, 0.4375, 0.1875, 0.8125, 1.125, 0.8125), BooleanOp.OR);
    return shape;
  }

  @Override
  public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
    if (!pLevel.isClientSide) {
      MenuProvider screenHandlerFactory = pState.getMenuProvider(pLevel, pPos);
      BlockEntity blockEntity = pLevel.getBlockEntity(pPos);

      if (screenHandlerFactory != null) {
//        pPlayer.openMenu(screenHandlerFactory); Old
        ServerPlayer serverPlayer = (ServerPlayer) pPlayer;
        BuilderTileEntity builderTileEntity = (BuilderTileEntity) blockEntity;
        NetworkHooks.openScreen(serverPlayer, builderTileEntity, pPos);

      }
    }
    return InteractionResult.SUCCESS;
  }

//  Block Entity Rendering
  @Override
  public RenderShape getRenderShape(BlockState pState) {
    return RenderShape.ENTITYBLOCK_ANIMATED;
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
    return createTickerHelper(pBlockEntityType, TileRegistry.BUILDER_TILE.get(), BuilderTileEntity::tick);
  }

}