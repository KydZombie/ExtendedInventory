package com.github.kydzombie.invtest.item;

import com.github.kydzombie.extendedinventory.item.TemplateTrinket;
import com.github.kydzombie.extendedinventory.item.TrinketType;
import net.modificationstation.stationapi.api.registry.Identifier;

public class DebugCharm extends TemplateTrinket {
    public DebugCharm(Identifier identifier) {
        super(identifier, TrinketType.CHARM);
    }
}
