package com.github.kydzombie.extendedinventory;

import com.github.kydzombie.extendedinventory.item.TrinketType;

public class ExtendedInventoryConfig {
    private static TrinketType[][] acceptedTypes = new TrinketType[][] {
            new TrinketType[] { TrinketType.NECK },
            new TrinketType[] { TrinketType.GLOVE },
            new TrinketType[] { TrinketType.RING },
            new TrinketType[] { TrinketType.BELT },
            new TrinketType[] { TrinketType.CHARM }
    };

    public static TrinketType[][] getAllAcceptedTypes() {
        return acceptedTypes;
    }

    private static UIStyle style = UIStyle.BAUBLEY;

    public static UIStyle getStyle() {
        return style;
    }

    private static int trinketTextUpdateTime = 1500;

    public static int getTrinketTextUpdateTime() {
        return trinketTextUpdateTime;
    }
}

