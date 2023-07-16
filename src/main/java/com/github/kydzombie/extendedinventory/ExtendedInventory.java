package com.github.kydzombie.extendedinventory;

import com.github.kydzombie.extendedinventory.item.DebugNecklace;
import com.github.kydzombie.extendedinventory.item.DebugRing;
import com.github.kydzombie.extendedinventory.trinkets.GuiTrinkets;
import com.github.kydzombie.extendedinventory.trinkets.TrinketPlayerHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemBase;
import net.modificationstation.stationapi.api.event.entity.player.PlayerEvent;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;
import uk.co.benjiweber.expressions.tuple.BiTuple;

public class ExtendedInventory {
    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    public static ItemBase DEBUG_RING;
    public static ItemBase DEBUG_NECKLACE;

    @EventListener
    public void registerTestItems(ItemRegistryEvent event) {
        System.out.println("Loading Extended Inventory items...");
        DEBUG_RING = new DebugRing(MOD_ID.id("debugRing"));
        DEBUG_NECKLACE = new DebugNecklace(MOD_ID.id("debugNecklace"));
    }

    @EventListener
    public void registerPlayerHandlers(PlayerEvent.HandlerRegister event) {
        event.playerHandlers.add(new TrinketPlayerHandler());
    }

    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerGuiHandlers(GuiHandlerRegistryEvent event) {
        event.registry.registerValueNoMessage(MOD_ID.id("openTrinkets"), BiTuple.of(this::openTrinkets, null));
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openTrinkets(PlayerBase player, InventoryBase inventoryBase) {
        return new GuiTrinkets(player);
    }
}
