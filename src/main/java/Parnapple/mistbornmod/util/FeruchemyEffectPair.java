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
        if(this.type.equals("iron")) {
            return ModEffects.WEIGHT.get();
        }else if(this.type.equals("aluminum")) {
            return ModEffects.IDENTITY.get();
        }else if(this.type.equals("zinc")) {
            return ModEffects.MENTAL_SPEED.get();
        }else if(this.type.equals("bronze")) {
            return ModEffects.AWAKE.get();
        }

        return tappingEffect;
    }

    public MobEffect getStoringEffect() {
        if(this.type.equals("iron")) {
            return ModEffects.LIGHTNESS.get();
        } else if(this.type.equals("brass")) {
            return ModEffects.COLD.get();
        }else if(this.type.equals("cadmium")) {
            return ModEffects.DROWNING.get();
        }else if(this.type.equals("aluminum")) {
            return ModEffects.NO_IDENTITY.get();
        }else if(this.type.equals("zinc")) {
            return ModEffects.MENTAL_SLOWDOWN.get();
        }else if(this.type.equals("duralumin")) {
            return ModEffects.DISCONNECT.get();
        }

        return storingEffect;
    }
}
