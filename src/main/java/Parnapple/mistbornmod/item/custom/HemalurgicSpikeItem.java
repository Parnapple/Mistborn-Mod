package Parnapple.mistbornmod.item.custom;

import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.capability.allomancy.IAllomancerData;
import Parnapple.mistbornmod.capability.feruchemy.IFeruchemistData;
import Parnapple.mistbornmod.entity.custom.MistagerPewterEntity;
import Parnapple.mistbornmod.item.ModCreativeModeTab;
import Parnapple.mistbornmod.util.Metal;
import com.google.common.collect.Sets;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HemalurgicSpikeItem extends SwordItem {
    public enum Power {
        Strength, Senses, Destiny, Investiture, Connection, Emotional_Fortitude, Memory,
        Allomantic_Iron, Allomantic_Steel, Allomantic_Tin, Allomantic_Pewter, Allomantic_Zinc, Allomantic_Brass, Allomantic_Copper, Allomantic_Bronze, Allomantic_Cadmium, Allomantic_Bendalloy, Allomantic_Gold, Allomantic_Electrum, Allomantic_Chromium, Allomantic_Nicrosil, Allomantic_Aluminum, Allomantic_Duralumin,
        Feruchemic_Iron, Feruchemic_Steel, Feruchemic_Tin, Feruchemic_Pewter, Feruchemic_Zinc, Feruchemic_Brass, Feruchemic_Copper, Feruchemic_Bronze, Feruchemic_Cadmium, Feruchemic_Bendalloy, Feruchemic_Gold, Feruchemic_Electrum, Feruchemic_Chromium, Feruchemic_Nicrosil, Feruchemic_Aluminum, Feruchemic_Duralumin
    }


    public Metal metal;

    public HemalurgicSpikeItem(Metal metal) {
        super(Tiers.STONE, 2, 2.0F, new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(1).durability(64));
        this.metal = metal;
    }

    public void onPlayerKill(Player player, ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if(tag.contains("power")) {
            return;
        }

        if(this.metal.equals(Metal.ALUMINUM)) {
            player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(IAllomancerData::removeAllPowers);
            player.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(IFeruchemistData::removeAllPowers);
        }
        else if(this.metal.equals(Metal.STEEL)) {
            player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
                Set<Metal> options = Sets.newHashSet(Metal.TIN, Metal.PEWTER, Metal.IRON, Metal.STEEL);

                for(Metal option: options) {
                    if(data.hasPower(option)) {
                        data.removePower(option);

                        if(option.equals(Metal.TIN))
                           tag.putString("power", Power.Allomantic_Tin.name());
                        else if(option.equals(Metal.PEWTER))
                            tag.putString("power", Power.Allomantic_Pewter.name());
                        else if(option.equals(Metal.STEEL))
                            tag.putString("power", Power.Allomantic_Steel.name());
                        else if(option.equals(Metal.IRON))
                            tag.putString("power", Power.Allomantic_Iron.name());

                        break;
                    }
                }
            });
        }
        else if(this.metal.equals(Metal.PEWTER)) {
            player.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
                Set<Metal> options = Sets.newHashSet(Metal.TIN, Metal.PEWTER, Metal.IRON, Metal.STEEL);

                for(Metal option: options) {
                    if(data.hasPower(option)) {
                        data.removePower(option);

                        if(option.equals(Metal.TIN))
                            tag.putString("power", Power.Feruchemic_Tin.name());
                        else if(option.equals(Metal.PEWTER))
                            tag.putString("power", Power.Feruchemic_Pewter.name());
                        else if(option.equals(Metal.STEEL))
                            tag.putString("power", Power.Feruchemic_Steel.name());
                        else if(option.equals(Metal.IRON))
                            tag.putString("power", Power.Feruchemic_Iron.name());

                        break;
                    }
                }
            });
        }
        else if(this.metal.equals(Metal.BRASS)) {
            player.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
                Set<Metal> options = Sets.newHashSet(Metal.ZINC, Metal.COPPER, Metal.BRASS, Metal.BRONZE);

                for(Metal option: options) {
                    if(data.hasPower(option)) {
                        data.removePower(option);

                        if(option.equals(Metal.ZINC))
                            tag.putString("power", Power.Feruchemic_Zinc.name());
                        else if(option.equals(Metal.COPPER))
                            tag.putString("power", Power.Feruchemic_Copper.name());
                        else if(option.equals(Metal.BRASS))
                            tag.putString("power", Power.Feruchemic_Brass.name());
                        else if(option.equals(Metal.BRONZE))
                            tag.putString("power", Power.Feruchemic_Bronze.name());

                        break;
                    }
                }
            });
        }
        else if(this.metal.equals(Metal.BRONZE)) {
            player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
                Set<Metal> options = Sets.newHashSet(Metal.ZINC, Metal.COPPER, Metal.BRASS, Metal.BRONZE);

                for(Metal option: options) {
                    if(data.hasPower(option)) {
                        data.removePower(option);

                        if(option.equals(Metal.ZINC))
                            tag.putString("power", Power.Allomantic_Zinc.name());
                        else if(option.equals(Metal.COPPER))
                            tag.putString("power", Power.Allomantic_Copper.name());
                        else if(option.equals(Metal.BRASS))
                            tag.putString("power", Power.Allomantic_Brass.name());
                        else if(option.equals(Metal.BRONZE))
                            tag.putString("power", Power.Allomantic_Bronze.name());

                        break;
                    }
                }
            });
        }
        else if(this.metal.equals(Metal.CADMIUM)) {
            player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
                Set<Metal> options = Sets.newHashSet(Metal.GOLD, Metal.ELECTRUM, Metal.CADMIUM, Metal.BENDALLOY);

                for(Metal option: options) {
                    if(data.hasPower(option)) {
                        data.removePower(option);

                        if(option.equals(Metal.GOLD))
                            tag.putString("power", Power.Allomantic_Gold.name());
                        else if(option.equals(Metal.ELECTRUM))
                            tag.putString("power", Power.Allomantic_Electrum.name());
                        else if(option.equals(Metal.CADMIUM))
                            tag.putString("power", Power.Allomantic_Cadmium.name());
                        else if(option.equals(Metal.BENDALLOY))
                            tag.putString("power", Power.Allomantic_Bendalloy.name());

                        break;
                    }
                }
            });
        }
        else if(this.metal.equals(Metal.GOLD)) {
            player.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
                Set<Metal> options = Sets.newHashSet(Metal.GOLD, Metal.ELECTRUM, Metal.CADMIUM, Metal.BENDALLOY);

                for(Metal option: options) {
                    if(data.hasPower(option)) {
                        data.removePower(option);

                        if(option.equals(Metal.GOLD))
                            tag.putString("power", Power.Feruchemic_Gold.name());
                        else if(option.equals(Metal.ELECTRUM))
                            tag.putString("power", Power.Feruchemic_Electrum.name());
                        else if(option.equals(Metal.CADMIUM))
                            tag.putString("power", Power.Feruchemic_Cadmium.name());
                        else if(option.equals(Metal.BENDALLOY))
                            tag.putString("power", Power.Feruchemic_Bendalloy.name());

                        break;
                    }
                }
            });
        }
        else if(this.metal.equals(Metal.BENDALLOY)) {
            player.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
                Set<Metal> options = Sets.newHashSet(Metal.CHROMIUM, Metal.NICROSIL, Metal.ALUMINUM, Metal.DURALUMIN);

                for(Metal option: options) {
                    if(data.hasPower(option)) {
                        data.removePower(option);

                        if(option.equals(Metal.CHROMIUM))
                            tag.putString("power", Power.Feruchemic_Chromium.name());
                        else if(option.equals(Metal.NICROSIL))
                            tag.putString("power", Power.Feruchemic_Nicrosil.name());
                        else if(option.equals(Metal.ALUMINUM))
                            tag.putString("power", Power.Feruchemic_Aluminum.name());
                        else if(option.equals(Metal.DURALUMIN))
                            tag.putString("power", Power.Feruchemic_Duralumin.name());

                        break;
                    }
                }
            });
        }
        else if(this.metal.equals(Metal.ELECTRUM)) {
            player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
                Set<Metal> options = Sets.newHashSet(Metal.CHROMIUM, Metal.NICROSIL, Metal.ALUMINUM, Metal.DURALUMIN);

                for(Metal option: options) {
                    if(data.hasPower(option)) {
                        data.removePower(option);

                        if(option.equals(Metal.CHROMIUM))
                            tag.putString("power", Power.Allomantic_Chromium.name());
                        else if(option.equals(Metal.NICROSIL))
                            tag.putString("power", Power.Allomantic_Nicrosil.name());
                        else if(option.equals(Metal.ALUMINUM))
                            tag.putString("power", Power.Allomantic_Aluminum.name());
                        else if(option.equals(Metal.DURALUMIN))
                            tag.putString("power", Power.Allomantic_Duralumin.name());

                        break;
                    }
                }
            });
        }



    }

    public void onEntityKill(LivingEntity entity, ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if(tag.contains("power")) {
            return;
        }


        if(entity instanceof MistagerPewterEntity && this.metal.equals(Metal.STEEL)) {
            tag.putString("power", Power.Allomantic_Pewter.name());
        }

        if(entity instanceof Evoker && entity.getRandom().nextInt(100) <= 7) {
            if (this.metal.equals(Metal.PEWTER)) {

                Set<Metal> options = Sets.newHashSet(Metal.TIN, Metal.PEWTER, Metal.IRON, Metal.STEEL);

                for (Metal option : options) {
                    if (option.equals(Metal.TIN))
                        tag.putString("power", Power.Feruchemic_Tin.name());
                    else if (option.equals(Metal.PEWTER))
                        tag.putString("power", Power.Feruchemic_Pewter.name());
                    else if (option.equals(Metal.STEEL))
                        tag.putString("power", Power.Feruchemic_Steel.name());
                    else if (option.equals(Metal.IRON))
                        tag.putString("power", Power.Feruchemic_Iron.name());

                    break;
                }
            }
            else if(this.metal.equals(Metal.BRASS)) {

                Set<Metal> options = Sets.newHashSet(Metal.ZINC, Metal.COPPER, Metal.BRASS, Metal.BRONZE);

                for(Metal option: options) {

                    if(option.equals(Metal.ZINC))
                        tag.putString("power", Power.Feruchemic_Zinc.name());
                    else if(option.equals(Metal.COPPER))
                        tag.putString("power", Power.Feruchemic_Copper.name());
                    else if(option.equals(Metal.BRASS))
                        tag.putString("power", Power.Feruchemic_Brass.name());
                    else if(option.equals(Metal.BRONZE))
                        tag.putString("power", Power.Feruchemic_Bronze.name());
                    break;
                }
            }
            else if(this.metal.equals(Metal.GOLD)) {
                Set<Metal> options = Sets.newHashSet(Metal.GOLD, Metal.ELECTRUM, Metal.CADMIUM, Metal.BENDALLOY);

                for(Metal option: options) {
                    if(option.equals(Metal.GOLD))
                        tag.putString("power", Power.Feruchemic_Gold.name());
                    else if(option.equals(Metal.ELECTRUM))
                        tag.putString("power", Power.Feruchemic_Electrum.name());
                    else if(option.equals(Metal.CADMIUM))
                        tag.putString("power", Power.Feruchemic_Cadmium.name());
                    else if(option.equals(Metal.BENDALLOY))
                        tag.putString("power", Power.Feruchemic_Bendalloy.name());

                    break;
                }
            }
            else if(this.metal.equals(Metal.BENDALLOY)) {
                Set<Metal> options = Sets.newHashSet(Metal.CHROMIUM, Metal.NICROSIL, Metal.ALUMINUM, Metal.DURALUMIN);

                for(Metal option: options) {
                    if(option.equals(Metal.CHROMIUM))
                        tag.putString("power", Power.Feruchemic_Chromium.name());
                    else if(option.equals(Metal.NICROSIL))
                        tag.putString("power", Power.Feruchemic_Nicrosil.name());
                    else if(option.equals(Metal.ALUMINUM))
                        tag.putString("power", Power.Feruchemic_Aluminum.name());
                    else if(option.equals(Metal.DURALUMIN))
                        tag.putString("power", Power.Feruchemic_Duralumin.name());

                    break;
                }
            }

        } // Evokers have a 28% chance of being a feruchemist, so if you use a random one of the four spikes for stealing feruchemy, you have a 7% chance of steeling their power


    }

    public Metal getType() {
        return this.metal;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(pStack.hasTag())
            pTooltipComponents.add(new TextComponent(pStack.getTag().getString("power")));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if(pPlayer.isDiscrete() && stack.hasTag() && stack.getTag().contains("power")) {
            Power power = Power.valueOf(stack.getTag().getString("power"));
            if (power.toString().contains("Allomantic")) {
                pPlayer.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
                    Metal powerToGive = Metal.valueOf(power.name().replace("Allomantic_", "").toUpperCase());
                    data.givePower(powerToGive);
                });
            }
            if (power.toString().contains("Feruchemic")) {
                pPlayer.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
                    Metal powerToGive = Metal.valueOf(power.name().replace("Feruchemic_", "").toUpperCase());
                    data.givePower(powerToGive);
                });
            }

            stack.shrink(1);
            pPlayer.playSound(SoundEvents.ARROW_HIT_PLAYER, 10, 10);
            pPlayer.getCapability(ModCapabilities.HEMALURGY_INSTANCE).ifPresent(data -> {
                data.setSpikeCount(data.getSpikeCount() + 1);
            });
        }

        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        @Nullable
        String power = pStack.hasTag() ? pStack.getTag().getString("power") : null;
        if(power != null && !power.equals("")) {
            return true;
        }
        return super.isFoil(pStack);
    }
}
