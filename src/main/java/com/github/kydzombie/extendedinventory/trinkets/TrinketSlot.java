package com.github.kydzombie.extendedinventory.trinkets;

import com.github.kydzombie.extendedinventory.item.Trinket;
import com.github.kydzombie.extendedinventory.item.TrinketType;
import net.minecraft.container.slot.Slot;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;

public class TrinketSlot extends Slot {
    private final TrinketType[] acceptedTypes;
    public TrinketSlot(InventoryBase inventory, TrinketType[] acceptedTypes, int i, int x, int y) {
        super(inventory, i, x, y);
        this.acceptedTypes = acceptedTypes;
    }
    public TrinketSlot(InventoryBase inventory, TrinketType acceptedType, int i, int x, int y) {
        this(inventory, new TrinketType[]{acceptedType}, i, x, y);
    }

    @Override
    public boolean canInsert(ItemInstance itemInstance) {
        if (itemInstance != null && itemInstance.getType() instanceof Trinket trinket) {
            for (TrinketType acceptedType : acceptedTypes) {
                if (acceptedType == trinket.getTrinketType()) return true;
            }
        }
        return false;
    }
}
