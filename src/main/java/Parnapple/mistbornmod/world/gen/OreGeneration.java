package Parnapple.mistbornmod.world.gen;

import Parnapple.mistbornmod.world.feature.OreFeature;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

public class OreGeneration {
    public static final RuleTest DIRT_TEST = new TagMatchTest(BlockTags.DIRT);

    public static void generateOres(final BiomeLoadingEvent event) {
        List<Holder<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        for(OreFeature feature: OreFeature.values()) {
            base.add(feature.placedFeature());
        }
    }
}
