package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.CluckyTileEntity;
import com.seacroak.plushables.registry.assets.SoundRegistry;
import com.seacroak.plushables.registry.uncommon.TileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.jetbrains.annotations.Nullable;

public class CluckyBlock extends BasePoweredPlushable<CluckyTileEntity> {

  public CluckyBlock() {
    super(CluckyTileEntity.class,SoundRegistry.CLUCKY_CLUCK);
    this.cooldownPeriod = 70;
  }



  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0625, 0.375, 0.625, 0.25, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.3125, 0.75, 0.0625, 0.75));
    return shape;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return TileRegistry.CLUCKY_TILE.instantiate(pos, state);
  }
}