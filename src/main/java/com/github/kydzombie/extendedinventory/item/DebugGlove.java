package com.github.kydzombie.extendedinventory.item;

import com.github.kydzombie.extendedinventory.TrinketRenderHelper;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;

public class DebugGlove extends TemplateTrinket {
    public DebugGlove(Identifier identifier) {
        super(identifier, TrinketType.GLOVE);
    }

    @Override
    public void renderFirstPerson(PlayerBase player, PlayerRenderer renderer, ItemInstance item, float field_2404, float field_2403, float f, int slot) {
        TrinketRenderHelper.renderGlove(player, renderer, "/assets/extendedinventory/stationapi/textures/misc/test_glove.png", field_2404, field_2403, f);
    }
}
