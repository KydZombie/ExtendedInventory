package com.github.kydzombie.extendedinventory.trinkets;

import com.github.kydzombie.extendedinventory.ExtendedInventoryConfig;
import com.github.kydzombie.extendedinventory.item.TrinketType;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.client.gui.screen.container.PlayerInventory;
import net.minecraft.client.render.RenderHelper;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.player.PlayerBase;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Unique;

public class GuiBaubleyTrinkets extends ContainerBase {
    private float mouseX;
    private float mouseY;

    private static final int UNSELECTED_X = 176;
    private static final int SELECTED_X = UNSELECTED_X + 16;

    private static final int SLOT_UV_Y = 166;
    private static final int SLOT_START_X = 79;
    private static final int SLOT_START_Y = 7;
    private static final int MAX_SLOTS_Y = 4;

    public GuiBaubleyTrinkets(PlayerBase player) {
        super(new ContainerTrinkets(player));
    }

    public void render(int i, int j, float f) {
        super.render(i, j, f);
        this.mouseX = (float)i;
        this.mouseY = (float)j;
    }

    @Override
    protected void renderContainerBackground(float f) {
        int textureId = this.minecraft.textureManager.getTextureId("/assets/extendedinventory/stationapi/gui/trinket_ui.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(textureId);
        int x = (this.width - this.containerWidth) / 2;
        int y = (this.height - this.containerHeight) / 2;
        this.blit(x, y, 0, 0, this.containerWidth, this.containerHeight);

        // Render button
        int buttonX = x + 62;
        int buttonY = y + 8;
        blit(buttonX, buttonY, isHovering(buttonX, buttonY) ? SELECTED_X : UNSELECTED_X, 0, 16, 16);
        // Stop render button

        // Render slots
        var slotX = 0;
        var slotY = 0;
        for (int i = 0; i < ExtendedInventoryConfig.getSlotCount(); i++) {
            var primaryType = ExtendedInventoryConfig.getAcceptedTypes(i)[0];
            var textureXOffset = primaryType.ordinal() * 18;

            blit(x + SLOT_START_X + (slotX * 18), y + SLOT_START_Y + (slotY * 18), textureXOffset, SLOT_UV_Y, 18, 18);
            slotY++;
            if (slotY >= MAX_SLOTS_Y) {
                slotX++;
                slotY = 0;
            }
        }
        // Stop render slots

        GL11.glEnable(32826);
        GL11.glEnable(2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(x + 51), (float)(y + 75), 50.0F);
        float var5 = 30.0F;
        GL11.glScalef(-var5, var5, var5);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float var6 = minecraft.player.field_1012;
        float var7 = minecraft.player.yaw;
        float var8 = minecraft.player.pitch;
        float var9 = (float)(x + 51) - mouseX;
        float var10 = (float)(y + 75 - 50) - mouseY;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan(var10 / 40.0F)) * 20.0F, 1.0F, 0.0F, 0.0F);
        minecraft.player.field_1012 = (float)Math.atan(var9 / 40.0F) * 20.0F;
        minecraft.player.yaw = (float)Math.atan(var9 / 40.0F) * 40.0F;
        minecraft.player.pitch = -((float)Math.atan(var10 / 40.0F)) * 20.0F;
        minecraft.player.field_1617 = 1.0F;
        GL11.glTranslatef(0.0F, minecraft.player.standingEyeHeight, 0.0F);
        EntityRenderDispatcher.INSTANCE.field_2497 = 180.0F;
        EntityRenderDispatcher.INSTANCE.method_1920(minecraft.player, 0.0, 0.0, 0.0, 0.0F, 1.0F);
        minecraft.player.field_1617 = 0.0F;
        minecraft.player.field_1012 = var6;
        minecraft.player.yaw = var7;
        minecraft.player.pitch = var8;
        GL11.glPopMatrix();
        RenderHelper.disableLighting();
        GL11.glDisable(32826);
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
            minecraft.openScreen(new PlayerInventory(minecraft.player));
        } else {
            super.mouseClicked(i, j, k);
        }
    }
}
