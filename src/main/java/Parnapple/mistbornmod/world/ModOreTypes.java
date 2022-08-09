package Parnapple.mistbornmod.world;

import Parnapple.mistbornmod.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;

import java.util.ArrayList;
import java.util.List;

//This isn't normal, but so what
public enum ModOreTypes {
    TIN("tin_ore" , ModBlocks.TIN_ORE.get(), ModBlocks.DEEPSLATE_TIN_ORE.get(), 20, 15, 96, 3),
    ZINC("zinc_ore" , ModBlocks.ZINC_ORE.get(), ModBlocks.DEEPSLATE_ZINC_ORE.get(), 8, 5, 70, 20),
    CADMIUM("cadmium_ore" , ModBlocks.CADMIUM_ORE.get(), ModBlocks.DEEPSLATE_CADMIUM_ORE.get(), 5, 0, 96, 15),
    CHROMIUM("chromium_ore" , ModBlocks.CHROMIUM_ORE.get(), ModBlocks.DEEPSLATE_CHROMIUM_ORE.get(), 9, 0, 63, 17),
    SILVER("silver_ore" , ModBlocks.SILVER_ORE.get(), ModBlocks.DEEPSLATE_SILVER_ORE.get(), 9, 0, 35, 5),
    LEAD("lead_ore" , ModBlocks.LEAD_ORE.get(), ModBlocks.DEEPSLATE_LEAD_ORE.get(), 7, 31, 63, 12),
    BISMUTH("bismuth_ore" , ModBlocks.BISMUTH_ORE.get(), ModBlocks.DEEPSLATE_BISMUTH_ORE.get(), 9, 10, 45, 5),
    NICKEL("nickel_ore" , ModBlocks.NICKEL_ORE.get(), ModBlocks.DEEPSLATE_NICKEL_ORE.get(), 10, 20, 70, 18),

    EXTRA_TIN("extra_tin_ore", ModBlocks.TIN_ORE.get(), ModBlocks.DEEPSLATE_TIN_ORE.get(), 8, 0, 70, 16),
    EXTRA_ZINC("extra_zinc_ore", ModBlocks.ZINC_ORE.get(), ModBlocks.DEEPSLATE_ZINC_ORE.get(), 8, 0, 70, 16),
    EXTRA_CADMIUM("extra_cadmium_ore", ModBlocks.CADMIUM_ORE.get(), ModBlocks.DEEPSLATE_CADMIUM_ORE.get(), 8, 0, 70, 16),
    EXTRA_CHROMIUM("extra_chromium_ore", ModBlocks.CHROMIUM_ORE.get(), ModBlocks.DEEPSLATE_CHROMIUM_ORE.get(), 8, 0, 70, 16),
    EXTRA_SILVER("extra_silver_ore", ModBlocks.SILVER_ORE.get(), ModBlocks.DEEPSLATE_SILVER_ORE.get(), 8, 0, 70, 10),
    EXTRA_LEAD("extra_lead_ore", ModBlocks.LEAD_ORE.get(), ModBlocks.DEEPSLATE_LEAD_ORE.get(), 8, 0, 70, 16),
    EXTRA_BISMUTH("extra_bismuth_ore", ModBlocks.BISMUTH_ORE.get(), ModBlocks.DEEPSLATE_BISMUTH_ORE.get(), 8, 0, 70, 16),
    EXTRA_NICKEL("extra_nickel_ore", ModBlocks.NICKEL_ORE.get(), ModBlocks.DEEPSLATE_NICKEL_ORE.get(), 8, 0, 70, 16),


    BAUXITE("bauxite_ore" , OreConfiguration.target(new BlockMatchTest(Blocks.DIRT), ModBlocks.BAUXITE_ORE.get().defaultBlockState()), 5, 60, 75, 50),
    EXTRA_BAUXITE("extra_bauxite_ore" , OreConfiguration.target(new BlockMatchTest(Blocks.DIRT), ModBlocks.BAUXITE_ORE.get().defaultBlockState()), 5, 60, 75, 50);


    private final List<OreConfiguration.TargetBlockState> targets;
    private final int size;
    private final int maxHeight;
    private final int minHeight;
    private final int veinCount;
    private final String name;
    private final OreConfiguration config;
    private final ConfiguredFeature<?, ?> feature;

    //for normal ore types with stone and deepslate types
    ModOreTypes(String name, Block stoneTarget, Block deepslateTarget, int size, int minHeight, int maxHeight, int veinCount) {
        this.name = name;
        this.maxHeight = maxHeight;
        this.minHeight = minHeight;
        this.size = size;
        this.veinCount = veinCount;
        this.targets = new ArrayList<>();
        targets.add(OreConfiguration.target(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, stoneTarget.defaultBlockState()));
        targets.add(OreConfiguration.target(OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES, deepslateTarget.defaultBlockState()));
        this.config = new OreConfiguration(targets, size);

        this.feature = Feature.ORE.configured(config).rangeUniform(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)).squared().count(veinCount);
    }

    //for abnormal ones with a different type. I used this for the Bauxite ore, since it generates in dirt.
    ModOreTypes(String name, OreConfiguration.TargetBlockState target, int size, int minHeight, int maxHeight, int veinCount) {
        this.name = name;
        this.maxHeight = maxHeight;
        this.minHeight = minHeight;
        this.size = size;
        this.veinCount = veinCount;
        this.targets = new ArrayList<>();
        targets.add(target);

        this.config = new OreConfiguration(targets, size);

        this.feature = Feature.ORE.configured(config).rangeUniform(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)).squared().count(veinCount);
    }


    public OreConfiguration getConfiguration() {
        return config;
    }

    public List<OreConfiguration.TargetBlockState> getTargets() {
        return targets;
    }

    public int getSize() {
        return size;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public ConfiguredFeature<?, ?> getFeature() {
        return feature;
    }

    public int getVeinCount() {
        return veinCount;
    }

    public String getName() {
        return name;
    }
}
