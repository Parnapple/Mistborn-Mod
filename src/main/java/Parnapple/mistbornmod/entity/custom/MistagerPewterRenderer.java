package Parnapple.mistbornmod.entity.custom;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.entity.client.MistagerPewterModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MistagerPewterRenderer extends GeoEntityRenderer<MistagerPewterEntity> {
    public MistagerPewterRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MistagerPewterModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(MistagerPewterEntity instance) {
        return new ResourceLocation(MistbornBaseMod.MOD_ID, "textures/entity/mistager_pewter.png");
    }

    @Override
    public RenderType getRenderType(MistagerPewterEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1.0F, 1.0F, 1.0F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
