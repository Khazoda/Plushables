package com.seacroak.plushables.registry;

import com.seacroak.plushables.block.BuilderBlock;

public class BlockRegistry {
	// public static final HabitatBlock HABITAT_BLOCK =
	// RegistryUtils.register("habitatblock", new HabitatBlock());
	// public static final FertilizerBlock FERTILIZER_BLOCK =
	// RegistryUtils.register("fertilizerblock",
	// new FertilizerBlock());

	public static final BuilderBlock BUILDER_BLOCK = RegistryUtils.register("builder_block", new BuilderBlock());

}
