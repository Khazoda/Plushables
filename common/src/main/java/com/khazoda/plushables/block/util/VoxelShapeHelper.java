package com.khazoda.plushables.block.util;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Utility class for handling VoxelShape operations, particularly rotation and
 * caching.
 * Provides methods to rotate and cache block shapes for different cardinal
 * directions.
 */
public class VoxelShapeHelper {

  /**
   * Rotates a VoxelShape from one direction to another.
   * This method performs the mathematical transformation needed to rotate a shape
   * around the Y axis.
   *
   * @param from  The initial Direction the shape is facing
   * @param to    The target Direction to rotate the shape to
   * @param shape The VoxelShape to rotate
   * @return A new VoxelShape that has been rotated to the target direction
   *
   * @apiNote This operation is computationally expensive. Results should be
   *          cached
   *          at block initialization rather than being calculated every frame.
   */
  public static VoxelShape rotateShape(Direction from, Direction to, VoxelShape shape) {
    VoxelShape[] buffer = new VoxelShape[]{shape, Shapes.empty()};
    int times = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;
    for (int i = 0; i < times; i++) {
      buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1],
          Shapes.create(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
      buffer[0] = buffer[1];
      buffer[1] = Shapes.empty();
    }
    return buffer[0];
  }

  /**
   * Generates an array of VoxelShapes for all cardinal directions.
   * Creates rotated versions of the input shape for North, East, South, and West
   * directions.
   *
   * @param blockShape The base VoxelShape to generate rotations from
   * @return An array of VoxelShapes containing the original and rotated shapes
   * Index 0: North (original)
   * Index 1: East
   * Index 2: South
   * Index 3: West
   */
  public static VoxelShape[] calculateBlockShapes(VoxelShape blockShape) {
    return new VoxelShape[] {
        blockShape,
        rotateShape(Direction.NORTH, Direction.EAST, blockShape),
        rotateShape(Direction.NORTH, Direction.SOUTH, blockShape),
        rotateShape(Direction.NORTH, Direction.WEST, blockShape)
    };
  }

  /**
   * Gets the appropriate VoxelShape for a given direction from a pre-calculated
   * array.
   * This method is an optimized way to get directional shapes without performing
   * rotations.
   *
   * @param direction   The direction to get the shape for
   * @param blockShape  The default shape
   * @param blockShapes Array of pre-calculated shapes for cardinal directions
   * @return The appropriate VoxelShape for the given direction
   */
  public static VoxelShape getSidedOutlineShape(Direction direction, VoxelShape blockShape, VoxelShape[] blockShapes) {
    return switch (direction) {
      case NORTH -> blockShapes[0];
      case EAST -> blockShapes[1];
      case SOUTH -> blockShapes[2];
      case WEST -> blockShapes[3];
      default -> blockShape;
    };
  }
}
