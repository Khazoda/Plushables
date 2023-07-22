package com.seacroak.plushables.client.renderer.tile;

import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.client.model.tile.BuilderModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BuilderTileRenderer extends GeoBlockRenderer<BuilderTileEntity> {
    public BuilderTileRenderer(BlockEntityRendererProvider.Context context) {
        super(new BuilderModel());
    }
}
