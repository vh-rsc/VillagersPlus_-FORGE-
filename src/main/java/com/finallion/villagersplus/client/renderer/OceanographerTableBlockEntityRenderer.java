package com.finallion.villagersplus.client.renderer;

import com.finallion.villagersplus.blockentities.OceanographerTableBlockEntity;
import com.finallion.villagersplus.blocks.OceanographerTableBlock;
import com.finallion.villagersplus.init.ModTags;
import com.finallion.villagersplus.util.DuckBucketable;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CoralFanBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.ModelData;


public class OceanographerTableBlockEntityRenderer implements BlockEntityRenderer<OceanographerTableBlockEntity> {
    private final BlockRenderDispatcher manager;
    private final EntityRenderDispatcher entityRenderDispatcher;
    private final Minecraft minecraft;
    private float[] xOffset = new float[]{0.06F, 1.5F, 1.3F, 0.1F};
    private final float[] yOffsetNorth = new float[]{0.45F, 0.75F, 0.3F, 0.45F};
    private final float[] yOffsetSouth = new float[]{0.75F, 0.45F, 0.45F, 0.3F};
    private final float[] yOffsetEast = new float[]{0.45F, 0.3F, 0.45F, 0.75F};
    private final float[] yOffsetWest = new float[]{0.3F, 0.45F, 0.75F, 0.45F};
    private float[] zOffset = new float[]{0.06F, 1.5F, 0.3F, 1.45F};

    private float[] xOffsetFan = new float[]{0.2F, 1.25F, 1.2F, 0.2F};
    private float[] zOffsetFan = new float[]{0.17F, 1.25F, 0.3F, 1.2F};

    public OceanographerTableBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.minecraft = Minecraft.getInstance();
        this.manager = ctx.getBlockRenderDispatcher();
        this.entityRenderDispatcher = ctx.getEntityRenderer();
    }

    public void render(OceanographerTableBlockEntity blockEntity, float f, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, int j) {
        BlockState blockState = blockEntity.getBlockState();
        BlockPos pos = blockEntity.getBlockPos();
        Level world = blockEntity.getLevel();
        NonNullList<ItemStack> defaultedList = blockEntity.getItems();

        if (blockState.getBlock() instanceof OceanographerTableBlock) {
            float rotation = 0;
            float[] yOffset = new float[4];
            switch (blockState.getValue(OceanographerTableBlock.FACING)) {
                case EAST -> {
                    rotation = 90.0F;
                    yOffset = yOffsetEast;
                    xOffset = new float[]{0.06F, 1.5F, 1.3F, 0.015F}; // offset adjustments
                    zOffset = new float[]{0.06F, 1.5F, 0.3F, 1.5F};
                    xOffsetFan = new float[]{0.2F, 1.25F, 1.2F, 0.115F};
                    zOffsetFan = new float[]{0.17F, 1.25F, 0.3F, 1.3F};
                }
                case WEST -> {
                    rotation = 270.0F;
                    yOffset = yOffsetWest;
                    xOffset = new float[]{0.06F, 1.5F, 1.55F, 0.1F}; // offset adjustments
                    zOffset = new float[]{0.06F, 1.5F, 0.15F, 1.45F};
                    xOffsetFan = new float[]{0.2F, 1.25F, 1.45F, 0.2F};
                    zOffsetFan = new float[]{0.17F, 1.25F, 0.15F, 1.2F};
                }
                case NORTH -> {
                    rotation = 180.0F;
                    yOffset = yOffsetNorth;
                }
                case SOUTH -> {
                    rotation = 0.0F;
                    yOffset = yOffsetSouth;
                }
            }

            for (int it = 0; it < 4; it++) {
                matrixStack.pushPose();
                Block coral = Block.byItem(defaultedList.get(it).getItem());
                Vec3 offset = coral.defaultBlockState().getOffset(world, pos);
                matrixStack.scale(0.4F, 0.4F, 0.4F);
                if (coral instanceof CoralFanBlock) {
                    matrixStack.translate(-offset.x + xOffsetFan[it], -offset.y + yOffset[it], -offset.z + zOffsetFan[it]);
                } else {
                    matrixStack.translate(-offset.x + xOffset[it], -offset.y + yOffset[it], -offset.z + zOffset[it]);
                }
                renderCoral(coral, world, pos, matrixStack, vertexConsumerProvider, j);
                matrixStack.popPose();
            }



            ItemStack itemStack = defaultedList.get(4);
            if (itemStack.getItem() instanceof MobBucketItem bucketItem) {
                Entity fish = ((DuckBucketable) bucketItem).getEntityType().create(world);

                if (fish != null && itemStack.getTag() != null) {
                    fish.load(itemStack.getOrCreateTag());

                    if (fish instanceof TropicalFish && itemStack.getTag().contains("BucketVariantTag")) {
                        int id = itemStack.getTag().getInt("BucketVariantTag");
                        ((TropicalFish) fish).setVariant(id);
                    }
                }

                matrixStack.pushPose();

                if (fish != null) {
                    long time = world.getGameTime();
                    float g = 0.53125F;
                    float h = Math.max(fish.getBbWidth(), fish.getBbHeight());
                    if ((double) h > 1.0D) {
                        g /= h;
                    }

                    float k = Mth.sin(time * 0.1F) / 2.0F + 0.5F;
                    k += k * k;
                    matrixStack.translate(0.5D, (double) (0.5F + k * 0.05F), 0.5D);

                    matrixStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
                    Vector3f vec3f = new Vector3f(0.5F, 1.0F, 0.5F);
                    vec3f.normalize();
                    matrixStack.mulPose(vec3f.rotationDegrees(h));

                    if (itemStack.is(ModTags.ROTATIONAL_BUCKET_ENTITIES)) {
                        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
                    }

                    matrixStack.scale(g, g, g);

                    if (!(fish instanceof Axolotl)) {
                        f = (float) time / 4;
                    } else {
                        matrixStack.translate(0.0D, -0.2F, 0.0D);
                        matrixStack.scale(0.8F, 0.8F, 0.8F);
                        //f = Math.abs(f) * 10.0F;
                        f = 0.0F;
                    }

                    this.entityRenderDispatcher.render(fish, 0.0D, 0.0D, 0.0D, 0.0F, f, matrixStack, vertexConsumerProvider, i);
                }

                matrixStack.popPose();
            }
        }
    }

    private void renderCoral(Block coral, Level world, BlockPos pos, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int overlay) {
        this.manager.getModelRenderer().tesselateBlock(world, this.manager.getBlockModel(coral.defaultBlockState()), coral.defaultBlockState(), pos, matrixStack, vertexConsumerProvider.getBuffer(RenderType.cutoutMipped()), false, RandomSource.create(), coral.defaultBlockState().getSeed(pos), overlay, ModelData.EMPTY, RenderType.cutoutMipped());
    }
}

