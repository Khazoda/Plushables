package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.StatuetteTileEntity;
import com.seacroak.plushables.registry.assets.SoundRegistry;
import com.seacroak.plushables.registry.uncommon.TileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.jetbrains.annotations.Nullable;

public class StatuetteBlock extends BasePoweredPlushable<StatuetteTileEntity> {

  public StatuetteBlock() {
    super(StatuetteTileEntity.class,SoundRegistry.STATUETTE,1f);
    this.cooldownPeriod = 70;  }


  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.25, 0.75, 0.8125, 0.625));
    return shape;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return TileRegistry.STATUETTE_TILE.instantiate(pos, state);
  }
}