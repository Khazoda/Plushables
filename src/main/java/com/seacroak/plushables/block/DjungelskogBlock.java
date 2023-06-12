package com.seacroak.plushables.block;

import com.seacroak.plushables.util.VoxelShapeUtils;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class DjungelskogBlock extends SimplePlushable {
    public static Random rand;

    public DjungelskogBlock() {
        super();
        rand = new Random();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(Properties.HORIZONTAL_FACING, context.getHorizontalPlayerFacing().getOpposite());

    }
  static final VoxelShape blockShape = getShape();
  static final VoxelShape[] blockShapes = { blockShape,
    VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.EAST, blockShape),
    VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.SOUTH, blockShape),
    VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.WEST, blockShape) };

  static public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.0625, 0.3125, 0.125, 0.25));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0, 0.0625, 0.8125, 0.125, 0.25));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.25, 0.8125, 0.5, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.09375, 0.203125, 0.6875, 0.484375, 0.28125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.21875, 0.5, 0.3125, 0.78125, 0.75, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.75, 0.25, 0.6875, 1.03125, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.75, 0.125, 0.5625, 0.875, 0.25));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.75, 0.75, 0.5625, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0, 0.875, 0.5625, 0.125, 0.9375));
    return shape;
  }

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
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

}
