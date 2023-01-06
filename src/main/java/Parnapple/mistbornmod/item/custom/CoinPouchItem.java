package Parnapple.mistbornmod.item.custom;

import Parnapple.mistbornmod.util.ModTags;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;

import static Parnapple.mistbornmod.util.MetalUtil.PUSHABLE_METALS;

public class CoinPouchItem extends BundleItem {


    public CoinPouchItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack pStack, ItemStack pOther, Slot pSlot, ClickAction pAction, Player pPlayer, SlotAccess pAccess) {
        if(pStack.is(Tags.Items.DYES)) {
//        if(true) {
            return super.overrideOtherStackedOnMe(pStack, pOther, pSlot, pAction, pPlayer, pAccess);
        }

        return false;
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack pStack, Slot pSlot, ClickAction pAction, Player pPlayer) {
        if(pStack.is(Tags.Items.DYES)) {
//        if(true) {
            return super.overrideStackedOnOther(pStack, pSlot, pAction, pPlayer);
        }

        return false;
    }
}
