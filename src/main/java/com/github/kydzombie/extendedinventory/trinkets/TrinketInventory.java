package com.github.kydzombie.extendedinventory.trinkets;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;

public class TrinketInventory implements InventoryBase {
    private final ItemInstance[] inventory = new ItemInstance[4];
    @Override
    public int getInventorySize() {
        return 4;
    }

    @Override
    public ItemInstance getInventoryItem(int i) {
        return inventory[i];
    }

    public ItemInstance takeInventoryItem(int i, int j) {
        if (inventory[i] != null) {
            ItemInstance item = inventory[i];
            inventory[i] = null;
            return item;
        } else {
            return null;
        }
    }

    @Override
    public void setInventoryItem(int i, ItemInstance itemInstance) {
        inventory[i] = itemInstance;
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
}
