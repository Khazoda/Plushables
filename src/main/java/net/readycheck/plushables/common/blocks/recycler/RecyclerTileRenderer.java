package net.readycheck.plushables.common.blocks.recycler;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class RecyclerTileRenderer extends GeoBlockRenderer<RecyclerTileEntity> {
    public RecyclerTileRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new RecyclerModel());
    }
}
