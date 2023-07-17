package com.github.kydzombie.extendedinventory.mixin;

import com.github.kydzombie.extendedinventory.ExtendedInventoryUtil;
import com.github.kydzombie.extendedinventory.item.Trinket;
import net.minecraft.class_556;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.item.ItemInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_556.class)
public class HandRenderMixin {
    @Shadow
    private Minecraft field_2401;

    @Shadow
    private ItemInstance field_2402;

    @Shadow
    private float field_2404;

    @Shadow
    private float field_2403;
    @Inject(method = "method_1860(F)V", at = @At(value = "TAIL"))
    private void addGauntlet(float f, CallbackInfo info) {
        if (field_2402 != null) return;

        var player = field_2401.player;
        EntityRenderer entityRenderer = EntityRenderDispatcher.INSTANCE.get(player);
        PlayerRenderer playerRenderer = (PlayerRenderer)entityRenderer;

        var trinketInventory = ExtendedInventoryUtil.getTrinketInventory(player);
        for (int i = 0; i < trinketInventory.getInventorySize(); i++) {
            var item = trinketInventory.getInventoryItem(i);
            if (item == null || !(item.getType() instanceof Trinket trinket)) continue;
            trinket.renderFirstPerson(player, playerRenderer, item, field_2404, field_2403, f, i);
        }
    }
}
