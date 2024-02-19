package Parnapple.mistbornmod.entity.client;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.entity.custom.MistagerPewterEntity;
import Parnapple.mistbornmod.entity.custom.MistagerPewterEntityOld;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MistagerPewterModel extends AnimatedGeoModel<MistagerPewterEntityOld> {
    @Override
    public ResourceLocation getModelLocation(MistagerPewterEntityOld object) {
        return new ResourceLocation(MistbornBaseMod.MOD_ID, "geo/mistager_pewter.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MistagerPewterEntityOld object) {
        return new ResourceLocation(MistbornBaseMod.MOD_ID, "textures/entity/mistager_pewter_old.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MistagerPewterEntityOld animatable) {
        return new ResourceLocation(MistbornBaseMod.MOD_ID, "animations/mistager_pewter.animation.json");
    }
}
