package Parnapple.mistbornmod.entity.client;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.entity.client.MistagerPewterModel;
import Parnapple.mistbornmod.entity.custom.Mistager;
import Parnapple.mistbornmod.entity.custom.MistagerPewterEntity;
import Parnapple.mistbornmod.util.Metal;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.VindicatorRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Vindicator;
import software.bernie.example.client.renderer.entity.layer.GeoExampleLayer;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MistagerPewterRenderer extends IllagerRenderer<MistagerPewterEntity>{
    private static final ResourceLocation MISTAGER_PEWTER = new ResourceLocation(MistbornBaseMod.MOD_ID, "textures/entity/mistager_pewter.png");

    public MistagerPewterRenderer(EntityRendererProvider.Context p_174439_) {
        super(p_174439_, new IllagerModel<>(p_174439_.bakeLayer(ModelLayers.PILLAGER)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(MistagerPewterEntity pEntity) {
        return MISTAGER_PEWTER;
    }

}
