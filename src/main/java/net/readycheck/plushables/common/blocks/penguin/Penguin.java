package net.readycheck.plushables.common.blocks.penguin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.readycheck.plushables.common.Plushables;
import net.readycheck.plushables.common.interfaces.AbstractPlushie;
import net.readycheck.plushables.common.interfaces.IPlushie;
import net.readycheck.plushables.tools.SetBlockStateFlag;

import javax.annotation.Nullable;
import java.util.List;

/**
 * User: The Grey Ghost
 * Date: 24/12/2014
 * <p>
 * BlockSimple is a ordinary solid cube with the six faces numbered from 0 - 5.
 * For background information on blocks see here http://greyminecraftcoder.blogspot.com/2020/02/blocks-1144.html
 * <p>
 * For a couple of the methods below the Forge guys have marked it as deprecated.  But you still need to override those
 * "deprecated" block methods.  What they mean is "when you want to find out what is a block's getRenderType(),
 * don't call block.getRenderType(), call blockState.getRenderType() instead".
 * If that doesn't make sense to you yet, don't worry.  Just ignore the "deprecated method" warning.
 */

/**
 * Recyclinger
 */
public class Penguin extends AbstractPlushie implements IPlushie {
    private static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public Penguin(Properties properties) {
        super(properties);
        BlockState defaultBlockState = this.stateContainer.getBaseState().with(FACING, Direction.SOUTH).with(ACTIVE, false);
        this.setDefaultState(defaultBlockState);
    }
    // Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST


    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, ACTIVE);
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext blockItemUseContext) {
        World world = blockItemUseContext.getWorld();
        BlockPos blockPos = blockItemUseContext.getPos();

        Direction direction = blockItemUseContext.getPlacementHorizontalFacing().getOpposite();  // north, east, south, or west
        float playerFacingDirectionAngle = blockItemUseContext.getPlacementYaw(); //if you want more directions than just NESW, you can use the yaw instead.
        // likewise the pitch is also available for up/down placement.

        BlockState blockState = getDefaultState().with(FACING, direction);
        return blockState;
    }

    // returns the shape of the block:
    //  The image that you see on the screen (when a block is rendered) is determined by the block model (i.e. the model json file).
    //  But Minecraft also uses a number of other �shapes� to control the interaction of the block with its environment and with the player.
    // See  https://greyminecraftcoder.blogspot.com/2020/02/block-shapes-voxelshapes-1144.html
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction direction = state.get(FACING);
        VoxelShape voxelShape = RECYCLER_SHAPE;
        return voxelShape != null ? voxelShape : VoxelShapes.fullCube();  // should always find it... just being defensive
        // you can also use direction.getHorizontalIndex() if you want, instead of a map.
    }

    private static final Vector3d RECYCLER_MIN_CORNER = new Vector3d(2.0, 0.0, 4.0);
    private static final Vector3d RECYCLER_MAX_CORNER = new Vector3d(14.0, 15.0, 12.0);
    private static final VoxelShape RECYCLER_SHAPE = Block.makeCuboidShape(RECYCLER_MIN_CORNER.getX(), RECYCLER_MIN_CORNER.getY(), RECYCLER_MIN_CORNER.getZ(),
            RECYCLER_MAX_CORNER.getX(), RECYCLER_MAX_CORNER.getY(), RECYCLER_MAX_CORNER.getZ());
    // Setting up sounds for the Recycler
    ResourceLocation recycler_active = new ResourceLocation(Plushables.MOD_ID, "recycler_active");
    ResourceLocation recycler_inactive = new ResourceLocation(Plushables.MOD_ID, "recycler_inactive");

    SoundEvent recycler_active_sound = new SoundEvent(recycler_active);
    SoundEvent recycler_inactive_sound = new SoundEvent(recycler_inactive);

    // ---- Turn the Recycler on and off by right clicking it
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult blockRayTraceResult) {
        boolean currentlyActive = state.get(ACTIVE);
        boolean newActiveState = !currentlyActive;

        final int FLAGS = SetBlockStateFlag.get(SetBlockStateFlag.BLOCK_UPDATE, SetBlockStateFlag.SEND_TO_CLIENTS);
        worldIn.setBlockState(pos, state.with(ACTIVE, newActiveState), FLAGS);
//    Plays sound based on whether it's turning on or off
//      Helper function for ActionResultType = Success that doesn't trigger two hand swings
        return ActionResultType.func_233537_a_(worldIn.isRemote);
    }

    // render using a BakedModel (mbe01_block_simple.json --> mbe01_block_simple_model.json)
    // not strictly required because the default (super method) is MODEL.
    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }
}
