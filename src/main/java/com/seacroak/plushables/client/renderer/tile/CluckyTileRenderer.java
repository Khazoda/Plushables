package com.seacroak.plushables.client.renderer.tile;

import com.seacroak.plushables.block.tile.CluckyTileEntity;
import com.seacroak.plushables.client.model.tile.CluckyModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class CluckyTileRenderer extends GeoBlockRenderer<CluckyTileEntity> {
    public CluckyTileRenderer(BlockEntityRendererProvider.Context context) {
        super(new CluckyModel());
    }
}
