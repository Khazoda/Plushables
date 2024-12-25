package com.khazoda.plushables.block;

import com.khazoda.plushables.block.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class BasePlushable extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
  public static final Properties defaultSettings = Properties.of().sound(SoundType.WOOL).strength(0.7f).noOcclusion().pushReaction(PushReaction.DESTROY);
  public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
  /* ==========[  Default Block Shape Definition  ]========== */
  final VoxelShape blockShape = getShape(); // Empty 12x12 voxel box
  final VoxelShape[] blockShapes = VoxelShapeHelper.calculateBlockShapes(blockShape); // Cache all shape directions

  /* ==========[  Constructors  ]========== */
  public BasePlushable() {
    this(defaultSettings);
  }

  public BasePlushable(Properties settings) {
    super(settings);
    registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH).setValue(WATERLOGGED, false));
  }

  @Override
  public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
  }

  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0, 0, 0, 0.8, 0.8, 0.8));
    return shape;
  }

  @Override
  protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    Direction direction = state.getValue(FACING);
    return VoxelShapeHelper.getSidedOutlineShape(direction, blockShape, blockShapes);
  }

  @Override
  protected RenderShape getRenderShape(BlockState state) {
    return RenderShape.MODEL;
  }

  /* ==========[  BlockState  ]========== */
  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(FACING, WATERLOGGED);
  }

  // - Set Initial BlockState Property Values - //
  @Nullable
  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    return this.defaultBlockState()
        .setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite())
        .setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).is(Fluids.WATER));
  }

  // - Get Block's WATERLOGGED State - //
  @Override
  public FluidState getFluidState(BlockState state) {
    return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
  }

  // - Update Blockstate on Neighbour Change - //
  @Override
  protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
    if (state.getValue(WATERLOGGED)) level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
    return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
  }

}
