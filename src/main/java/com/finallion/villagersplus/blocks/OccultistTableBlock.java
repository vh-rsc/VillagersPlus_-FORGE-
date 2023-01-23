package com.finallion.villagersplus.blocks;

import com.finallion.villagersplus.blockentities.OccultistTableBlockEntity;
import com.finallion.villagersplus.blockentities.OceanographerTableBlockEntity;
import com.finallion.villagersplus.init.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class OccultistTableBlock extends WorkstationBlock {
    public static final IntegerProperty FILLING;

    public OccultistTableBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FILLING, 0));
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState p_181242_, BlockGetter p_181243_, BlockPos p_181244_) {
        return false;
    }

    public BlockEntity newBlockEntity(BlockPos p_152698_, BlockState p_152699_) {
        return new OccultistTableBlockEntity(p_152698_, p_152699_);
    }

    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof OccultistTableBlockEntity tile && !player.isCreative()) {
            tile.interact(world, player);

            if (world.isClientSide) {
                if (player.isCrouching()) {
                    createParticleSpiral(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D, 250, ParticleTypes.SOUL, world.random);
                    world.playSound(player, pos, SoundEvents.SOUL_ESCAPE, SoundSource.BLOCKS, 3.0F, 0.0F);
                } else {
                    createParticleSpiral(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D, 250, ModParticles.EXPERIENCE_PARTICLE.get(), world.random);
                    world.playSound(player, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 3.0F, 1.0F);
                }
            }

            if (!world.isClientSide()) {
                if (tile.getLevels() >= 400) {
                    state = state.setValue(OccultistTableBlock.FILLING, 5);
                } else if (tile.getLevels() >= 300) {
                    state = state.setValue(OccultistTableBlock.FILLING, 4);
                } else if (tile.getLevels() >= 200) {
                    state = state.setValue(OccultistTableBlock.FILLING, 3);
                } else if (tile.getLevels() >= 100) {
                    state = state.setValue(OccultistTableBlock.FILLING, 2);
                } else if (tile.getLevels() > 0) {
                    state = state.setValue(OccultistTableBlock.FILLING, 1);
                } else {
                    state = state.setValue(OccultistTableBlock.FILLING, 0);
                }

                world.setBlock(pos, state, 2);
            }
            return InteractionResult.sidedSuccess(world.isClientSide);
        }
        return InteractionResult.PASS;

    }

    @Override
    public boolean propagatesSkylightDown(BlockState p_49928_, BlockGetter p_49929_, BlockPos p_49930_) {
        return true;
    }

    public static <T extends ParticleOptions> void createParticleSpiral(Level world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, int length, T type, RandomSource random) {
        double yCoord = y + 1.1D; // top of block

        for (int i = 0; i < length; i++) {
            float densityFactor = (float) i / 15;
            double xCoord = x + Mth.sin(densityFactor) / 3;
            yCoord += 0.0075;
            double zCoord = z + Mth.cos(densityFactor) / 3;
            if (random.nextInt(7) == 0) {
                world.addParticle(type, xCoord, yCoord, zCoord, velocityX, velocityY, velocityZ);
            }
        }
    }

    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (state.getValue(FILLING) > 0 && random.nextInt(3) == 0) {
            world.addParticle(ModParticles.EXPERIENCE_PARTICLE.get(), pos.getX() + 0.5D + random.nextDouble() - random.nextDouble(), pos.getY() + 1.0D + random.nextDouble(), pos.getZ() + 0.5D + random.nextDouble() - random.nextDouble(), 0.0D, 0.05D, 0.0D);
        }
    }


    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof OccultistTableBlockEntity table && !world.isClientSide()) {
                this.tryDropExperience((ServerLevel) world, pos, ItemStack.EMPTY, ConstantInt.of(table.getLevels()));
            }

            super.onRemove(state, world, pos, newState, moved);
        }
    }

    public int getAnalogOutputSignal(BlockState p_50926_, Level p_50927_, BlockPos p_50928_) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(p_50927_.getBlockEntity(p_50928_));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FILLING);
    }

    static {
        FILLING = IntegerProperty.create("filling", 0, 5);
    }
}
