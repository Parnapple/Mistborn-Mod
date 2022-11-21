package Parnapple.mistbornmod.screen;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.block.container.MetallurgyFurnaceContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MetallurgyFurnaceScreen extends AbstractContainerScreen<MetallurgyFurnaceContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MistbornBaseMod.MOD_ID, "textures/gui/container/metallurgy_furnace.png");

    public MetallurgyFurnaceScreen(MetallurgyFurnaceContainer container, Inventory playerInv, Component title) {
        super(container, playerInv, title);
        this.leftPos =0;
        this.topPos =0;
        this.imageWidth =176;
        this.imageHeight =204;
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTicks, int pMouseX, int pMouseY) {
        renderBackground(pPoseStack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(pPoseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        if(menu.isCrafting()) {
            blit(pPoseStack, this.leftPos + 80, this.topPos + 78, 176, 14, menu.getScaledProgress(), 16);
        }

        if(menu.hasFuel()) {
            blit(pPoseStack, this.leftPos + 56, this.topPos + 93  - menu.getScaledFuelProgress(), 176,
                    14 - menu.getScaledFuelProgress(), 14, menu.getScaledFuelProgress());
        }

    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTicks);
        this.font.draw(pPoseStack, title, this.leftPos + 8, this.topPos + 5, 0x404040);
        this.font.draw(pPoseStack, playerInventoryTitle, this.leftPos + 8, this.topPos + 110, 0x404040);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {}
}
