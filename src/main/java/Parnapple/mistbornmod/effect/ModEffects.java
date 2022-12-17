package Parnapple.mistbornmod.effect;

import Parnapple.mistbornmod.MistbornBaseMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MistbornBaseMod.MOD_ID);

    public static final RegistryObject<MobEffect> WOUNDED = MOB_EFFECTS.register("wounded",
            () -> new WoundedEffect(MobEffectCategory.HARMFUL, 1));

    public static final RegistryObject<MobEffect> STEALTH = MOB_EFFECTS.register("stealth",
            () -> new StealthEffect(MobEffectCategory.BENEFICIAL, 1140));

    public static final RegistryObject<MobEffect> PEWTER_STRENGTH = MOB_EFFECTS.register("pewter_strength",
            () -> new PewterStrengthEffect(MobEffectCategory.BENEFICIAL, 210));

    public static final RegistryObject<MobEffect> WEIGHT = MOB_EFFECTS.register("weight",
            () -> new WeightEffect(MobEffectCategory.NEUTRAL, 3640));

    public static final RegistryObject<MobEffect> LIGHTNESS = MOB_EFFECTS.register("lightness",
            () -> new LightnessEffect(MobEffectCategory.NEUTRAL, 6304));

    public static final RegistryObject<MobEffect> COLD = MOB_EFFECTS.register("cold",
            () -> new ColdEffect(MobEffectCategory.HARMFUL, 123123123));

    public static final RegistryObject<MobEffect> DROWNING = MOB_EFFECTS.register("drowning",
            () -> new DrowningEffect(MobEffectCategory.HARMFUL, 11111111));

    public static final RegistryObject<MobEffect> NO_IDENTITY = MOB_EFFECTS.register("no_identity",
            () -> new ModEffect(MobEffectCategory.NEUTRAL, 123456));

    public static final RegistryObject<MobEffect> IDENTITY = MOB_EFFECTS.register("identity",
            () -> new ModEffect(MobEffectCategory.NEUTRAL, 654321));

    public static final RegistryObject<MobEffect> MENTAL_SPEED = MOB_EFFECTS.register("mental_speed",
            () -> new ModEffect(MobEffectCategory.BENEFICIAL, 123323));

    public static final RegistryObject<MobEffect> MENTAL_SLOWDOWN = MOB_EFFECTS.register("mental_slowdown",
            () -> new ModEffect(MobEffectCategory.HARMFUL, 3464565));

    public static final RegistryObject<MobEffect> DISCONNECT = MOB_EFFECTS.register("disconnect",
            () -> new DisconnectEffect(MobEffectCategory.HARMFUL, 100000001));

    public static final RegistryObject<MobEffect> AWAKE = MOB_EFFECTS.register("awake",
            () -> new WakefulnessEffect(MobEffectCategory.BENEFICIAL, 5555));

    public static void register(IEventBus bus) {
        MOB_EFFECTS.register(bus);
    }

}
