package com.seacroak.plushables.block;
import com.seacroak.plushables.block.tile.RupertTileEntity;
import com.seacroak.plushables.registry.SoundRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import software.bernie.geckolib.core.animation.AnimationController;

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

  // Shift Right Click pickup code
  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos,
                            PlayerEntity player, Hand hand, BlockHitResult hit) {
    // Injects superclass method
    super.onUse(state, world, pos, player, hand, hit);
    if (world.isClient) {
      // Custom breaking particle code
      world.addParticle(ParticleTypes.NOTE, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
      for (int i = 0; i < 5; i++) {
        world.addParticle(ParticleTypes.GLOW, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
      }
        world.playSound(player, pos, SoundRegistry.SWMG, SoundCategory.BLOCKS, 0.25f,
          (float) 1f);

        return ActionResult.SUCCESS;
    }
    return ActionResult.PASS;
  }

}
