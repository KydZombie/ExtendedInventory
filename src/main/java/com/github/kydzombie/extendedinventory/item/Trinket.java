package com.github.kydzombie.extendedinventory.item;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;

public interface Trinket {
    TrinketType getTrinketType(ItemInstance item);

    /*
        Used only if your item can fit in multiple slots.
        If this is the case, then getTrinketType() returns the preferred slot to place the trinket in.
    */
    default boolean hasMultipleTrinketTypes(ItemInstance item) { return false; }
    default TrinketType[] getTrinketTypes(ItemInstance item) { return new TrinketType[] { getTrinketType(item) }; }

    // TODO Add translations
    default String getTrinketEquipText(ItemInstance item) {
        return "Equip in the " + getTrinketType(item).toString() + " slot.";
    }

    default void tickTrinket(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {}
    default void onTrinketEquipped(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {}
    default void onTrinketUnequipped(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {}
}
