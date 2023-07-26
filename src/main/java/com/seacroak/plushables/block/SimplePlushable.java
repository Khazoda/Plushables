package com.seacroak.plushables.block;

import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.util.VoxelShapeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public abstract class SimplePlushable extends HorizontalDirectionalBlock {
  public static Random rand;

  public SimplePlushable() {
    super(BlockBehaviour.Properties
        .copy(Blocks.WHITE_WOOL)
        .sound(SoundType.WOOL)
        .strength(0.7f)
        .requiresCorrectToolForDrops());
    rand = new Random();
  }


  @Override
  public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    // Serverside code
    if (!level.isClientSide) {
      if (player.isShiftKeyDown()) {
        Containers.dropItemStack(level, pos.getX(),pos.getY(),pos.getZ(), new ItemStack(this));
        level.updateNeighbourForOutputSignal(pos, this);
        level.removeBlock(pos, false);
        return InteractionResult.CONSUME;
      }
    }
    // Clientside code
    if (level.isClientSide) {
      if (player.isShiftKeyDown()) {
        level.playSound(player, pos, SoundRegistry.PLUSHABLE_POP.get(), SoundSource.BLOCKS, 0.5f, 1f);
        level.playSound(player, pos, SoundEvents.WOOL_HIT, SoundSource.BLOCKS, 0.5f, 1f);

        // Custom breaking particle code
        for (int i = 0; i < 5; i++) {
          level.addParticle(ParticleTypes.POOF, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
          level.addParticle(ParticleTypes.GLOW, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
        }
      }
    }
    return InteractionResult.PASS;
  }



  @Override
  public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
    if (level.isClientSide) {
      for (int i = 0; i < 5; i++) {
        level.addParticle(ParticleTypes.POOF, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
        level.addParticle(ParticleTypes.GLOW, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
        level.addParticle(ParticleTypes.FIREWORK, true, pos.getX(), pos.getY(), pos.getZ(), 0.1, 0.1, 0.1);
      }
    }
    return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
  }

  /**
   * buildShape() is overridable by children, and feeds a VoxelShape for this class to handle
   * blockShapes[] contains all 4 cardinally directed VoxelShapes
   * getShape() overrides the {@link net.minecraft.world.level.block.HorizontalDirectionalBlock} superclass' method with the correct shape
   */
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
