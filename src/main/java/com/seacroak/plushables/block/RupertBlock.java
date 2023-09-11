package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.RupertTileEntity;
import com.seacroak.plushables.registry.assets.SoundRegistry;
import com.seacroak.plushables.registry.uncommon.TileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.jetbrains.annotations.Nullable;

public class RupertBlock extends BasePoweredPlushable<RupertTileEntity> {

  public RupertBlock() {
    super(RupertTileEntity.class, SoundRegistry.RUPERT_PURR);
    this.cooldownPeriod = 70;
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.5, 0.75, 0.125, 0.6875));
    return shape;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return TileRegistry.RUPERT_TILE.instantiate(pos, state);
  }
}

