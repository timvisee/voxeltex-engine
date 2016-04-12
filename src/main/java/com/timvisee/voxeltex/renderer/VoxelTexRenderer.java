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

package com.timvisee.voxeltex.renderer;

import com.timvisee.voxeltex.VoxelTex;
import com.timvisee.voxeltex.VoxelTexEngine;
import com.timvisee.voxeltex.global.Input;
import com.timvisee.voxeltex.global.MainCamera;
import com.timvisee.voxeltex.global.Time;
import com.timvisee.voxeltex.render.OverlayUtil;
import com.timvisee.voxeltex.shader.ShaderManager;
import com.timvisee.voxeltex.shader.ShaderTracker;
import com.timvisee.voxeltex.texture.ImageTracker;
import com.timvisee.voxeltex.texture.TextureTracker;
import com.timvisee.voxeltex.window.VoxelTexWindow;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.memAddress;

public class VoxelTexRenderer extends VoxelTexBaseRenderer {

    /**
     * Engine instance this renderer was created from.
     */
    private VoxelTexEngine engine;

    /**
     * VoxelTex window where we'll be rendering on.
     */
    private VoxelTexWindow window;

    /**
     * Callbacks.
     */
    private GLFWErrorCallback errorCallback;
    private GLFWFramebufferSizeCallback fbCallback;

    /**
     * Free memory in MBs last time the update debug loop ran.
     */
    private long debugLastFreeMemory = -1L;

    /**
     * Constructor.
     *
     * @param engine Engine instance this renderer was created from.
     */
    public VoxelTexRenderer(VoxelTexEngine engine) {
        // Set the engine
        this.engine = engine;

        // Create a window instance
        this.window = new VoxelTexWindow();
    }

    /**
     * Get the engine instance this renderer was created from.
     *
     * @return Engine.
     */
    public VoxelTexEngine getEngine() {
        return this.engine;
    }

    /**
     * Get the rendering window.
     *
     * @return Window.
     */
    public VoxelTexWindow getWindow() {
        return window;
    }

    /**
     * Initialize the renderer.
     */
    public void init() {
        // Show a status message
        System.out.println("Initializing " + VoxelTex.ENGINE_NAME + " renderer...");

        // Create and configure the error callback, make sure it was created successfully
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        if(glfwInit() != GL11.GL_TRUE)
            throw new IllegalStateException("Unable to initialize GLFW");

        // Set the default window hints
        this.window.glDefaultWindowHints();

        // Set the visibility and resizability of the window
        this.window.setHintVisible(false);
        this.window.setHintResizable(true);

        // Create the window
        this.window.glCreateWindow();

        // Initialize the input manager for this window
        Input.init(this.window);

        // Create the framebuffer size callback
        glfwSetFramebufferSizeCallback(this.window.getWindowId(), fbCallback = new GLFWFramebufferSizeCallback() {
            @Override
            public void invoke(long windowId, int width, int height) {
                // Update the window size
                if(width > 0 && height > 0)
                    window.setSize(width, height);
            }
        });

        // Center the window
        this.window.centerWindow();

        // Create an int buffer for the window
        IntBuffer framebufferSize = BufferUtils.createIntBuffer(2);
        nglfwGetFramebufferSize(this.window.getWindowId(), memAddress(framebufferSize), memAddress(framebufferSize) + 4);

        // Set the window size
        this.window.setSize(framebufferSize.get(0), framebufferSize.get(1));

        // Make the window context
        this.window.glMakeContextCurrent();

        // Set the swap interval (V-sync)
        glfwSwapInterval(0);

        // Show the window
        this.window.glShowWindow();

        // Center the cursor
        Input.centerMouseCursor();

        // Create the rendering capabilities, required by LWJGL
        GL.createCapabilities();

        // Print the OpenGL version
        System.out.println("OpenGL " + GL11.glGetString(GL11.GL_VERSION));

        // Set the clear color
        glClearColor(0.9f, 0.9f, 0.9f, 1.0f);

        // Enable depth testing
        glEnable(GL_DEPTH_TEST);

        // Load the engine shaders
        ShaderManager.load();

        // Initialize the Time object
        Time.init();

        // Show a status message
        System.out.println(VoxelTex.ENGINE_NAME + " renderer initialized successfully!");
    }

    /**
     * Run the renderer.
     * This will initialize and start the rendering loop.
     */
    public void start() {
        try {
            // Loop the renderer
            loop();

            // Destroy the window
            this.window.glDestroyWindow();

            // Dispose all tracked textures, images and shaders
            TextureTracker.disposeAll();
            ImageTracker.disposeAll();
            ShaderTracker.disposeAll();

            // Free all callbacks
            fbCallback.release();

        } finally {
            // Terminate the renderer
            glfwTerminate();

            // Release the error callback
            errorCallback.release();
        }
    }

    /**
     * Rendering loop.
     */
    public void loop() {
        // Show a status message
        System.out.println(VoxelTex.ENGINE_NAME + " engine renderer started!");

        // FloatBuffer for transferring the projection view matrix to OpenGL
        final FloatBuffer matrixFrameBuffer = BufferUtils.createFloatBuffer(16);

        // Update the time to ensure it starts from zero in the first loop
        Time.update();

        // Start the scene if it hasn't started yet
        getEngine().getSceneManager().start();

        // Start a loop until the window should close
        while(!this.window.glWindowShouldCloseBoolean()) {
            // Update time Time object
            Time.update();

            // Update the overlay utils class
            OverlayUtil.setWindow(getEngine());

            // Update the input
            Input.update();

            // Update the light manager
            getEngine().getSceneManager().getScene().getLightManager().update();

            // Update the position of the main camera
            MainCamera.update();

            // Update the current scene
            getEngine().getSceneManager().update();

            // Set the default viewport and clear the color and depth buffer
            this.window.glViewportDefault();
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Enable face culling for back faces
            glEnable(GL_CULL_FACE);
            glCullFace(GL_BACK);

            // Enable alpha channel usages
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

            // Enable 3D drawing
            enableDraw3d(matrixFrameBuffer);

            // Draw the current 3D scene
            getEngine().getSceneManager().draw();

            // Enable overlay drawing
            enableDrawOverlay(matrixFrameBuffer);

            // Draw the current overlay scene
            getEngine().getSceneManager().drawOverlay();

            // Update the debug information
            updateDebug();

            // Swap the buffers to render the frame
            this.window.glSwapBuffers();

            // Close the window if requested
            if(Input.isKeyDownOnce(GLFW_KEY_ESCAPE))
                this.window.glSetWindowShouldClose(true);

            // Poll all events
            glfwPollEvents();
        }

        // Show a status message
        System.out.println(VoxelTex.ENGINE_NAME + " engine renderer stopped");
    }

    /**
     * Enable 3D drawing.
     *
     * @param matrixFrameBuffer Matrix frame buffer.
     */
    private void enableDraw3d(FloatBuffer matrixFrameBuffer) {
        // Get the window width and height
        final int windowWidth = this.window.getWidth();
        final int windowHeight = this.window.getHeight();

        // Enable the projection mode to configure the camera, and revert back to model view mode
        glMatrixMode(GL_PROJECTION);
        glLoadMatrixf(
                MainCamera.getProjectionMatrix()
                        .setPerspective(
                                (float) Math.toRadians(45),
                                (float) windowWidth / windowHeight,
                                0.01f, 1000.0f)
                        .get(matrixFrameBuffer)
        );
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        glEnable(GL11.GL_DEPTH_TEST);
        //glEnable(GL11.GL_LIGHTING);
    }

    /**
     * Enable overlay drawing.
     *
     * @param matrixFrameBuffer Matrix frame buffer.
     */
    private void enableDrawOverlay(FloatBuffer matrixFrameBuffer) {
        // Disable lighting and depth testing because they aren't required in 2D mode
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        // Enable the projection mode to configure the camera, and revert back to model view mode
        glMatrixMode(GL_PROJECTION);
        glLoadMatrixf(
                MainCamera.getProjectionMatrix()
                        .setOrtho2D(0, 1, 0, 1)
                        .get(matrixFrameBuffer)
        );
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    /**
     * Update the debug information and status.
     */
    public void updateDebug() {
        // Compare the last free memory count with the current to see whether to update the debug status
        if((Runtime.getRuntime().freeMemory() / 1000000) != debugLastFreeMemory) {
            // Get the runtime instance
            Runtime runtime = Runtime.getRuntime();

            // Get the total, free and used memory
            final long totalMemory = runtime.totalMemory() / 1000000;
            final long freeMemory = runtime.freeMemory() / 1000000;
            final long usedMemory = totalMemory - freeMemory;

            // Set the window title
            this.window.setTitle(VoxelTex.getEngineNameFull() + " - Debug - Memory: " + (usedMemory) + " MB / " + (totalMemory) + " MB");

            // Update the last free memory
            this.debugLastFreeMemory = freeMemory;
        }
    }
}
