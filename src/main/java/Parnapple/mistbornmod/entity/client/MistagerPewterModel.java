package Parnapple.mistbornmod.entity.client;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.entity.custom.MistagerPewterEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MistagerPewterModel extends AnimatedGeoModel<MistagerPewterEntity> {
    @Override
    public ResourceLocation getModelLocation(MistagerPewterEntity object) {
        return new ResourceLocation(MistbornBaseMod.MOD_ID, "geo/mistager_pewter.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MistagerPewterEntity object) {
        return new ResourceLocation(MistbornBaseMod.MOD_ID, "textures/entity/mistager_pewter.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MistagerPewterEntity animatable) {
        return new ResourceLocation(MistbornBaseMod.MOD_ID, "animations/mistager_pewter.animation.json");
    }
}
