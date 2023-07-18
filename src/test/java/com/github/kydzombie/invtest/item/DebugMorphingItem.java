package com.github.kydzombie.invtest.item;

import com.github.kydzombie.extendedinventory.ExtendedInventoryUtil;
import com.github.kydzombie.extendedinventory.item.TemplateTrinket;
import com.github.kydzombie.extendedinventory.item.TrinketType;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.Arrays;

public class DebugMorphingItem extends TemplateTrinket {
    public DebugMorphingItem(Identifier identifier) {
        super(identifier, TrinketType.RING, TrinketType.GLOVE);
    }

    @Override
    public void onTrinketEquipped(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {

        var preferredType = Arrays.stream(ExtendedInventoryUtil.getAcceptedTypes(trinketSlot)).filter(trinketType -> Arrays.stream(getTrinketTypes(item)).anyMatch(otherTrinket -> otherTrinket == trinketType)).findFirst();
        if (preferredType.isPresent()) {
            System.out.println("Morphed into " + preferredType.get().name());
            item.getStationNBT().put("invtest:placedInSlotOfType", preferredType.map(Enum::ordinal).get());
        } else {
            System.out.println("Impossible morph");
        }
    }

    @Override
    public void onTrinketUnequipped(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {
        System.out.println("Demorphed");
        item.getStationNBT().put("invtest:placedInSlotOfType", -1);
    }
}
