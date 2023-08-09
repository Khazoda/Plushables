package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.block.tile.HatRackBlockEntity;
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

public class HatRackBlock extends BlockWithEntity {
  public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

  public HatRackBlock() {
    super(FabricBlockSettings.create().sounds(BlockSoundGroup.WOOD).strength(1f).nonOpaque());
    setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return TileRegistry.HAT_RACK_TILE.instantiate(pos,state);
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

    if (world instanceof ServerWorld serverWorld) {
      BlockEntity entity = world.getBlockEntity(pos);
      if (entity instanceof HatRackBlockEntity) {
        Direction direction = hit.getSide();
        Direction facing = state.get(FACING);
        if (direction == facing) {
          Vec3d hitLocation = hit.getPos().subtract(pos.getX(), pos.getY(), pos.getZ());
          double x = getXFromHit(facing, hitLocation);
          double y = getYFromHit(facing, hitLocation);
//          player.sendMessage(Text.literal(String.valueOf(x)));
//          player.sendMessage(Text.literal(String.valueOf(y)));

          ItemStack itemStack = player.getEquippedStack(EquipmentSlot.MAINHAND);
          if (itemStack.getItem() instanceof BlockItem blockItem) {

            /* Prong 1 Clicked */
            if (y > 0.0 && y <= 0.5 && x >= 0.4 && x <= 0.82) {
              if (!world.isClient && ((HatRackBlockEntity) entity).setLeftHat(player, player.getAbilities().creativeMode ? itemStack.copy() : itemStack))
                return ActionResult.SUCCESS;
            }
            /* Prong 2 Clicked*/
            if (y > 0.50 && y <= 1.0 && x >= 0.4 && x <= 0.82) {
              if (!world.isClient && ((HatRackBlockEntity) entity).setRightHat(player, player.getAbilities().creativeMode ? itemStack.copy() : itemStack))
                return ActionResult.SUCCESS;
            }
          }
        }
      }
    }
    return ActionResult.PASS;
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
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0.4375, 0.875, 1, 0.8125, 1));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.5, 0.625, 0.8125, 0.625, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.625, 0.625, 0.3125, 0.75, 0.875));
    return shape;
  }

  final VoxelShape blockShape = getShape();
  final VoxelShape[] blockShapes = {blockShape,
      VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.EAST, blockShape),
      VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.SOUTH, blockShape),
      VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.WEST, blockShape)};

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
    return checkType(type, TileRegistry.HAT_RACK_TILE, HatRackBlockEntity::tick);
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
