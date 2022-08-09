package Parnapple.mistbornmod.world;

import Parnapple.mistbornmod.MistbornBaseMod;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class OreGeneration {

    public static final List<ConfiguredFeature<?, ?>> ORES = new ArrayList<>();
    public static final List<ConfiguredFeature<?, ?>> COLD_ORES = new ArrayList<>();
    public static final List<ConfiguredFeature<?, ?>> JUNGLE_ORES = new ArrayList<>();
    public static final List<ConfiguredFeature<?, ?>> FOREST_ORES = new ArrayList<>();
    public static final List<ConfiguredFeature<?, ?>> WARM_ORES = new ArrayList<>();


    public static void registerOres() {
        ORES.add(register(ModOreTypes.TIN.getName(), ModOreTypes.TIN.getFeature()));
        ORES.add(register(ModOreTypes.ZINC.getName(), ModOreTypes.ZINC.getFeature()));
        ORES.add(register(ModOreTypes.CADMIUM.getName(), ModOreTypes.CADMIUM.getFeature()));
        ORES.add(register(ModOreTypes.CHROMIUM.getName(), ModOreTypes.CHROMIUM.getFeature()));
        ORES.add(register(ModOreTypes.BAUXITE.getName(), ModOreTypes.BAUXITE.getFeature()));
        ORES.add(register(ModOreTypes.SILVER.getName(), ModOreTypes.SILVER.getFeature()));
        ORES.add(register(ModOreTypes.LEAD.getName(), ModOreTypes.LEAD.getFeature()));
        ORES.add(register(ModOreTypes.BISMUTH.getName(), ModOreTypes.BISMUTH.getFeature()));
        ORES.add(register(ModOreTypes.NICKEL.getName(), ModOreTypes.NICKEL.getFeature()));


        COLD_ORES.add(register(ModOreTypes.EXTRA_NICKEL.getName(), ModOreTypes.EXTRA_NICKEL.getFeature()));
        COLD_ORES.add(register(ModOreTypes.EXTRA_ZINC.getName(), ModOreTypes.EXTRA_ZINC.getFeature()));

        JUNGLE_ORES.add(register(ModOreTypes.EXTRA_BISMUTH.getName(), ModOreTypes.EXTRA_BISMUTH.getFeature()));
        JUNGLE_ORES.add(register(ModOreTypes.EXTRA_TIN.getName(), ModOreTypes.EXTRA_TIN.getFeature()));

        FOREST_ORES.add(register(ModOreTypes.EXTRA_CADMIUM.getName(), ModOreTypes.EXTRA_CADMIUM.getFeature()));
        FOREST_ORES.add(register(ModOreTypes.EXTRA_SILVER.getName(), ModOreTypes.EXTRA_SILVER.getFeature()));
        FOREST_ORES.add(register(ModOreTypes.EXTRA_LEAD.getName(), ModOreTypes.EXTRA_LEAD.getFeature()));

        WARM_ORES.add(register(ModOreTypes.EXTRA_BAUXITE.getName(), ModOreTypes.EXTRA_BAUXITE.getFeature()));
        WARM_ORES.add(register(ModOreTypes.EXTRA_CHROMIUM.getName(), ModOreTypes.EXTRA_CHROMIUM.getFeature()));

    }

    private static <Config extends FeatureConfiguration> ConfiguredFeature<Config, ?> register(String name, ConfiguredFeature<Config, ?> feature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(MistbornBaseMod.MOD_ID, name), feature);
    }

    @Mod.EventBusSubscriber(modid = MistbornBaseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeBusSubscriber {
        @SubscribeEvent
        public static void biomeLoading(BiomeLoadingEvent event) {
            List<Supplier<ConfiguredFeature<?, ?>>> features = event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

            switch(event.getCategory()) {
                case TAIGA, ICY -> OreGeneration.COLD_ORES.forEach(ore -> features.add(() -> ore));
                case JUNGLE -> OreGeneration.JUNGLE_ORES.forEach(ore -> features.add(() -> ore));
                case FOREST, SWAMP -> OreGeneration.FOREST_ORES.forEach(ore -> features.add(() -> ore));
                case SAVANNA, DESERT, MESA -> OreGeneration.WARM_ORES.forEach(ore -> features.add(() -> ore));
                default -> OreGeneration.ORES.forEach(ore -> features.add(() -> ore));
            }

        }


    }

}
