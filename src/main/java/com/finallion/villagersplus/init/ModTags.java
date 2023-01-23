package com.finallion.villagersplus.init;

import com.finallion.villagersplus.VillagersPlus;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTags {
    public static final TagKey<Item> PLANTABLE_BLOCKS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(VillagersPlus.MOD_ID, "plantable_blocks"));
    public static final TagKey<Item> TALL_PLANTABLE_BLOCKS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(VillagersPlus.MOD_ID, "tall_plantable_blocks"));
    public static final TagKey<Item> SMALL_PLANTABLE_BLOCKS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(VillagersPlus.MOD_ID, "small_plantable_blocks"));

    public static final TagKey<Item> AQUARIUM_PLANTABLE_BLOCKS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(VillagersPlus.MOD_ID, "aquarium_plantable_blocks"));
    public static final TagKey<Item> ROTATIONAL_BUCKET_ENTITIES = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(VillagersPlus.MOD_ID, "rotationable_bucket_entities"));

    public static final TagKey<Block> SCALABLE_BLOCKS = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(VillagersPlus.MOD_ID, "scalable_blocks"));

    public static void init() {}
}
