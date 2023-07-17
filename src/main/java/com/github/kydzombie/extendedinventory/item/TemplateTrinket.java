package com.github.kydzombie.extendedinventory.item;

import com.github.kydzombie.extendedinventory.ExtendedInventoryUtil;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.client.gui.CustomTooltipProvider;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class TemplateTrinket extends TemplateItemBase implements Trinket, CustomTooltipProvider {
    TrinketType[] types;

    public TemplateTrinket(Identifier identifier, TrinketType... trinketTypes) {
        super(identifier);
        setMaxStackSize(1);
        setTranslationKey(identifier.toString());

        types = trinketTypes;
    }

    @Override
    public boolean hasMultipleTrinketTypes(ItemInstance item) {
        return types.length > 1;
    }

    @Override
    public TrinketType[] getTrinketTypes(ItemInstance item) {
        return Trinket.super.getTrinketTypes(item);
    }

    @Override
    public TrinketType getTrinketType(ItemInstance item) {
        return types[0];
    }

    @Override
    public ItemInstance use(ItemInstance itemInstance, Level level, PlayerBase player) {
        if (ExtendedInventoryUtil.getTrinketInventory(player).attemptInsert(itemInstance)) {
            itemInstance.count--;
            return itemInstance;
        }
        return super.use(itemInstance, level, player);
    }

    @Override
    public String[] getTooltip(ItemInstance item, String originalTooltip) {
        return new String[] {
                originalTooltip,
                getTrinketEquipText(item)
        };
    }
}
