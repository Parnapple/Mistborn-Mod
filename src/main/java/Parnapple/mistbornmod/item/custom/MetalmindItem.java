package Parnapple.mistbornmod.item.custom;

import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.effect.ModEffects;
import Parnapple.mistbornmod.util.Metal;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

//TODO make code neater?
public class MetalmindItem extends Item {

    public Metal type;
    public boolean isNicrosil;

    public MetalmindItem(Metal type, Properties pProperties, boolean isNicrosil) {
        super(pProperties.stacksTo(1));
        this.type = type;
        this.isNicrosil = isNicrosil;
    }

    public MetalmindItem(Metal type, Properties properties) {
        this(type, properties, false);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        if(stack.hasTag()) {
            stack.getTag().putInt("feruchemyStoringStrength", 0);
            stack.getTag().putInt("feruchemyTappingStrength", 0);
        }


        return super.onEntityItemUpdate(stack, entity);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {
            ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);

            if (!itemstack.hasTag()) {
                CompoundTag tag = new CompoundTag();
                tag.putInt("feruchemyStoringStrength", 0);
                tag.putInt("feruchemyTappingStrength", 0);

                if(this.isNicrosil) {
                    tag.putInt("investitureCharge", 0);
                }

                if(!pPlayer.hasEffect(ModEffects.NO_IDENTITY.get())) {
                    tag.putString("feruchemyUUID", pPlayer.getStringUUID());
                } else {
                    tag.putString("feruchemyUUID", "");
                }
                itemstack.setTag(tag);

            }
            if(canPlayerUseMetalMind(itemstack, pPlayer)) {
                int tapping = itemstack.getTag().getInt("feruchemyTappingStrength");
                int storing = itemstack.getTag().getInt("feruchemyStoringStrength");

                if (pPlayer.isShiftKeyDown()) {
                    if (tapping > 0) {
                        itemstack.getTag().putInt("feruchemyTappingStrength", 0);
                    } else if(storing < 5) {
                        itemstack.getTag().putInt("feruchemyStoringStrength", storing + 1);
                        if(itemstack.getTag().getString("feruchemyUUID").equals("")) {
                            keyMetalmind(itemstack, pPlayer);
                        }
                    }


                } else {
                    if (storing > 0) {
                        itemstack.getTag().putInt("feruchemyStoringStrength", 0);
                    } else {
                        itemstack.getTag().putInt("feruchemyTappingStrength", tapping + 1);
                    }
                }
            }



        }

        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(!pLevel.isClientSide) {

            LivingEntity entity = ((LivingEntity) pEntity);

            if (pStack.hasTag()) {
                if(canPlayerUseMetalMind(pStack, entity)) {
                    int tapping = pStack.getTag().getInt("feruchemyTappingStrength");
                    int charge = pStack.getTag().getInt("feruchemalCharge");
                    int storing = pStack.getTag().getInt("feruchemyStoringStrength");

                    int investiture = pStack.getTag().getInt("investitureCharge");


                    if(charge < 0) {
                        pStack.getTag().putInt("feruchemalCharge",  0);
                    }

                    if (tapping > 0) {
                        if(charge > 0) {
                            if(this.isNicrosil) {
                                entity.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
                                    if(!data.hasPower(this.type)) {//if the player can't normally use this type of metalmind
                                        if(investiture > 0) {//if the investiture charge is high enough
                                            //decrease investiture charge
                                            pStack.getTag().putInt("investitureCharge", investiture - 1);
                                        } else {//if it wasn't enough, stop tapping
                                            pStack.getTag().putInt("feruchemyTappingStrength", 0);
                                        }
                                    }
                                });
                            }

                            int duration = getEffectDuration(pStack, entity);

                            if (pStack.getTag().getInt("feruchemyTappingStrength") > 0) {//prevents tapping if it is a nicrosilmind w/out Investiture
                                if(duration > 0)
                                    entity.addEffect(new MobEffectInstance(this.type.getFeruchemalPower().getTappingEffect(), duration, tapping-1, false, false, true));

                                pStack.getTag().putInt("feruchemalCharge",  charge - tapping);
                            }



                        } else {
                            pStack.getTag().putInt("feruchemyTappingStrength", 0);
                        }
                    } else if(storing > 0) {

                        //only do this if this is a nicrosilmind
                        if(this.isNicrosil) {
                            entity.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
                               if(data.hasPower(Metal.NICROSIL) && data.hasPower(this.type)) {//if the player can use nicrosil and the other metal, store Investiture
                                   pStack.getTag().putInt("investitureCharge", investiture + storing);
                               } else if(!data.hasPower(this.type)) {//otherwise
                                   if(investiture > 0) {//if investiture remains, decrease it by one, but continue allowing to store
                                       pStack.getTag().putInt("investitureCharge", investiture - 1);
                                   } else {//if no more investiture, stop storing
                                       pStack.getTag().putInt("feruchemyStoringStrength", 0);
                                   }
                               }
                            });
                        }

                        int duration = getEffectDuration(pStack, entity);

                        if (pStack.getTag().getInt("feruchemyStoringStrength") > 0) {//prevents storing if it is a nicrosilmind w/out Investiture
                            if(duration > 0)
                                entity.addEffect(new MobEffectInstance(this.type.getFeruchemalPower().getStoringEffect(), duration, storing-1, false, false, true));

                            pStack.getTag().putInt("feruchemalCharge",  charge + storing);
                        }
                    } else if(charge == 0 && storing == 0 && !this.isNicrosil) {
                        removeTags(pStack);
                    }
                }


            }

        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.mistbornmod.metalmind"));
            if(pStack.hasTag()) {
                pTooltipComponents.add(new TextComponent("UUID: " + pStack.getTag().getString("feruchemyUUID")));
                pTooltipComponents.add(new TextComponent("Charge: " + pStack.getTag().getInt("feruchemalCharge")));
                pTooltipComponents.add(new TextComponent("Tapping: " + pStack.getTag().getInt("feruchemyTappingStrength")));
                pTooltipComponents.add(new TextComponent("Storing: " + pStack.getTag().getInt("feruchemyStoringStrength")));
                //MetalmindItem casted = ((MetalmindItem) pStack.getItem());
                if(this.isNicrosil) {
                    pTooltipComponents.add(new TextComponent("Investiture: " + pStack.getTag().getInt("investitureCharge")));
                }

            }
        } else {
            pTooltipComponents.add(new TranslatableComponent("tooltip.mistbornmod.metalmind_shift"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private static int getEffectDuration(ItemStack stack, LivingEntity entity) {//only pass a MetalmindItem ItemStack into this. Other things probably cause problems. TODO:make this method safer
        int effectDuration = 1;

        MetalmindItem metalmindItemStack = (MetalmindItem) stack.getItem();//can't use `this` because this is a static method. But I might be able to make it not static... TODO:check if this can be not static
        int tapping = stack.getTag().getInt("feruchemyTappingStrength");
        int storing = stack.getTag().getInt("feruchemyStoringStrength");

        if(metalmindItemStack.type == Metal.GOLD) {//if the regen/poison from gold doesn't last enough ticks, it won't do anything
            if(!(entity.hasEffect(MobEffects.REGENERATION) ||entity.hasEffect(MobEffects.POISON))) {
                effectDuration = 50;
            } else {
                effectDuration = 0;
            }

        } else if(metalmindItemStack.type == Metal.TIN) {
            if(tapping > 0) {
                effectDuration = 200;
            } else {
                effectDuration = 50;
            }
        } else if(metalmindItemStack.type == Metal.BRASS) {
            if(storing > 0) {
                effectDuration = 10;
            } else {
                if(tapping > 4) {
                    effectDuration = 10;
                } else {
                    effectDuration = -1;
                }
            }
        } else if(metalmindItemStack.type == Metal.CADMIUM) {
            effectDuration = 10;
        } else if(metalmindItemStack.type == Metal.BENDALLOY && storing > 0) {
            if(!entity.hasEffect(MobEffects.HUNGER)) {
                effectDuration = 50;
            } else {
                effectDuration = 0;
            }
        }



       return effectDuration;
    }

    private void removeTags(ItemStack stack) {
        stack.removeTagKey("feruchemyUUID");
        stack.removeTagKey("feruchemalCharge");
        stack.removeTagKey("feruchemyTappingStrength");
        stack.removeTagKey("feruchemyStoringStrength");
    }

    public static boolean canPlayerUseMetalMind(ItemStack itemstack, LivingEntity entity) {
        AtomicBoolean canUse = new AtomicBoolean(false);

        if(itemstack.getItem().getClass().equals(CoppermindItem.class)) {//if the metalmind being checked is a coppermind
            entity.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
                CoppermindItem metalmindItemstack = (CoppermindItem) itemstack.getItem();

                if (data.hasPower(Metal.COPPER) || metalmindItemstack.isNicrosil) {
                    if (itemstack.hasTag() && (itemstack.getTag().getString("feruchemyUUID").equals(entity.getStringUUID()) || itemstack.getTag().getString("feruchemyUUID").equals(""))) {
                        canUse.set(true);
                    }
                }
            });
        } else {


            entity.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
                MetalmindItem metalmindItemstack = (MetalmindItem) itemstack.getItem();

                if (data.hasPower(metalmindItemstack.type) || metalmindItemstack.isNicrosil) {
                    if (itemstack.hasTag() && (itemstack.getTag().getString("feruchemyUUID").equals(entity.getStringUUID()) || itemstack.getTag().getString("feruchemyUUID").equals(""))) {
                        canUse.set(true);
                    }
                }
            });
        }

        return canUse.get();

    }

    public static void keyMetalmind(ItemStack itemstack, Player player) {
        if(!player.hasEffect(ModEffects.NO_IDENTITY.get())) {
            itemstack.getTag().putString("feruchemyUUID", player.getStringUUID());
        } else {
            itemstack.getTag().putString("feruchemyUUID", "");
        }
    }

}
