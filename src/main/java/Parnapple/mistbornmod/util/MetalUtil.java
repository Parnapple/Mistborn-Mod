package Parnapple.mistbornmod.util;

import Parnapple.mistbornmod.block.custom.MetallurgyFurnaceBlock;
import Parnapple.mistbornmod.entity.CoinProjectileEntity;
import com.google.common.collect.Sets;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.Set;

public class MetalUtil {

    public static Set<String> PUSHABLE_METALS = Sets.newHashSet(
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
        if(block.equals(Blocks.CHAIN)
                || block instanceof AnvilBlock
                || block instanceof BellBlock
                || block instanceof HopperBlock
                || block instanceof BlastFurnaceBlock
                || block instanceof MetallurgyFurnaceBlock
        ) {
            return true;
        }

        if(!block.getRegistryName().getPath().contains("ore")){
            for (String metal : PUSHABLE_METALS) {
                if (block.getRegistryName().getPath().contains(metal)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isItemMetal(Item item) {
        if(item instanceof CrossbowItem
                || (item instanceof BlockItem block && isBlockMetal(block.getBlock()))
                || item.equals(Items.CHAIN)
                || item instanceof ShearsItem
                || item instanceof ShieldItem
                || item instanceof BucketItem
                || item instanceof MinecartItem
        ) {
            return true;
        }

        for (String metal : PUSHABLE_METALS) {
            if (item.getRegistryName().getPath().contains(metal)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isEntityMetal(Entity entity) {
        if(entity instanceof CoinProjectileEntity
                || entity instanceof IronGolem
                || entity instanceof Minecart
                || (entity instanceof ItemEntity item && isItemMetal(item.getItem().getItem()))
                || (entity instanceof ItemFrame frame && isItemMetal(frame.getItem().getItem()))
        ) {
            return true;
        }


        for(ItemStack item: entity.getArmorSlots()) {
            if(isItemMetal(item.getItem())) {
                return true;
            }
        }


        return false;
    }

}
