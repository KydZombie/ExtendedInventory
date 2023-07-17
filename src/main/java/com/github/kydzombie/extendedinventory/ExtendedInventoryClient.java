package com.github.kydzombie.extendedinventory;

import com.github.kydzombie.extendedinventory.trinkets.GuiBaubleyTrinkets;
import com.github.kydzombie.extendedinventory.trinkets.TrinketInventory;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.packet.Message;
import net.modificationstation.stationapi.api.packet.PacketHelper;
import org.lwjgl.input.Keyboard;
import uk.co.benjiweber.expressions.tuple.BiTuple;

public class ExtendedInventoryClient {
    public static KeyBinding testKeyBind;

    @EventListener
    public void registerGuiHandlers(GuiHandlerRegistryEvent event) {
        event.registry.registerValueNoMessage(ExtendedInventory.MOD_ID.id("openTrinkets"), BiTuple.of(this::openTrinkets, TrinketInventory::new));
    }

    public ScreenBase openTrinkets(PlayerBase player, InventoryBase inventoryBase) {
        return new GuiBaubleyTrinkets(player);
    }

    @EventListener
    public void registerKeyBindings(KeyBindingRegisterEvent event) {
        var list = event.keyBindings;
        list.add(testKeyBind = new KeyBinding("key.extendedinventory.test", Keyboard.KEY_B));
    }

    @EventListener
    public void keyPressed(KeyStateChangedEvent event) {
        if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(testKeyBind.key)) {
            PacketHelper.send(new Message(ExtendedInventory.MOD_ID.id("testKey")));
        }
    }
}
