package com.github.kydzombie.invtest;

import com.github.kydzombie.extendedinventory.item.TrinketType;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.options.KeyBinding;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import net.modificationstation.stationapi.api.client.event.render.model.ItemModelPredicateProviderRegistryEvent;
import net.modificationstation.stationapi.api.packet.Message;
import net.modificationstation.stationapi.api.packet.PacketHelper;
import org.lwjgl.input.Keyboard;

public class InvTestClient {
    public static KeyBinding testKeyBind;
    @EventListener
    public void registerKeyBindings(KeyBindingRegisterEvent event) {
        var list = event.keyBindings;
        list.add(testKeyBind = new KeyBinding("key.invtest.belt_damage", Keyboard.KEY_B));
    }

    @EventListener
    public void keyPressed(KeyStateChangedEvent event) {
        if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(testKeyBind.key)) {
            PacketHelper.send(new Message(InvTest.MOD_ID.id("testKey")));
        }
    }

    @EventListener
    public void registerItemModelPredicates(ItemModelPredicateProviderRegistryEvent event) {
        event.registry.register(InvTest.DEBUG_MORPHING_ITEM, InvTest.MOD_ID.id("currentSlot"),
                ((stack, world, entity, seed) -> {
                    int value = stack.getStationNBT().getInt("invtest:placedInSlotOfType");
                    if (value == TrinketType.RING.ordinal()) {
                        return 0.5f;
                    } else if (value == TrinketType.GLOVE.ordinal()) {
                        return 1f;
                    } else {
                        return 0;
                    }
                }
                ));
    }
}
