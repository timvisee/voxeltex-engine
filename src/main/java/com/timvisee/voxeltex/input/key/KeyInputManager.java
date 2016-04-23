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

package com.timvisee.voxeltex.input.key;

import com.timvisee.voxeltex.window.VoxelTexWindow;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

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
     * List of pressed once keys.
     */
    private boolean[] keysDownOnce = new boolean[GLFW.GLFW_KEY_LAST];

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
                // Set whether the key is pressed
                keysDown[key] = (keysDownOnce[key] = action == GLFW.GLFW_PRESS) || action == GLFW.GLFW_REPEAT;
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

    @Override
    public boolean isKeyDownOnce(int keyCode) {
        // Check whether the key is down
        boolean down = this.keysDownOnce[keyCode];

        // Reset the flag, and return the result
        this.keysDownOnce[keyCode] = false;
        return down;
    }
}
