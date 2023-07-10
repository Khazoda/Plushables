package com.seacroak.plushables.block;

import com.seacroak.plushables.util.VoxelShapeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class SimplePlushable extends HorizontalDirectionalBlock {

  public SimplePlushable() {
    super(BlockBehaviour.Properties
        .copy(Blocks.WHITE_WOOL)
        .sound(SoundType.WOOL)
        .strength(0.7f)
        .requiresCorrectToolForDrops());
//    this.registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
  }


  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.box(0, 0, 0, 0.8, 0.8, 0.8);
    return shape;
  }

  final VoxelShape blockShape = buildShape();
  final VoxelShape[] blockShapes = {blockShape,
      VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.EAST, blockShape),
      VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.SOUTH, blockShape),
      VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.WEST, blockShape)};

  //  Default VoxelShape
  @Override
  public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
    Direction direction = (Direction) pState.getValue(FACING);

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

  public RenderShape getRenderShape(BlockState pState) {
    return RenderShape.MODEL;
  }
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
    pBuilder.add(FACING);
  }
  public BlockState getStateForPlacement(BlockPlaceContext pContext) {
    return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
  }
}
