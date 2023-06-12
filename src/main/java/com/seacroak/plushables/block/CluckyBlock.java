package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.CluckyTileEntity;
import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.registry.TileRegistry;
import com.seacroak.plushables.util.VoxelShapeUtils;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.LocalRandom;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimationController;

import java.util.Random;

public class CluckyBlock extends BlockWithEntity {
	static java.util.Random rand;
	static net.minecraft.util.math.random.Random randMoan;
	public BlockEntity blockEntityReference;

	// HorizontalFacingBlock Code
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return (BlockState) state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}
	//

	public CluckyBlock() {
		super(FabricBlockSettings.create().sounds(BlockSoundGroup.WOOL).strength(0.7f).nonOpaque());
		setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
		rand = new Random();
		randMoan = new LocalRandom(100);

		blockEntityReference = null;
	}

	// Shift Right Click pickup code
	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos,
			PlayerEntity player, Hand hand, BlockHitResult hit) {

		// Serverside code
		if (!world.isClient) {
			if (player.isSneaking()) {
				ItemScatterer.spawn(world, pos, DefaultedList.ofSize(1, new ItemStack(this)));
				world.updateComparators(pos, this);
				world.removeBlock(pos, false);
				return ActionResult.CONSUME;
			}
		}
		if (world.isClient) {
			if (player.isSneaking()) {
				world.playSound(player, pos, SoundRegistry.PLUSHABLE_POP, SoundCategory.BLOCKS, 0.5f, 1f);
				world.playSound(player, pos, SoundEvents.BLOCK_WOOL_HIT, SoundCategory.BLOCKS, 0.5f, 1f);

				// Custom breaking particle code
				for (int i = 0; i < 5; i++) {
					world.addParticle(ParticleTypes.POOF, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
							rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f),
							rand.nextFloat(-0.05f, 0.05f));
					world.addParticle(ParticleTypes.GLOW, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
							rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f),
							rand.nextFloat(-0.05f, 0.05f));
				}
				// Right click interaction
			} else {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity instanceof CluckyTileEntity) {
					CluckyTileEntity cluckyEntity = (CluckyTileEntity) blockEntity;
					cluckyEntity.setShouldLook(true);
					// One in 20 chance to make a sus cluck
					if (cluckyEntity.getShouldLook()
							&& cluckyEntity.lookController.getAnimationState() == AnimationController.State.STOPPED) {
						if (randMoan.nextBetween(0, 10) == 5) {
							world.playSound(player, pos, SoundRegistry.CLUCKY_MOAN, SoundCategory.BLOCKS, 0.5f, 1f);
						} else {
							System.out.println((float) randMoan.nextFloat());
							world.playSound(player, pos, SoundRegistry.CLUCKY_CLUCK, SoundCategory.BLOCKS, 0.5f,
									(float) 0.7f + randMoan.nextFloat() / 2);
						}
					}

					return ActionResult.SUCCESS;
				}
			}

		}
		return ActionResult.PASS;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return TileRegistry.CLUCKY_TILE.instantiate(pos, state);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return this.getDefaultState().with(Properties.HORIZONTAL_FACING, context.getHorizontalPlayerFacing().getOpposite());

	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	static final VoxelShape blockShape = getShape();
	static final VoxelShape[] blockShapes = { blockShape,
			VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.EAST, blockShape),
			VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.SOUTH, blockShape),
			VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.WEST, blockShape) };

	static public VoxelShape getShape() {
		VoxelShape shape = VoxelShapes.empty();
		shape = VoxelShapes.union(shape,
				VoxelShapes.cuboid(0.375, 0.0703125, 0.375, 0.625, 0.2578125, 0.6875));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0078125, 0.3125, 0.75, 0.0703125, 0.75));

		return shape;
	}

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

	// Custom breaking particle code
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

}