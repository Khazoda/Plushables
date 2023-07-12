package com.seacroak.plushables.client.renderer.tile;

import com.seacroak.plushables.block.tile.RupertTileEntity;
import com.seacroak.plushables.client.model.tile.RupertModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class RupertTileRenderer extends GeoBlockRenderer<RupertTileEntity> {
    public RupertTileRenderer(BlockEntityRendererProvider.Context context) {
        super(new RupertModel());
    }
}
