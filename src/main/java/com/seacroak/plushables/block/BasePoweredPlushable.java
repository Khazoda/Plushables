package com.seacroak.plushables.block;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.LocalRandom;
import org.jetbrains.annotations.Nullable;

public abstract class BasePoweredPlushable extends BasePlushable implements BlockEntityProvider {
  //  Fields
  public static LocalRandom randPitch;
  public BlockEntity blockEntityReference;

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
