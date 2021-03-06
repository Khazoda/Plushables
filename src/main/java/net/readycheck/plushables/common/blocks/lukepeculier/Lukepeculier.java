package net.readycheck.plushables.common.blocks.lukepeculier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.readycheck.plushables.common.Plushables;
import net.readycheck.plushables.common.interfaces.AbstractPlushie;
import net.readycheck.plushables.tools.SetBlockStateFlag;

import javax.annotation.Nullable;
import java.util.Random;

public class Lukepeculier extends AbstractPlushie {
    Boolean spawnParticles = false;
    public Lukepeculier(Properties properties) {
        super(properties);

    }

    // Setting up sounds for the Recycler
    ResourceLocation lukepeculier_active = new ResourceLocation(Plushables.MOD_ID, "wocky_slush");

    SoundEvent lukepeculier_active_sound = new SoundEvent(lukepeculier_active);

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult blockRayTraceResult) {
        boolean currentlyActive = state.get(ACTIVE);
        boolean newActiveState = !currentlyActive;

        if (newActiveState == true) {
//          Is switching on
            spawnParticles = true;
            worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), lukepeculier_active_sound, SoundCategory.MUSIC, 10, 1);
        } else {
            spawnParticles = false;
//          Is switching off
            worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), null, SoundCategory.MUSIC, 0, 1);
        }

        final int FLAGS = SetBlockStateFlag.get(SetBlockStateFlag.BLOCK_UPDATE, SetBlockStateFlag.SEND_TO_CLIENTS);
        worldIn.setBlockState(pos, state.with(ACTIVE, newActiveState), FLAGS);
//    Plays sound based on whether it's turning on or off
//      Helper function for ActionResultType = Success that doesn't trigger two hand swings
        return ActionResultType.func_233537_a_(worldIn.isRemote);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (worldIn.isRemote && spawnParticles == true) {

            double xpos = pos.getX() + 0.5;
            double ypos = pos.getY() + 1.0;
            double zpos = pos.getZ() + 0.5;
            double velocityX = 0; // increase in x position every tick
            double velocityY = 1; // increase in y position every tick;
            double velocityZ = 0; // increase in z position every tick

            final boolean IGNORE_RANGE_CHECK = false; // if true, always render particle regardless of how far away the player is
            final double PERCENT_CHANCE_OF_LAVA_SPAWN = 90;  // only spawn Lava particles occasionally (visually distracting if too many)

            if (rand.nextDouble() < PERCENT_CHANCE_OF_LAVA_SPAWN / 100.0) {
                worldIn.addParticle(ParticleTypes.NOTE, IGNORE_RANGE_CHECK,
                        xpos, ypos, zpos, velocityX, velocityY, velocityZ);
            }

        }
    }

    private static final Vector3d MIN_CORNER = new Vector3d(6.0, 0.0, 6.0);
    private static final Vector3d MAX_CORNER = new Vector3d(10.0, 16.0, 10.0);
    private static final VoxelShape VOXEL_SHAPE = Block.makeCuboidShape(
            MIN_CORNER.getX(), MIN_CORNER.getY(), MIN_CORNER.getZ(),
            MAX_CORNER.getX(), MAX_CORNER.getY(), MAX_CORNER.getZ()
    );

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        return VOXEL_SHAPE != null ? VOXEL_SHAPE : VoxelShapes.fullCube();
    }
}
