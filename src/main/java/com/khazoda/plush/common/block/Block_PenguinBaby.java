package com.khazoda.plush.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Block_PenguinBaby extends AbstractBlockLight {

    public static final PropertyBool IS_GLOWING = PropertyBool.create("is_glowing");
    public static final PropertyBool INVERTED = PropertyBool.create("inverted");

    public static final AxisAlignedBB BOUNDS = new AxisAlignedBB(2, 0, 2, 14, 15, 14);

    public Block_PenguinBaby(String name, Material material) {
        super(name, material);
        setDefaultState(getDefaultState().withProperty(IS_GLOWING, false).withProperty(INVERTED, false));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (worldIn.isRemote) {
            return true;
        } else {
            state = state.cycleProperty(IS_GLOWING);
            if (playerIn.getHeldItem(hand).getItem() == Items.GLOWSTONE_DUST) {
                worldIn.setBlockState(pos, state);
            }
        }
        return false;
    }

    @Override
    protected int getBrightnessFromState(IBlockState state) {
        return (state.getValue(IS_GLOWING) ^ state.getValue(INVERTED)) ? 15 : 0;
    }

    @Override
    protected IBlockState setStateBrightness(IBlockState state, int powerLevel) {
        return null;
    }

    protected IBlockState getInvertedState(IBlockState in) {
        return in.withProperty(INVERTED, !in.getValue(INVERTED));
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return getBrightnessFromState(state);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, IS_GLOWING, INVERTED);
    }

}
