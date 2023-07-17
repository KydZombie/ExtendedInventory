package com.github.kydzombie.extendedinventory;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.util.maths.MathHelper;
import org.lwjgl.opengl.GL11;

public class TrinketRenderHelper {
    public static void renderGlove(PlayerBase player, PlayerRenderer renderer, String texture, float field_2404, float field_2403, float f) {
        Minecraft minecraft = ((Minecraft) FabricLoader.getInstance().getGameInstance());

        float f5 = field_2404 + (field_2403 - field_2404) * f;

        GL11.glPushMatrix();
        float f13 = 0.8f;
        float f4 = player.method_930(f);
        float f3 = MathHelper.sin(f4 * (float)Math.PI);
        float f2 = MathHelper.sin(MathHelper.sqrt(f4) * (float)Math.PI);
        GL11.glTranslatef(-f2 * 0.3f, MathHelper.sin(MathHelper.sqrt(f4) * (float)Math.PI * 2.0f) * 0.4f, -f3 * 0.4f);
        GL11.glTranslatef(0.8f * f13, -0.75f * f13 - (1.0f - f5) * 0.6f, -0.9f * f13);
        GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
        GL11.glEnable(32826);
        f4 = player.method_930(f);
        f3 = MathHelper.sin(f4 * f4 * (float)Math.PI);
        f2 = MathHelper.sin(MathHelper.sqrt(f4) * (float)Math.PI);
        GL11.glRotatef(f2 * 70.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-f3 * 20.0f, 0.0f, 0.0f, 1.0f);
        var textureId = minecraft.textureManager.getTextureId(texture);
        minecraft.textureManager.bindTexture(textureId);
        GL11.glTranslatef(-1.0f, 3.6f, 3.5f);
        GL11.glRotatef(120.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(200.0f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(-135.0f, 0.0f, 1.0f, 0.0f);
        GL11.glScalef(1.0f, 1.0f, 1.0f);
        GL11.glTranslatef(5.6f, 0.0f, 0.0f);
        f2 = 1.0f;
        GL11.glScalef(f2, f2, f2);
        renderer.method_345();
        GL11.glPopMatrix();
    }
}
