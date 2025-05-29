package com.khazoda.plushables.block;

import com.khazoda.plushables.block.interaction.InteractionEffectData;
import com.khazoda.plushables.block.tooltip.TooltipData;
import com.khazoda.plushables.block.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
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

  protected final InteractionEffectData effectData;
  protected final TooltipData tooltipData;

  public BasePlushable(Properties settings) {
    this(settings, TooltipData.DEFAULT, InteractionEffectData.DEFAULT);
  }

  public BasePlushable(Properties settings, InteractionEffectData effectData) {
    this(settings, TooltipData.DEFAULT, effectData);
  }

  public BasePlushable(Properties settings, TooltipData tooltipData) {
    this(settings, tooltipData, InteractionEffectData.DEFAULT);
  }

  public BasePlushable(Properties settings, TooltipData tooltipData, InteractionEffectData effectData) {
    super(settings.lightLevel((blockState) -> effectData.lightLevel()));
    this.effectData = effectData;
    this.tooltipData = tooltipData;
    registerDefaultState(this.stateDefinition.any().setValue(ON_COOLDOWN, false).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH).setValue(WATERLOGGED, false));
  }

  /**
   * {@link #useWithoutItem}, {@link #playInteractionEffects} and {@link #startCooldown}
   * all work together to play interaction sounds and effects at a set cooldown.
   */
  protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    return state.getValue(ON_COOLDOWN) ? InteractionResult.CONSUME : this.playInteractionEffects(level, state, hitResult, player) ? InteractionResult.SUCCESS_SERVER : InteractionResult.PASS;
  }

  public boolean playInteractionEffects(Level level, BlockState state, BlockHitResult hitResult, Entity entity) {
    BlockPos blockPos = hitResult.getBlockPos();

    /* Play Sound */
    level.playSound(null, blockPos, effectData.soundEvent(), SoundSource.BLOCKS, effectData.soundVolume(), effectData.soundPitch());

    /* Spawn Particles */
    if (level.isClientSide && effectData.particleEffect() != null) {
      RandomSource random = level.getRandom();
      for (int i = 0; i < effectData.particleCount(); i++) {
        double spread = effectData.particleSpread();
        double x = blockPos.getX() + 0.5 + (random.nextDouble() - 0.5) * spread;
        double y = blockPos.getY() + 0.75 + (random.nextDouble() - 0.5) * spread;
        double z = blockPos.getZ() + 0.5 + (random.nextDouble() - 0.5) * spread;
        level.addParticle(effectData.particleEffect(), x, y, z, 0, 0, 0);
      }
    }

    this.startCooldown(state, level, blockPos);
    level.gameEvent(entity, GameEvent.BLOCK_ACTIVATE, blockPos);
    return true;
  }

  public void startCooldown(BlockState state, Level level, BlockPos pos) {
    level.setBlock(pos, state.setValue(ON_COOLDOWN, true), 3);
    level.updateNeighborsAt(pos, this);
    level.scheduleTick(pos, this, effectData.cooldownPeriod());
  }

  @Override
  protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
    level.setBlock(pos, state.setValue(ON_COOLDOWN, false), 3);
    level.updateNeighborsAt(pos, this);
  }

  public TooltipData getTooltipData() {
    return tooltipData;
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
    return this.defaultBlockState().setValue(ON_COOLDOWN, false).setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).is(Fluids.WATER));
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
  protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
    if (state.getValue(WATERLOGGED))
      scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
    return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
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
