package com.github.kydzombie.extendedinventory.mixin;

import com.github.kydzombie.extendedinventory.ExtendedInventoryUtil;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerBase.class)
public abstract class PlayerBaseMixin {

    @Shadow public abstract void dropItem(ItemInstance arg, boolean bl);

    @Inject(method = "onKilledBy(Lnet/minecraft/entity/EntityBase;)V", at = @At("HEAD"))
    private void dropTrinkets(EntityBase par1, CallbackInfo ci) {
        var inventory = ExtendedInventoryUtil.getTrinketInventory((PlayerBase) (Object) this);
        for (int i = 0; i < inventory.getInventorySize(); i++) {
            if (inventory.getInventoryItem(i) == null) continue;
            this.dropItem(inventory.getInventoryItem(i), true);
        }
    }

}
