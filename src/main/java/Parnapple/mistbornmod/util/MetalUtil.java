package Parnapple.mistbornmod.util;

import com.google.common.collect.Sets;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MetalUtil {

    public static Set<String> pushable_metals = Sets.newHashSet(
            "iron",
            "steel",
            "brass",
            "bronze",
            "zinc",
            "copper",
            "tin_",
            "pewter",
            "nicrosil",
            "chromium",
            "gold",
            "electrum",
            "cadmium",
            "bendalloy",
            "lead_",
            "nickel",
            "silver",
            "netherite",
            "bismuth",
            "platinum",
            "osmium",
            "invar");

    public static boolean isBlockMetal(Block block) {
        if(!block.getRegistryName().getPath().contains("ore")){
            for (String metal : pushable_metals) {
                if (block.getRegistryName().getPath().contains(metal)) {
                    return true;
                }
            }
        }
        return false;
    }

}
