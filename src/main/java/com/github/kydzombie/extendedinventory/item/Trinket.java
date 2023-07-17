package com.github.kydzombie.extendedinventory.item;

import net.minecraft.client.render.entity.PlayerRenderer;
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
    default void renderThirdPerson(PlayerBase player, PlayerRenderer renderer, ItemInstance item, int slot) {}
    default void renderFirstPerson(PlayerBase player, PlayerRenderer renderer, ItemInstance item, float field_2404, float field_2403, float f, int slot) {}
}
