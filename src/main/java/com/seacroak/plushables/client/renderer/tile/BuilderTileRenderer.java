package com.seacroak.plushables.client.renderer.tile;

import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.client.model.tile.BuilderModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BuilderTileRenderer extends GeoBlockRenderer<BuilderTileEntity> {
    public BuilderTileRenderer() {
        super(new BuilderModel());
    }
}
