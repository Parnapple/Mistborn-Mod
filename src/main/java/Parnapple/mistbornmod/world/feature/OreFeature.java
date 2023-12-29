package Parnapple.mistbornmod.world.feature;

import Parnapple.mistbornmod.block.ModBlocks;
import Parnapple.mistbornmod.world.gen.OreGeneration;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public enum OreFeature {
    TIN("tin_ore", 20, 10, 80, 0, ModBlocks.TIN_ORE.get(), ModBlocks.DEEPSLATE_TIN_ORE.get()),
    ZINC("zinc_ore", 8, 20, 60, -20, ModBlocks.ZINC_ORE.get(), ModBlocks.DEEPSLATE_ZINC_ORE.get()),
    CADMIUM("cadmium_ore", 5, 20, 20, -64, ModBlocks.CADMIUM_ORE.get(), ModBlocks.DEEPSLATE_CADMIUM_ORE.get()),
    CHROMIUM("chromium_ore", 9, 20, 20, -50, ModBlocks.CHROMIUM_ORE.get(), ModBlocks.DEEPSLATE_CHROMIUM_ORE.get()),
    ALUMINUM("aluminum_ore", 5, 50, 200, 50, List.of(
            OreConfiguration.target(OreGeneration.DIRT_TEST, ModBlocks.BAUXITE_ORE.get().defaultBlockState()))),
    SILVER("silver_ore", 9, 12, 10, -30, ModBlocks.SILVER_ORE.get(), ModBlocks.DEEPSLATE_SILVER_ORE.get()),
    LEAD("lead_ore", 7, 25, 70, -10, ModBlocks.LEAD_ORE.get(), ModBlocks.DEEPSLATE_LEAD_ORE.get()),
    BISMUTH("bismuth_ore", 10, 20, 35, -25, ModBlocks.BISMUTH_ORE.get(), ModBlocks.DEEPSLATE_BISMUTH_ORE.get()),
    NICKEL("nickel_ore", 9, 25, 70, -10, ModBlocks.NICKEL_ORE.get(), ModBlocks.DEEPSLATE_NICKEL_ORE.get());

    private List<OreConfiguration.TargetBlockState> targets;
    private Holder<ConfiguredFeature<OreConfiguration, ?>> configured;
    private Holder<PlacedFeature> placed;


    OreFeature(String name, int size, int count, int maxHeight, int minHeight, List<OreConfiguration.TargetBlockState> targets) {
        this.targets = targets;

        this.configured = FeatureUtils.register(name,
                Feature.ORE, new OreConfiguration(this.targets, size));

        this.placed = PlacementUtils.register(name + "_placed",
                this.configured, ModOrePlacement.commonOrePlacement(count, // VeinsPerChunk
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(minHeight + 64), VerticalAnchor.aboveBottom(maxHeight + 64))));//+64 makes it so the params are for actual y-level, not height above bottom
    }

    OreFeature(String name, int size, int count, int maxHeight, int minHeight, Block stone, Block deepslate) {
        this(name , size, count, maxHeight, minHeight, List.of(
                OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, stone.defaultBlockState()),
                OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, deepslate.defaultBlockState())));
    }

    public Holder<PlacedFeature> placedFeature() {
        return this.placed;
    }
}
