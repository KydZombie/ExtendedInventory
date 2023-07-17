package com.github.kydzombie.extendedinventory;

import com.github.kydzombie.extendedinventory.item.TrinketType;
import com.github.kydzombie.extendedinventory.trinkets.TrinketInventory;
import com.github.kydzombie.extendedinventory.trinkets.TrinketPlayerHandler;
import net.minecraft.entity.player.PlayerBase;
import net.modificationstation.stationapi.api.entity.player.PlayerHandlerContainer;

public class ExtendedInventoryUtil {
    public static TrinketPlayerHandler getTrinketHandler(PlayerBase player) {
        return ((TrinketPlayerHandler) ((PlayerHandlerContainer) player).getPlayerHandlers().stream().filter(item -> item instanceof TrinketPlayerHandler).findFirst().orElseThrow());
    }

    public static TrinketInventory getTrinketInventory(PlayerBase player) {
        return getTrinketHandler(player).inventory;
    }

    public static TrinketType[] getAcceptedTypes(int slot) {
        return ExtendedInventoryConfig.getAllAcceptedTypes()[slot];
    }

    public static int getSlotCount() {
        return ExtendedInventoryConfig.getAllAcceptedTypes().length;
    }

    public static TrinketType getSlotPrimaryType(int i) {
        return getAcceptedTypes(i)[0];
    }
}
