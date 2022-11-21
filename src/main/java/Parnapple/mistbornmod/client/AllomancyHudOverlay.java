package Parnapple.mistbornmod.client;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.util.Metal;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.IIngameOverlay;

public class AllomancyHudOverlay {
    private static final ResourceLocation BURNING = new ResourceLocation(MistbornBaseMod.MOD_ID,
            "textures/allomancy/burning.png");
    private static final ResourceLocation ELECTRUM_COMPASS = new ResourceLocation(MistbornBaseMod.MOD_ID,
            "textures/gui/electrum_compass.png");
    private static final ResourceLocation GOLD_COMPASS = new ResourceLocation(MistbornBaseMod.MOD_ID,
            "textures/gui/gold_compass.png");

    public static final IIngameOverlay ALLOMANCY_HUD = ((gui, poseStack, partialTick, width, height) -> {
        int x = width/2;
        int y = height;

        if(ClientAllomancyData.isBurning(Metal.GOLD)) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, GOLD_COMPASS);

            int yOffset = 0;
            int xOffset = 0;

            if(ClientAllomancyData.getGoldCompassPos() % 2 == 0)
                yOffset = 16;

            if(ClientAllomancyData.getGoldCompassPos() == 3 || ClientAllomancyData.getGoldCompassPos() == 4)
                xOffset = 16;
            if(ClientAllomancyData.getGoldCompassPos() == 6 || ClientAllomancyData.getGoldCompassPos() == 5)
                xOffset = 32;
            if(ClientAllomancyData.getGoldCompassPos() == 8 || ClientAllomancyData.getGoldCompassPos() == 7)
                xOffset = 48;

            GuiComponent.blit(poseStack, (x-(width/2)) +  10, 50, xOffset, yOffset, 16, 16, 64, 32);
        }

        if(ClientAllomancyData.isBurning(Metal.ELECTRUM)) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, ELECTRUM_COMPASS);

            int yOffset = 0;
            int xOffset = 0;

            if(ClientAllomancyData.getElectrumCompassPos() % 2 == 0)
                yOffset = 16;

            if(ClientAllomancyData.getElectrumCompassPos() == 3 || ClientAllomancyData.getElectrumCompassPos() == 4)
                xOffset = 16;
            if(ClientAllomancyData.getElectrumCompassPos() == 6 || ClientAllomancyData.getElectrumCompassPos() == 5)
                xOffset = 32;
            if(ClientAllomancyData.getElectrumCompassPos() == 8 || ClientAllomancyData.getElectrumCompassPos() == 7)
                xOffset = 48;

            GuiComponent.blit(poseStack, (x-(width/2)) +  10, 75, xOffset, yOffset, 16, 16, 64, 32);
        }


        for(Metal mt: Metal.values()) {
            ResourceLocation resourceLocation = new ResourceLocation(MistbornBaseMod.MOD_ID,
                    "textures/allomancy/" + mt.getName() + ".png");
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShaderTexture(0, resourceLocation);
                for(int i = 0; i < ClientAllomancyData.get(mt) / 250; i++) {
                    GuiComponent.blit(poseStack, (x-(width/2)) +  10 + (mt.ordinal()*5), y - (i*2), 0, 0, 4, 4, 4, 4);
                }

                RenderSystem.setShaderTexture(0, BURNING);
                if(ClientAllomancyData.isBurning(mt)) {
                    GuiComponent.blit(poseStack, (x-(width/2)) +  10 + (mt.ordinal()*5), y - ((ClientAllomancyData.get(mt) / 250) * 2), 0, 0, 4, 4, 4, 4);
                }
        }


    });
}
