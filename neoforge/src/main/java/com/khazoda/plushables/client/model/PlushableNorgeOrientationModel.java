package com.khazoda.plushables.client.model;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.BakedModelWrapper;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PlushableNorgeOrientationModel extends BakedModelWrapper<BakedModel> {
  private final PlushableOrientationModel orientedModel;

  public PlushableNorgeOrientationModel(BakedModel model) {
    super(model);
    this.orientedModel = new PlushableOrientationModel(model);
  }

  @Override
  public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource random) {
    return orientedModel.getQuads(state, side, random);
  }

  @Override
  public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource random, ModelData modelData, @Nullable RenderType renderType) {
    return renderType == null || getRenderTypes(state, random, modelData).contains(renderType) ? getQuads(state, side, random) : List.of();
  }
}
