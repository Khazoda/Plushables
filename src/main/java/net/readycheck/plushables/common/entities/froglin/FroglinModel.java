package net.readycheck.plushables.common.entities.froglin;

import net.minecraft.util.ResourceLocation;
import net.readycheck.plushables.common.Plushables;
import software.bernie.example.item.JackInTheBoxItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FroglinModel extends AnimatedGeoModel<FroglinEntity>{

    @Override
    public ResourceLocation getModelLocation(FroglinEntity froglinEntity) {
        return new ResourceLocation(Plushables.MOD_ID, "geo/froglin.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FroglinEntity froglinEntity) {
        return new ResourceLocation(Plushables.MOD_ID, "textures/entities/froglin.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FroglinEntity froglinEntity) {
        return new ResourceLocation(Plushables.MOD_ID, "animations/froglin.animation.json");
    }
}
