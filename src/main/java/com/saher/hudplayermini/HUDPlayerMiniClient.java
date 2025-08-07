package com.saher.hudplayermini;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;

public class HUDPlayerMiniClient implements ClientModInitializer {

    // HUD position & size
    private static final int PADDING = 8;
    private static final int MODEL_HEIGHT = 60; // pixels above bottom
    private static final int SCALE = 28;        // model scale

    @Override
    public void onInitializeClient() {
        // 1.21.x: HudRenderCallback now passes RenderTickCounter, not float
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> onHudRender(drawContext, tickCounter));
    }

    private static void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.player == null || mc.options.hudHidden) return;

        int sw = drawContext.getScaledWindowWidth();
        int sh = drawContext.getScaledWindowHeight();

        // Bottom-right anchor
        int x = sw - PADDING - 30;
        int y = sh - PADDING - MODEL_HEIGHT;

        PlayerEntity player = mc.player;

        // 1.21.x InventoryScreen.drawEntity overload requires extra floats
        // Signature (visible in error): drawEntity(DrawContext,int,int,int,int,int,float,float,float,LivingEntity)
        int mouseX = 0;
        int mouseY = 0;
        float bodyYaw = 0f;
        float yaw = 0f;
        float pitch = 0f;

        InventoryScreen.drawEntity(drawContext, x, y, SCALE, mouseX, mouseY, bodyYaw, yaw, pitch, player);
    }
}
