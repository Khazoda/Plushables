package com.khazoda.plushables.block;

import com.khazoda.plushables.block.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Base class for all plushable blocks in the mod.
 * Implements core functionality for directional placement, waterlogging, and
 * block shapes.
 * Extends HorizontalDirectionalBlock for cardinal direction placement and
 * implements SimpleWaterloggedBlock for waterlogging support.
 */
public abstract class BasePlushable extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
  public static final Properties defaultSettings = Properties.of().sound(SoundType.WOOL).strength(0.1f).noOcclusion().pushReaction(PushReaction.DESTROY);
  public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
  public static final BooleanProperty ON_COOLDOWN = BooleanProperty.create("on_cooldown");
  final VoxelShape blockShape = useShape(); // Empty 12x12 voxel box
  final VoxelShape[] blockShapes = VoxelShapeHelper.calculateBlockShapes(blockShape); // Cache all shape directions

  /* These fields can be overwritten by child classes */
  protected SoundEvent interactionSound = SoundEvents.WOOL_HIT;
  protected int cooldownPeriod = 10;

  /* ==========[ Constructors ]========== */
  public BasePlushable() {
    this(defaultSettings);
  }

  public BasePlushable(Properties settings) {
    super(settings);
    registerDefaultState(this.stateDefinition.any().setValue(ON_COOLDOWN, false)
        .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
        .setValue(WATERLOGGED, false));
  }

  /**
   * {@link #useWithoutItem}, {@link #playInteractionEffects} & {@link #startCooldown}
   * all work together to play interaction sounds and effects at a set cooldown.
   */
  protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    return state.getValue(ON_COOLDOWN) ? InteractionResult.CONSUME :
        this.playInteractionEffects(level, state, hitResult, player) ? InteractionResult.sidedSuccess(level.isClientSide) : InteractionResult.PASS;
  }

  public boolean playInteractionEffects(Level level, BlockState state, BlockHitResult hitResult, Entity entity) {
    BlockPos blockPos = hitResult.getBlockPos();
    this.startCooldown(state, level, blockPos);
    level.playSound(null, blockPos, interactionSound, SoundSource.BLOCKS, 2.0F, 1.0F);
    level.gameEvent(entity, GameEvent.BLOCK_ACTIVATE, blockPos);
    return true;
  }

  public void startCooldown(BlockState state, Level level, BlockPos pos) {
    level.setBlock(pos, state.setValue(ON_COOLDOWN, true), 3);
    level.updateNeighborsAt(pos, this);
    level.scheduleTick(pos, this, cooldownPeriod);
  }

  @Override
  protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
    level.setBlock(pos, state.setValue(ON_COOLDOWN, false), 3);
    level.updateNeighborsAt(pos, this);
  }

  /**
   * Revealable tooltips
   */
  @Override
  public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
  }

  /**
   * Defines the base shape of the plushable.
   * Override this in child classes to define custom shapes.
   *
   * @return The VoxelShape for this plushable
   */
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0, 0, 0, 0.8, 0.8, 0.8));
    return shape;
  }

  /**
   * Takes the shape generated in useShape() and passes it to parents to use as the block's VoxelShape.
   * Uses cached shapes for better performance instead of recalculating on each block placement.
   */
  @Override
  protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    Direction direction = state.getValue(FACING);
    return VoxelShapeHelper.getSidedOutlineShape(direction, blockShape, blockShapes);
  }

  @Override
  protected RenderShape getRenderShape(BlockState state) {
    return RenderShape.MODEL;
  }

  /* ==========[ BlockState ]========== */

  /**
   * Adds FACING and WATERLOGGED properties to the block's state definition.
   */
  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(ON_COOLDOWN, FACING, WATERLOGGED);
  }

  /**
   * Determines the initial state of the block when placed.
   * Sets facing direction based on player placement and checks for waterlogging.
   */
  @Nullable
  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    return this.defaultBlockState().setValue(ON_COOLDOWN, false)
        .setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite())
        .setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).is(Fluids.WATER));
  }

  /**
   * Gets the fluid state for this block, handling waterlogging.
   */
  @Override
  public FluidState getFluidState(BlockState state) {
    return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
  }

  /**
   * Updates the block state when neighboring blocks change.
   * Handles waterlogging state updates.
   */
  @Override
  protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
    if (state.getValue(WATERLOGGED)) level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
    return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
  }

  /**
   * Standard Method Overrides
   */
  @Override
  protected BlockState rotate(BlockState state, Rotation rot) {
    return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
  }

  @Override
  protected BlockState mirror(BlockState state, Mirror mirror) {
    return state.rotate(mirror.getRotation(state.getValue(FACING)));
  }

  @Override
  protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
    return false;
  }
}
