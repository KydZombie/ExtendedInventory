package com.github.kydzombie.invtest;

import com.github.kydzombie.extendedinventory.ExtendedInventoryUtil;
import com.github.kydzombie.invtest.item.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.ItemBase;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.MessageListenerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Null;

public class InvTest {
    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    public static ItemBase DEBUG_RING;
    public static ItemBase DEBUG_NECKLACE;
    public static ItemBase DEBUG_BELT;
    public static ItemBase DEBUG_CHARM;
    public static ItemBase DEBUG_GLOVE;
    public static ItemBase DEBUG_MORPHING_ITEM;

    @EventListener
    public void registerTestItems(ItemRegistryEvent event) {
        System.out.println("Mod id is " + MOD_ID);
        System.out.println("Loading Extended Inventory items...");
        DEBUG_RING = new DebugRing(MOD_ID.id("debugRing"));
        DEBUG_NECKLACE = new DebugNecklace(MOD_ID.id("debugNecklace"));
        DEBUG_BELT = new DebugBelt(MOD_ID.id("debugBelt"));
        DEBUG_CHARM = new DebugCharm(MOD_ID.id("debugCharm"));
        DEBUG_GLOVE = new DebugGlove(MOD_ID.id("debugGlove"));
        DEBUG_MORPHING_ITEM = new DebugMorphingItem(MOD_ID.id("debugMorphingItem"));
    }

    @EventListener
    public void registerMessageListeners(MessageListenerRegistryEvent event) {
        Registry.register(event.registry, MOD_ID.id("testKey"), (player, message) -> {
            var trinketHandler = ExtendedInventoryUtil.getTrinketHandler(player);
            trinketHandler.getTrinket(DEBUG_BELT).ifPresent(trinket -> player.damage(player, 5));
        });
    }
}
