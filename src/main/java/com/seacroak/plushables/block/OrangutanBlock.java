package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.OrangutanTileEntity;
import com.seacroak.plushables.registry.assets.SoundRegistry;
import com.seacroak.plushables.registry.uncommon.TileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.jetbrains.annotations.Nullable;

public class OrangutanBlock extends BasePoweredPlushable {

  public OrangutanBlock() {
    super(OrangutanTileEntity.class, SoundRegistry.ORANGUTAN);
    this.cooldownPeriod = 70;

  }


  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.3125, 0.3125, 0.375, 0.5));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0, 0.3125, 0.875, 0.375, 0.5));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.75, 0.3125, 0.375, 0.9375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0, 0.75, 0.875, 0.375, 0.9375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.203125, 0.3125, 0.875, 0.4375, 0.5));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.203125, 0.5, 0.875, 0.375, 0.9375));

    return shape;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return TileRegistry.ORANGUTAN_TILE.instantiate(pos, state);
  }
}