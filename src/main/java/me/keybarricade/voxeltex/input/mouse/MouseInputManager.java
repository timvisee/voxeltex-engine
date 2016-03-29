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

import me.keybarricade.voxeltex.window.VoxelTexWindow;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseInputManager implements MouseInputInterface {

    /**
     * Normal mouse cursor mode.
     * The mouse cursor behaves as normal.
     */
    public static final int CURSOR_MODE_NORMAL = GLFW.GLFW_CURSOR_NORMAL;

    /**
     * Hidden mouse cursor mode.
     * The mouse cursor behaves as normal, but is hidden.
     */
    public static final int CURSOR_MODE_HIDDEN = GLFW.GLFW_CURSOR_HIDDEN;

    /**
     * Captured mouse cursor mode.
     * The mouse cursor is captured by the window and thus invisible. The mouse moves around like normal, but in a
     * virtually infinite space.
     */
    public static final int CURSOR_MODE_CAPTURED = GLFW.GLFW_CURSOR_DISABLED;

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
     * Local flag which defines whether the mouse cursor handler has started.
     */
    private boolean mouseCursorHandlerActive = false;

    /**
     * The X position of the mouse relative to the window (real time).
     */
    private float mouseX = 0f;

    /**
     * The Y position of the mouse relative to the window (real time).
     */
    private float mouseY = 0f;

    /**
     * The mouse X position of the current mouse tick.
     */
    private float mouseTickX = 0f;

    /**
     * The mouse Y position of the current mouse tick.
     */
    private float mouseTickY = 0f;

    /**
     * The mouse X relative movement since the last mouse tick.
     */
    private float mouseDeltaX = 0f;

    /**
     * The mouse Y relative movement since the last mouse tick.
     */
    private float mouseDeltaY = 0f;

    /**
     * The cursor mode the mouse is currently in.
     */
    private int cursorMode = CURSOR_MODE_NORMAL;

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
        // Create and set the mouse button callback
        GLFW.glfwSetMouseButtonCallback(this.window.getWindowId(), mouseButtonCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                // Set whether the mouse button is pressed
                buttonDown[button] = action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT;
            }
        });

        // Create and set the cursor position callback
        GLFW.glfwSetCursorPosCallback(this.window.getWindowId(), cursorPosCallback = new GLFWCursorPosCallback() {
            public void invoke(long window, double xPos, double yPos) {
                mouseX = (float) xPos;
                mouseY = (float) yPos;
            }
        });
    }

    /**
     * Update/tick the mouse cursor input.
     */
    public void update() {
        // Make sure the mouse input handler started before calculating the mouse delta
        if(this.mouseCursorHandlerActive) {
            this.mouseDeltaX = this.mouseX - this.mouseTickX;
            this.mouseDeltaY = this.mouseY - this.mouseTickY;
        }

        // Store the mouse positions to make them readable next frame
        this.mouseTickX = this.mouseX;
        this.mouseTickY = this.mouseY;

        // Start the mouse handler when coordinates are received
        if(!this.mouseCursorHandlerActive && (this.mouseX != 0 || this.mouseY != 0))
            this.mouseCursorHandlerActive = true;
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
        return this.window;
    }

    @Override
    public float getMouseX() {
        return this.mouseTickX;
    }

    @Override
    public float getMouseY() {
        return this.mouseTickY;
    }

    @Override
    public float getMouseDeltaX() {
        return this.mouseDeltaX;
    }

    @Override
    public float getMouseDeltaY() {
        return this.mouseDeltaY;
    }

    @Override
    public float getMouseAsyncX() {
        return this.mouseX;
    }

    @Override
    public float getMouseAsyncY() {
        return this.mouseY;
    }

    @Override
    public boolean isMouseButtonDown(int mouseButtonCode) {
        return this.buttonDown[mouseButtonCode];
    }

    @Override
    public void centerMouseCursor() {
        this.window.centerCursorPosition();
    }

    @Override
    public int getMouseCursorMode() {
        return this.cursorMode;
    }

    @Override
    public void setMouseCursorMode(int cursorMode) {
        // Set the cursor mode
        GLFW.glfwSetInputMode(this.window.getWindowId(), GLFW.GLFW_CURSOR, cursorMode);

        // Update the cursor mode field
        this.cursorMode = cursorMode;
    }
}
