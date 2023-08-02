package com.seacroak.plushables.client.renderer.tile;

import com.seacroak.plushables.block.tile.OrangutanTileEntity;
import com.seacroak.plushables.block.tile.RupertTileEntity;
import com.seacroak.plushables.client.model.tile.OrangutanModel;
import com.seacroak.plushables.client.model.tile.RupertModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class OrangutanTileRenderer extends GeoBlockRenderer<OrangutanTileEntity> {
    public OrangutanTileRenderer() {
        super(new OrangutanModel());
    }
}
