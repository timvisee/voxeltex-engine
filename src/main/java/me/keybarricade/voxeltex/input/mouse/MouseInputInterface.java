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

package me.keybarricade.voxeltex.input.mouse;

import org.lwjgl.glfw.GLFW;

public interface MouseInputInterface {

    /**
     * Get the mouse cursor position of this tick/frame on the X axis.
     *
     * @return X position.
     */
    float getMouseX();

    /**
     * Get the mouse cursor position of this tick/frame on the Y axis.
     *
     * @return Y position.
     */
    float getMouseY();

    /**
     * Get the relative mouse movement of this tick/frame on the X axis.
     *
     * @return X movement.
     */
    float getMouseDeltaX();

    /**
     * Get the relative mouse movement of this tick/frame on the Y axis.
     *
     * @return Y movement.
     */
    float getMouseDeltaY();

    /**
     * Get the mouse cursor position on the X axis asynchronously. This getter fully ignores the mouse ticks and thus
     * returns real-time mouse cursor position data.
     * Usage of this method for frame-dependent calculations might be inaccurate.
     *
     * @return X position.
     */
    float getMouseAsyncX();

    /**
     * Get the mouse cursor position on the Y axis asynchronously. This getter fully ignores the mouse ticks and thus
     * returns real-time mouse cursor position data.
     * Usage of this method for frame-dependent calculations might be inaccurate.
     *
     * @return Y position.
     */
    float getMouseAsyncY();

    /**
     * Check whether a mouse button is pressed.
     *
     * @param mouseButtonCode Mouse button code, for example {@link GLFW#GLFW_MOUSE_BUTTON_LEFT} or {@link GLFW#GLFW_MOUSE_BUTTON_RIGHT}.
     *
     * @return True if the mouse button is down, false if not.
     */
    boolean isMouseButtonDown(int mouseButtonCode);

    /**
     * Center the mouse cursor on the window.
     * Note: This doesn't work on Mac OS X based systems.
     */
    void centerMouseCursor();

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
    int getMouseCursorMode();

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
    void setMouseCursorMode(int cursorMode);
}
