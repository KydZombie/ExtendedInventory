package com.github.kydzombie.extendedinventory.mixin;

import com.github.kydzombie.extendedinventory.trinkets.TrinketInventory;
import com.github.kydzombie.extendedinventory.trinkets.TrinketPlayerHandler;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.modificationstation.stationapi.api.entity.player.PlayerHandlerContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin {
    @Shadow public PlayerBase player;

    @Inject(
            method = "tickInventory()V",
            at = @At("HEAD")
    )
    private void tickTrinkets(CallbackInfo ci) {
        TrinketInventory trinketInventory = ((TrinketPlayerHandler) ((PlayerHandlerContainer) player).getPlayerHandlers().stream().filter(item -> item instanceof TrinketPlayerHandler).toArray()[0]).inventory;
        trinketInventory.tickTrinkets(player);
    }
}
