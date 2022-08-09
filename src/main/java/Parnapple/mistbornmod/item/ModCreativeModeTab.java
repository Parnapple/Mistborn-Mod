package Parnapple.mistbornmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {

    public static final CreativeModeTab MISTBORN_TAB = new CreativeModeTab("mistborn_mod_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TIN_INGOT.get());
        }
    };


}
