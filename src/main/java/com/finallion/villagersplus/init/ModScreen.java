package com.finallion.villagersplus.init;

import com.finallion.villagersplus.VillagersPlus;
import com.finallion.villagersplus.client.screen.AlchemistTableScreenHandler;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModScreen {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, VillagersPlus.MOD_ID);

    public final static RegistryObject<MenuType<AlchemistTableScreenHandler>> ALCHEMIST_TABLE_SCREEN_HANDLER = MENUS.register("alchemist_table_screen_handler", () ->
            new MenuType<>(AlchemistTableScreenHandler::new)
    );

}

