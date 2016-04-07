/******************************************************************************
 * Copyright (c) Tim Visee 2016. All rights reserved.                         *
 *                                                                            *
 * @author Tim Visee                                                          *
 * @website http://timvisee.com/                                              *
 *                                                                            *
 * Open Source != No Copyright                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * You should have received a copy of The MIT License (MIT) along with this   *
 * program. If not, see <http://opensource.org/licenses/MIT/>.                *
 ******************************************************************************/

package me.keybarricade.voxeltex.window;

import me.keybarricade.voxeltex.VoxelTex;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class VoxelTexWindow {

    /**
     * Window title.
     */
    private String title = VoxelTex.getEngineNameFull() + " Window";

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
    private long window = -1;

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
        // Set the window title
        this.title = title;

        // Update the title if the window is already created
        if(isCreated())
            glfwSetWindowTitle(this.window, title);
    }

    /**
     * Get window width.
     *
     * @return Window width.
     */
    public int getWidth() {
        return this.width;
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
        return this.height;
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
     * Check whether the window is created and/or visible.
     *
     * @return True if the window is created and/or visible, false if not.
     */
    public boolean isCreated() {
        return this.window >= 0;
    }

    /**
     * Center the cursor to the middle of the screen.
     */
    public void centerCursorPosition() {
        glfwSetCursorPos(this.window, this.width / 2, this.height / 2);
    }

    /**
     * Center the window.
     */
    public void centerWindow() {
        // Get the video mode
        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        // Set the window position
        glfwSetWindowPos(this.window, (videoMode.width() - this.width) / 2, (videoMode.height() - this.height) / 2);
    }

    /**
     * Create the window.
     */
    public void glCreateWindow() {
        // Create the window and store it's ID
        this.window = glfwCreateWindow(this.width, this.height, this.title, glfwGetPrimaryMonitor(), NULL);

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
        // Destroy the window
        glfwDestroyWindow(this.window);

        // Reset the window handle
        this.window = -1;
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
    public void glViewportDefault() {
        glViewport(0, 0, width, height);
    }

    /**
     * Set whether the window should close.
     *
     * @param close True to close the window.
     */
    public void glSetWindowShouldClose(boolean close) {
        glfwSetWindowShouldClose(this.window, close ? GL11.GL_TRUE : GL11.GL_TRUE);
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

    /**
     * Set the default window hints.
     */
    public void glDefaultWindowHints() {
        glfwDefaultWindowHints();
    }

    /**
     * Set a window hint.
     *
     * @param target Hint target.
     * @param hint Hint value.
     */
    public void glWindowHint(int target, int hint) {
        glfwWindowHint(target, hint);
    }

    /**
     * Set a window hint as boolean.
     *
     * @param target Hint target.
     * @param bool Hint value as boolean.
     */
    public void glWindowHintBoolean(int target, boolean bool) {
        glWindowHint(target, bool ? GL_TRUE : GL_FALSE);
    }

    /**
     * Set the window visibility hint.
     *
     * @param visible True if visible, false if not.
     */
    public void setHintVisible(boolean visible) {
        glWindowHintBoolean(GLFW_VISIBLE, visible);
    }

    /**
     * Set the window resizability hint.
     *
     * @param resizable True if resizable, false if not.
     */
    public void setHintResizable(boolean resizable) {
        glWindowHintBoolean(GLFW_RESIZABLE, resizable);
    }

    /**
     * Set the window focused hint.
     *
     * @param focused True if focused, false if not.
     */
    public void setHintFocussed(boolean focused) {
        glWindowHintBoolean(GLFW_FOCUSED, focused);
    }
}
