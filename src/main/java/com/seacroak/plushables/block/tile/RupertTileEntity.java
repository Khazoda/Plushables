package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.registry.TileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class RupertTileEntity extends AnimatronicBlockEntity {

	public RupertTileEntity(BlockPos pos, BlockState state) {
		super(TileRegistry.RUPERT_TILE, pos, state);
	}
}
