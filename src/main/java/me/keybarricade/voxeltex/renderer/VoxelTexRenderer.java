package me.keybarricade.voxeltex.renderer;

import me.keybarricade.voxeltex.component.camera.AbstractCameraComponent;
import me.keybarricade.voxeltex.component.camera.CameraComponent;
import me.keybarricade.voxeltex.component.drawable.CubeDrawComponent;
import me.keybarricade.voxeltex.component.drawable.GridDrawComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.input.Input;
import me.keybarricade.voxeltex.scene.Scene;
import me.keybarricade.voxeltex.time.Time;
import me.keybarricade.voxeltex.window.VoxelTexWindow;
import org.joml.Matrix4f;
import org.joml.Vector3f;
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
     * Test scene.
     */
    // TODO: Remove this!
    private Scene testScene = new Scene();

    /**
     * VoxelTex window where we'll be rendering on.
     */
    private VoxelTexWindow window;

    /**
     * The main camera component used for rendering.
     */
    private AbstractCameraComponent mainCameraComponent;

    /**
     * Callbacks.
     */
    private GLFWErrorCallback errorCallback;
    private GLFWFramebufferSizeCallback fbCallback;

    /**
     * Constructor.
     */
    public VoxelTexRenderer() {
        // Construct the window
        this.window = new VoxelTexWindow();
    }

    /**
     * Get the VoxelTex window.
     *
     * @return VoxelTex window.
     */
    public VoxelTexWindow getWindow() {
        return this.window;
    }

    /**
     * Run the renderer.
     * This will initialize and start the rendering loop.
     */
    public void run() {
        // Create an object for testing
        GameObject myObj = new GameObject("TestObject");

        // Set some angular velocity
        myObj.getTransform().setAngularVelocity(new Vector3f(0, 0, 1.0f));

        // Add the grid renderer and cube component
        myObj.addComponent(new GridDrawComponent());
        myObj.addComponent(new CubeDrawComponent());

        // Create the main camera object and set it's position
        GameObject camObj = new GameObject("MainCamera");
        camObj.getTransform().setPosition(new Vector3f(0, 1, 10));

        // Create and add the camera component
        this.mainCameraComponent = new CameraComponent();
        camObj.addComponent(this.mainCameraComponent);

        // Add the game object
        testScene.addGameObject(myObj);
        testScene.addGameObject(camObj);

        try {
            // Initialize the renderer
            init();

            // Loop the renderer
            loop();

            // Destroy the window
            this.window.glDestroyWindow();

            // Free all callbacks
            // TODO: Fix this, free methods not available anymore
            //keyCallback.free();
            //fbCallback.free();
            //cpCallback.free();

        } finally {
            // Terminate the renderer
            glfwTerminate();

            // Free all callbacks
            // TODO: Fix this, free method not available anymore
            //errorCallback.free();
        }
    }

    /**
     * Initialize the renderer.
     */
    public void init() {
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

        // Initialize the input manager
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

        // Set the swap interval
        glfwSwapInterval(0);

        // Show the window
        this.window.glShowWindow();

        // Center the cursor
        Input.centerMouseCursor();
    }

    /**
     * Rendering loop.
     */
    public void loop() {
        // Create the rendering capabilities, required by LWJGL
        GL.createCapabilities();

        // Initialize the Time object
        Time.init();

        // Set the clear (default) color
        glClearColor(0.9f, 0.9f, 0.9f, 1.0f);

        // Enable depth testing
        glEnable(GL_DEPTH_TEST);

        // Set the default line width
        // TODO: Set this somewhere else?
        glLineWidth(1.0f);

        Vector3f tmp = new Vector3f();
        Matrix4f mat = new Matrix4f();
        // FloatBuffer for transferring matrices to OpenGL
        FloatBuffer fb = BufferUtils.createFloatBuffer(16);

        // Start a loop until the window should close
        while(!this.window.glWindowShouldCloseBoolean()) {
            // Update time Time object
            Time.update();

            // Update the camera
            // TODO: Can we do this in the regular update call?
            mainCameraComponent.updateCamera();

            // Update the test scene
            testScene.update();

            // Set the default viewport
            this.window.glViewportDefault();

            // Clear the color and depth buffer
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Get the window width and height
            final int windowWidth = this.window.getWidth();
            final int windowHeight = this.window.getHeight();

            // Enable matrix mode
            glMatrixMode(GL_PROJECTION);
            glLoadMatrixf(mat.setPerspective((float) Math.toRadians(45), (float) windowWidth / windowHeight, 0.01f, 100.0f).get(fb));

            // Apply the camera's view matrix
            this.mainCameraComponent.applyViewMatrix();

            // Draw the test scene
            testScene.draw();

            // Swap the buffers
            this.window.glSwapBuffers();

            // Poll all events
            glfwPollEvents();
        }
    }
}
