package com.seacroak.plushables.client.renderer.tile;

import com.seacroak.plushables.block.tile.CluckyTileEntity;
import com.seacroak.plushables.block.tile.RupertTileEntity;
import com.seacroak.plushables.client.model.tile.CluckyModel;
import com.seacroak.plushables.client.model.tile.RupertModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class RupertTileRenderer extends GeoBlockRenderer<RupertTileEntity> {
    public RupertTileRenderer() {
        super(new RupertModel());
    }
}
