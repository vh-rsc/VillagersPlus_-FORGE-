package com.finallion.villagersplus;

import com.finallion.villagersplus.client.VillagersPlusClient;
import com.finallion.villagersplus.init.*;
import com.finallion.villagersplus.villagers.ModPointOfInterestType;
import com.finallion.villagersplus.villagers.ModProfessions;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(VillagersPlus.MOD_ID)
public class VillagersPlus {
	public static final Logger LOGGER = LoggerFactory.getLogger("villagersplus");
	public static final String MOD_ID = "villagersplus";

	public VillagersPlus() {
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> VillagersPlusClient::new);

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModBlocks.BLOCKS.register(modEventBus);
		ModItems.ITEMS.register(modEventBus);
		ModBlocks.TILE_ENTITIES.register(modEventBus);
		ModScreen.MENUS.register(modEventBus);
		ModParticles.PARTICLES.register(modEventBus);
		ModPointOfInterestType.POI_TYPES.register(modEventBus);
		ModProfessions.VILLAGER_PROFESSIONS.register(modEventBus);
	}

	@SubscribeEvent
	public void onServerAboutToStartEvent(ServerAboutToStartEvent event) {
		ModStructures.registerJigsaws(event.getServer());
	}

	public static final CreativeModeTab GROUP = new CreativeModeTab ("group") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModBlocks.OAK_HORTICULTURIST_TABLE_BLOCK.get());
		}

	};


/*
	public static ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier(MOD_ID, "group"))
			.displayName(Text.literal("VillagersPlus"))
			.icon(() -> new ItemStack(ModBlocks.OAK_HORTICULTURIST_TABLE_BLOCK))
			.entries((enabledFeatures, entries, operatorEnabled) -> {
				entries.add(ModBlocks.OAK_HORTICULTURIST_TABLE_BLOCK);
				entries.add(ModBlocks.BIRCH_HORTICULTURIST_TABLE_BLOCK);
				entries.add(ModBlocks.SPRUCE_HORTICULTURIST_TABLE_BLOCK);
				entries.add(ModBlocks.DARK_OAK_HORTICULTURIST_TABLE_BLOCK);
				entries.add(ModBlocks.JUNGLE_HORTICULTURIST_TABLE_BLOCK);
				entries.add(ModBlocks.ACACIA_HORTICULTURIST_TABLE_BLOCK);
				entries.add(ModBlocks.MANGROVE_HORTICULTURIST_TABLE_BLOCK);
				entries.add(ModBlocks.CRIMSON_HORTICULTURIST_TABLE_BLOCK);
				entries.add(ModBlocks.WARPED_HORTICULTURIST_TABLE_BLOCK);
				entries.add(ModBlocks.OCEANOGRAPHER_TABLE_BLOCK);
				entries.add(ModBlocks.ALCHEMIST_TABLE_BLOCK);
				entries.add(ModBlocks.OCCULTIST_TABLE_BLOCK);

			})
			.build();

 */

}
