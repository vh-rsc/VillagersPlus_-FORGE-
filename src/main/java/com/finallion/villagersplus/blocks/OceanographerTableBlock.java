package com.finallion.villagersplus.blocks;

import com.finallion.villagersplus.blockentities.OceanographerTableBlockEntity;
import com.finallion.villagersplus.init.ModParticles;
import com.finallion.villagersplus.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class OceanographerTableBlock extends WorkstationBlock {
    public static final IntegerProperty CORALS;
    public static final IntegerProperty FISH;
    public static final DirectionProperty FACING;
    public static final BooleanProperty IS_FILLED;

    public OceanographerTableBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.getStateDefinition().any().setValue(CORALS, 0).setValue(FISH, 0).setValue(IS_FILLED, false).setValue(FACING, Direction.NORTH));
    }

    public BlockEntity newBlockEntity(BlockPos p_152698_, BlockState p_152699_) {
        return new OceanographerTableBlockEntity(p_152698_, p_152699_);
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (random.nextBoolean()) {
            double x = pos.getX() + 0.1D + (pos.getX() + 0.9D - (pos.getX() + 0.1D)) * random.nextDouble();
            double y = pos.getY() + 0.1D + (pos.getY() + 0.4D - (pos.getY() + 0.1D)) * random.nextDouble();
            double z = pos.getZ() + 0.1D + (pos.getZ() + 0.9D - (pos.getZ() + 0.1D)) * random.nextDouble();

            world.addParticle(ModParticles.BUBBLE_PARTICLE.get(), x, y, z, 0.0D, 0.000001D, 0.0D);
        }

    }

    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (world.getBlockEntity(pos) instanceof OceanographerTableBlockEntity blockEntity) {
                if (itemStack.is(ModTags.AQUARIUM_PLANTABLE_BLOCKS) && state.getValue(CORALS) < 4) {
                    if (!player.isCreative()) {
                        itemStack.shrink(1);
                    }

                    blockEntity.insertCoral(itemStack, state.getValue(CORALS));
                    if (!world.isClientSide()) {
                        world.setBlock(pos, state.setValue(CORALS, state.getValue(CORALS) + 1), 3);
                        world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                    }

                    return InteractionResult.sidedSuccess(world.isClientSide());
                } else if (itemStack.getItem() instanceof MobBucketItem bucketItem && state.getValue(FISH) < 1) {
                    blockEntity.insertCoral(itemStack, 4);

                    if (!player.isCreative()) {
                        itemStack.shrink(1);
                    }


                    if (!world.isClientSide()) {
                        world.setBlock(pos, state.setValue(FISH, state.getValue(FISH) + 1), 3);
                        world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                    }

                    return InteractionResult.sidedSuccess(world.isClientSide());
                }
        }
        return InteractionResult.PASS;
    }

    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof OceanographerTableBlockEntity) {
                Containers.dropContents(world, pos, (OceanographerTableBlockEntity)blockEntity);
            }

            super.onRemove(state, world, pos, newState, moved);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CORALS, FISH, IS_FILLED, FACING);
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

    public int getAnalogOutputSignal(BlockState state, Level p_50927_, BlockPos p_50928_) {
        return (Integer)state.getValue(FISH) + state.getValue(CORALS);
    }

    static {
        IS_FILLED = BooleanProperty.create("is_filled");
        FISH = IntegerProperty.create("fish", 0, 1);
        CORALS = IntegerProperty.create("corals", 0, 4);
        FACING = HorizontalDirectionalBlock.FACING;
    }
}
