package com.github.kydzombie.extendedinventory.trinkets;

import com.github.kydzombie.extendedinventory.ExtendedInventoryUtil;
import net.minecraft.container.ContainerBase;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerBase;

public class ContainerTrinkets extends ContainerBase {
    private static final int SLOT_START_X = 80;
    private static final int SLOT_START_Y = 8;
    private static final int MAX_SLOTS_Y = 4;

    public ContainerTrinkets(PlayerBase player) {
        // Player Inventory
        for(int row = 0; row < 3; ++row) {
            for(int column = 0; column < 9; ++column) {
                addSlot(new Slot(player.inventory, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }
        for(int i = 0; i < 9; ++i) {
            addSlot(new Slot(player.inventory, i, 8 + i * 18, 142));
        }

        TrinketInventory trinketInventory = ExtendedInventoryUtil.getTrinketInventory(player);

        // Trinket Inventory
        var slotX = 0;
        var slotY = 0;
        for (int i = 0; i < ExtendedInventoryUtil.getSlotCount(); i++) {
            addSlot(new TrinketSlot(trinketInventory, i, SLOT_START_X + (slotX * 18), SLOT_START_Y + (slotY * 18)));
            slotY++;
            if (slotY >= MAX_SLOTS_Y) {
                slotX++;
                slotY = 0;
            }
        }
    }

    @Override
    public boolean canUse(PlayerBase arg) {
        return true;
    }
}
