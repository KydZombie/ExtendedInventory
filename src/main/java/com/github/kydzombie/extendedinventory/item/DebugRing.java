package com.github.kydzombie.extendedinventory.item;

import net.modificationstation.stationapi.api.registry.Identifier;

public class DebugRing extends TemplateTrinket {
    public DebugRing(Identifier identifier) {
        super(identifier, TrinketType.RING);
        setMaxStackSize(1);
        setTranslationKey(identifier.toString());
    }
}
