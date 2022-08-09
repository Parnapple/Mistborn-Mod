package Parnapple.mistbornmod.item.custom;

import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.effect.ModEffects;
import Parnapple.mistbornmod.util.openmods.EnchantmentUtils;
import Parnapple.mistbornmod.util.Metal;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerXpEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//TODO make code neater
public class CoppermindItem extends Item {

    public boolean isNicrosil;

    public CoppermindItem(Properties pProperties, boolean isNicrosil) {
        super(pProperties.stacksTo(1));
        this.isNicrosil = isNicrosil;
    }

    public CoppermindItem(Properties properties) {
        this(properties, false);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {

            ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);

            if (itemstack.hasTag()) {

                if (MetalmindItem.canPlayerUseMetalMind(itemstack, pPlayer)) {

                    int investiture = itemstack.getTag().getInt("investitureCharge");

                    if (pPlayer.isShiftKeyDown()) {

                        int xpForCurrentLevel = EnchantmentUtils.getExperienceForLevel(pPlayer.experienceLevel);
                        AtomicInteger xpToStore = new AtomicInteger(EnchantmentUtils.getPlayerXP(pPlayer) - xpForCurrentLevel);
                        if(xpToStore.get() == 0 && pPlayer.experienceLevel > 0) //player has exactly x > 0 levels (xp bar looks empty)
                            xpToStore.set(xpForCurrentLevel - EnchantmentUtils.getExperienceForLevel(pPlayer.experienceLevel - 1));

                        if(this.isNicrosil) {
                            pPlayer.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
                                if (data.hasPower(Metal.NICROSIL) && data.hasPower(Metal.COPPER)) {
                                    itemstack.getTag().putInt("investitureCharge", investiture + 1);
                                } else if(!data.hasPower(Metal.COPPER)){
                                    if (investiture > 0) {
                                        itemstack.getTag().putInt("investitureCharge", investiture - 1);
                                    } else {
                                        xpToStore.set(0);
                                    }
                                }
                            });
                        }

                        if(xpToStore.get() == 0)
                            return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);

                        int xpStored = itemstack.getTag().getInt("xpStored");


                        itemstack.getTag().putInt("xpStored", xpStored + xpToStore.get());


                        int previousLevel = pPlayer.experienceLevel;

                        MinecraftForge.EVENT_BUS.post(new PlayerXpEvent.XpChange(pPlayer, -1 * xpToStore.get()));
                        EnchantmentUtils.addPlayerXP(pPlayer,-1 * xpToStore.get()); //negative value removes xp

                        if(previousLevel != pPlayer.experienceLevel)
                            MinecraftForge.EVENT_BUS.post(new PlayerXpEvent.LevelChange(pPlayer, pPlayer.experienceLevel));

                        if(itemstack.getTag().getString("feruchemyUUID").equals("")) {
                            MetalmindItem.keyMetalmind(itemstack, pPlayer);
                        }


                    } else if(itemstack.getTag().getInt("xpStored") > 0) {

                        AtomicInteger xpForPlayer = new AtomicInteger(EnchantmentUtils.getExperienceForLevel(pPlayer.experienceLevel + 1) - EnchantmentUtils.getPlayerXP(pPlayer));

                        if(this.isNicrosil) {
                            pPlayer.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
                                if(!data.hasPower(Metal.COPPER)) {
                                    if(investiture > 0) {
                                        itemstack.getTag().putInt("investitureCharge", investiture - 1);
                                    } else {
                                        xpForPlayer.set(0);
                                    }
                                }
                            });
                        }
                        
                        if(xpForPlayer.get() >= itemstack.getTag().getInt("xpStored")) {
                            xpForPlayer.set(itemstack.getTag().getInt("xpStored"));
                        }


                        int previousLevel = pPlayer.experienceLevel;

                        MinecraftForge.EVENT_BUS.post(new PlayerXpEvent.XpChange(pPlayer, xpForPlayer.get()));
                        EnchantmentUtils.addPlayerXP(pPlayer, xpForPlayer.get());
                        itemstack.getTag().putInt("xpStored", itemstack.getTag().getInt("xpStored") - xpForPlayer.get());


                        if(previousLevel != pPlayer.experienceLevel)
                            MinecraftForge.EVENT_BUS.post(new PlayerXpEvent.LevelChange(pPlayer, pPlayer.experienceLevel));

                    }
                }

                if(itemstack.getTag().getInt("xpStored") == 0 && !this.isNicrosil) {
                    itemstack.removeTagKey("feruchemyUUID");
                    itemstack.removeTagKey("xpStored");
                }


            } else {
                CompoundTag tag = new CompoundTag();
                if(!pPlayer.hasEffect(ModEffects.NO_IDENTITY.get())) {
                    tag.putString("feruchemyUUID", pPlayer.getStringUUID());
                } else {
                    tag.putString("feruchemyUUID", "");
                }
                tag.putInt("xpStored", 0);
                tag.putInt("investitureCharge", 0);

                itemstack.setTag(tag);

            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.mistbornmod.metalmind"));
            if(pStack.hasTag()) {
                pTooltipComponents.add(new TextComponent("UUID: " + pStack.getTag().getString("feruchemyUUID")));
                pTooltipComponents.add(new TextComponent("Charge: " + pStack.getTag().getInt("xpStored")));
                if(this.isNicrosil) {
                    pTooltipComponents.add(new TextComponent("Investiture: " + pStack.getTag().getInt("investitureCharge")));
                }
            }
        } else {
            pTooltipComponents.add(new TranslatableComponent("tooltip.mistbornmod.metalmind_shift"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

}
