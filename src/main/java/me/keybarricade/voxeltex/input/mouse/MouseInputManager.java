package me.keybarricade.voxeltex.input.mouse;

import me.keybarricade.voxeltex.window.VoxelTexWindow;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.*;

public class MouseInputManager implements MouseInputInterface {

    /**
     * Cursor position callback.
     */
    private GLFWCursorPosCallback cursorPosCallback;

    /**
     * Mouse button callback.
     */
    private GLFWMouseButtonCallback mouseButtonCallback;

    /**
     * The window this mouse input instance is for.
     */
    private VoxelTexWindow window;

    /**
     * List of pressed mouse buttons.
     */
    private boolean[] buttonDown = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

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
     * @param window Window this mouse input manager is for.
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

        // Create and set the mouse button callback
        glfwSetMouseButtonCallback(this.window.getWindowId(), mouseButtonCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                // Set whether the mouse button is pressed
                buttonDown[button] = action == GLFW_PRESS || action == GLFW_REPEAT;
            }
        });

        // Create and set the cursor position callback
        glfwSetCursorPosCallback(this.window.getWindowId(), cursorPosCallback = new GLFWCursorPosCallback() {
            public void invoke(long window, double xPos, double yPos) {
                float normX = (float) ((xPos - windowWidth / 2.0) / windowWidth * 2.0);
                float normY = (float) ((yPos - windowHeight / 2.0) / windowHeight * 2.0);
                mouseX = Math.max(-windowWidth / 2.0f, Math.min(windowWidth / 2.0f, normX));
                mouseY = Math.max(-windowHeight / 2.0f, Math.min(windowHeight / 2.0f, normY));
            }
        });
    }

    /**
     * Destroy the mouse input manager.
     */
    public void destroy() {
        // Release and destroy the cursor position input callback
        this.mouseButtonCallback.release();
        this.mouseButtonCallback = null;
        this.cursorPosCallback.release();
        this.cursorPosCallback = null;
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

    @Override
    public boolean isMouseButtonDown(int mouseButtonCode) {
        return this.buttonDown[mouseButtonCode];
    }

    /**
     * Center the mouse cursor on the window.
     */
    public void centerMouseCursor() {
        this.window.centerCursorPosition();
    }
}
