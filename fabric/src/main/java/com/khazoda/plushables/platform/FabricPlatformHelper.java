package com.khazoda.plushables.platform;

import com.khazoda.plushables.block.BasePlushableBlockEntity;
import com.khazoda.plushables.platform.services.IPlatformHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class FabricPlatformHelper implements IPlatformHelper {

  @Override
  public String getPlatformName() {
    return "Fabric";
  }

  @Override
  public boolean isModLoaded(String modId) {

    return FabricLoader.getInstance().isModLoaded(modId);
  }

  @Override
  public boolean isDevelopmentEnvironment() {

    return FabricLoader.getInstance().isDevelopmentEnvironment();
  }

  @Override
  public boolean isClientSide() {
    return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
  }

  @Override //vanilla builder methods are private in common, have to call it on loaders instead
  public BlockEntityType<BasePlushableBlockEntity> createPlushableBlockEntityType(Block... validBlocks) {
    return BlockEntityType.Builder.of(BasePlushableBlockEntity::new, validBlocks).build(null);
  }
}
