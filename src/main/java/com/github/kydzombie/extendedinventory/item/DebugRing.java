package com.github.kydzombie.extendedinventory.item;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;

public class DebugRing extends TemplateTrinket {
    private static final byte CHARGE_TIME = 40;
    public DebugRing(Identifier identifier) {
        super(identifier, TrinketType.RING);
    }

    @Override
    public void tickTrinket(Level level, PlayerBase player, ItemInstance item, int trinketSlot) {
        byte charge = item.getStationNBT().getByte("charge");
        if (charge >= CHARGE_TIME) {
            player.addHealth(1);
            charge = 0;
        } else {
            charge++;
        }
        item.getStationNBT().put("charge", charge);
    }
}
