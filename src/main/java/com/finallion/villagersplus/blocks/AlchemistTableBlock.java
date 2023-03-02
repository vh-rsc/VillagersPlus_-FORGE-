package com.finallion.villagersplus.blocks;

import com.finallion.villagersplus.blockentities.AlchemistTableBlockEntity;
import com.finallion.villagersplus.blockentities.OceanographerTableBlockEntity;
import com.finallion.villagersplus.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class AlchemistTableBlock extends WorkstationBlock {
    public static final BooleanProperty[] BOTTLE_PROPERTIES;
    public static final BooleanProperty HAS_FUEL;
    public static final BooleanProperty IS_BREWING;
    public static final DirectionProperty FACING;
    protected static final VoxelShape SHAPE;

    public AlchemistTableBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.getStateDefinition().any().setValue(BOTTLE_PROPERTIES[0], false).setValue(BOTTLE_PROPERTIES[1], false).setValue(BOTTLE_PROPERTIES[2], false).setValue(BOTTLE_PROPERTIES[3], false).setValue(IS_BREWING, false).setValue(HAS_FUEL, false).setValue(FACING, Direction.NORTH));
    }

    public BlockEntity newBlockEntity(BlockPos p_152698_, BlockState p_152699_) {
        return new AlchemistTableBlockEntity(p_152698_, p_152699_);
    }

    public VoxelShape getShape(BlockState p_50952_, BlockGetter p_50953_, BlockPos p_50954_, CollisionContext p_50955_) {
        return SHAPE;
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152694_, BlockState p_152695_, BlockEntityType<T> p_152696_) {
        return p_152694_.isClientSide ? null : createTickerHelper(p_152696_, ModBlocks.ALCHEMIST_TABLE_BLOCK_ENTITY.get(), AlchemistTableBlockEntity::tick);
    }

    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof AlchemistTableBlockEntity) {
                player.openMenu((AlchemistTableBlockEntity)blockEntity);
            }

            return InteractionResult.CONSUME;
        }
    }

    public void setPlacedBy(Level p_50913_, BlockPos p_50914_, BlockState p_50915_, LivingEntity p_50916_, ItemStack p_50917_) {
        if (p_50917_.hasCustomHoverName()) {
            BlockEntity blockentity = p_50913_.getBlockEntity(p_50914_);
            if (blockentity instanceof AlchemistTableBlockEntity) {
                ((AlchemistTableBlockEntity)blockentity).setCustomName(p_50917_.getHoverName());
            }
        }
    }

    public void animateTick(BlockState state, Level world, BlockPos pos, Random random) {
        double d, f;
        double e = (double)pos.getY() + 0.125D + (double)random.nextFloat() * 0.15D;

        switch (state.getValue(FACING)) {
            default -> {
                d = (double)pos.getX() + 0.36D;
                f = (double)pos.getZ() + 0.15D;
            }
            case SOUTH -> {
                d = (double)pos.getX() + 1.0D - 0.36D;
                f = (double)pos.getZ() + 1.0D - 0.15D;
            }
            case EAST -> {
                d = (double)pos.getX() + 1.2D - 0.36D;
                f = (double)pos.getZ() + 0.37D;
            }
            case WEST -> {
                d = (double)pos.getX() + 0.15D;
                f = (double)pos.getZ() + 0.56D;
            }
        }

        if (state.getValue(IS_BREWING) && random.nextInt(3) == 0) {
            world.addParticle(ParticleTypes.FLAME, d, e, f, 0.0D, 0.0D, 0.0D);
        }

        if (state.getValue(HAS_FUEL) || state.getValue(IS_BREWING)) {
            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0D, 0.05D, 0.0D);
        }
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState p_181242_, BlockGetter p_181243_, BlockPos p_181244_) {
        return false;
    }

    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof AlchemistTableBlockEntity) {
                Containers.dropContents(world, pos, (AlchemistTableBlockEntity)blockEntity);
            }

            super.onRemove(state, world, pos, newState, moved);
        }
    }

    public int getAnalogOutputSignal(BlockState p_50926_, Level p_50927_, BlockPos p_50928_) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(p_50927_.getBlockEntity(p_50928_));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BOTTLE_PROPERTIES[0], BOTTLE_PROPERTIES[1], BOTTLE_PROPERTIES[2], BOTTLE_PROPERTIES[3], HAS_FUEL, IS_BREWING, FACING);
    }

    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    public BlockState rotate(BlockState p_54540_, Rotation p_54541_) {
        return p_54540_.setValue(FACING, p_54541_.rotate(p_54540_.getValue(FACING)));
    }

    public BlockState mirror(BlockState p_54537_, Mirror p_54538_) {
        return p_54537_.rotate(p_54538_.getRotation(p_54537_.getValue(FACING)));
    }

    static {
        BOTTLE_PROPERTIES = new BooleanProperty[]{BlockStateProperties.HAS_BOTTLE_0, BlockStateProperties.HAS_BOTTLE_1, BlockStateProperties.HAS_BOTTLE_2, BlockStateProperties.EXTENDED};
        HAS_FUEL = BooleanProperty.create("has_fuel");
        IS_BREWING = BooleanProperty.create("is_brewing");
        FACING = HorizontalDirectionalBlock.FACING;
        SHAPE = Block.box(0.0D, 1.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    }
}
