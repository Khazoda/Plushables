package com.khazoda.plushables.block.util;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VoxelShapeHelper {
  public static final int ORIENTATIONS_PER_FACE = 4;
  private static final Direction[] DIRECTIONS = Direction.values();
  public static final int DIRECTION_COUNT = DIRECTIONS.length;

  private VoxelShapeHelper() {
  }

  public static VoxelShape[] calculateBlockShapes(VoxelShape blockShape) {
    VoxelShape[] blockShapes = new VoxelShape[DIRECTION_COUNT * ORIENTATIONS_PER_FACE];
    for (Direction attachment : DIRECTIONS) {
      for (int rotation = 0; rotation < ORIENTATIONS_PER_FACE; rotation++) {
        blockShapes[orientationIndex(attachment, rotation)] = rotateShape(Orientation.of(attachment, rotation), blockShape);
      }
    }
    return blockShapes;
  }

  private static VoxelShape rotateShape(Orientation orientation, VoxelShape shape) {
    VoxelShape[] result = new VoxelShape[]{Shapes.empty()};

    shape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> {
      double newMinX = Double.POSITIVE_INFINITY, newMinY = Double.POSITIVE_INFINITY, newMinZ = Double.POSITIVE_INFINITY;
      double newMaxX = Double.NEGATIVE_INFINITY, newMaxY = Double.NEGATIVE_INFINITY, newMaxZ = Double.NEGATIVE_INFINITY;
      for (int corner = 0; corner < 8; corner++) {
        Vec3 transformed = orientation.transform(
            (corner & 1) == 0 ? minX : maxX,
            (corner & 2) == 0 ? minY : maxY,
            (corner & 4) == 0 ? minZ : maxZ);
        newMinX = Math.min(newMinX, transformed.x);
        newMinY = Math.min(newMinY, transformed.y);
        newMinZ = Math.min(newMinZ, transformed.z);
        newMaxX = Math.max(newMaxX, transformed.x);
        newMaxY = Math.max(newMaxY, transformed.y);
        newMaxZ = Math.max(newMaxZ, transformed.z);
      }
      result[0] = Shapes.or(result[0], Shapes.create(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ));
    });
    return result[0];
  }

  public static Direction frontFromRotation(Direction attachment, int rotation) {
    Direction front = attachment.getAxis().isVertical() ? Direction.NORTH : Direction.UP;
    for (int i = 0; i < rotation; i++) front = rotateAroundAttachment(front, attachment);
    return front;
  }

  public static int rotationFromFront(Direction attachment, Direction front) {
    for (int rotation = 0; rotation < ORIENTATIONS_PER_FACE; rotation++) {
      if (frontFromRotation(attachment, rotation) == front) return rotation;
    }
    return 0;
  }

  private static Direction rotateAroundAttachment(Direction direction, Direction attachment) {
    return attachment.getAxisDirection() == Direction.AxisDirection.POSITIVE
        ? direction.getCounterClockWise(attachment.getAxis())
        : direction.getClockWise(attachment.getAxis());
  }

  public static VoxelShape getSidedOutlineShape(Direction attachment, int rotation, VoxelShape[] blockShapes) {
    return blockShapes[orientationIndex(attachment, rotation)];
  }

  public static int orientationIndex(Direction attachment, int rotation) {
    return attachment.ordinal() * ORIENTATIONS_PER_FACE + rotation;
  }

  public record Orientation(Direction attachment, Direction front, Direction right) {
    public static Orientation of(Direction attachment, int rotation) {
      Direction front = frontFromRotation(attachment, rotation);
      return new Orientation(attachment, front, rotateAroundAttachment(front, attachment).getOpposite());
    }

    public Vec3 transform(double x, double y, double z) {
      double centeredX = x - 0.5, centeredY = y - 0.5, centeredZ = z - 0.5;
      return new Vec3(
          0.5 + right.getStepX() * centeredX + attachment.getStepX() * centeredY - front.getStepX() * centeredZ,
          0.5 + right.getStepY() * centeredX + attachment.getStepY() * centeredY - front.getStepY() * centeredZ,
          0.5 + right.getStepZ() * centeredX + attachment.getStepZ() * centeredY - front.getStepZ() * centeredZ);
    }

    public Direction transform(Direction direction) {
      return Direction.fromDelta(
          right.getStepX() * direction.getStepX() + attachment.getStepX() * direction.getStepY() - front.getStepX() * direction.getStepZ(),
          right.getStepY() * direction.getStepX() + attachment.getStepY() * direction.getStepY() - front.getStepY() * direction.getStepZ(),
          right.getStepZ() * direction.getStepX() + attachment.getStepZ() * direction.getStepY() - front.getStepZ() * direction.getStepZ());
    }
  }
}
