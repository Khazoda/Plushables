package com.seacroak.plushables.util;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public abstract class HorizontalDirectionalBaseEntityBlock extends BlockWithEntity {

  public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

  protected HorizontalDirectionalBaseEntityBlock(Settings settings) {
    super(settings);
    setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));

  }

  //  Default Voxel Shape
  public VoxelShape getShape() {
    VoxelShape shape  = VoxelShapes.cuboid(0, 0, 0, 1, 1, 1);
    return shape;
  }

  final VoxelShape blockShape = getShape();
  final VoxelShape[] blockShapes = VoxelShapeUtils.calculateBlockShapes(blockShape);

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    Direction direction = state.get(FACING);
    return VoxelShapeUtils.getSidedOutlineShape(direction, blockShape, blockShapes);
  }


  // Render Type
  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.ENTITYBLOCK_ANIMATED;
  }


  public BlockState rotate(BlockState state, BlockRotation rotation) {
    return state.with(FACING, rotation.rotate(state.get(FACING)));
  }

  public BlockState mirror(BlockState state, BlockMirror mirror) {
    return state.rotate(mirror.getRotation(state.get(FACING)));
  }

  // Initial state upon placing
  @Override
  public BlockState getPlacementState(ItemPlacementContext context) {
    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, context.getHorizontalPlayerFacing().getOpposite());

  }

  // Append initial properties
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }


}
