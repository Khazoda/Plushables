package com.seacroak.plushables.block;

import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.util.VoxelShapeUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static com.seacroak.plushables.registry.MainRegistry.TRUFFLES_PLUSHABLE;


public class PigBlock extends SimplePlushable {

  public PigBlock() {
    super();
  }

  // Shift Right Click pickup code
  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

    // Serverside code
    if (!world.isClient) {
      if (player.isSneaking()) {
        ItemScatterer.spawn(world, pos, DefaultedList.ofSize(1, new ItemStack(this)));
        world.updateComparators(pos, this);
        world.removeBlock(pos, false);
        return ActionResult.SUCCESS;

      } else {

        //  Turn into Truffles
        if (hand == hand.MAIN_HAND && player.getStackInHand(hand.MAIN_HAND).isOf(Items.LILY_OF_THE_VALLEY)) {
          world.removeBlock(pos, false);
          ItemScatterer.spawn(world, pos, DefaultedList.ofSize(1, new ItemStack((TRUFFLES_PLUSHABLE))));
          player.getStackInHand(hand.MAIN_HAND).decrement(1);
        }
        return ActionResult.SUCCESS;
      }
    }
    if (world.isClient) {
      if (player.isSneaking()) {
        world.playSound(player, pos, SoundRegistry.PLUSHABLE_POP, SoundCategory.BLOCKS, 0.5f, 1f);
        world.playSound(player, pos, SoundEvents.BLOCK_WOOL_HIT, SoundCategory.BLOCKS, 0.5f, 1f);

        // Custom breaking particle code
        for (int i = 0; i < 5; i++) {
          world.addParticle(ParticleTypes.POOF, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
          world.addParticle(ParticleTypes.GLOW, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
        }
      } else {
        // Turn into Truffles
        if (hand == hand.MAIN_HAND && player.getStackInHand(hand.MAIN_HAND).isOf(Items.LILY_OF_THE_VALLEY)) {
          world.playSound(player, pos, SoundRegistry.PLUSHABLE_POP, SoundCategory.BLOCKS, 0.5f, 0.2f);
          world.playSound(player, pos, SoundEvents.BLOCK_WOOL_HIT, SoundCategory.BLOCKS, 0.5f, 0.2f);

          // Custom breaking particle code
          for (int i = 0; i < 20; i++) {
            world.addParticle(ParticleTypes.SCULK_SOUL, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
            world.addParticle(ParticleTypes.LARGE_SMOKE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
          }
        }
      }
    }
    return ActionResult.PASS;
  }

  static public VoxelShape createPigShape() {
    VoxelShape s = VoxelShapes.empty();
    s = VoxelShapes.union(s, VoxelShapes.cuboid(0.125, 0, 0.125, 0.3125, 0.1875, 0.5));
    s = VoxelShapes.union(s, VoxelShapes.cuboid(0.59375, 0, 0.1875, 0.96875, 0.1875, 0.375));
    s = VoxelShapes.union(s, VoxelShapes.cuboid(0.25, 0.003125, 0.21875, 0.75, 0.3156249999999998, 0.875));
    s = VoxelShapes.union(s, VoxelShapes.cuboid(0.5625, 0, 0.875, 0.75, 0.1875, 1));
    s = VoxelShapes.union(s, VoxelShapes.cuboid(0.25, 0, 0.875, 0.4375, 0.1875, 1));
    s = VoxelShapes.union(s, VoxelShapes.cuboid(0.34375, 0, 0.03125, 0.65625, 0.3125, 0.21875));
    s = VoxelShapes.union(s, VoxelShapes.cuboid(0.421875, 0.0625, -0.03125, 0.578125, 0.15625, 0.03125));
    s = VoxelShapes.union(s, VoxelShapes.cuboid(0.375, 0.3125, 0.0625, 0.5, 0.328125, 0.1875));
    s = VoxelShapes.union(s, VoxelShapes.cuboid(0.375, 0.328125, 0.125, 0.5, 0.515625, 0.125));
    return s;
  }

  //VoxelShape
  VoxelShape pigShape = createPigShape();
  VoxelShape blockShape = pigShape;
  VoxelShape[] blockShapes = new VoxelShape[]{blockShape, // NORTH Direction VoxelShape
    VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.EAST, blockShape), // EAST Direction VoxelShape
    VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.SOUTH, blockShape), // SOUTH Direction VoxelShape
    VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.WEST, blockShape)}; // WEST Direction VoxelShape

  // Set the model's bounding box based on which direction it's facing
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

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }
}