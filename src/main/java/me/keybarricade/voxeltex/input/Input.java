package me.keybarricade.voxeltex.input;

import me.keybarricade.voxeltex.input.key.KeyInputManager;
import me.keybarricade.voxeltex.window.VoxelTexWindow;
import org.lwjgl.glfw.GLFW;

public class Input {

    /**
     * Key input manager.
     */
    private static KeyInputManager keyInputManager;

    /**
     * Initialize the input manager for the given window.
     *
     * @param window Window to initialize the input manager for.
     */
    public static void init(VoxelTexWindow window) {
        // Set up and initialize the key input manager
        keyInputManager = new KeyInputManager(window, true);
    }

    /**
     * Destroy the input manager.
     */
    public static void destroy() {
        // Destroy the key manager
        keyInputManager.destroy();
    }

    /**
     * Check whether a key is pressed.
     *
     * @param keyCode Key code, for example {@link GLFW#GLFW_KEY_W} or {@link GLFW#GLFW_KEY_ENTER}.
     *
     * @return True if the key is down, false if not.
     */
    public static boolean isKeyDown(int keyCode) {
        return keyInputManager.isKeyDown(keyCode);
    }
}
