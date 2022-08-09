package Parnapple.mistbornmod.item;

import Parnapple.mistbornmod.util.ModTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;


import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum ModItemTier implements Tier {
    BRONZE(2, 300, 6.1F, 2.0F, 18, () -> {
        return Ingredient.of(ModTags.Items.BRONZE_INGOT);
    }),
    STEEL(2, 400, 7.2F, 2.5F, 14, () -> {
        return Ingredient.of(ModTags.Items.STEEL_INGOT);
    }),
    OBSIDIAN(2, 4, 20F, 10F, 25, () -> {
        return Ingredient.of(Items.OBSIDIAN);
    }),
    GLASS(2, 2, 25F, 9.5F, 7, () -> {
        return Ingredient.of(Tags.Items.GLASS);
    });


    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    ModItemTier(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }


    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Nullable
    @Override
    public Tag<Block> getTag() {
        return Tier.super.getTag();
    }
}
