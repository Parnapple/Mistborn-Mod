package Parnapple.mistbornmod.util.keys;

import Parnapple.mistbornmod.MistbornBaseMod;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public class ModKeyMappings {
    public static KeyMapping burnKeyMapping;
    public static final String CATEGORY_MISTBORN = "key.category.mistborn";


    public static void init() {
        burnKeyMapping = registerKey("burn_key", CATEGORY_MISTBORN, InputConstants.KEY_V);
    }

    private static KeyMapping registerKey(String name, String category, int keyCode) {
        final var key = new KeyMapping("key." + MistbornBaseMod.MOD_ID + "." + name, keyCode, category);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }
}
