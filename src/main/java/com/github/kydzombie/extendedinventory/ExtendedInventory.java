package com.github.kydzombie.extendedinventory;

import com.github.kydzombie.extendedinventory.item.*;
import com.github.kydzombie.extendedinventory.trinkets.ContainerTrinkets;
import com.github.kydzombie.extendedinventory.trinkets.TrinketInventory;
import com.github.kydzombie.extendedinventory.trinkets.TrinketPlayerHandler;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.ItemBase;
import net.modificationstation.stationapi.api.event.entity.player.PlayerEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.MessageListenerRegistryEvent;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Null;

public class ExtendedInventory {
    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    public static ItemBase DEBUG_RING;
    public static ItemBase DEBUG_NECKLACE;
    public static ItemBase DEBUG_BELT;
    public static ItemBase DEBUG_CHARM;
    public static ItemBase DEBUG_GLOVE;

    @EventListener
    public void registerTestItems(ItemRegistryEvent event) {
        System.out.println("Loading Extended Inventory items...");
        DEBUG_RING = new DebugRing(MOD_ID.id("debugRing"));
        DEBUG_NECKLACE = new DebugNecklace(MOD_ID.id("debugNecklace"));
        DEBUG_BELT = new DebugBelt(MOD_ID.id("debugBelt"));
        DEBUG_CHARM = new DebugCharm(MOD_ID.id("debugCharm"));
        DEBUG_GLOVE = new DebugGlove(MOD_ID.id("debugGlove"));
    }

    @EventListener
    public void registerPlayerHandlers(PlayerEvent.HandlerRegister event) {
        event.playerHandlers.add(new TrinketPlayerHandler(event.player));
    }

    @EventListener
    public void registerMessageListeners(MessageListenerRegistryEvent event) {
        Registry.register(event.registry, MOD_ID.id("testKey"), (player, message) -> {
            var trinketHandler = ExtendedInventoryUtil.getTrinketHandler(player);
            trinketHandler.getTrinket(DEBUG_BELT).ifPresent(trinket -> player.damage(player, 5));
        });
        Registry.register(event.registry, MOD_ID.id("openTrinketsButton"), (player, message) -> {
            GuiHelper.openGUI(
                    player,
                    ExtendedInventory.MOD_ID.id("openTrinkets"),
                    new TrinketInventory(player),
                    new ContainerTrinkets(player)
            );
        });

    }
}
