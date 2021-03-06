package net.readycheck.plushables.common.interfaces;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AbstractPlushie extends Block {
    private Properties properties = Properties.create(Material.WOOL).hardnessAndResistance(0.5f).harvestLevel(1).notSolid();
    protected static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    protected static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public AbstractPlushie(Properties properties) {
        super(properties);
        BlockState defaultBlockState = this.stateContainer.getBaseState().with(FACING, Direction.SOUTH).with(ACTIVE, false);
        this.setDefaultState(defaultBlockState);
    }
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

        BlockState blockState = getDefaultState().with(FACING, direction);
        return blockState;
    }


    private static final Vector3d MIN_CORNER = new Vector3d(0.0, 0.0, 0.0);
    private static final Vector3d MAX_CORNER = new Vector3d(16.0, 16.0, 16.0);
    private static final VoxelShape VOXEL_SHAPE = Block.makeCuboidShape(
            MIN_CORNER.getX(), MIN_CORNER.getY(), MIN_CORNER.getZ(),
            MAX_CORNER.getX(), MAX_CORNER.getY(), MAX_CORNER.getZ()
    );

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VOXEL_SHAPE != null ? VOXEL_SHAPE : VoxelShapes.fullCube();
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }


}
