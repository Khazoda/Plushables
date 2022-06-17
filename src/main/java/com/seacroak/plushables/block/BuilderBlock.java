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
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class BuilderBlock extends FacingBlock implements BlockEntityProvider {

	public BuilderBlock() {
		super(AbstractBlock.Settings.of(Material.STONE).nonOpaque());
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	// protected void appendProperties(StateManager.Builder<Block, BlockState>
	// builder) {
	// builder.add(FACING);
	// }

	// @Override
	// public BlockState getPlacementState(ItemPlacementContext context) {
	// return this.getDefaultState().with(FACING,
	// context.getPlayerLookDirection().getOpposite());
	// }

	// @Override
	// public void appendTooltip(ItemStack stack, @Nullable BlockView world,
	// List<Text> tooltip, TooltipContext options) {
	// tooltip.add(Text.translatable("Plushables").formatted(Formatting.ITALIC).formatted(Formatting.GREEN));
	// super.appendTooltip(stack, world, tooltip, options);
	// }

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return TileRegistry.BUILDER_TILE.instantiate(pos, state);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return getShape();
	}

	public VoxelShape getShape() {
		VoxelShape shape = VoxelShapes.empty();
		shape = VoxelShapes.union(shape,
				VoxelShapes.cuboid(0.0625, -8.673617379884035e-19, 0.0625, 0.1875, 0.625, 0.1875));
		shape = VoxelShapes.union(shape,
				VoxelShapes.cuboid(0.0625, -8.673617379884035e-19, 0.8125, 0.1875, 0.625, 0.9375));
		shape = VoxelShapes.union(shape,
				VoxelShapes.cuboid(0.8125, -8.673617379884035e-19, 0.8125, 0.9375, 0.625, 0.9375));
		shape = VoxelShapes.union(shape,
				VoxelShapes.cuboid(0.8125, -8.673617379884035e-19, 0.0625, 0.9375, 0.625, 0.1875));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.625, 0.0625, 0.9375, 0.75, 0.9375));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.078125, 0.3125, 0.078125, 0.921875, 0.4375, 0.921875));

		return shape;
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