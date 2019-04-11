package com.khazoda.plush.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public abstract class AbstractBlockLight extends ParentBlock {

    public AbstractBlockLight(String name, Material mat) {
        super(name,mat); //TODO CHANGE
    }


    protected abstract int getBrightnessFromState(IBlockState state);
    protected abstract IBlockState setStateBrightness(IBlockState state, int powerLevel);
    protected abstract IBlockState getInvertedState(IBlockState in);


    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return getBrightnessFromState(state);
    }

}
