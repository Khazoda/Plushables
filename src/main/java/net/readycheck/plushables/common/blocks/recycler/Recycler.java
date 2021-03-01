package net.readycheck.plushables.common.blocks.recycler;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.readycheck.plushables.common.Plushables;
import net.readycheck.plushables.common.interfaces.IAnimationListener;
import net.readycheck.plushables.tools.SetBlockStateFlag;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

import javax.annotation.Nullable;

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
 * Recycler
 */
public class Recycler extends Block {
    public Recycler() {
        super(Properties.create(Material.ROCK).notSolid());  // look at Block.Properties for further options
        // typically useful: hardnessAndResistance(), harvestLevel(), harvestTool()
        BlockState defaultBlockState = this.stateContainer.getBaseState().with(FACING, Direction.SOUTH).with(ACTIVE, false).with(SWITCHING_ON, false);
        this.setDefaultState(defaultBlockState);
    }

    public Boolean isActive = false;
    public Boolean isSwitchingOn = false;
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public static final BooleanProperty SWITCHING_ON = BooleanProperty.create("switching_on");
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    // Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST

    //  Tile Entity Code
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new RecyclerTileEntity();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, ACTIVE, SWITCHING_ON);
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

    private static final Vector3d RECYCLER_MIN_CORNER = new Vector3d(1.0, 0.0, 1.0);
    private static final Vector3d RECYCLER_MAX_CORNER = new Vector3d(15.0, 12.0, 15.0);
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
//        worldIn.setBlockState(pos, state.with(ACTIVE, newActiveState), FLAGS);
//    Plays sound based on whether it's turning on or off
        if (newActiveState == true) {
//          Is switching on
            isSwitchingOn = true;
            worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), recycler_active_sound, SoundCategory.BLOCKS, 10, 1);
        } else {
//          Is switching off
            isSwitchingOn = false;
            worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), recycler_inactive_sound, SoundCategory.BLOCKS, 10, 1);
        }
        worldIn.setBlockState(pos, state.with(SWITCHING_ON, isSwitchingOn).with(ACTIVE, newActiveState), FLAGS);
        if(worldIn.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ())) instanceof IAnimationListener) {
            ((IAnimationListener) worldIn.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()))).startAnimation(isSwitchingOn);
        }
//        Helper function for ActionResultType = Success that doesn't trigger two hand swings
        return ActionResultType.func_233537_a_(worldIn.isRemote);
    }

    //    Animation Predicate

//    RECYCLER LOGIC =============================================

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {


        if (!worldIn.isRemote) {
//            If the entity colliding is an item
            if (entityIn.getType() == EntityType.ITEM) {
                ItemEntity iEntity = (ItemEntity) entityIn;
                ItemStack itemStack = iEntity.getItem().copy();
//                If the item colliding is from the Plushables group
                if (itemStack.getItem().getGroup() == Plushables.PlushieGroup) {
//                    GRIND UP THE PLUSHIE >:)
                    entityIn.remove();
                }
            }
        }

//            int i = this.getRedstoneStrength(state);
//            if (i == 0) {
//                this.updateState(worldIn, pos, state, i);
//            }

    }


    // ENTITYBLOCK_ANIMATED because this block/tile entity is being animated by GeckoLib
    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}
