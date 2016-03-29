package me.keybarricade.voxeltex.input.key;

import me.keybarricade.voxeltex.window.VoxelTexWindow;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL11;

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
        this.keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scanCode, int action, int mods) {
                // Schedule a renderer window close when the escape key is pressed
                if(key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE)
                    GLFW.glfwSetWindowShouldClose(window, GL11.GL_TRUE);

                // Make sure the key code is known
                if(key < 0) {
                    System.out.println("Unable to handle pressed key, key unknown.");
                    return;
                }

                // Set whether the key is pressed
                keysDown[key] = action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT;
            }
        };

        // Register the key callback for the given window.
        GLFW.glfwSetKeyCallback(window.getWindowId(), keyCallback);
    }

    /**
     * Destroy the key input manager.
     */
    public void destroy() {
        // Release and destroy the key input callback
        this.keyCallback.release();
        this.keyCallback = null;
    }

    /**
     * Get the window this key input manager is for.
     *
     * @return Window.
     */
    public VoxelTexWindow getWindow() {
        return this.window;
    }

    @Override
    public boolean isKeyDown(int keyCode) {
        return this.keysDown[keyCode];
    }
}
