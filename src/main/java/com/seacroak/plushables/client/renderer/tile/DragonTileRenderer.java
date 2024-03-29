package com.seacroak.plushables.client.renderer.tile;

import com.seacroak.plushables.block.tile.DragonTileEntity;
import com.seacroak.plushables.client.model.tile.DragonModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DragonTileRenderer extends GeoBlockRenderer<DragonTileEntity> {
    public DragonTileRenderer() {
        super(new DragonModel());
    }
}
