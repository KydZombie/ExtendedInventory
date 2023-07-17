package com.github.kydzombie.extendedinventory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.options.KeyBinding;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import net.modificationstation.stationapi.api.packet.Message;
import net.modificationstation.stationapi.api.packet.PacketHelper;
import org.lwjgl.input.Keyboard;

public class ExtendedInventoryClient {
    public static KeyBinding testKeyBind;

    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerKeyBindings(KeyBindingRegisterEvent event) {
        var list = event.keyBindings;
        list.add(testKeyBind = new KeyBinding("key.extendedinventory.test", Keyboard.KEY_B));
    }

    @Environment(EnvType.CLIENT)
    @EventListener
    public void keyPressed(KeyStateChangedEvent event) {
        if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(testKeyBind.key)) {
            PacketHelper.send(new Message(ExtendedInventory.MOD_ID.id("testKey")));
        }
    }
}
