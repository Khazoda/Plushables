package com.seacroak.plushables.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class CodexBlock extends HorizontalFacingBlock {

  public CodexBlock() {
    super(FabricBlockSettings.create().sounds(BlockSoundGroup.WOOL).strength(0.5f).nonOpaque());
    setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
  }


  // Render Type
  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.MODEL;
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