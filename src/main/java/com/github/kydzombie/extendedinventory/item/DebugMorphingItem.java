package com.github.kydzombie.extendedinventory.item;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;

public class DebugMorphingItem extends TemplateTrinket {
    public DebugMorphingItem(Identifier identifier) {
        super(identifier, TrinketType.RING, TrinketType.GLOVE);
    }

    @Override
    public void onTrinketEquipped(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {
        System.out.println("Morphed into " + TrinketType.values()[trinketSlot].name());
        item.getStationNBT().put("extendedinventory:placedInSlotOfType", TrinketType.values()[trinketSlot].ordinal());
    }

    @Override
    public void onTrinketUnequipped(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {
        System.out.println("Demorphed");
        item.getStationNBT().put("extendedinventory:placedInSlotOfType", 0);
    }
}
