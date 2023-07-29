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
  final VoxelShape[] blockShapes = {blockShape,
      VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.EAST, blockShape),
      VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.SOUTH, blockShape),
      VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.WEST, blockShape)};

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    Direction direction = (Direction) state.get(FACING);

    switch (direction) {
      case NORTH: {
        return blockShapes[0];
      }
      case EAST: {
        return blockShapes[1];
      }
      case SOUTH: {
        return blockShapes[2];

      }
      case WEST: {
        return blockShapes[3];

      }
      default:
        return blockShape;
    }
  }

  // Render Type
  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.ENTITYBLOCK_ANIMATED;
  }


  public BlockState rotate(BlockState state, BlockRotation rotation) {
    return (BlockState) state.with(FACING, rotation.rotate((Direction) state.get(FACING)));
  }

  public BlockState mirror(BlockState state, BlockMirror mirror) {
    return state.rotate(mirror.getRotation((Direction) state.get(FACING)));
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
