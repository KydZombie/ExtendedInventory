package com.github.kydzombie.extendedinventory.trinkets;

import com.github.kydzombie.extendedinventory.item.Trinket;
import com.github.kydzombie.extendedinventory.item.TrinketType;
import net.minecraft.container.slot.Slot;
import net.minecraft.item.ItemInstance;

import java.util.Arrays;

public class TrinketSlot extends Slot {
    private final TrinketType[] acceptedTypes;
    public TrinketSlot(TrinketInventory inventory, TrinketType[] acceptedTypes, int slot, int x, int y) {
        super(inventory, slot, x, y);
        this.acceptedTypes = acceptedTypes;
    }
    public TrinketSlot(TrinketInventory inventory, int slot, int x, int y) {
        this(inventory, inventory.getAcceptedTypes(slot), slot, x, y);
    }

    @Override
    public boolean canInsert(ItemInstance itemInstance) {
        if (itemInstance != null && itemInstance.getType() instanceof Trinket trinket) {
            if (Arrays.stream(trinket.getTrinketTypes(itemInstance)).anyMatch(type -> type == TrinketType.CHARM)) {
                return true;
            }
            for (TrinketType acceptedType : acceptedTypes) {
                if (Arrays.stream(trinket.getTrinketTypes(itemInstance)).anyMatch(type -> type == acceptedType)) return true;
            }
        }
        return false;
    }
}
