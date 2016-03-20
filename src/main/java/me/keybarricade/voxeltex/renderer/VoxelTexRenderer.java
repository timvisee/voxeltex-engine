package me.keybarricade.voxeltex.renderer;

import me.keybarricade.voxeltex.VoxelTex;
import me.keybarricade.voxeltex.VoxelTexEngine;
import me.keybarricade.voxeltex.global.MainCamera;
import me.keybarricade.voxeltex.global.Input;
import me.keybarricade.voxeltex.shader.ShaderManager;
import me.keybarricade.voxeltex.shader.ShaderTracker;
import me.keybarricade.voxeltex.texture.ImageTracker;
import me.keybarricade.voxeltex.texture.TextureTracker;
import me.keybarricade.voxeltex.global.Time;
import me.keybarricade.voxeltex.window.VoxelTexWindow;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

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

    // TODO: Move this
    public static Matrix4f mat = new Matrix4f();

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
            // TODO: Free/release the input callbacks
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
        System.out.println(VoxelTex.ENGINE_NAME + " engine started!");

        // FloatBuffer for transferring the projection view matrix to OpenGL
        FloatBuffer fb = BufferUtils.createFloatBuffer(16);

        // Update the time to ensure it starts from zero in the first loop
        Time.update();

        // Start a loop until the window should close
        while(!this.window.glWindowShouldCloseBoolean()) {
            // Update time Time object
            Time.update();

            // Update the position of the main camera
            MainCamera.update();

            // Update the current scene
            getEngine().getSceneManager().update();

            // Set the default viewport and clear the color and depth buffer
            this.window.glViewportDefault();
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Get the window width and height
            final int windowWidth = this.window.getWidth();
            final int windowHeight = this.window.getHeight();

            // Enable the projection mode to configure the camera, and revert back to model view mode
            glMatrixMode(GL_PROJECTION);
            glLoadMatrixf(mat.setPerspective((float) Math.toRadians(45), (float) windowWidth / windowHeight, 0.01f, 100.0f).get(fb));
            glMatrixMode(GL_MODELVIEW);

            // Draw the current scene
            getEngine().getSceneManager().draw();

            // Swap the buffers to render the frame
            this.window.glSwapBuffers();

            // Poll all events
            glfwPollEvents();
        }
    }
}
