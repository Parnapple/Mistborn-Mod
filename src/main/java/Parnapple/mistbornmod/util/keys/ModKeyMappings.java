package Parnapple.mistbornmod.util.keys;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.util.Metal;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;

public class ModKeyMappings {
    public static KeyMapping burnKeyMapping;
    public static KeyMapping pushPullKey;
    public static KeyMapping[] metals;
    public static final String CATEGORY_MISTBORN = "key.category.mistborn";


    public static void init() {
        burnKeyMapping = registerKey("burn_key", CATEGORY_MISTBORN, InputConstants.KEY_V);
        pushPullKey = registerKey("push_pull_key", CATEGORY_MISTBORN, InputConstants.KEY_LALT);

        metals = new KeyMapping[Metal.values().length];
        for(Metal mt: Metal.values()) {
            metals[mt.getIndex()] = registerKey("burn_" + mt.getName() + "_key", CATEGORY_MISTBORN, GLFW.GLFW_KEY_UNKNOWN);
        }

    }

    private static KeyMapping registerKey(String name, String category, int keyCode) {
        final var key = new KeyMapping("key." + MistbornBaseMod.MOD_ID + "." + name, keyCode, category);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }
}
