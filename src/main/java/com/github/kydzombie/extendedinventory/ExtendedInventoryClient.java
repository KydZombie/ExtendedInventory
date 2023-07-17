package com.github.kydzombie.extendedinventory;

import com.github.kydzombie.extendedinventory.trinkets.GuiBaubleyTrinkets;
import com.github.kydzombie.extendedinventory.trinkets.TrinketInventory;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import uk.co.benjiweber.expressions.tuple.BiTuple;

public class ExtendedInventoryClient {
    @EventListener
    public void registerGuiHandlers(GuiHandlerRegistryEvent event) {
        event.registry.registerValueNoMessage(ExtendedInventory.MOD_ID.id("openTrinkets"), BiTuple.of(this::openTrinkets, TrinketInventory::new));
    }

    public ScreenBase openTrinkets(PlayerBase player, InventoryBase inventoryBase) {
        return new GuiBaubleyTrinkets(player);
    }


}
