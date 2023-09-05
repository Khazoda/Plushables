package com.seacroak.plushables.client.renderer.tile;

import com.seacroak.plushables.block.tile.OwlTileEntity;
import com.seacroak.plushables.client.model.tile.OwlModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class OwlTileRenderer extends GeoBlockRenderer<OwlTileEntity> {
    public OwlTileRenderer() {
        super(new OwlModel());
    }
}
