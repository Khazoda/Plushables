package com.seacroak.plushables.registry;

import com.seacroak.plushables.block.BuilderBlock;
import com.seacroak.plushables.block.PenguinBlock;

public class BlockRegistry {
	// public static final HabitatBlock HABITAT_BLOCK =
	// RegistryUtils.register("habitatblock", new HabitatBlock());
	// public static final FertilizerBlock FERTILIZER_BLOCK =
	// RegistryUtils.register("fertilizerblock",
	// new FertilizerBlock());

	public static final BuilderBlock BUILDER_BLOCK = RegistryUtils.register("builder_block", new BuilderBlock());
	public static final PenguinBlock PENGUIN_BLOCK = RegistryUtils.register("penguin_block", new PenguinBlock());

}
