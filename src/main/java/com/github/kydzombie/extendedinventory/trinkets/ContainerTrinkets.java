package com.github.kydzombie.extendedinventory.trinkets;

import com.github.kydzombie.extendedinventory.item.TrinketType;
import net.minecraft.container.ContainerBase;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerBase;
import net.modificationstation.stationapi.api.entity.player.PlayerHandlerContainer;

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

        TrinketInventory trinketInventory = ((TrinketPlayerHandler) ((PlayerHandlerContainer) player).getPlayerHandlers().stream().filter(item -> item instanceof TrinketPlayerHandler).toArray()[0]).inventory;

        // Trinket Inventory
        addSlot(new TrinketSlot(trinketInventory, TrinketType.NECKLACE, 0, 80, 8));
        addSlot(new TrinketSlot(trinketInventory, TrinketType.RING, 1, 80, 8 + 18));
        addSlot(new TrinketSlot(trinketInventory, TrinketType.RING, 2, 80, 8 + 18 + 18));
        addSlot(new TrinketSlot(trinketInventory, TrinketType.BELT, 3, 80, 8 + 18 + 18 + 18));
    }
    @Override
    public boolean canUse(PlayerBase arg) {
        return true;
    }
}
