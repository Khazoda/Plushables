package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.registry.uncommon.TileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class StatuetteTileEntity extends PoweredBlockEntity {

  public StatuetteTileEntity(BlockPos pos, BlockState state) {
    super(TileRegistry.STATUETTE_TILE, pos, state);
  }
}
