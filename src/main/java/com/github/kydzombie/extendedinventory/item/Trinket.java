package com.github.kydzombie.extendedinventory.item;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;

public interface Trinket {
    TrinketType[] getTrinketTypes(ItemInstance item);

    // TODO Add translations
    default String getTrinketEquipText(ItemInstance item) {
        return "Equip in the " + getTrinketTypes(item)[0].toString() + " slot.";
    }

    default void tickTrinket(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {}
    default void onTrinketEquipped(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {}
    default void onTrinketUnequipped(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {}
}
