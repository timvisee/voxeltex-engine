package me.keybarricade.voxeltex.input;

import me.keybarricade.voxeltex.input.key.KeyInputManager;
import me.keybarricade.voxeltex.input.mouse.MouseInputManager;
import me.keybarricade.voxeltex.window.VoxelTexWindow;
import org.lwjgl.glfw.GLFW;

public class Input {

    /**
     * Key input manager.
     */
    private static KeyInputManager keyInputManager;

    /**
     * Mouse input manager.
     */
    private static MouseInputManager mouseInputManager;

    /**
     * Initialize the input manager for the given window.
     *
     * @param window Window to initialize the input manager for.
     */
    public static void init(VoxelTexWindow window) {
        // Set up and initialize the input managers
        keyInputManager = new KeyInputManager(window, true);
        mouseInputManager = new MouseInputManager(window, true);
    }

    /**
     * Destroy the input manager.
     */
    public static void destroy() {
        // Destroy the input managers
        keyInputManager.destroy();
        mouseInputManager.destroy();
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

    /**
     * Get the x coordinate of the mouse cursor.
     *
     * @return X coordinate.
     */
    public static float getMouseX() {
        return mouseInputManager.getMouseX();
    }

    /**
     * Get the y coordinate of the mouse cursor.
     *
     * @return Y coordinate.
     */
    public static float getMouseY() {
        return mouseInputManager.getMouseY();
    }

    /**
     * Center the mouse cursor on the window.
     */
    public static void centerMouseCursor() {
        mouseInputManager.centerMouseCursor();
    }
}
