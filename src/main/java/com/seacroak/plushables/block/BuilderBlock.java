package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.NewBuilderTileEntity;
import com.seacroak.plushables.registry.TileRegistry;
import com.seacroak.plushables.util.HorizontalDirectionalBaseEntityBlock;
import com.seacroak.plushables.util.VoxelShapeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import static com.seacroak.plushables.util.HorizontalDirectionalBaseEntityBlock.HorizontalDirectionalBlock.FACING;

public class BuilderBlock extends HorizontalDirectionalBaseEntityBlock {

  public BuilderBlock() {
    super(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops());
    registerDefaultState(this.defaultBlockState());
  }

  @Override
  public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
    return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
  }

  //  Block Shape Caching
  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.09375, 0.0156, 0.09375, 0.90625, 0.445, 0.9062), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.8125, 0.0156, 0.0625, 0.9375, 0.635, 0.1875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.0625, 0.0156, 0.0625, 0.1875, 0.635, 0.1875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.0625, 0.0156, 0.8125, 0.1875, 0.635, 0.9375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.8125, 0.0156, 0.8125, 0.9375, 0.635, 0.9375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.125, 0.4531, 0.125, 0.875, 1.195, 0.875), BooleanOp.OR);
    return shape;
  }

  final VoxelShape blockShape = buildShape();
  final VoxelShape[] blockShapes = {blockShape,
      VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.EAST, blockShape),
      VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.SOUTH, blockShape),
      VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.WEST, blockShape)};

  @Override
  public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
    Direction direction = (Direction) pState.getValue(FACING);
    switch (direction) {
      case NORTH:
        return blockShapes[0];
      case EAST:
        return blockShapes[1];
      case SOUTH:
        return blockShapes[2];
      case WEST:
        return blockShapes[3];
      default:
        return blockShape;
    }
  }

  /* BLOCK ENTITY */
  @Override
  public RenderShape getRenderShape(BlockState pState) {
    return RenderShape.INVISIBLE;
  }

  @Override
  public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
    if (pState.getBlock() != pNewState.getBlock()) {
      BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
      if (blockEntity instanceof NewBuilderTileEntity) {
        ((NewBuilderTileEntity) blockEntity).drops();
      }
    }
    super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
  }

/*
  TODO OLD USE METHOD - MAY NEED TO REVERT TO THIS
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
*/

  @Override
  public InteractionResult use(BlockState state, Level level, BlockPos pos,
                               Player player, InteractionHand hand, BlockHitResult hit) {
    if (!level.isClientSide()) {
      BlockEntity blockEntity = level.getBlockEntity(pos);
      if (blockEntity instanceof NewBuilderTileEntity) {
        NetworkHooks.openScreen(((ServerPlayer) player), (NewBuilderTileEntity) blockEntity, pos);
      } else {
        throw new IllegalStateException("Container Provider is missing!");
      }
    }
    return InteractionResult.sidedSuccess(level.isClientSide());
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    return new NewBuilderTileEntity(pos, state);
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
    return createTickerHelper(type, TileRegistry.BUILDER_TILE.get(), NewBuilderTileEntity::tick);
  }

  // Base BlockState Properties
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
    pBuilder.add(FACING);
  }

  public BlockState getStateForPlacement(BlockPlaceContext pContext) {
    return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
  }

}