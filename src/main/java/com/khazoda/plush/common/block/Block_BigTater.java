package com.khazoda.plush.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Block_BigTater extends ParentBlock {

    public static final PropertyBool IS_ANGRY = PropertyBool.create("is_angry");

    public Block_BigTater(String name, Material material) {
        super(name,material);
        setDefaultState(getDefaultState().withProperty(IS_ANGRY,false));

    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        if(worldIn.isRemote) {
        } else {
            IBlockState state = worldIn.getBlockState(pos);
            state = state.cycleProperty(IS_ANGRY);
            worldIn.setBlockState(pos,state);
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, IS_ANGRY);
    }
}
