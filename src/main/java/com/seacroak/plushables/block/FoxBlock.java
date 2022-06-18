package com.seacroak.plushables.block;

import java.util.List;
import java.util.Random;

import org.jetbrains.annotations.Nullable;

import com.seacroak.plushables.gui.BuilderScreenHandler;
import com.seacroak.plushables.registry.TileRegistry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class FoxBlock extends FacingBlock {
	Random rand;

	public FoxBlock() {
		super(AbstractBlock.Settings.of(Material.STONE).nonOpaque());
		rand = new Random();

	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	static final VoxelShape blockShape = getShape();

	static public VoxelShape getShape() {
		VoxelShape shape = VoxelShapes.empty();
		shape = VoxelShapes.union(shape,
				VoxelShapes.cuboid(0.1875, 0, 0.1875, 0.5, 0.3125, 0.5));
		shape = VoxelShapes.union(shape,
				VoxelShapes.cuboid(0.1875, 0.3125, 0.25, 0.3125, 0.375, 0.3125));
		shape = VoxelShapes.union(shape,
				VoxelShapes.cuboid(0.375, 0.3125, 0.25, 0.5, 0.375, 0.3125));
		shape = VoxelShapes.union(shape,
				VoxelShapes.cuboid(0.25, 0.125, 0.125, 0.4375, 0.1875, 0.1875));
		shape = VoxelShapes.union(shape,
				VoxelShapes.cuboid(0.125, 0, 0.125, 0.25, 0.0625, 0.25));
		shape = VoxelShapes.union(shape,
				VoxelShapes.cuboid(0.4375, 0, 0.125, 0.5625, 0.0625, 0.25));
		shape = VoxelShapes.union(shape,
				VoxelShapes.cuboid(0.125, 0, 0.4375, 0.25, 0.0625, 0.5625));
		shape = VoxelShapes.union(shape,
				VoxelShapes.cuboid(0.4375, 0, 0.4375, 0.5625, 0.0625, 0.5625));

		return shape;
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (world.isClient) {
			for (int i = 0; i < 5; i++) {
				world.addParticle(ParticleTypes.POOF, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
						rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
				world.addParticle(ParticleTypes.GLOW, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
						rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
			}
		}
		world.addParticle(ParticleTypes.FIREWORK, true, pos.getX(), pos.getY(), pos.getZ(), 0.1, 0.1, 0.1);
		super.onBreak(world, pos, state, player);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return this.getDefaultState().with(FACING,
				context.getPlayerLookDirection().getOpposite());
	}

	// @Override
	// public void appendTooltip(ItemStack stack, @Nullable BlockView world,
	// List<Text> tooltip, TooltipContext options) {
	// tooltip.add(Text.translatable("Plushables").formatted(Formatting.ITALIC).formatted(Formatting.GREEN));
	// super.appendTooltip(stack, world, tooltip, options);
	// }

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Direction direction = (Direction) state.get(FACING);

		switch (direction) {
			case NORTH: {
				return blockShape;
			}
			case SOUTH: {
				return rotateShape(Direction.NORTH, Direction.SOUTH, blockShape);
				// return blockShape;
			}
			case WEST: {
				return rotateShape(Direction.NORTH, Direction.WEST, blockShape);
				// return blockShape;

			}
			case EAST: {
				return rotateShape(Direction.NORTH, Direction.EAST, blockShape);
				// return blockShape;

			}
			default:
				return blockShape;
		}
	}

	public static VoxelShape rotateShape(Direction from, Direction to, VoxelShape shape) {
		VoxelShape[] buffer = new VoxelShape[] { shape, VoxelShapes.empty() };

		int times = (to.getHorizontal() - from.getHorizontal() + 4) % 4;
		for (int i = 0; i < times; i++) {
			buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.union(buffer[1],
					VoxelShapes.cuboid(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
			buffer[0] = buffer[1];
			buffer[1] = VoxelShapes.empty();
		}
		return buffer[0];
	}

}