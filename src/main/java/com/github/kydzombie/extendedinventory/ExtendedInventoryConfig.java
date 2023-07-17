package com.github.kydzombie.extendedinventory;

import com.github.kydzombie.extendedinventory.item.TrinketType;

public class ExtendedInventoryConfig {
    private static TrinketType[][] acceptedTypes = new TrinketType[][] {
            new TrinketType[] { TrinketType.NECKLACE },
            new TrinketType[] { TrinketType.RING },
            new TrinketType[] { TrinketType.RING },
            new TrinketType[] { TrinketType.BELT, TrinketType.NECKLACE }
    };

    public static TrinketType[] getAcceptedTypes(int slot) {
        return acceptedTypes[slot];
    }
}

