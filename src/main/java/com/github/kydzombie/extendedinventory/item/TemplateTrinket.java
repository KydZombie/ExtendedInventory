package com.github.kydzombie.extendedinventory.item;

import net.minecraft.item.ItemInstance;
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
    public TrinketType getTrinketType() {
        return type;
    }

    @Override
    public String[] getTooltip(ItemInstance itemInstance, String originalTooltip) {
        return new String[] {
                originalTooltip,
                "Equip in the " + type.toString() + " slot."
        };
    }
}
