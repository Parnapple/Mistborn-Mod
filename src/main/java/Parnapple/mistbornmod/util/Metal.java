package Parnapple.mistbornmod.util;

import net.minecraft.world.effect.MobEffects;

public enum Metal {
    IRON("iron", new FeruchemyEffectPair(null, null, "iron")),
    STEEL("steel", new FeruchemyEffectPair(MobEffects.MOVEMENT_SPEED, MobEffects.MOVEMENT_SLOWDOWN)),
    ZINC("zinc", new FeruchemyEffectPair(null, null, "zinc")),
    BRASS("brass", new FeruchemyEffectPair(MobEffects.FIRE_RESISTANCE, null, "brass")),
    TIN("tin", new FeruchemyEffectPair(MobEffects.NIGHT_VISION, MobEffects.BLINDNESS)),
    PEWTER("pewter", new FeruchemyEffectPair(MobEffects.DAMAGE_BOOST, MobEffects.WEAKNESS)),
    COPPER("copper", null),
    BRONZE("bronze", new FeruchemyEffectPair(null, MobEffects.CONFUSION, "bronze")),
    GOLD("gold", new FeruchemyEffectPair(MobEffects.REGENERATION, MobEffects.POISON)),
    ELECTRUM("electrum", new FeruchemyEffectPair(MobEffects.DIG_SPEED, MobEffects.DIG_SLOWDOWN)),
    BENDALLOY("bendalloy", new FeruchemyEffectPair(MobEffects.SATURATION, MobEffects.HUNGER)),
    CADMIUM("cadmium", new FeruchemyEffectPair(MobEffects.WATER_BREATHING, null, "cadmium")),
    CHROMIUM("chromium", new FeruchemyEffectPair(MobEffects.LUCK, MobEffects.UNLUCK)),
    NICROSIL("nicrosil", null),
    ALUMINUM("aluminum", new FeruchemyEffectPair(null, null, "aluminum")),
    DURALUMIN("duralumin", new FeruchemyEffectPair(MobEffects.HERO_OF_THE_VILLAGE, null, "duralumin"));



    private final String name;
    private final FeruchemyEffectPair feruchemalPower;

    private Metal(String name, FeruchemyEffectPair feruchemy) {
        this.name = name;
        this.feruchemalPower = feruchemy;
    }

    public String getName() {
        return name;
    }

    public FeruchemyEffectPair getFeruchemalPower() {
        return feruchemalPower;
    }

    public static Metal getMetal(int index) {
        for (Metal metal : values()) {
            if (metal.getIndex() == index) {
                return metal;
            }
        }
        throw new IllegalArgumentException("Mistborn Mod: Bad Metal Index");
    }

    public int getIndex() {
        return ordinal();
    }

}
