package com.seacroak.plushables.block;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.seacroak.plushables.registry.TileRegistry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.text.Text;

import net.minecraft.util.math.BlockPos;

import net.minecraft.world.BlockView;

public class BuilderBlock extends FacingBlock implements BlockEntityProvider {

	public BuilderBlock() {
		super(AbstractBlock.Settings.of(Material.STONE).nonOpaque());
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return this.getDefaultState().with(FACING, context.getPlayerLookDirection().getOpposite());
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
		tooltip.add(Text.literal("Turn on rain to see the fertilizer model!"));
		super.appendTooltip(stack, world, tooltip, options);
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return TileRegistry.BUILDER_TILE.instantiate(pos, state);
	}

	// @Override
	// public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos
	// pos, ShapeContext context) {
	// Direction direction = (Direction) state.get(FACING);
	// switch (direction) {
	// case NORTH: {
	// return Block.createCuboidShape(0, 0, 0, 16, 16, 16);
	// }
	// case SOUTH: {
	// return Block.createCuboidShape(-16, 0, 0, 16, 16, 16);
	// }
	// case WEST: {
	// return Block.createCuboidShape(0, 0, -16, 16, 16, 16);
	// }
	// default:
	// return Block.createCuboidShape(0, 0, 0, 16, 16, 16);
	// }
	// }

}