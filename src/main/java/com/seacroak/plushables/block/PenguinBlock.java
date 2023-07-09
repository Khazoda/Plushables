package com.seacroak.plushables.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PenguinBlock extends Block {

  public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

  public PenguinBlock(Properties p) {
    super(p);
    this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));

  }

  @Override
  public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.25, 0.875, 0.875, 0.625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.6875, 0, 0.125, 0.875, 0.0625, 0.25), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.125, 0.3125, 0.0625, 0.25), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.6875, 0.625, 0.1875, 0.9375, 0.875, 0.25), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.0625, 0.625, 0.1875, 0.3125, 0.875, 0.25), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.375, 0.625, 0.1875, 0.625, 0.75, 0.25), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.25, 0.875, 0.3125, 0.75, 0.9375, 0.5625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.125, 0.875, 0.1875, 0.1875, 0.9375, 0.4375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.8125, 0.875, 0.1875, 0.875, 0.9375, 0.4375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.1875, 0.0625, 0.625, 0.8125, 0.8125, 0.6875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.1875, 0.125, 0.6875, 0.8125, 0.5, 0.75), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.1875, 0.5625, 0.6875, 0.8125, 0.8125, 0.75), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.375, 0.125, 0.75, 0.625, 0.25, 0.8125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.3125, 0.9375, 0.3125, 0.6875, 1, 0.5625), BooleanOp.OR);
    return shape;
  }

  public RenderShape getRenderShape(BlockState pState) {
    return RenderShape.MODEL;
  }

  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
    pBuilder.add(FACING);
  }

  public BlockState getStateForPlacement(BlockPlaceContext pContext) {
    return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
  }

  public BlockState rotate(BlockState pState, Rotation pRotation) {
    return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
  }

  public BlockState mirror(BlockState pState, Mirror pMirror) {
    return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
  }

}
