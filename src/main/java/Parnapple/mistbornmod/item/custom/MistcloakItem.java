package Parnapple.mistbornmod.item.custom;

import Parnapple.mistbornmod.effect.ModEffects;
import Parnapple.mistbornmod.item.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MistcloakItem extends ArmorItem {
    public MistcloakItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {

        if(!pLevel.isClientSide()) {
            LivingEntity entity = (LivingEntity) pEntity;

            if(hasMistcloak(entity)) {
                entity.addEffect(new MobEffectInstance(ModEffects.STEALTH.get(), 1));
            }

        }


        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    private boolean hasMistcloak(LivingEntity entity) {
        NonNullList<ItemStack> slots = (NonNullList<ItemStack>)entity.getArmorSlots();
        boolean check = true;

        check = slots.get(1).getItem() == ModItems.MISTCLOAK_LEGGINGS.get() && slots.get(2).getItem() == ModItems.MISTCLOAK.get();

        return check;
    }

}
