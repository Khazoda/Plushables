package com.khazoda.plushables.block;

import com.khazoda.plushables.block.interaction.InteractionEffectData;
import com.khazoda.plushables.block.tooltip.TooltipData;
import com.khazoda.plushables.block.util.VoxelShapeHelper;
import com.khazoda.plushables.item.PlushableBlockItem;
import com.khazoda.plushables.platform.Services;
import com.khazoda.plushables.registry.SoundRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Base class for all plushable blocks in the mod.
 * Implements core functionality for directional placement, waterlogging, and
 * block shapes.
 */
public abstract class BasePlushable extends Block implements SimpleWaterloggedBlock, EntityBlock {
  public static final Properties defaultSettings = Properties.of().sound(SoundType.WOOL).strength(0.1f).noOcclusion().pushReaction(PushReaction.DESTROY);
  public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
  public static final DirectionProperty ATTACHMENT = DirectionProperty.create("attachment");
  public static final IntegerProperty ROTATION = IntegerProperty.create("rotation", 0, 3);
  public static final BooleanProperty ON_COOLDOWN = BooleanProperty.create("on_cooldown");
  final VoxelShape[] blockShapes = VoxelShapeHelper.calculateBlockShapes(useShape());

  protected final InteractionEffectData effectData;
  protected final TooltipData tooltipData;

  /* ==========[ Constructors ]========== */
  public BasePlushable() {
    this(defaultSettings);
  }

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
    registerDefaultState(this.stateDefinition.any().setValue(ON_COOLDOWN, false).setValue(ATTACHMENT, Direction.UP).setValue(ROTATION, 0).setValue(WATERLOGGED, false));
  }

  /**
   * {@link #useWithoutItem}, {@link #playInteractionEffects} and {@link #startCooldown}
   * all work together to play interaction sounds and effects at a set cooldown, and allow an item
   * to be deposited and extracted.
   */
  protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    // Shift + RClick = extract item
    if (player.isSecondaryUseActive()) {
      if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.CONSUME;
      return extractItemFromPlushable(serverLevel, state, pos, player) ? InteractionResult.SUCCESS : InteractionResult.PASS;
    }

    // RClick = play sound & particle effects
    if (state.getValue(ON_COOLDOWN)) return InteractionResult.CONSUME;
    if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.SUCCESS;
    if (this.playInteractionEffects(serverLevel, state, pos, player))
      return InteractionResult.CONSUME;
    return InteractionResult.PASS;
  }

  @Override
  protected ItemInteractionResult useItemOn(ItemStack heldStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
    if (!(level instanceof ServerLevel serverLevel)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    return storeItemInPlushable(serverLevel, state, pos, player, heldStack) ? ItemInteractionResult.SUCCESS : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
  }

  private static boolean storeItemInPlushable(ServerLevel serverLevel, BlockState state, BlockPos pos, Player player, ItemStack heldStack) {
    if (!(serverLevel.getBlockEntity(pos) instanceof BasePlushableBlockEntity blockEntity)) return false;
    if (!blockEntity.getTheItem().isEmpty() || heldStack.isEmpty()) return false;
    if (!canStoreInPlushable(heldStack)) return false;

    ItemStack item = player.isCreative() ? heldStack.copyWithCount(1) : heldStack.split(1);
    blockEntity.setTheItem(item);
    if (serverLevel.getBlockEntity(pos) != blockEntity) return true;

    playStorageEffects(serverLevel, state, pos, SoundRegistry.INSERT_ITEM.get(), 1.0F, 1.0F);
    serverLevel.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
    return true;
  }

  private static boolean extractItemFromPlushable(ServerLevel serverLevel, BlockState state, BlockPos pos, Player player) {
    if (!(serverLevel.getBlockEntity(pos) instanceof BasePlushableBlockEntity blockEntity)) return false;

    ItemStack item = blockEntity.removeTheItem();
    if (item.isEmpty()) return false;

    if (!player.addItem(item)) player.drop(item, false);
    playStorageEffects(serverLevel, state, pos, SoundRegistry.EXTRACT_ITEM.get(), 0.6F, 1.0F);
    serverLevel.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
    return true;
  }

  private static void playStorageEffects(ServerLevel serverLevel, BlockState state, BlockPos pos, SoundEvent sound, float volume, float pitch) {
    serverLevel.playSound(null, pos, sound, SoundSource.BLOCKS, volume, pitch);
    sendFluffFromServer(serverLevel, pos, state.getValue(ATTACHMENT));
  }

  public boolean playInteractionEffects(ServerLevel serverLevel, BlockState state, BlockPos blockPos, Entity entity) {
    boolean hasStoredItem = hasStoredItem(serverLevel, blockPos);

    serverLevel.playSound(null, blockPos, effectData.soundEvent(), SoundSource.BLOCKS, effectData.soundVolume(), hasStoredItem ? effectData.soundPitch() * 0.75F : effectData.soundPitch());
    if (hasStoredItem) sendFluffFromServer(serverLevel, blockPos, state.getValue(ATTACHMENT));
    if (effectData.particleEffect() != null) {
      serverLevel.sendParticles(effectData.particleEffect(), blockPos.getX() + 0.5, blockPos.getY() + 0.75 + effectData.particleYOffset(), blockPos.getZ() + 0.5, effectData.particleCount(), effectData.particleSpread() * 0.5, effectData.particleSpread() * 0.5, effectData.particleSpread() * 0.5, 0);
    }

    this.startCooldown(state, serverLevel, blockPos);
    serverLevel.gameEvent(entity, GameEvent.BLOCK_ACTIVATE, blockPos);
    return true;
  }

  private static boolean hasStoredItem(Level level, BlockPos pos) {
    return level.getBlockEntity(pos) instanceof BasePlushableBlockEntity blockEntity && !blockEntity.getTheItem().isEmpty();
  }

  /* Spawn snowflake particles on the attachment face (for storage related interaction) */
  private static void sendFluffFromServer(ServerLevel level, BlockPos pos, Direction attachment) {
    Direction.Axis axis = attachment.getAxis();
    double plane = attachment.getAxisDirection() == Direction.AxisDirection.POSITIVE ? 0.12 : 0.88;
    double x = pos.getX() + (axis == Direction.Axis.X ? plane : 0.5);
    double y = pos.getY() + (axis == Direction.Axis.Y ? plane : 0.5);
    double z = pos.getZ() + (axis == Direction.Axis.Z ? plane : 0.5);
    double xSpread = axis == Direction.Axis.X ? 0.03 : 0.25;
    double ySpread = axis == Direction.Axis.Y ? 0.03 : 0.25;
    double zSpread = axis == Direction.Axis.Z ? 0.03 : 0.25;

    level.sendParticles(ParticleTypes.SNOWFLAKE, x, y, z, 5, xSpread, ySpread, zSpread, 0.01);
  }

  public void startCooldown(BlockState state, ServerLevel serverLevel, BlockPos pos) {
    serverLevel.setBlock(pos, state.setValue(ON_COOLDOWN, true), 3);
    serverLevel.updateNeighborsAt(pos, this);
    serverLevel.scheduleTick(pos, this, effectData.cooldownPeriod());
  }

  @Override
  protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
    level.setBlock(pos, state.setValue(ON_COOLDOWN, false), 3);
    level.updateNeighborsAt(pos, this);
  }

  @Override
  protected boolean hasAnalogOutputSignal(BlockState state) {
    return true;
  }

  @Override
  protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
    return hasStoredItem(level, pos) ? 15 : 0;
  }

  @Override
  protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
    if (!state.is(newState.getBlock())) {
      level.updateNeighbourForOutputSignal(pos, state.getBlock());
    }
    super.onRemove(state, level, pos, newState, movedByPiston);
  }

  @Override
  protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
    if (level instanceof ServerLevel serverLevel && tryExplodeStoredTnt(serverLevel, pos)) {
      return;
    }
    super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
  }

  static boolean tryExplodeStoredTnt(ServerLevel serverLevel, BlockPos pos) {
    if (!serverLevel.hasNeighborSignal(pos)) return false;
    if (!(serverLevel.getBlockEntity(pos) instanceof BasePlushableBlockEntity blockEntity)) return false;
    if (!blockEntity.getTheItem().is(Blocks.TNT.asItem())) return false;

    serverLevel.removeBlock(pos, false);
    serverLevel.sendParticles(ParticleTypes.SNOWFLAKE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 90, 1.0, 1.0, 1.0, 0.08);
    serverLevel.explode(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 3.0F, Level.ExplosionInteraction.TNT);
    return true;
  }

  /**
   * Revealable tooltips
   */
  @Override
  public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

    if (!Services.PLATFORM.isClientSide()) return;
    boolean extendTooltip = Screen.hasControlDown();

    if (!extendTooltip) {
      tooltipComponents.add(Component.translatable("tooltip.plushables.holdctrl").withStyle(ChatFormatting.GRAY));
    }

    if (extendTooltip) {
      // Add basic info
      tooltipComponents.add(Component.literal(tooltipData.number()).withStyle(ChatFormatting.YELLOW));
      tooltipComponents.add(Component.translatable("tooltip.plushables.artist").append(" \u00B7 " + tooltipData.artist()).withStyle(ChatFormatting.GREEN));
      tooltipComponents.add(Component.translatable("tooltip.plushables.created").append(" \u00B7 " + tooltipData.localizeDate(Minecraft.getInstance().getLanguageManager().getSelected())).withStyle(ChatFormatting.DARK_GREEN));

      // Add trivia if available
      if (tooltipData.trivia() != null) {
        tooltipComponents.add(CommonComponents.EMPTY);
        addTrivia(tooltipComponents, tooltipData.trivia());
      }
    }

    if (isTotallyStuffed(stack)) {
      tooltipComponents.add(Component.translatable("tooltip.plushables.totally_stuffed").withStyle(ChatFormatting.DARK_GRAY));
    } else if (!storedPlushableItem(stack).isEmpty()) {
      tooltipComponents.add(Component.translatable("tooltip.plushables.contains_item").withStyle(ChatFormatting.DARK_GRAY));
    }
  }

  static boolean canStoreInPlushable(ItemStack stack) {
    if (stack.getItem() instanceof PlushableBlockItem) {
      return !isTotallyStuffed(stack) && StoredItemComponentAllowlist.allows(stack, DataComponents.CONTAINER);
    }
    return StoredItemComponentAllowlist.allows(stack);
  }

  private static boolean isTotallyStuffed(ItemStack stack) {
    for (int depth = 0; depth < 8; depth++) {
      if (!(stack.getItem() instanceof PlushableBlockItem)) return false;
      stack = storedPlushableItem(stack);
    }
    return true;
  }

  private static ItemStack storedPlushableItem(ItemStack stack) {
    return stack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyOne();
  }

  private void addTrivia(List<Component> tooltipComponents, String trivia) {
    String[] words = trivia.split(" ");
    StringBuilder currentLine = new StringBuilder();

    for (String word : words) {
      if (currentLine.length() + word.length() > 35) {
        tooltipComponents.add(Component.literal(currentLine.toString().trim()).withStyle(ChatFormatting.GRAY));
        currentLine.setLength(0);
      }
      currentLine.append(word).append(" ");
    }
    if (!currentLine.isEmpty()) {
      tooltipComponents.add(Component.literal(currentLine.toString().trim()).withStyle(ChatFormatting.GRAY));
    }
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
    return VoxelShapeHelper.getSidedOutlineShape(state.getValue(ATTACHMENT), state.getValue(ROTATION), blockShapes);
  }

  @Override
  protected RenderShape getRenderShape(BlockState state) {
    return RenderShape.MODEL;
  }

  /* =========[ Block Entity ]========= */

  @Override
  public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    return new BasePlushableBlockEntity(pos, state);
  }

  /* ==========[ Bounciness ]========== */
  public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
    super.fallOn(level, state, pos, entity, fallDistance * 0.5F);
  }

  public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
    if (entity.isSuppressingBounce()) {
      super.updateEntityAfterFallOn(level, entity);
    } else {
      this.bounceUp(entity);
    }
  }

  private void bounceUp(Entity entity) {
    Vec3 vec3 = entity.getDeltaMovement();
    if (vec3.y < (double) 0.0F) {
      double d = entity instanceof LivingEntity ? (double) 1.0F : 0.8;
      entity.setDeltaMovement(vec3.x, -vec3.y * (double) 0.33F * d, vec3.z);
    }
  }

  /* ==========[ BlockState ]========== */

  /**
   * Adds attachment, rotation and waterlogged properties to the block's state definition.
   */
  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(ON_COOLDOWN, ATTACHMENT, ROTATION, WATERLOGGED);
  }

  /**
   * Determines the initial state of the block when placed.
   * Sets facing direction based on player placement and checks for waterlogging.
   */
  @Nullable
  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    Direction attachment = context.getClickedFace();
    Direction front = getPlacementFront(context, attachment);
    int rotation = VoxelShapeHelper.rotationFromFront(attachment, front);
    boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).is(Fluids.WATER);

    return this.defaultBlockState().setValue(ON_COOLDOWN, false).setValue(ATTACHMENT, attachment).setValue(ROTATION, rotation).setValue(WATERLOGGED, waterlogged);
  }

  private Direction getPlacementFront(BlockPlaceContext context, Direction attachment) {
    Vec3 center = Vec3.atCenterOf(context.getClickedPos());
    Vec3 offset = context.getClickLocation().subtract(center);
    Direction.Axis attachmentAxis = attachment.getAxis();
    double x = attachmentAxis == Direction.Axis.X ? 0 : offset.x;
    double y = attachmentAxis == Direction.Axis.Y ? 0 : offset.y;
    double z = attachmentAxis == Direction.Axis.Z ? 0 : offset.z;
    boolean hasNoClearFront = x * x + y * y + z * z < 1.0E-6;
    return hasNoClearFront ? VoxelShapeHelper.frontFromRotation(attachment, 0) : Direction.getNearest(x, y, z);
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
    Direction attachment = rot.rotate(state.getValue(ATTACHMENT));
    Direction front = rot.rotate(VoxelShapeHelper.frontFromRotation(state.getValue(ATTACHMENT), state.getValue(ROTATION)));
    return state.setValue(ATTACHMENT, attachment).setValue(ROTATION, VoxelShapeHelper.rotationFromFront(attachment, front));
  }

  @Override
  protected BlockState mirror(BlockState state, Mirror mirror) {
    Direction attachment = mirror.mirror(state.getValue(ATTACHMENT));
    Direction front = mirror.mirror(VoxelShapeHelper.frontFromRotation(state.getValue(ATTACHMENT), state.getValue(ROTATION)));
    return state.setValue(ATTACHMENT, attachment).setValue(ROTATION, VoxelShapeHelper.rotationFromFront(attachment, front));
  }

  @Override
  protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
    return false;
  }
}
