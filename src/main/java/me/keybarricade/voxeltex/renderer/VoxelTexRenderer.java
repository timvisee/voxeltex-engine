package me.keybarricade.voxeltex.renderer;

import me.keybarricade.voxeltex.camera.FreeCamera;
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
     * VoxelTex window where we'll be rendering on.
     */
    private VoxelTexWindow window;

    /**
     * Camera.
     */
    FreeCamera camera = new FreeCamera();

    /**
     * Callbacks.
     */
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;
    private GLFWFramebufferSizeCallback fbCallback;
    private GLFWCursorPosCallback cpCallback;

    /**
     * Mouse position.
     */
    float mouseX, mouseY;

    /**
     * Key list.
     */
    boolean[] keyDown = new boolean[GLFW.GLFW_KEY_LAST];

    /**
     * Constructor.
     */
    public VoxelTexRenderer() {
        // Construct the window
        this.window = new VoxelTexWindow();

        // Set the camera position
        this.camera.getPosition().set(1, 5, 10);
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

        // Get the window width and height
        final int windowWidth = this.window.getWidth();
        final int windowHeight = this.window.getHeight();

        // Create the key callback
        glfwSetKeyCallback(this.window.getWindowId(), keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                // Schedule a renderer window close when the escape key is pressed
                if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                    glfwSetWindowShouldClose(window, GL_TRUE);

                // Set whether the key is pressed
                keyDown[key] = action == GLFW_PRESS || action == GLFW_REPEAT;
            }
        });

        // Create the framebuffer size callback
        glfwSetFramebufferSizeCallback(this.window.getWindowId(), fbCallback = new GLFWFramebufferSizeCallback() {
            @Override
            public void invoke(long windowId, int width, int height) {
                // Update the window size
                if(width > 0 && height > 0)
                    window.setSize(width, height);
            }
        });

        // Create the cursor position callback
        glfwSetCursorPosCallback(this.window.getWindowId(), cpCallback = new GLFWCursorPosCallback() {
            public void invoke(long window, double xPos, double yPos) {
                float normX = (float) ((xPos - windowWidth/2.0) / windowWidth * 2.0);
                float normY = (float) ((yPos - windowHeight/2.0) / windowHeight * 2.0);
                mouseX = Math.max(-windowWidth/2.0f, Math.min(windowWidth/2.0f, normX));
                mouseY = Math.max(-windowHeight/2.0f, Math.min(windowHeight/2.0f, normY));
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
        this.window.centerCursorPosition();
    }

    /**
     * Rendering loop.
     */
    public void loop() {
        // Create the rendering capabilities, required by LWJGL
        GL.createCapabilities();

        // Set the clear (default) color
        glClearColor(0.9f, 0.9f, 0.9f, 1.0f);

        // Enable depth testing
        glEnable(GL_DEPTH_TEST);

        // Set the default line width
        // TODO: Set this somewhere else?
        glLineWidth(1.4f);

        // Remember the current time.
        long lastTime = System.nanoTime();

        Vector3f tmp = new Vector3f();
        Matrix4f mat = new Matrix4f();
        // FloatBuffer for transferring matrices to OpenGL
        FloatBuffer fb = BufferUtils.createFloatBuffer(16);

        // Start a loop until the window should close
        while(!this.window.glWindowShouldCloseBoolean()) {
            // Reset t he camera acceleration
            this.camera.getAngularVelocity().zero();

            // Define the acceleration factor
            float accFactor = 6.0f;
            float rotateZ = 0.0f;

            // Handle camera inputs
            if (keyDown[GLFW_KEY_W])
                this.camera.getLinearAcceleration().fma(accFactor, this.camera.forward(tmp));
            if (keyDown[GLFW_KEY_S])
                this.camera.getLinearAcceleration().fma(-accFactor, this.camera.forward(tmp));
            if (keyDown[GLFW_KEY_D])
                this.camera.getLinearAcceleration().fma(accFactor, this.camera.right(tmp));
            if (keyDown[GLFW_KEY_A])
                this.camera.getLinearAcceleration().fma(-accFactor, this.camera.right(tmp));
            if (keyDown[GLFW_KEY_Q])
                rotateZ -= 1.0f;
            if (keyDown[GLFW_KEY_E])
                rotateZ += 1.0f;
            if (keyDown[GLFW_KEY_SPACE])
                this.camera.getLinearAcceleration().fma(accFactor, this.camera.up(tmp));
            if (keyDown[GLFW_KEY_LEFT_CONTROL])
                this.camera.getLinearAcceleration().fma(-accFactor, this.camera.up(tmp));

            // Set the angular velocity of the camera
            this.camera.getAngularVelocity().set(mouseY, mouseX, rotateZ);

            // Compute the delta time
            // TODO: Create a class for this!
            long thisTime = System.nanoTime();
            float diff = (float) ((thisTime - lastTime) / 1E9);
            lastTime = thisTime;

            // Update the camera
            camera.update(diff);

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

            // Obtain the camera's view matrix
            glMatrixMode(GL_MODELVIEW);
            glLoadMatrixf(camera.apply(mat.identity()).get(fb));

            // Render the testing grid and cube
            renderGrid();
            renderCube();

            // Swap the buffers
            this.window.glSwapBuffers();

            // Poll all events
            glfwPollEvents();
        }
    }



    /**
     * Rendering tests.
     */

    /**
     * Render a cube for testing.
     */
    void renderCube() {
        // Enter quad drawing mode
        glBegin(GL_QUADS);

        // Set the color and draw the quad
        glColor3f(   0.0f,  0.0f,  0.2f );
        glVertex3f(  0.5f, -0.5f, -0.5f );
        glVertex3f(  0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f, -0.5f, -0.5f );

        // Set the color and draw the quad
        glColor3f(   0.0f,  0.0f,  1.0f );
        glVertex3f(  0.5f, -0.5f,  0.5f );
        glVertex3f(  0.5f,  0.5f,  0.5f );
        glVertex3f( -0.5f,  0.5f,  0.5f );
        glVertex3f( -0.5f, -0.5f,  0.5f );

        // Set the color and draw the quad
        glColor3f(   1.0f,  0.0f,  0.0f );
        glVertex3f(  0.5f, -0.5f, -0.5f );
        glVertex3f(  0.5f,  0.5f, -0.5f );
        glVertex3f(  0.5f,  0.5f,  0.5f );
        glVertex3f(  0.5f, -0.5f,  0.5f );

        // Set the color and draw the quad
        glColor3f(   0.2f,  0.0f,  0.0f );
        glVertex3f( -0.5f, -0.5f,  0.5f );
        glVertex3f( -0.5f,  0.5f,  0.5f );
        glVertex3f( -0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f, -0.5f, -0.5f );

        // Set the color and draw the quad
        glColor3f(   0.0f,  1.0f,  0.0f );
        glVertex3f(  0.5f,  0.5f,  0.5f );
        glVertex3f(  0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f,  0.5f,  0.5f );

        // Set the color and draw the quad
        glColor3f(   0.0f,  0.2f,  0.0f );
        glVertex3f(  0.5f, -0.5f, -0.5f );
        glVertex3f(  0.5f, -0.5f,  0.5f );
        glVertex3f( -0.5f, -0.5f,  0.5f );
        glVertex3f( -0.5f, -0.5f, -0.5f );

        // Finish drawing
        glEnd();
    }

    /**
     * Render a grid for testing.
     */
    void renderGrid() {
        // Enable line drawing mode
        glBegin(GL_LINES);

        // Set the grid color
        glColor3f(0.2f, 0.2f, 0.2f);

        // Draw the grid
        for(int i = -20; i <= 20; i++) {
            glVertex3f(-20.0f, 0.0f, i);
            glVertex3f(20.0f, 0.0f, i);
            glVertex3f(i, 0.0f, -20.0f);
            glVertex3f(i, 0.0f, 20.0f);
        }

        // Finish drawing
        glEnd();
    }
}
