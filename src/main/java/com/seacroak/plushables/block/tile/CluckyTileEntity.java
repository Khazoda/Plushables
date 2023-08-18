package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.registry.TileRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class CluckyTileEntity extends PoweredBlockEntity {

  public CluckyTileEntity(BlockPos pos, BlockState state) {
    super(TileRegistry.CLUCKY_TILE, pos, state);
  }
}
