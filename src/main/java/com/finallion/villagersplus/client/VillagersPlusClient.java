package com.finallion.villagersplus.client;

import com.finallion.villagersplus.client.renderer.HorticulturistTableBlockEntityRenderer;
import com.finallion.villagersplus.client.renderer.OceanographerTableBlockEntityRenderer;
import com.finallion.villagersplus.client.screen.AlchemistTableScreen;
import com.finallion.villagersplus.init.ModBlocks;
import com.finallion.villagersplus.init.ModParticles;
import com.finallion.villagersplus.init.ModScreen;
import com.finallion.villagersplus.particles.BubbleParticle;
import com.finallion.villagersplus.particles.ExperienceParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class VillagersPlusClient {
    private static final RenderType CUTOUT = RenderType.cutoutMipped();

    public VillagersPlusClient() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::initRenderLayers);
        modEventBus.addListener(this::initScreens);
        modEventBus.addListener(this::initParticles);
        modEventBus.addListener(this::initBlockEntityRenderers);
    }

    @SubscribeEvent
    public void initParticles(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.EXPERIENCE_PARTICLE.get(), ExperienceParticle.ExperienceParticleFactory::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.BUBBLE_PARTICLE.get(), BubbleParticle.Provider::new);
    }

    @SubscribeEvent
    public void initRenderLayers(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.OCEANOGRAPHER_TABLE_BLOCK.get(), CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ALCHEMIST_TABLE_BLOCK.get(), CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.OCCULTIST_TABLE_BLOCK.get(), CUTOUT);
    }


    @SubscribeEvent
    public void initBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlocks.HORTICULTURIST_TABLE_BLOCK_ENTITY.get(), HorticulturistTableBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlocks.OCEANOGRAPHER_TABLE_BLOCK_ENTITY.get(), OceanographerTableBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public void initScreens(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModScreen.ALCHEMIST_TABLE_SCREEN_HANDLER.get(), AlchemistTableScreen::new);
        });
    }

    //HandledScreens.register(ModScreen.ALCHEMIST_TABLE_SCREEN_HANDLER, AlchemistTableScreen::new);


}
