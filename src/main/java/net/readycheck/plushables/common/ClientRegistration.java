package net.readycheck.plushables.common;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.readycheck.plushables.common.blocks.recycler.RecyclerTileRenderer;
import net.readycheck.plushables.common.entities.froglin.FroglinRenderer;

public class ClientRegistration {
    public static void setupEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(Registration.FROGLIN.get(), FroglinRenderer::new);
        ClientRegistry.bindTileEntityRenderer(Registration.RECYCLER_TILE.get(), RecyclerTileRenderer::new);


    }

    public static void setup() {
        setupEntityRenderers();
    }
}
