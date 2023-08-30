package com.seacroak.plushables.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.LocalRandom;
import org.jetbrains.annotations.Nullable;

public abstract class BasePoweredPlushable extends BasePlushable implements BlockEntityProvider {
  //  Fields
  public static LocalRandom randPitch;
  public BlockEntity blockEntityReference;
  public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

  //  Constructor
  public BasePoweredPlushable() {
    super();
    randPitch = new LocalRandom(100);
    blockEntityReference = null;
  }

  // Render Type
  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.ENTITYBLOCK_ANIMATED;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return null;
  }

}
