package com.seacroak.plushables.block;

import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.util.VoxelShapeUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static com.seacroak.plushables.registry.MainRegistry.TRUFFLES_PLUSHABLE;


public class PigBlock extends SimplePlushable {

  public PigBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.003125, 0.21875, 0.75, 0.315625, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5625, 0, 0.875, 0.75, 0.1875, 1));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.875, 0.4375, 0.1875, 1));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.34375, 0.003125, 0.03125, 0.65625, 0.315625, 0.21875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.421875, 0.0625, -0.03125, 0.578125, 0.15625, 0.03125));

    return shape;
  }
}