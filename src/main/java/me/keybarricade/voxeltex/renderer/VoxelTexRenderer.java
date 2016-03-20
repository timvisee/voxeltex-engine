package me.keybarricade.voxeltex.renderer;

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

            // Dispose all tracked textures, images and shaders
            TextureTracker.disposeAll();
            ImageTracker.disposeAll();
            ShaderTracker.disposeAll();

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

        // Load the engine shaders
        ShaderManager.load();

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
