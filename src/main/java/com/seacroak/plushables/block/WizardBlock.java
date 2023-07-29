package com.seacroak.plushables.block;

import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.util.PlushablesNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

public class WizardBlock extends SimplePlushable {

  public WizardBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.25, 0.75, 0.6406, 0.625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.9375, 0.1562, 0.625, 1, 0.5));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.625, 0.1562, 0.6875, 0.9375, 0.5625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.125, 0.375, 0.0937, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4218, 0.2968, 0.2187, 0.5781, 0.4375, 0.25));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0, 0.125, 0.75, 0.0937, 0.3125));

    return shape;
  }

  //   Shift Right Click pickup code
  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos,
                            PlayerEntity player, Hand hand, BlockHitResult hit) {
    // Injects superclass method
    super.onUse(state, world, pos, player, hand, hit);

    if (player.shouldCancelInteraction()) return ActionResult.PASS;

    if (world instanceof ServerWorld serverWorld) {
      PlushablesNetworking.sendDataToClients(serverWorld, new PlushablesNetworking.SoundPacket(player, pos));
      return ActionResult.SUCCESS;
    }
    playSoundAnimation(world, pos.toCenterPos(), pos, 1f);
    return ActionResult.SUCCESS;

  }

  public static void playSoundAnimation(World world, Vec3d vec, BlockPos pos, float volume) {
    world.addParticle(ParticleTypes.NOTE, vec.x, vec.y + 0.5, vec.z, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
    for (int i = 0; i < 5; i++) {
      world.addParticle(ParticleTypes.GLOW, vec.x, vec.y, vec.z, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
    }
    world.playSoundAtBlockCenter(BlockPos.ofFloored(vec) , SoundRegistry.SWMG, SoundCategory.BLOCKS, volume, 1, true);
  }
}
