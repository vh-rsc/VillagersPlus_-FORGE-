package com.finallion.villagersplus.blocks;

import com.finallion.villagersplus.blockentities.HorticulturistTableBlockEntity;
import com.finallion.villagersplus.blockentities.OceanographerTableBlockEntity;
import com.finallion.villagersplus.init.ModTags;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class HorticulturistTableBlock extends WorkstationBlock {
    public static final IntegerProperty FLOWERS;
    public static final BooleanProperty IS_TALL_FLOWER;

    public HorticulturistTableBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FLOWERS, 0).setValue(IS_TALL_FLOWER, false));
    }

    public BlockEntity newBlockEntity(BlockPos p_152698_, BlockState p_152699_) {
        return new HorticulturistTableBlockEntity(p_152698_, p_152699_);
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState p_181242_, BlockGetter p_181243_, BlockPos p_181244_) {
        return false;
    }

    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (world.getBlockEntity(pos) instanceof HorticulturistTableBlockEntity blockEntity && state.getValue(FLOWERS) < 4) {
            if (itemStack.is(ModTags.TALL_PLANTABLE_BLOCKS)) {
                if (!player.isCreative()) {
                    itemStack.shrink(1);
                }

                blockEntity.insertFlower(itemStack, state.getValue(FLOWERS));
                if (!world.isClientSide()) {
                    world.setBlock(pos, state.setValue(FLOWERS, 4).setValue(IS_TALL_FLOWER, true), 3);
                    world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide());
            } else if (itemStack.is(ModTags.SMALL_PLANTABLE_BLOCKS)) {
                if (!player.isCreative()) {
                    itemStack.shrink(1);
                }

                blockEntity.insertFlower(itemStack, state.getValue(FLOWERS));
                if (!world.isClientSide()) {
                    world.setBlock(pos, state.setValue(FLOWERS, state.getValue(FLOWERS) + 1).setValue(IS_TALL_FLOWER, false), 3);
                    world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                }
                return InteractionResult.sidedSuccess(world.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }

    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof HorticulturistTableBlockEntity) {
                Containers.dropContents(world, pos, (HorticulturistTableBlockEntity)blockEntity);
            }

            super.onRemove(state, world, pos, newState, moved);
        }
    }


    public int getAnalogOutputSignal(BlockState p_50926_, Level p_50927_, BlockPos p_50928_) {
        return p_50926_.getValue(FLOWERS);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FLOWERS, IS_TALL_FLOWER);
    }

    static {
        IS_TALL_FLOWER = BooleanProperty.create("is_tall_flower");
        FLOWERS = IntegerProperty.create("flowers", 0, 4);
    }
}
