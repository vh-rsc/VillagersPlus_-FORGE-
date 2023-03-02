package com.finallion.villagersplus.init;

import com.finallion.villagersplus.VillagersPlus;
import com.finallion.villagersplus.blockentities.AlchemistTableBlockEntity;
import com.finallion.villagersplus.blockentities.HorticulturistTableBlockEntity;
import com.finallion.villagersplus.blockentities.OccultistTableBlockEntity;
import com.finallion.villagersplus.blockentities.OceanographerTableBlockEntity;
import com.finallion.villagersplus.blocks.AlchemistTableBlock;
import com.finallion.villagersplus.blocks.HorticulturistTableBlock;
import com.finallion.villagersplus.blocks.OccultistTableBlock;
import com.finallion.villagersplus.blocks.OceanographerTableBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, VillagersPlus.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, VillagersPlus.MOD_ID);

    public static final RegistryObject<Block> ALCHEMIST_TABLE_BLOCK = BLOCKS.register("alchemist_table", () -> new AlchemistTableBlock(BlockBehaviour.Properties.of(Material.METAL).strength(0.5F).lightLevel((state) -> 1).noOcclusion()));
    public static final RegistryObject<Block> OCEANOGRAPHER_TABLE_BLOCK = BLOCKS.register("oceanographer_table", () -> new OceanographerTableBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.5F).lightLevel((state) -> 12).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false)));
    public static final RegistryObject<Block> OAK_HORTICULTURIST_TABLE_BLOCK = BLOCKS.register("oak_horticulturist_table", () -> new HorticulturistTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F).noOcclusion()));
    public static final RegistryObject<Block> DARK_OAK_HORTICULTURIST_TABLE_BLOCK = BLOCKS.register("dark_oak_horticulturist_table", () -> new HorticulturistTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F).noOcclusion()));
    public static final RegistryObject<Block> ACACIA_HORTICULTURIST_TABLE_BLOCK = BLOCKS.register("acacia_horticulturist_table", () -> new HorticulturistTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F).noOcclusion()));
    public static final RegistryObject<Block> JUNGLE_HORTICULTURIST_TABLE_BLOCK = BLOCKS.register("jungle_horticulturist_table", () -> new HorticulturistTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F).noOcclusion()));
    public static final RegistryObject<Block> SPRUCE_HORTICULTURIST_TABLE_BLOCK = BLOCKS.register("spruce_horticulturist_table", () -> new HorticulturistTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F).noOcclusion()));
    public static final RegistryObject<Block> BIRCH_HORTICULTURIST_TABLE_BLOCK = BLOCKS.register("birch_horticulturist_table", () -> new HorticulturistTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F).noOcclusion()));
    //public static final RegistryObject<Block> MANGROVE_HORTICULTURIST_TABLE_BLOCK = BLOCKS.register("mangrove_horticulturist_table", () -> new HorticulturistTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F).noOcclusion()));
    public static final RegistryObject<Block> CRIMSON_HORTICULTURIST_TABLE_BLOCK = BLOCKS.register("crimson_horticulturist_table", () -> new HorticulturistTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F).noOcclusion()));
    public static final RegistryObject<Block> WARPED_HORTICULTURIST_TABLE_BLOCK = BLOCKS.register("warped_horticulturist_table", () -> new HorticulturistTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F).noOcclusion()));
    public static final RegistryObject<Block> OCCULTIST_TABLE_BLOCK = BLOCKS.register("occultist_table", () -> new OccultistTableBlock(BlockBehaviour.Properties.of(Material.STONE).strength(0.5F).lightLevel((state) -> state.getValue(OccultistTableBlock.FILLING) * 2).noOcclusion()));


    private static <T extends BlockEntityType<?>> RegistryObject<T> register(String name, Supplier<T> tileEntity) {
        return TILE_ENTITIES.register(name, tileEntity);
    }

    public static final RegistryObject<BlockEntityType<OceanographerTableBlockEntity>> OCEANOGRAPHER_TABLE_BLOCK_ENTITY = register("oceanographer_table_block_entity", () -> BlockEntityType.Builder.of(OceanographerTableBlockEntity::new,
            OCEANOGRAPHER_TABLE_BLOCK.get()).build(null)
    );

    public static final RegistryObject<BlockEntityType<AlchemistTableBlockEntity>> ALCHEMIST_TABLE_BLOCK_ENTITY = register("alchemist_table_block_entity", () -> BlockEntityType.Builder.of(AlchemistTableBlockEntity::new,
        ALCHEMIST_TABLE_BLOCK.get()).build(null)
    );

    public static final RegistryObject<BlockEntityType<OccultistTableBlockEntity>> OCCULTIST_TABLE_BLOCK_ENTITY = register("occultist_table_block_entity", () -> BlockEntityType.Builder.of(OccultistTableBlockEntity::new,
            OCCULTIST_TABLE_BLOCK.get()).build(null)
    );

    public static final RegistryObject<BlockEntityType<HorticulturistTableBlockEntity>> HORTICULTURIST_TABLE_BLOCK_ENTITY = register("horticulturist_table_block_entity", () -> BlockEntityType.Builder.of(HorticulturistTableBlockEntity::new,
                    OAK_HORTICULTURIST_TABLE_BLOCK.get(),
                    DARK_OAK_HORTICULTURIST_TABLE_BLOCK.get(),
                    ACACIA_HORTICULTURIST_TABLE_BLOCK.get(),
                    JUNGLE_HORTICULTURIST_TABLE_BLOCK.get(),
                    SPRUCE_HORTICULTURIST_TABLE_BLOCK.get(),
                    BIRCH_HORTICULTURIST_TABLE_BLOCK.get(),
                    //MANGROVE_HORTICULTURIST_TABLE_BLOCK.get(),
                    CRIMSON_HORTICULTURIST_TABLE_BLOCK.get(),
                    WARPED_HORTICULTURIST_TABLE_BLOCK.get()).build(null)
    );

}
