package me.keybarricade.voxeltex.input.key;

import me.keybarricade.voxeltex.window.VoxelTexWindow;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_REPEAT;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class KeyInputManager implements KeyInputInterface {

    /**
     * Key callback.
     */
    private GLFWKeyCallback keyCallback;

    /**
     * The window this key input instance is for.
     */
    private VoxelTexWindow window;

    /**
     * List of pressed keys.
     */
    private boolean[] keysDown = new boolean[GLFW.GLFW_KEY_LAST];

    /**
     * Constructor.
     *
     * @param window Window this key input manager is for.
     */
    public KeyInputManager(VoxelTexWindow window) {
        this(window, false);
    }

    /**
     * Constructor.
     *
     * @param window Window this key input manager is for.
     * @param init True to initialize, false if not.
     */
    public KeyInputManager(VoxelTexWindow window, boolean init) {
        this.window = window;

        // Initialize
        if(init)
            init();
    }

    /**
     * Initialize.
     */
    public void init() {
        // Create and set up the key callback
        keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                // TODO: Move this to a different class?
                // Schedule a renderer window close when the escape key is pressed
                if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                    glfwSetWindowShouldClose(window, GL_TRUE);

                // Set whether the key is pressed
                keysDown[key] = action == GLFW_PRESS || action == GLFW_REPEAT;
            }
        };

        // Register the key callback for the given window.
        glfwSetKeyCallback(window.getWindowId(), keyCallback);
    }

    /**
     * Destroy the key input manager.
     */
    public void destroy() {
        // Release and destroy the key input callback
        keyCallback.release();
        keyCallback = null;
    }

    /**
     * Get the window this key input manager is for.
     *
     * @return Window.
     */
    public VoxelTexWindow getWindow() {
        return window;
    }

    @Override
    public boolean isKeyDown(int keyCode) {
        return this.keysDown[keyCode];
    }
}
