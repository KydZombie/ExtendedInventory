package com.github.kydzombie.extendedinventory.mixin;

import com.github.kydzombie.extendedinventory.ExtendedInventoryUtil;
import com.github.kydzombie.extendedinventory.item.Trinket;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
    @Unique
    final PlayerRenderer renderer = (PlayerRenderer) (Object) this;
    @Inject(method = "render(Lnet/minecraft/entity/EntityBase;DDDFF)V", at = @At(value = "TAIL"))
    private void renderTrinkets(EntityBase entity, double x, double y, double z, float h, float v, CallbackInfo ci) {
        final var pkgedData = new Object[]{ x, y, z, h, v};
        if (!(entity instanceof PlayerBase player)) return;

        var trinketInventory = ExtendedInventoryUtil.getTrinketInventory(player);
        for (int i = 0; i < trinketInventory.getInventorySize(); i++) {
            var item = trinketInventory.getInventoryItem(i);
            if (item == null || !(item.getType() instanceof Trinket trinket)) continue;
            trinket.renderThirdPerson(player, renderer, item, i);
        }
    }
}
