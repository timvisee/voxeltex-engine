package me.keybarricade.voxeltex.renderer;

import me.keybarricade.voxeltex.VoxelTex;
import me.keybarricade.voxeltex.VoxelTexEngine;
import me.keybarricade.voxeltex.component.drawable.*;
import me.keybarricade.voxeltex.component.mesh.filter.MeshFilterComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.global.MainCamera;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.global.Input;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.mesh.generator.CubeMeshGenerator;
import me.keybarricade.voxeltex.prefab.camera.FpsCameraPrefab;
import me.keybarricade.voxeltex.prefab.primitive.CubePrefab;
import me.keybarricade.voxeltex.scene.Scene;
import me.keybarricade.voxeltex.shader.ShaderManager;
import me.keybarricade.voxeltex.shader.ShaderTracker;
import me.keybarricade.voxeltex.texture.Image;
import me.keybarricade.voxeltex.texture.ImageTracker;
import me.keybarricade.voxeltex.texture.Texture;
import me.keybarricade.voxeltex.texture.TextureTracker;
import me.keybarricade.voxeltex.global.Time;
import me.keybarricade.voxeltex.window.VoxelTexWindow;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
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
     * Engine instance this renderer was created from.
     */
    private VoxelTexEngine engine;

    /**
     * VoxelTex window where we'll be rendering on.
     */
    private VoxelTexWindow window;

    /**
     * Test scene.
     * TODO: Move this to a scene manager in the engine class
     */
    private Scene testScene = new Scene();

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

    // TODO: Move these
    private boolean created = false;
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

        if(!created) {
            // TODO: Put this in a different spot, where the engine has already loaded!

            // Create a grid renderer object
            GameObject axisObject = new GameObject("AxisGridRenderer");
            axisObject.addComponent(new AxisDrawComponent());
            this.testScene.addGameObject(axisObject);

            // Create a grid renderer object
            GameObject gridObject = new GameObject("AxisGridRenderer");
            gridObject.addComponent(new GridDrawComponent());
            this.testScene.addGameObject(gridObject);

            // Create an object for testing
            CubePrefab baseObject = new CubePrefab();
            baseObject.getTransform().setPosition(new Vector3f(0, 1, -1.0f));
            baseObject.getTransform().setAngularVelocity(new Vector3f(0, 0.5f, 0));
            testScene.addGameObject(baseObject);

            // Create a sub object for testing
            CubePrefab subObject1 = new CubePrefab();
            subObject1.getTransform().setAngularVelocity(new Vector3f(0.0f, 2.5f, 0.0f));
            subObject1.getTransform().setPosition(new Vector3f(1.5f, 1.5f, 0));
            baseObject.addChild(subObject1);

            // Create a sub object for testing
            CubePrefab subObject2 = new CubePrefab();
            subObject2.getTransform().setAngularVelocity(new Vector3f(0.0f, 3.0f, 0.0f));
            subObject2.getTransform().setPosition(new Vector3f(1.5f, 1.5f, 0));
            subObject1.addChild(subObject2);

            // Create a sub object for testing
            CubePrefab subObject3 = new CubePrefab();
            subObject3.getTransform().setPosition(new Vector3f(-1.5f, 1.5f, 0));
            subObject3.getTransform().setRotation(new Quaternionf(0.25f, 0, 0));
            subObject3.getTransform().setAngularVelocity(new Vector3f(0, -3.3f, 0));
            subObject1.addChild(subObject3);

            // Create a sub object for testing
            CubePrefab subObject4 = new CubePrefab();
            subObject4.getTransform().setPosition(new Vector3f(0, 1.35f, 0));
            subObject4.getTransform().setAngularVelocity(new Vector3f(3.1f, 4.2f, 2.9f));
            subObject3.addChild(subObject4);

            // Create the main camera object and set it's position
            FpsCameraPrefab fpsCameraPrefab = new FpsCameraPrefab();
            fpsCameraPrefab.getTransform().setPosition(new Vector3f(0.5f, 1.50f, 5.0f));
            testScene.addGameObject(fpsCameraPrefab);

            // Create a grid renderer object
            GameObject testAxis = new GameObject("TestAxis");
            testAxis.getTransform().setPosition(new Vector3f(-1.60f, -1.19f, -3.0f));
            testAxis.addComponent(new AxisDrawComponent());
            fpsCameraPrefab.addChild(testAxis);

            Texture texture = Texture.fromImage(Image.loadFromEngineAssets("images/box.png"));
            for(int i = 0; i < 8; i++) {
                // Load the texture shader
                GameObject quadTest = new GameObject("TextureRenderer");
                quadTest.getTransform().setPosition(new Vector3f(1.2f * i, 0, 0));
                quadTest.addComponent(new MeshFilterComponent(new CubeMeshGenerator().createMesh()));
                quadTest.addComponent(new MeshRendererComponent(new Material(ShaderManager.SHADER_DEFAULT_TEXTURED, texture)));
                this.testScene.addGameObject(quadTest);
            }

            created = true;
        }

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

            // Update the scene
            testScene.update();

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

            // Draw the scene
            testScene.draw();

            // Swap the buffers to render the frame
            this.window.glSwapBuffers();

            // Poll all events
            glfwPollEvents();
        }
    }
}
