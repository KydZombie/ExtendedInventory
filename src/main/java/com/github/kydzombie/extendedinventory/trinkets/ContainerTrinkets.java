package com.github.kydzombie.extendedinventory.trinkets;

import com.github.kydzombie.extendedinventory.ExtendedInventoryUtil;
import net.minecraft.container.ContainerBase;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerBase;

public class ContainerTrinkets extends ContainerBase {
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
        addSlot(new TrinketSlot(trinketInventory, 0, 80, 8));
        addSlot(new TrinketSlot(trinketInventory, 1, 80, 8 + 18));
        addSlot(new TrinketSlot(trinketInventory, 2, 80, 8 + 18 + 18));
        addSlot(new TrinketSlot(trinketInventory, 3, 80, 8 + 18 + 18 + 18));
    }
    @Override
    public boolean canUse(PlayerBase arg) {
        return true;
    }
}
