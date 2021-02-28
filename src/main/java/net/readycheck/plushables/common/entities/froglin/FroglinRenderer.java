package net.readycheck.plushables.common.entities.froglin;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FroglinRenderer extends GeoEntityRenderer<FroglinEntity> {

    public FroglinRenderer(EntityRendererManager renderManager) {
        super(renderManager, new FroglinModel());
        this.shadowSize = 0.7F; //change 0.7 to the desired shadow size.
    }


}
