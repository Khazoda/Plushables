package com.khazoda.plushables.platform;

import com.khazoda.plushables.block.BasePlushableBlockEntity;
import com.khazoda.plushables.platform.services.IPlatformHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLLoader;

public class NorgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public boolean isClientSide() {
        return FMLEnvironment.dist.isClient();
    }

    @Override //vanilla builder methods are private in common, have to call it on loaders instead
    public BlockEntityType<BasePlushableBlockEntity> createPlushableBlockEntityType(Block... validBlocks) {
        return BlockEntityType.Builder.of(BasePlushableBlockEntity::new, validBlocks).build(null);
    }
}