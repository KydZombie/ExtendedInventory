package com.github.kydzombie.extendedinventory.mixin;

import com.github.kydzombie.extendedinventory.ExtendedInventory;
import com.github.kydzombie.extendedinventory.trinkets.ContainerTrinkets;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.client.gui.screen.container.PlayerInventory;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.packet.Message;
import net.modificationstation.stationapi.api.packet.PacketHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public abstract class InventoryRenderMixin extends ContainerBase {
    @Shadow private float mouseX;

    @Shadow private float mouseY;

    @Unique
    private static final int UNSELECTED_X = 176;
    @Unique
    private static final int SELECTED_X = UNSELECTED_X + 16;

    public InventoryRenderMixin(net.minecraft.container.ContainerBase arg) {
        super(arg);
    }

    @Inject(
            method = "renderContainerBackground",
            at = @At(
                    value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/container/PlayerInventory;blit(IIIIII)V",
                    ordinal = 0,
                    shift = At.Shift.BY,
                    by = 1
            )
    )
    private void addButton(float f, CallbackInfo ci) {
        int textureId = minecraft.textureManager.getTextureId("/assets/extendedinventory/stationapi/gui/trinket_ui.png");
        minecraft.textureManager.bindTexture(textureId);
        int x = ((width - containerWidth) / 2) + 62;
        int y = ((height - containerHeight) / 2) + 8;
        blit(x, y, isHovering(x, y) ? SELECTED_X : UNSELECTED_X, 0, 16, 16);
    }

    @Unique
    private boolean isHovering(int x, int y) {
        return mouseX > x && mouseX < x + 16 && mouseY > y && mouseY < y + 16;
    }

    @Override
    protected void mouseClicked(int i, int j, int k) {
        int x = ((width - containerWidth) / 2) + 62;
        int y = ((height - containerHeight) / 2) + 8;
        if (isHovering(x, y)) {
            PacketHelper.send(new Message(ExtendedInventory.MOD_ID.id("openTrinketsButton")));
        } else {
            super.mouseClicked(i, j, k);
        }
    }
}
