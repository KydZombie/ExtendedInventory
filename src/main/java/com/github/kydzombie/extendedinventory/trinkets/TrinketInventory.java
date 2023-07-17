package com.github.kydzombie.extendedinventory.trinkets;

import com.github.kydzombie.extendedinventory.ExtendedInventoryConfig;
import com.github.kydzombie.extendedinventory.item.Trinket;
import com.github.kydzombie.extendedinventory.item.TrinketType;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;

import java.util.Arrays;

public class TrinketInventory implements InventoryBase {
    private final ItemInstance[] inventory = new ItemInstance[4];

    @Override
    public int getInventorySize() {
        return 4;
    }

    @Override
    public ItemInstance getInventoryItem(int slot) {
        return inventory[slot];
    }

    public ItemInstance takeInventoryItem(int slot, int count) {
        if (inventory[slot] != null) {
            ItemInstance item = inventory[slot];
            inventory[slot] = null;
            return item;
        } else {
            return null;
        }
    }

    @Override
    public void setInventoryItem(int slot, ItemInstance itemInstance) {
        inventory[slot] = itemInstance;
    }

    @Override
    public String getContainerName() {
        return "Trinkets";
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean canPlayerUse(PlayerBase arg) {
        return true;
    }

    public TrinketType[] getAcceptedTypes(int slot) {
        return ExtendedInventoryConfig.getAcceptedTypes(slot);
    }

    public boolean attemptInsert(ItemInstance item) {
        if (item == null) return false;
        if (item.getType() instanceof Trinket trinket) {
            var type = trinket.getTrinketType(item);
            for (int i = 0; i < getInventorySize(); i++) {
                if (inventory[i] != null) continue;
                if (Arrays.stream(getAcceptedTypes(i)).toList().contains(type)) {
                    setInventoryItem(i, item.copy());
                    return true;
                }
            }
        }
        return false;
    }

    public void tickTrinkets(PlayerBase player) {
        for (int i = 0; i < inventory.length; ++i) {
            if (inventory[i] == null) continue;
            ((Trinket) inventory[i].getType()).tickTrinket(player.level, player, inventory[i], i);
        }
    }
}
