package com.seacroak.plushables.block;

import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.util.VoxelShapeUtils;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
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

public class CodexBlock extends HorizontalFacingBlock {

  public CodexBlock() {
    super(FabricBlockSettings.create().sounds(BlockSoundGroup.CHISELED_BOOKSHELF).strength(0.25f).nonOpaque());
    setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (world instanceof ServerWorld serverWorld) {
      ItemScatterer.spawn(world, pos, DefaultedList.ofSize(1, new ItemStack(MainRegistry.CODEX_ITEM)));
      world.updateComparators(pos, this);
      world.removeBlock(pos, false);
      return ActionResult.CONSUME;
    } else if (world.isClient) {
      world.playSound(player,pos, SoundEvents.BLOCK_CHISELED_BOOKSHELF_PICKUP, SoundCategory.BLOCKS,1f,1f);
      return ActionResult.SUCCESS;
    }
    return ActionResult.PASS;
  }

    public VoxelShape getShape () {
      VoxelShape shape = VoxelShapes.empty();
      shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.28125, 0, 0.25, 0.8, 0.172, 0.8));
      return shape;
    }

    final VoxelShape blockShape = getShape();
    final VoxelShape[] blockShapes = VoxelShapeUtils.calculateBlockShapes(blockShape);

    @Override
    public VoxelShape getOutlineShape (BlockState state, BlockView world, BlockPos pos, ShapeContext context){
      Direction direction = state.get(FACING);
      return VoxelShapeUtils.getSidedOutlineShape(direction, blockShape, blockShapes);
    }

    // Render Type
    @Override
    public BlockRenderType getRenderType (BlockState state){
      return BlockRenderType.MODEL;
    }
    // Initial state upon placing
    @Override
    public BlockState getPlacementState (ItemPlacementContext context){
      return this.getDefaultState().with(Properties.HORIZONTAL_FACING, context.getHorizontalPlayerFacing().getOpposite());
    }
    // Append initial properties
    protected void appendProperties (StateManager.Builder < Block, BlockState > builder){
      builder.add(FACING);
    }


  }