package com.github.kydzombie.invtest.item;

import com.github.kydzombie.extendedinventory.item.TemplateTrinket;
import com.github.kydzombie.extendedinventory.item.TrinketType;
import net.modificationstation.stationapi.api.registry.Identifier;

public class DebugBelt extends TemplateTrinket {
    public DebugBelt(Identifier identifier) {
        super(identifier, TrinketType.BELT);
    }
}
