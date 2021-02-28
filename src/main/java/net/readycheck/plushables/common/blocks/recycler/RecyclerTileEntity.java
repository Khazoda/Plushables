package net.readycheck.plushables.common.blocks.recycler;

import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.readycheck.plushables.common.Registration;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class RecyclerTileEntity extends TileEntity implements IAnimatable {
    private static final Blocks validBlocks = null;

    public RecyclerTileEntity() {
        super(Registration.RECYCLER_TILE.get());
    }


    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return null;
    }
}
