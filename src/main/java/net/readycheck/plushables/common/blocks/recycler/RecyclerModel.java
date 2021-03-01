package net.readycheck.plushables.common.blocks.recycler;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.readycheck.plushables.common.Plushables;
import net.readycheck.plushables.common.entities.froglin.FroglinEntity;
import software.bernie.example.item.JackInTheBoxItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RecyclerModel extends AnimatedGeoModel<RecyclerTileEntity> {

    @Override
    public ResourceLocation getModelLocation(RecyclerTileEntity recyclerTileEntity) {
        return new ResourceLocation(Plushables.MOD_ID, "geo/recycler.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RecyclerTileEntity recyclerTileEntity) {
        return new ResourceLocation(Plushables.MOD_ID, "textures/blocks/recycler/texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RecyclerTileEntity recyclerTileEntity) {
        return new ResourceLocation(Plushables.MOD_ID, "animations/recycler_active.animation.json");
    }
}
