package me.keybarricade.voxeltex.renderer;

import me.keybarricade.voxeltex.component.drawable.*;
import me.keybarricade.voxeltex.global.MainCamera;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.global.Input;
import me.keybarricade.voxeltex.prefab.camera.FpsCameraPrefab;
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

    private boolean created = false;
    public static Matrix4f mat = new Matrix4f();

    /**
     * Rendering loop.
     */
    public void loop() {
        // Create the rendering capabilities, required by LWJGL
        GL.createCapabilities();

        if(!created) {
            // TODO: Put this in a different spot, where the engine has already loaded!

            // Create a grid renderer object
            GameObject axisObject = new GameObject("AxisGridRenderer");
            axisObject.addComponent(new AxisDrawComponent());
            axisObject.getTransform().setPosition(new Vector3f(0.05f));
            this.testScene.addGameObject(axisObject);

            // Create a grid renderer object
            GameObject gridObject = new GameObject("AxisGridRenderer");
            gridObject.addComponent(new GridDrawComponent());
            this.testScene.addGameObject(gridObject);

            // Create an object for testing
            GameObject baseObject = new GameObject("BaseObject");
            baseObject.getTransform().setPosition(new Vector3f(0, 1, -1.0f));
            baseObject.getTransform().setAngularVelocity(new Vector3f(0, 0.5f, 0));
            baseObject.addComponent(new CubeDrawComponent());
            testScene.addGameObject(baseObject);

            // Create a sub object for testing
            GameObject subObject1 = new GameObject("SubObject1");
            subObject1.getTransform().setAngularVelocity(new Vector3f(0.0f, 2.5f, 0.0f));
            subObject1.getTransform().setPosition(new Vector3f(1.5f, 1.5f, 0));
            subObject1.addComponent(new CubeDrawComponent());
            baseObject.addChild(subObject1);

            // Create a sub object for testing
            GameObject subObject2 = new GameObject("SubObject2");
            subObject2.getTransform().setAngularVelocity(new Vector3f(0.0f, 3.0f, 0.0f));
            subObject2.getTransform().setPosition(new Vector3f(1.5f, 1.5f, 0));
            subObject2.addComponent(new CubeDrawComponent());
            subObject1.addChild(subObject2);

            // Create a sub object for testing
            GameObject subObject3 = new GameObject("SubObject3");
            subObject3.getTransform().setPosition(new Vector3f(-1.5f, 1.5f, 0));
            subObject3.getTransform().setAngularVelocity(new Vector3f(0.0f, 1.2f, 0.0f));
            subObject3.addComponent(new CubeDrawComponent());
            subObject1.addChild(subObject3);

            // Create the main camera object and set it's position
            FpsCameraPrefab fpsCameraPrefab = new FpsCameraPrefab();
            fpsCameraPrefab.getTransform().setPosition(new Vector3f(0.5f, 1.50f, 5.0f));
            testScene.addGameObject(fpsCameraPrefab);

            // Create a grid renderer object
            GameObject testAxis = new GameObject("TestAxis");
            testAxis.getTransform().setPosition(new Vector3f(-1.35f, -1.10f, -3.0f));
            testAxis.addComponent(new AxisDrawComponent());
            fpsCameraPrefab.addChild(testAxis);

            // Create a grid renderer object
            GameObject quadTest = new GameObject("TextureRenderer");
            quadTest.getTransform().setPosition(new Vector3f(1.5f, 0, 0));
            quadTest.addComponent(new TexturedQuadDrawComponent());
            this.testScene.addGameObject(quadTest);

            created = true;
        }

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
        mat = new Matrix4f();
        // FloatBuffer for transferring matrices to OpenGL
        FloatBuffer fb = BufferUtils.createFloatBuffer(16);

        // Start a loop until the window should close
        while(!this.window.glWindowShouldCloseBoolean()) {
            // Update time Time object
            Time.update();

            // Update the position of the main camera
            MainCamera.update();

            // Update the test scene
            testScene.update();

            // Set the default viewport
            this.window.glViewportDefault();

            // Clear the color and depth buffer
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Get the window width and height
            final int windowWidth = this.window.getWidth();
            final int windowHeight = this.window.getHeight();

            // Enable the projection mode and configure the camera
            glMatrixMode(GL_PROJECTION);
            glLoadMatrixf(mat.setPerspective((float) Math.toRadians(45), (float) windowWidth / windowHeight, 0.01f, 100.0f).get(fb));

            // Enable the model view mode
            glMatrixMode(GL_MODELVIEW);

            //int vbo = glGenBuffers();

            // Draw the test scene
            testScene.draw();

            // Swap the buffers
            this.window.glSwapBuffers();

            // Poll all events
            glfwPollEvents();
        }
    }
}
