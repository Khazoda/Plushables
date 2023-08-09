package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.block.tile.BasketBlockEntity;
import com.seacroak.plushables.item.CapArmorItem;
import com.seacroak.plushables.item.PlushableBlockItem;
import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.registry.TileRegistry;
import com.seacroak.plushables.util.VoxelShapeUtils;
import com.seacroak.plushables.util.networking.ParticlePacketHandler;
import com.seacroak.plushables.util.networking.PlushablesNetworking;
import com.seacroak.plushables.util.networking.SoundPacketHandler;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.state.property.Properties.FACING;

public class BasketBlock extends BlockWithEntity {
  public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

  public BasketBlock() {
    super(FabricBlockSettings.create().sounds(BlockSoundGroup.WOOD).strength(1f).nonOpaque());
    setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return TileRegistry.HAT_RACK_TILE.instantiate(pos, state);
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

    if (!player.canModifyBlocks()) return ActionResult.CONSUME;
    if (player.isSneaking()) {
      /* Remove top item from stack */
      if (!(world instanceof ServerWorld)) return ActionResult.CONSUME;
      BasketBlockEntity be = ((BasketBlockEntity) world.getBlockEntity(pos));
      if ((be == null)) return ActionResult.CONSUME;
      be.removeFromStack(player);
    } else if (!player.isSneaking()) {
      /* Add held item to stack */
      if (!(world instanceof ServerWorld)) return ActionResult.CONSUME;
      ItemStack itemStack = player.getEquippedStack(EquipmentSlot.MAINHAND);
      if (itemStack.getItem() instanceof PlushableBlockItem) {
        BasketBlockEntity be = ((BasketBlockEntity) world.getBlockEntity(pos));
        if ((be == null)) return ActionResult.CONSUME;
        if(be.getPlushieStack().get(3) != ItemStack.EMPTY) return ActionResult.CONSUME;
        be.addToStack(player, itemStack.copyWithCount(1));
      }
    }
    return ActionResult.SUCCESS;
  }

  private double getXFromHit(Direction facing, Vec3d hit) {
    return switch (facing) {
      case UP -> hit.z;
      case DOWN -> 1 - hit.z;
      case NORTH -> hit.y;
      case SOUTH -> hit.y;
      case WEST -> hit.y;
      case EAST -> hit.y;
    };
  }

  private double getYFromHit(Direction facing, Vec3d hit) {
    return switch (facing) {
      case UP -> 1 - hit.x;
      case DOWN -> 1 - hit.x;
      case NORTH -> 1 - hit.x;
      case SOUTH -> hit.x;
      case WEST -> hit.z;
      case EAST -> 1 - hit.z;
    };
  }

  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.0625, 0.875, 0.9375, 0.125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.875, 0.875, 0.9375, 0.9375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0, 0.125, 0.9375, 0.9375, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0, 0.125, 0.125, 0.9375, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.125, 0.875, 0.0625, 0.875));
    return shape;
  }

  final VoxelShape blockShape = getShape();
  final VoxelShape[] blockShapes = {blockShape, VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.EAST, blockShape), VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.SOUTH, blockShape), VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.WEST, blockShape)};

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

  // Render Type
  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.MODEL;
  }

  public BlockState rotate(BlockState state, BlockRotation rotation) {
    return (BlockState) state.with(FACING, rotation.rotate((Direction) state.get(FACING)));
  }

  public BlockState mirror(BlockState state, BlockMirror mirror) {
    return state.rotate(mirror.getRotation((Direction) state.get(FACING)));
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
    return checkType(type, TileRegistry.HAT_RACK_TILE, BasketBlockEntity::tick);
  }

  // Initial state upon placing
  @Override
  public BlockState getPlacementState(ItemPlacementContext context) {
    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, context.getHorizontalPlayerFacing().getOpposite());

  }

  // Append initial properties
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }
}
