package com.github.kydzombie.invtest.item;

import com.github.kydzombie.extendedinventory.item.TemplateTrinket;
import com.github.kydzombie.extendedinventory.item.TrinketType;
import net.modificationstation.stationapi.api.registry.Identifier;

public class DebugNecklace extends TemplateTrinket {
    public DebugNecklace(Identifier identifier) {
        super(identifier, TrinketType.NECK);
    }
}
