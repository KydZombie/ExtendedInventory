package com.github.kydzombie.extendedinventory.item;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;

public class DebugBelt extends TemplateTrinket {
    public DebugBelt(Identifier identifier) {
        super(identifier, TrinketType.BELT);
    }

    @Override
    public void onTrinketEquipped(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {
        System.out.println("Shoulder bag has been equipped.");
        super.onTrinketEquipped(level, player, item, trinketSlot);
    }

    @Override
    public void onTrinketUnequipped(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {
        System.out.println("Shoulder bag has been unequipped.");
        super.onTrinketUnequipped(level, player, item, trinketSlot);
    }
}
