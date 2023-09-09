package com.seacroak.plushables.client.renderer.tile;

import com.seacroak.plushables.block.tile.StatuetteTileEntity;
import com.seacroak.plushables.client.model.tile.StatuetteModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class StatuetteTileRenderer extends GeoBlockRenderer<StatuetteTileEntity> {
    public StatuetteTileRenderer() {
        super(new StatuetteModel());
    }
}
