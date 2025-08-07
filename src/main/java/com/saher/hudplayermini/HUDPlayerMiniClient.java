package com.saher.hudplayermini;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerEntity;

public class HUDPlayerMiniClient implements ClientModInitializer {

    // Position & size
    private static final int PADDING = 8;
    private static final int MODEL_HEIGHT = 60; // pixel anchor Y from bottom
    private static final int SCALE = 28;        // model scale

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(HUDPlayerMiniClient::onHudRender);
    }

    private static void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.player == null || mc.options.hudHidden) return;

        int sw = drawContext.getScaledWindowWidth();
        int sh = drawContext.getScaledWindowHeight();

        // Bottom-right corner anchor
        int x = sw - PADDING - 30; // shift left so it doesn't get cut off
        int y = sh - PADDING - MODEL_HEIGHT;

        PlayerEntity player = mc.player;

        // Make the model face slightly towards the screen, tiny idle spin
        float mouseX = 10f;
        float mouseY = -10f;

        // Render the live player entity in HUD using InventoryScreen helper
        // This draws the actual 3D model including armor/items.
        InventoryScreen.drawEntity(drawContext, x, y, SCALE, mouseX, mouseY, player);
    }
}