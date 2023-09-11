package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.registry.uncommon.TileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class RupertTileEntity extends PoweredBlockEntity {

	public RupertTileEntity(BlockPos pos, BlockState state) {
		super(TileRegistry.RUPERT_TILE, pos, state);
	}
}
