package com.github.kydzombie.extendedinventory.item;

import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;

public interface HasPlayerRenderer {
    void renderWhileEquipped(PlayerBase player, PlayerRenderer renderer, ItemInstance item, int slot);
}
