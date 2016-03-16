package me.keybarricade.voxeltex.window;

import me.keybarricade.voxeltex.VoxelTex;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.system.MemoryUtil.NULL;

public class VoxelTexWindow {

    /**
     * Window title.
     */
    private String title = VoxelTex.ENGINE_NAME + " window";

    /**
     * Window width.
     */
    private int width = 800;

    /**
     * Window height.
     */
    private int height = 600;

    /**
     * Window key.
     */
    private long window;

    /**
     * Constructor.
     */
    public VoxelTexWindow() { }

    /**
     * Get the frame title.
     *
     * @return Frame title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the frame title.
     *
     * @param title Frame title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get window width.
     *
     * @return Window width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set window width.
     *
     * @param width Window width.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Get window height.
     *
     * @return Window height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set window height.
     *
     * @param height Window height.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Set the window size.
     *
     * @param width Window width.
     * @param height Window height.
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Get the window ID.
     *
     * @return Window ID.
     */
    public long getWindowId() {
        return window;
    }

    /**
     * Set the window ID.
     *
     * @param window Window ID.
     */
    public void setWindowId(long window) {
        this.window = window;
    }

    /**
     * Center the cursor to the middle of the screen.
     */
    public void centerCursorPosition() {
        glfwSetCursorPos(this.window, this.width / 2, this.height / 2);
    }

    /**
     * Create the window.
     */
    public void glCreateWindow() {
        // Create the window and store it's ID
        this.window = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

        // Make sure the window was successfully created
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");
    }

    /**
     * Show the window.
     */
    public void glShowWindow() {
        glfwShowWindow(this.window);
    }

    /**
     * Destroy the window.
     */
    public void glDestroyWindow() {
        glfwDestroyWindow(this.window);
    }

    /**
     * Make the window context current.
     */
    public void glMakeContextCurrent() {
        glfwMakeContextCurrent(this.window);
    }

    /**
     * Swap buffers.
     */
    public void glSwapBuffers() {
        glfwSwapBuffers(this.window);
    }

    /**
     * Set the GL viewport with the default configuration.
     */
    // TODO: Make this configurable!
    public void glViewportDefault() {
        glViewport(0, 0, width, height);
    }

    /**
     * Check whether the window should close.
     *
     * @return Result.
     */
    public int glWindowShouldClose() {
        return glfwWindowShouldClose(window);
    }

    /**
     * Check whether the window should close.
     *
     * @return True if the window should close, false if not.
     */
    public boolean glWindowShouldCloseBoolean() {
        return glWindowShouldClose() != GL_FALSE;
    }
}
