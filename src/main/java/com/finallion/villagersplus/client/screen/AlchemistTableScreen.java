package com.finallion.villagersplus.client.screen;

import com.finallion.villagersplus.VillagersPlus;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class AlchemistTableScreen extends AbstractContainerScreen<AlchemistTableScreenHandler> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(VillagersPlus.MOD_ID, "textures/gui/container/alchemist_table.png");
    private static final int[] BUBBLE_PROGRESS = new int[]{29, 24, 20, 16, 11, 6, 0};

    public AlchemistTableScreen(AlchemistTableScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    public void render(PoseStack p_98341_, int p_98342_, int p_98343_, float p_98344_) {
        this.renderBackground(p_98341_);
        super.render(p_98341_, p_98342_, p_98343_, p_98344_);
        this.renderTooltip(p_98341_, p_98342_, p_98343_);
    }

    protected void renderBg(PoseStack p_98336_, float p_98337_, int p_98338_, int p_98339_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(p_98336_, i, j, 0, 0, this.imageWidth, this.imageHeight);
        int k = this.menu.getFuel();
        int l = Mth.clamp((18 * k + 20 - 1) / 20, 0, 18);
        if (l > 0) {
            this.blit(p_98336_, i + 60, j + 44, 176, 29, l, 4);
        }

        int i1 = this.menu.getBrewTime();
        if (i1 > 0) {
            int j1 = (int)(28.0F * (1.0F - (float)i1 / 400.0F));
            if (j1 > 0) {
                this.blit(p_98336_, i + 97, j + 16, 176, 0, 9, j1);
            }

            j1 = BUBBLE_PROGRESS[i1 / 2 % 7];
            if (j1 > 0) {
                this.blit(p_98336_, i + 63, j + 14 + 29 - j1, 185, 29 - j1, 12, j1);
            }
        }


    }
}
