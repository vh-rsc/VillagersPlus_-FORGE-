package com.finallion.villagersplus.init;

import com.finallion.villagersplus.mixin.StructurePoolAccessor;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.registries.ForgeRegistries;


import java.util.ArrayList;
import java.util.List;

public class ModStructures {
    private static final ResourceKey<StructureProcessorList> EMPTY_PROCESSOR_LIST_KEY = ResourceKey.create(Registry.PROCESSOR_LIST_REGISTRY, new ResourceLocation("minecraft", "empty"));
    private static final ResourceLocation plainsPoolLocation = new ResourceLocation("minecraft:village/plains/houses");
    private static final ResourceLocation desertPoolLocation = new ResourceLocation("minecraft:village/desert/houses");
    private static final ResourceLocation savannaPoolLocation = new ResourceLocation("minecraft:village/savanna/houses");
    private static final ResourceLocation snowyPoolLocation = new ResourceLocation("minecraft:village/snowy/houses");
    private static final ResourceLocation taigaPoolLocation = new ResourceLocation("minecraft:village/taiga/houses");

    public static void registerJigsaws(MinecraftServer server) {
        Registry<StructureTemplatePool> templatePoolRegistry = server.registryAccess().registry(Registry.TEMPLATE_POOL_REGISTRY).orElseThrow();
        Registry<StructureProcessorList> processorListRegistry = server.registryAccess().registry(Registry.PROCESSOR_LIST_REGISTRY).orElseThrow();

        addBuildingToPool(templatePoolRegistry, processorListRegistry, plainsPoolLocation, "villagersplus:village/plains/plains_alchemist", 10);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, plainsPoolLocation, "villagersplus:village/plains/plains_oceanographer", 10);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, plainsPoolLocation, "villagersplus:village/plains/plains_horticulturist", 10);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, plainsPoolLocation, "villagersplus:village/plains/plains_occultist", 10);

        addBuildingToPool(templatePoolRegistry, processorListRegistry, taigaPoolLocation, "villagersplus:village/taiga/taiga_alchemist", 10);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, taigaPoolLocation, "villagersplus:village/taiga/taiga_oceanographer", 10);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, taigaPoolLocation, "villagersplus:village/taiga/taiga_horticulturist", 10);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, taigaPoolLocation, "villagersplus:village/taiga/taiga_occultist", 10);

        addBuildingToPool(templatePoolRegistry, processorListRegistry, savannaPoolLocation, "villagersplus:village/savanna/savanna_alchemist", 10);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, savannaPoolLocation, "villagersplus:village/savanna/savanna_oceanographer", 10);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, savannaPoolLocation, "villagersplus:village/savanna/savanna_horticulturist", 10);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, savannaPoolLocation, "villagersplus:village/savanna/savanna_occultist", 10);

        addBuildingToPool(templatePoolRegistry, processorListRegistry, snowyPoolLocation, "villagersplus:village/snowy/snowy_alchemist", 10);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, snowyPoolLocation, "villagersplus:village/snowy/snowy_oceanographer", 10);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, snowyPoolLocation, "villagersplus:village/snowy/snowy_horticulturist", 10);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, snowyPoolLocation, "villagersplus:village/snowy/snowy_occultist", 10);

        addBuildingToPool(templatePoolRegistry, processorListRegistry, desertPoolLocation, "villagersplus:village/desert/desert_alchemist", 10);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, desertPoolLocation, "villagersplus:village/desert/desert_oceanographer", 10);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, desertPoolLocation, "villagersplus:village/desert/desert_horticulturist", 10);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, desertPoolLocation, "villagersplus:village/desert/desert_occultist", 10);
    }

    public static void addBuildingToPool(Registry<StructureTemplatePool> templatePoolRegistry, Registry<StructureProcessorList> processorListRegistry, ResourceLocation poolRL, String nbtPieceRL, int weight) {
        Holder<StructureProcessorList> processorList = processorListRegistry.getHolderOrThrow(EMPTY_PROCESSOR_LIST_KEY);

        StructureTemplatePool pool = templatePoolRegistry.get(poolRL);
        if (pool == null) return;

        SinglePoolElement piece = SinglePoolElement.single(nbtPieceRL, processorList).apply(StructureTemplatePool.Projection.RIGID);

        for (int i = 0; i < weight; i++) {
            ((StructurePoolAccessor) pool).getTemplates().add(piece);
        }

        List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(((StructurePoolAccessor) pool).getRawTemplates());
        listOfPieceEntries.add(new Pair<>(piece, weight));
        ((StructurePoolAccessor) pool).setRawTemplates(listOfPieceEntries);
    }
}
