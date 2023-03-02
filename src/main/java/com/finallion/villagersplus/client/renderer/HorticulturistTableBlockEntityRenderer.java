package com.finallion.villagersplus.client.renderer;

import com.finallion.villagersplus.blockentities.HorticulturistTableBlockEntity;
import com.finallion.villagersplus.blocks.HorticulturistTableBlock;
import com.finallion.villagersplus.init.ModTags;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.EmptyModelData;

import java.util.Random;

public class HorticulturistTableBlockEntityRenderer implements BlockEntityRenderer<HorticulturistTableBlockEntity> {
    private final BlockRenderDispatcher manager;

    public HorticulturistTableBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.manager = ctx.getBlockRenderDispatcher();
    }

    public void render(HorticulturistTableBlockEntity blockEntity, float f, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, int j) {
        BlockState blockState = blockEntity.getBlockState();
        BlockPos pos = blockEntity.getBlockPos();
        NonNullList<ItemStack> defaultedList = blockEntity.getItems();

        if (blockState.getBlock() instanceof HorticulturistTableBlock) {
            matrixStack.pushPose();
            if (blockState.getValue(HorticulturistTableBlock.IS_TALL_FLOWER)) {
                Block flower = Block.byItem(defaultedList.get(0).getItem());
                if (flower instanceof DoublePlantBlock) {
                    Vec3 offset = flower.defaultBlockState().getOffset(blockEntity.getLevel(), pos);
                    matrixStack.translate(-offset.x, -offset.y + 0.95D, -offset.z);
                    renderTallFlower(flower.defaultBlockState().getBlock(), blockEntity.getLevel(), pos, matrixStack, vertexConsumerProvider, true, j);
                    matrixStack.translate(-offset.x, -offset.y + 1.0D, -offset.z);
                    renderTallFlower(flower.defaultBlockState().getBlock(), blockEntity.getLevel(), pos, matrixStack, vertexConsumerProvider, false, j);
                } else {
                    Block flowerOne = Block.byItem(defaultedList.get(0).getItem());
                    if (flowerOne.defaultBlockState().is(Blocks.CACTUS)) {
                        matrixStack.scale(0.75F, 0.75F, 0.75F);
                        matrixStack.translate(0.15D, 0.15D, 0.15D);
                    }

                    Vec3 offset = flowerOne.defaultBlockState().getOffset(blockEntity.getLevel(), pos);
                    matrixStack.translate(-offset.x, -offset.y + 0.95D, -offset.z);
                    renderFlower(Block.byItem(defaultedList.get(0).getItem()), blockEntity.getLevel(), pos, matrixStack, vertexConsumerProvider, j);
                }
            } else {
                switch (blockState.getValue(HorticulturistTableBlock.FLOWERS)) {
                    case 1 -> {
                        Block flowerOne = Block.byItem(defaultedList.get(0).getItem());
                        Vec3 offset = flowerOne.defaultBlockState().getOffset(blockEntity.getLevel(), pos);
                        matrixStack.translate(-offset.x, -offset.y + 0.95D, -offset.z);
                        renderFlower(flowerOne, blockEntity.getLevel(), pos, matrixStack, vertexConsumerProvider, j);
                    }
                    case 2 -> {
                        Block flowerOne = Block.byItem(defaultedList.get(0).getItem());
                        Block flowerTwo = Block.byItem(defaultedList.get(1).getItem());
                        Vec3 offset = flowerOne.defaultBlockState().getOffset(blockEntity.getLevel(), pos);
                        Vec3 offset2 = flowerTwo.defaultBlockState().getOffset(blockEntity.getLevel(), pos);
                        matrixStack.translate(-offset.x + 0.15D, -offset.y + 0.95D, -offset.z + 0.15D);
                        if (flowerOne.defaultBlockState().is(ModTags.SCALABLE_BLOCKS)) {
                            matrixStack.scale(0.75F, 0.75F, 0.75F);
                            matrixStack.translate(0, 0.15D, 0);
                        }
                        renderFlower(flowerOne, blockEntity.getLevel(), pos, matrixStack, vertexConsumerProvider, j);
                        matrixStack.translate(-offset2.x - 0.3D, -offset2.y, -offset2.z - 0.3D - 0.05D);
                        if (flowerTwo.defaultBlockState().is(ModTags.SCALABLE_BLOCKS)) {
                            matrixStack.scale(0.75F, 0.75F, 0.75F);
                            matrixStack.translate(0, 0.15D, 0);
                        }
                        renderFlower(flowerTwo, blockEntity.getLevel(), pos, matrixStack, vertexConsumerProvider, j);
                    }
                    case 3 -> {
                        Block flowerOne = Block.byItem(defaultedList.get(0).getItem());
                        Block flowerTwo = Block.byItem(defaultedList.get(1).getItem());
                        Block flowerThree = Block.byItem(defaultedList.get(2).getItem());
                        Vec3 offset = flowerOne.defaultBlockState().getOffset(blockEntity.getLevel(), pos);
                        Vec3 offset2 = flowerTwo.defaultBlockState().getOffset(blockEntity.getLevel(), pos);
                        Vec3 offset3 = flowerThree.defaultBlockState().getOffset(blockEntity.getLevel(), pos);
                        matrixStack.translate(-offset.x + 0.15D, -offset.y + 0.95D, -offset.z);
                        if (flowerOne.defaultBlockState().is(ModTags.SCALABLE_BLOCKS)) {
                            matrixStack.scale(0.6F, 0.6F, 0.6F);
                            matrixStack.translate(0, 0.2D, 0);
                        }
                        renderFlower(flowerOne, blockEntity.getLevel(), pos, matrixStack, vertexConsumerProvider, j);
                        matrixStack.translate(-offset2.x - 0.3D, -offset2.y - 0.05D, -offset2.z - 0.15D);
                        if (flowerTwo.defaultBlockState().is(ModTags.SCALABLE_BLOCKS)) {
                            matrixStack.scale(0.6F, 0.6F, 0.6F);
                            matrixStack.translate(0, 0.2D, 0);
                        }
                        renderFlower(flowerTwo, blockEntity.getLevel(), pos, matrixStack, vertexConsumerProvider, j);
                        matrixStack.translate(-offset3.x - 0.05D, -offset3.y + 0, -offset3.z + 0.3D);
                        if (flowerThree.defaultBlockState().is(ModTags.SCALABLE_BLOCKS)) {
                            matrixStack.scale(0.6F, 0.6F, 0.6F);
                            matrixStack.translate(0, 0.2D, 0);
                        }
                        renderFlower(flowerThree, blockEntity.getLevel(), pos, matrixStack, vertexConsumerProvider, j);
                    }
                    case 4 -> {
                        Block flowerOne = Block.byItem(defaultedList.get(0).getItem());
                        Block flowerTwo = Block.byItem(defaultedList.get(1).getItem());
                        Block flowerThree = Block.byItem(defaultedList.get(2).getItem());
                        Block flowerFour = Block.byItem(defaultedList.get(3).getItem());
                        Vec3 offset = flowerOne.defaultBlockState().getOffset(blockEntity.getLevel(), pos);
                        Vec3 offset2 = flowerTwo.defaultBlockState().getOffset(blockEntity.getLevel(), pos);
                        Vec3 offset3 = flowerThree.defaultBlockState().getOffset(blockEntity.getLevel(), pos);
                        Vec3 offset4 = flowerFour.defaultBlockState().getOffset(blockEntity.getLevel(), pos);
                        matrixStack.translate(-offset.x + 0.15D, -offset.y + 0.95D, -offset.z + 0.15D);
                        if (flowerOne.defaultBlockState().is(ModTags.SCALABLE_BLOCKS)) {
                            matrixStack.scale(0.5F, 0.5F, 0.5F);
                            matrixStack.translate(0, 0.25D, 0);
                        }
                        renderFlower(flowerOne, blockEntity.getLevel(), pos, matrixStack, vertexConsumerProvider, j);
                        matrixStack.translate(-offset2.x, -offset2.y - 0.05D, -offset2.z - 0.3D - 0.05D);
                        if (flowerTwo.defaultBlockState().is(ModTags.SCALABLE_BLOCKS)) {
                            matrixStack.scale(0.5F, 0.5F, 0.5F);
                            matrixStack.translate(0, 0.25D, 0);
                        }
                        renderFlower(Block.byItem(defaultedList.get(1).getItem()), blockEntity.getLevel(), pos, matrixStack, vertexConsumerProvider, j);
                        matrixStack.translate(-offset3.x - 0.3D, -offset3.y + 0, -offset3.z);
                        if (flowerThree.defaultBlockState().is(ModTags.SCALABLE_BLOCKS)) {
                            matrixStack.scale(0.5F, 0.5F, 0.5F);
                            matrixStack.translate(0, 0.25D, 0);
                        }
                        renderFlower(Block.byItem(defaultedList.get(2).getItem()), blockEntity.getLevel(), pos, matrixStack, vertexConsumerProvider, j);
                        matrixStack.translate(-offset4.x - 0.05D, -offset4.y - 0.05D, -offset4.z + 0.37D);
                        if (flowerFour.defaultBlockState().is(ModTags.SCALABLE_BLOCKS)) {
                            matrixStack.scale(0.5F, 0.5F, 0.5F);
                            matrixStack.translate(0, 0.25D, 0);
                        }
                        renderFlower(Block.byItem(defaultedList.get(3).getItem()), blockEntity.getLevel(), pos, matrixStack, vertexConsumerProvider, j);
                    }
                }
            }
            matrixStack.popPose();
        }
    }

    private void renderFlower(Block flower, Level world, BlockPos pos, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int overlay) {
        this.manager.getModelRenderer().tesselateBlock(world, this.manager.getBlockModel(flower.defaultBlockState()), flower.defaultBlockState(), pos, matrixStack, vertexConsumerProvider.getBuffer(RenderType.cutoutMipped()), false, new Random(), flower.defaultBlockState().getSeed(pos), overlay);
    }


    private void renderTallFlower(Block flower, Level world, BlockPos pos, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, boolean lower, int overlay) {
        if (lower) {
            this.manager.getModelRenderer().tesselateBlock(world, this.manager.getBlockModel(flower.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)), flower.defaultBlockState(), pos, matrixStack, vertexConsumerProvider.getBuffer(RenderType.cutoutMipped()), false, new Random(), flower.defaultBlockState().getSeed(pos), overlay, EmptyModelData.INSTANCE);
        } else {
            this.manager.getModelRenderer().tesselateBlock(world, this.manager.getBlockModel(flower.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)), flower.defaultBlockState(), pos, matrixStack, vertexConsumerProvider.getBuffer(RenderType.cutoutMipped()), false, new Random(), flower.defaultBlockState().getSeed(pos), overlay, EmptyModelData.INSTANCE);
        }
    }
}

