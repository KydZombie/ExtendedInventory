package com.github.kydzombie.extendedinventory.item;

import com.github.kydzombie.extendedinventory.ExtendedInventoryConfig;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;

import java.util.Arrays;

public interface Trinket {
    TrinketType[] getTrinketTypes(ItemInstance item);

    // TODO Add translations
    default String getTrinketEquipText(ItemInstance item) {
        var types = getTrinketTypes(item);
        if (Arrays.stream(types).anyMatch(type -> type == TrinketType.CHARM)) {
            return TranslationStorage.getInstance().translate("tooltip.extendedinventory:equipCharm");
        }
        int index = (int) ((System.currentTimeMillis() / ExtendedInventoryConfig.getTrinketTextUpdateTime()) % types.length);
        var slotName = types[index].name();
        return String.format(TranslationStorage.getInstance().translate("tooltip.extendedinventory:equipGeneric"), slotName);
    }

    default void tickTrinket(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {}
    default void onTrinketEquipped(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {}
    default void onTrinketUnequipped(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {}
    default void renderThirdPerson(PlayerBase player, PlayerRenderer renderer, ItemInstance item, int slot) {}
    default void renderFirstPerson(PlayerBase player, PlayerRenderer renderer, ItemInstance item, float field_2404, float field_2403, float f, int slot) {}
}
