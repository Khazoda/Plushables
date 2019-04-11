package com.khazoda.plush.common.block;


import com.khazoda.plush.common.Plushables;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public abstract class ParentBlock extends Block implements IBlockPlushables {

    public static final PropertyDirection FACING = PropertyDirection.create("facing",EnumFacing.Plane.HORIZONTAL);
    public static final String modID = Plushables.MODID;
    protected AxisAlignedBB boundingBox;


    public ParentBlock(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);


        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        setHardness(0.1f);
        setSoundType(SoundType.CLOTH);
    }

    @Override
    public ParentBlock setBoundingBox(double x1, double y1, double z1, double x2, double y2, double z2) {
        this.boundingBox = new AxisAlignedBB(x1 / 16, y1 / 16, z1 / 16, x2 / 16, y2 / 16, z2 / 16);
        return this;
    }

    @Override
    public ParentBlock setBoundingBox(double x, double y, double z) {
        return setBoundingBox(x, 0, z, (16 - x), y, (16 - z));
    }

    @Override
    public ParentBlock setBoundingBox(AxisAlignedBB a) {
        this.boundingBox = a;
        return this;
    }

    /**
     * sets bounding box to full cube if no special bounding box has been set
     */
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return this.boundingBox != null ? this.boundingBox : FULL_BLOCK_AABB;
    }

    /**
     * Gets, and then sets the block state retrieved from meta values (lower 2 bits)
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getHorizontal(meta);
        return this.getDefaultState().withProperty(FACING,facing);
    }

    /**
     *Gets, and then sets the lower 2 bit meta values for the block from the BlockState
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        EnumFacing facing = state.getValue(FACING);
        int facingbits = facing.getHorizontalIndex();
        return facingbits;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        // find the quadrant the player is facing
        EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
        enumfacing = enumfacing.getOpposite();

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        if (player.isCreative()) { //only drops item if in survival

        } else {
            ItemStack block = new ItemStack(state.getBlock());
            spawnAsEntity(worldIn, pos, block);
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
    }


}
