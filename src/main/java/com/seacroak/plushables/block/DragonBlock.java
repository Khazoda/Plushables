package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.DragonTileEntity;
import com.seacroak.plushables.registry.assets.SoundRegistry;
import com.seacroak.plushables.registry.uncommon.TileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.jetbrains.annotations.Nullable;

public class DragonBlock extends BasePoweredPlushable<DragonTileEntity> {

  public DragonBlock() {
    super(DragonTileEntity.class, SoundRegistry.LIGHTFURY);
    this.cooldownPeriod = 70;
  }


  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.1875, 0.40625, 0.125, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0, 0.1875, 0.59375, 0.125, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.3125, 0.59375, 0.4375, 0.5625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.3125, 0.171875, 0.59375, 0.5, 0.421875));

    return shape;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return TileRegistry.DRAGON_TILE.instantiate(pos, state);
  }
}