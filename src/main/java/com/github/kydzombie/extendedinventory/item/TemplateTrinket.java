package com.github.kydzombie.extendedinventory.item;

import com.github.kydzombie.extendedinventory.ExtendedInventoryUtil;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.client.gui.CustomTooltipProvider;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class TemplateTrinket extends TemplateItemBase implements Trinket, CustomTooltipProvider {
    TrinketType type;
    public TemplateTrinket(Identifier identifier, TrinketType trinketType) {
        super(identifier);
        setMaxStackSize(1);
        setTranslationKey(identifier.toString());

        type = trinketType;
    }

    @Override
    public TrinketType getTrinketType(ItemInstance item) {
        return type;
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
