package com.seacroak.plushables.block;

import org.jetbrains.annotations.Nullable;

import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.registry.TileRegistry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BuilderBlock extends BlockWithEntity {

	public BuilderBlock() {
		super(AbstractBlock.Settings.of(Material.STONE).nonOpaque());
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos,
			PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient) {
			NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

			if (screenHandlerFactory != null) {
				player.openHandledScreen(screenHandlerFactory);
			}
		}

		return ActionResult.SUCCESS;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return TileRegistry.BUILDER_TILE.instantiate(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
			BlockEntityType<T> type) {
		return checkType(type, TileRegistry.BUILDER_TILE, BuilderTileEntity::tick);
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