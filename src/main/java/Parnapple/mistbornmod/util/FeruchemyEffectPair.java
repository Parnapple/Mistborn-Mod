package Parnapple.mistbornmod.util;

import Parnapple.mistbornmod.effect.ModEffects;
import net.minecraft.world.effect.MobEffect;

public class FeruchemyEffectPair {

    private final MobEffect tappingEffect;
    private final MobEffect storingEffect;
    private final String type;

    public FeruchemyEffectPair(MobEffect tapping, MobEffect storing, String type) {
        this.tappingEffect = tapping;
        this.storingEffect = storing;
        this.type = type;
    }
    public FeruchemyEffectPair(MobEffect tapping, MobEffect storing) {
        this(tapping, storing, "");
    }

    public MobEffect getTappingEffect() {
        if(this.type == "iron") {
            return ModEffects.WEIGHT.get();
        }else if(this.type == "aluminum") {
            return ModEffects.IDENTITY.get();
        }else if(this.type == "zinc") {
            return ModEffects.MENTAL_SPEED.get();
        }

        return tappingEffect;
    }

    public MobEffect getStoringEffect() {
        if(this.type == "iron") {
            return ModEffects.LIGHTNESS.get();
        } else if(this.type == "brass") {
            return ModEffects.COLD.get();
        }else if(this.type == "cadmium") {
            return ModEffects.DROWNING.get();
        }else if(this.type == "aluminum") {
            return ModEffects.NO_IDENTITY.get();
        }else if(this.type == "zinc") {
            return ModEffects.MENTAL_SLOWDOWN.get();
        }

        return storingEffect;
    }
}
