package com.khazoda.plushables.client.model;

import com.khazoda.plushables.Constants;
import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.util.VoxelShapeHelper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class PlushableOrientationModel implements BakedModel {
  private static final Direction[] DIRECTIONS = Direction.values();
  private static final int SIDE_COUNT = VoxelShapeHelper.DIRECTION_COUNT + 1;

  private final BakedModel model;
  private final List<BakedQuad>[] quads;

  public static boolean isPlushableBlockModel(ModelResourceLocation location) {
    // exclude GUI, non-plushables-namespaced & non-instances of BasePlushable
    return !ModelResourceLocation.INVENTORY_VARIANT.equals(location.variant())
        && Constants.MOD_ID.equals(location.id().getNamespace())
        && BuiltInRegistries.BLOCK.get(location.id()) instanceof BasePlushable;
  }

  @SuppressWarnings("unchecked")
  public PlushableOrientationModel(BakedModel model) {
    this.model = model;
    this.quads = new List[VoxelShapeHelper.DIRECTION_COUNT * VoxelShapeHelper.ORIENTATIONS_PER_FACE * SIDE_COUNT];

    for (Direction attachment : DIRECTIONS) {
      for (int rotation = 0; rotation < VoxelShapeHelper.ORIENTATIONS_PER_FACE; rotation++) {
        VoxelShapeHelper.Orientation orientation = VoxelShapeHelper.Orientation.of(attachment, rotation);
        List<BakedQuad>[] orientedQuads = new List[SIDE_COUNT];
        for (int side = 0; side < SIDE_COUNT; side++) orientedQuads[side] = new ArrayList<>();

        for (Direction sourceSide : DIRECTIONS) {
          addQuads(orientedQuads[sideIndex(orientation.transform(sourceSide))], orientation, sourceSide);
        }
        addQuads(orientedQuads[SIDE_COUNT - 1], orientation, null);

        for (int side = 0; side < SIDE_COUNT; side++) {
          quads[cacheIndex(attachment, rotation, side)] = List.copyOf(orientedQuads[side]);
        }
      }
    }
  }

  private void addQuads(List<BakedQuad> target, VoxelShapeHelper.Orientation orientation, @Nullable Direction side) {
    for (BakedQuad quad : model.getQuads(null, side, RandomSource.create(42L))) {
      target.add(transformQuad(quad, orientation));
    }
  }

  private BakedQuad transformQuad(BakedQuad quad, VoxelShapeHelper.Orientation orientation) {
    int[] vertices = quad.getVertices().clone();
    int stride = vertices.length / 4;

    for (int vertex = 0; vertex < 4; vertex++) {
      int offset = vertex * stride;
      float x = Float.intBitsToFloat(vertices[offset]);
      float y = Float.intBitsToFloat(vertices[offset + 1]);
      float z = Float.intBitsToFloat(vertices[offset + 2]);
      Vec3 transformed = orientation.transform(x, y, z);
      vertices[offset] = Float.floatToRawIntBits((float) transformed.x);
      vertices[offset + 1] = Float.floatToRawIntBits((float) transformed.y);
      vertices[offset + 2] = Float.floatToRawIntBits((float) transformed.z);
      if (stride > 7) vertices[offset + 7] = transformNormal(vertices[offset + 7], orientation);
    }

    return new BakedQuad(vertices, quad.getTintIndex(), orientation.transform(quad.getDirection()), quad.getSprite(), quad.isShade());
  }

  private static int transformNormal(int packedNormal, VoxelShapeHelper.Orientation orientation) {
    Vec3 transformed = orientation.transformVector((byte) packedNormal, (byte) (packedNormal >> 8), (byte) (packedNormal >> 16));
    return packedNormal & 0xFF000000
        | packNormal(transformed.x)
        | packNormal(transformed.y) << 8
        | packNormal(transformed.z) << 16;
  }

  private static int packNormal(double normal) {
    return Math.max(Byte.MIN_VALUE, Math.min(Byte.MAX_VALUE, Math.round((float) normal))) & 0xFF;
  }

  @Override
  public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource random) {
    if (state == null || !state.hasProperty(BasePlushable.ATTACHMENT) || !state.hasProperty(BasePlushable.ROTATION)) {
      return model.getQuads(state, side, random);
    }
    return quads[cacheIndex(state.getValue(BasePlushable.ATTACHMENT), state.getValue(BasePlushable.ROTATION), sideIndex(side))];
  }

  private static int cacheIndex(Direction attachment, int rotation, int side) {
    return VoxelShapeHelper.orientationIndex(attachment, rotation) * SIDE_COUNT + side;
  }

  private static int sideIndex(@Nullable Direction side) {
    return side == null ? SIDE_COUNT - 1 : side.ordinal();
  }

  @Override
  public boolean useAmbientOcclusion() {
    return false;
  }

  @Override
  public boolean isGui3d() {
    return model.isGui3d();
  }

  @Override
  public boolean usesBlockLight() {
    return model.usesBlockLight();
  }

  @Override
  public boolean isCustomRenderer() {
    return model.isCustomRenderer();
  }

  @Override
  public TextureAtlasSprite getParticleIcon() {
    return model.getParticleIcon();
  }

  @Override
  public ItemTransforms getTransforms() {
    return model.getTransforms();
  }

  @Override
  public ItemOverrides getOverrides() {
    return model.getOverrides();
  }
}
