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

package me.keybarricade.voxeltex.global;

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
     * Update all attached input managers.
     * This should be called once in the beginning of each frame.
     */
    public static void update() {
        mouseInputManager.update();
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
     * Get the attached key input manager instance.
     *
     * @return Key input manager.
     */
    public static KeyInputManager getKeyInputManager() {
        return keyInputManager;
    }

    /**
     * Get the attached mouse input manager instance.
     *
     * @return Mouse input manager.
     */
    public static MouseInputManager getMouseInputManager() {
        return mouseInputManager;
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
     * Get the mouse cursor position of this tick/frame on the X axis.
     *
     * @return X position.
     */
    public static float getMouseX() {
        return mouseInputManager.getMouseX();
    }

    /**
     * Get the mouse cursor position of this tick/frame on the Y axis.
     *
     * @return Y position.
     */
    public static float getMouseY() {
        return mouseInputManager.getMouseY();
    }

    /**
     * Get the relative mouse movement of this tick/frame on the X axis.
     *
     * @return X movement.
     */
    public static float getMouseDeltaX() {
        return mouseInputManager.getMouseDeltaX();
    }

    /**
     * Get the relative mouse movement of this tick/frame on the Y axis.
     *
     * @return Y movement.
     */
    public static float getMouseDeltaY() {
        return mouseInputManager.getMouseDeltaY();
    }

    /**
     * Get the mouse cursor position on the X axis asynchronously. This getter fully ignores the mouse ticks and thus
     * returns real-time mouse cursor position data.
     * Usage of this method for frame-dependent calculations might be inaccurate.
     *
     * @return X position.
     */
    public static float getMouseAsyncX() {
        return mouseInputManager.getMouseAsyncX();
    }

    /**
     * Get the mouse cursor position on the Y axis asynchronously. This getter fully ignores the mouse ticks and thus
     * returns real-time mouse cursor position data.
     * Usage of this method for frame-dependent calculations might be inaccurate.
     *
     * @return Y position.
     */
    public static float getMouseAsyncY() {
        return mouseInputManager.getMouseAsyncY();
    }

    /**
     * Check whether a mouse button is pressed.
     *
     * @param mouseButtonCode Mouse button code, for example {@link GLFW#GLFW_MOUSE_BUTTON_LEFT} or {@link GLFW#GLFW_MOUSE_BUTTON_RIGHT}.
     *
     * @return True if the mouse button is down, false if not.
     */
    public static boolean isMouseButtonDown(int mouseButtonCode) {
        return mouseInputManager.isMouseButtonDown(mouseButtonCode);
    }

    /**
     * Center the mouse cursor on the window.
     * Note: This doesn't work on Mac OS X based systems.
     */
    public static void centerMouseCursor() {
        mouseInputManager.centerMouseCursor();
    }

    /**
     * Get the current mode of the mouse cursor.
     *
     * Will be one of:
     *  - CURSOR_MODE_NORMAL
     *  - CURSOR_MODE_HIDDEN
     *  - CURSOR_MODE_CAPTURED
     *
     * @return Mouse cursor mode.
     */
    public static int getMouseCursorMode() {
        return mouseInputManager.getMouseCursorMode();
    }

    /**
     * Set the mode of the mouse cursor.
     *
     * Choose from:
     *  - CURSOR_MODE_NORMAL
     *  - CURSOR_MODE_HIDDEN
     *  - CURSOR_MODE_CAPTURED
     *
     * @param cursorMode Mouse cursor mode.
     */
    public static void setMouseCursorMode(int cursorMode) {
        mouseInputManager.setMouseCursorMode(cursorMode);
    }
}
