package com.finallion.villagersplus.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

;

public abstract class WorkstationBlock extends BaseEntityBlock {
    protected static final VoxelShape SHAPE;

    protected WorkstationBlock(BlockBehaviour.Properties settings) {
        super(settings);
    }

    public RenderShape getRenderShape(BlockState p_50950_) {
        return RenderShape.MODEL;
    }

    public boolean isPathfindable(BlockState p_50921_, BlockGetter p_50922_, BlockPos p_50923_, PathComputationType p_50924_) {
        return false;
    }

    @Override
    public float getShadeBrightness(BlockState p_60472_, BlockGetter p_60473_, BlockPos p_60474_) {
        return 1.0F;
    }

    public boolean hasAnalogOutputSignal(BlockState p_50919_) {
        return true;
    }


    public VoxelShape getShape(BlockState p_50952_, BlockGetter p_50953_, BlockPos p_50954_, CollisionContext p_50955_) {
        return SHAPE;
    }

    static {
        SHAPE = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D));
    }
}
