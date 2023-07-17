package com.github.kydzombie.extendedinventory;

import com.github.kydzombie.extendedinventory.trinkets.ContainerTrinkets;
import com.github.kydzombie.extendedinventory.trinkets.TrinketInventory;
import com.github.kydzombie.extendedinventory.trinkets.TrinketPlayerHandler;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.entity.player.PlayerEvent;
import net.modificationstation.stationapi.api.event.registry.MessageListenerRegistryEvent;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Null;

public class ExtendedInventory {
    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerPlayerHandlers(PlayerEvent.HandlerRegister event) {
        event.playerHandlers.add(new TrinketPlayerHandler(event.player));
    }

    @EventListener
    public void registerMessageListeners(MessageListenerRegistryEvent event) {
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
