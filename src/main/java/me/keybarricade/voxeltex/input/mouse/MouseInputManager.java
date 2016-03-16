package me.keybarricade.voxeltex.input.mouse;

import me.keybarricade.voxeltex.window.VoxelTexWindow;
import org.lwjgl.glfw.GLFWCursorPosCallback;

import static org.lwjgl.glfw.GLFW.*;

public class MouseInputManager implements MouseInputInterface {

    /**
     * Mouse callback.
     */
    private GLFWCursorPosCallback cursorPosCallback;

    /**
     * The window this mouse input instance is for.
     */
    private VoxelTexWindow window;

    /**
     * The x coordinate of the mouse cursor.
     */
    public float mouseX;

    /**
     * The y coordinate of the mouse cursor.
     */
    public float mouseY;

    /**
     * Constructor.
     *
     * @param window Window this mouse input manager is for.
     */
    public MouseInputManager(VoxelTexWindow window) {
        this(window, false);
    }

    /**
     * Constructor.
     *
     * @param window Window this key input manager is for.
     * @param init True to initialize, false if not.
     */
    public MouseInputManager(VoxelTexWindow window, boolean init) {
        this.window = window;

        // Initialize
        if(init)
            init();
    }

    /**
     * Initialize.
     */
    public void init() {
        // Get the window width and height
        final int windowWidth = this.window.getWidth();
        final int windowHeight = this.window.getHeight();

        // Create the cursor position callback
        glfwSetCursorPosCallback(this.window.getWindowId(), cursorPosCallback = new GLFWCursorPosCallback() {
            public void invoke(long window, double xPos, double yPos) {
                float normX = (float) ((xPos - windowWidth / 2.0) / windowWidth * 2.0);
                float normY = (float) ((yPos - windowHeight / 2.0) / windowHeight * 2.0);
                mouseX = Math.max(-windowWidth / 2.0f, Math.min(windowWidth / 2.0f, normX));
                mouseY = Math.max(-windowHeight / 2.0f, Math.min(windowHeight / 2.0f, normY));
            }
        });

        // Register the mouse cursor position callback for the given window.
        glfwSetCursorPosCallback(window.getWindowId(), cursorPosCallback);
    }

    /**
     * Destroy the mouse input manager.
     */
    public void destroy() {
        // Release and destroy the cursor position input callback
        cursorPosCallback.release();
        cursorPosCallback = null;
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
    public float getMouseX() {
        return mouseX;
    }

    /**
     * Get the y coordinate of the mouse cursor.
     *
     * @return Y coordinate.
     */
    public float getMouseY() {
        return mouseY;
    }

    /**
     * Center the mouse cursor on the window.
     */
    public void centerMouseCursor() {
        this.window.centerCursorPosition();
    }
}
