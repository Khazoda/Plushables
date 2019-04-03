package com.khazoda.plush.common.block;

import com.khazoda.plush.common.Plushables;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Block_PenguinBaby extends ParentBlock {

    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public Block_PenguinBaby() {
        setRegistryName("penguin_baby");
        setUnlocalizedName(modID + ".penguin_baby");
    }

}
