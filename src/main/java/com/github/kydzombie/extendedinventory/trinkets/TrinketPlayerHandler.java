package com.github.kydzombie.extendedinventory.trinkets;

import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;
import net.modificationstation.stationapi.api.entity.player.PlayerHandler;

public class TrinketPlayerHandler implements PlayerHandler {
    public TrinketInventory inventory = new TrinketInventory();

    @Override
    public boolean readEntityBaseFromNBT(CompoundTag tag) {
        ListTag var2 = tag.getListTag("Items");
        for (int i = 0; i < var2.size(); ++i) {
            CompoundTag itemTag = (CompoundTag) var2.get(i);
            byte rawItemData = itemTag.getByte("Slot");
            if (rawItemData >= 0 && rawItemData < inventory.getInventorySize()) {
                inventory.setInventoryItem(rawItemData, new ItemInstance(itemTag));
            }
        }
        return false;
    }

    @Override
    public boolean writeEntityBaseToNBT(CompoundTag tag) {
        ListTag var2 = new ListTag();
        for (int i = 0; i < inventory.getInventorySize(); ++i) {
            if (inventory.getInventoryItem(i) != null) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.put("Slot", (byte) i);
                inventory.getInventoryItem(i).toTag(itemTag);
                var2.add(itemTag);
            }
        }

        tag.put("Items", var2);
        return false;
    }
}
