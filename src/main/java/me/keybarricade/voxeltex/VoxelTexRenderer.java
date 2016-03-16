package me.keybarricade.voxeltex;

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

public class VoxelTexRenderer {

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
    float mouseX, mouseY;
    boolean[] keyDown = new boolean[GLFW.GLFW_KEY_LAST];

    /**
     * Constructor.
     */
    public VoxelTexRenderer() {
        // Construct the window
        this.window = new VoxelTexWindow();

        // Set the camera position
        this.camera.position.set(1, 5, 10);
    }

    /**
     * Get the VoxelTex window.
     *
     * @return VoxelTex window.
     */
    public VoxelTexWindow getWindow() {
        return this.window;
    }

    public void run() {
        try {
            init();
            loop();

            this.window.glDestroyWindow();
            // TODO: Fix
            //keyCallback.free();
            //fbCallback.free();
            //cpCallback.free();
        } finally {
            glfwTerminate();
            // TODO: Fix
            //errorCallback.free();
        }
    }

    void init() {
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        if (glfwInit() != GL11.GL_TRUE)
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure our window
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        // Create the window
        this.window.glCreateWindow();

        // Get the window width and height
        final int windowWidth = this.window.getWidth();
        final int windowHeight = this.window.getHeight();

        glfwSetKeyCallback(this.window.getWindowId(), keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                    glfwSetWindowShouldClose(window, GL_TRUE);

                if (action == GLFW_PRESS || action == GLFW_REPEAT) {
                    keyDown[key] = true;
                } else {
                    keyDown[key] = false;
                }
            }
        });
        glfwSetFramebufferSizeCallback(this.window.getWindowId(), fbCallback = new GLFWFramebufferSizeCallback() {
            @Override
            public void invoke(long windowId, int w, int h) {
                if (w > 0 && h > 0) {
                    window.setSize(w, h);
                }
            }
        });
        glfwSetCursorPosCallback(this.window.getWindowId(), cpCallback = new GLFWCursorPosCallback() {
            public void invoke(long window, double xpos, double ypos) {
                float normX = (float) ((xpos - windowWidth/2.0) / windowWidth * 2.0);
                float normY = (float) ((ypos - windowHeight/2.0) / windowHeight * 2.0);
                mouseX = Math.max(-windowWidth/2.0f, Math.min(windowWidth/2.0f, normX));
                mouseY = Math.max(-windowHeight/2.0f, Math.min(windowHeight/2.0f, normY));
            }
        });

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(this.window.getWindowId(), (vidmode.width() - windowWidth) / 2, (vidmode.height() - windowHeight) / 2);

        IntBuffer framebufferSize = BufferUtils.createIntBuffer(2);
        nglfwGetFramebufferSize(this.window.getWindowId(), memAddress(framebufferSize), memAddress(framebufferSize) + 4);

        this.window.setSize(framebufferSize.get(0), framebufferSize.get(1));

        this.window.glMakeContextCurrent();
        glfwSwapInterval(0);
        this.window.glShowWindow();
        this.window.centerCursorPosition();
    }

    void renderCube() {
        glBegin(GL_QUADS);
        glColor3f(   0.0f,  0.0f,  0.2f );
        glVertex3f(  0.5f, -0.5f, -0.5f );
        glVertex3f(  0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f, -0.5f, -0.5f );
        glColor3f(   0.0f,  0.0f,  1.0f );
        glVertex3f(  0.5f, -0.5f,  0.5f );
        glVertex3f(  0.5f,  0.5f,  0.5f );
        glVertex3f( -0.5f,  0.5f,  0.5f );
        glVertex3f( -0.5f, -0.5f,  0.5f );
        glColor3f(   1.0f,  0.0f,  0.0f );
        glVertex3f(  0.5f, -0.5f, -0.5f );
        glVertex3f(  0.5f,  0.5f, -0.5f );
        glVertex3f(  0.5f,  0.5f,  0.5f );
        glVertex3f(  0.5f, -0.5f,  0.5f );
        glColor3f(   0.2f,  0.0f,  0.0f );
        glVertex3f( -0.5f, -0.5f,  0.5f );
        glVertex3f( -0.5f,  0.5f,  0.5f );
        glVertex3f( -0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f, -0.5f, -0.5f );
        glColor3f(   0.0f,  1.0f,  0.0f );
        glVertex3f(  0.5f,  0.5f,  0.5f );
        glVertex3f(  0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f,  0.5f,  0.5f );
        glColor3f(   0.0f,  0.2f,  0.0f );
        glVertex3f(  0.5f, -0.5f, -0.5f );
        glVertex3f(  0.5f, -0.5f,  0.5f );
        glVertex3f( -0.5f, -0.5f,  0.5f );
        glVertex3f( -0.5f, -0.5f, -0.5f );
        glEnd();
    }

    void renderGrid() {
        glBegin(GL_LINES);
        glColor3f(0.2f, 0.2f, 0.2f);
        for (int i = -20; i <= 20; i++) {
            glVertex3f(-20.0f, 0.0f, i);
            glVertex3f(20.0f, 0.0f, i);
            glVertex3f(i, 0.0f, -20.0f);
            glVertex3f(i, 0.0f, 20.0f);
        }
        glEnd();
    }

    void loop() {
        GL.createCapabilities();

        // Set the clear color
        glClearColor(0.9f, 0.9f, 0.9f, 1.0f);
        // Enable depth testing
        glEnable(GL_DEPTH_TEST);
        glLineWidth(1.4f);

        // Remember the current time.
        long lastTime = System.nanoTime();

        Vector3f tmp = new Vector3f();
        Matrix4f mat = new Matrix4f();
        // FloatBuffer for transferring matrices to OpenGL
        FloatBuffer fb = BufferUtils.createFloatBuffer(16);

        // Start a loop until the window should close
        while(!this.window.glWindowShouldCloseBoolean()) {
            // Update camera input
            camera.linAcc.zero();
            float accFactor = 6.0f;
            float rotateZ = 0.0f;
            if (keyDown[GLFW_KEY_W])
                camera.linAcc.fma(accFactor, camera.forward(tmp));
            if (keyDown[GLFW_KEY_S])
                camera.linAcc.fma(-accFactor, camera.forward(tmp));
            if (keyDown[GLFW_KEY_D])
                camera.linAcc.fma(accFactor, camera.right(tmp));
            if (keyDown[GLFW_KEY_A])
                camera.linAcc.fma(-accFactor, camera.right(tmp));
            if (keyDown[GLFW_KEY_Q])
                rotateZ -= 1.0f;
            if (keyDown[GLFW_KEY_E])
                rotateZ += 1.0f;
            if (keyDown[GLFW_KEY_SPACE])
                camera.linAcc.fma(accFactor, camera.up(tmp));
            if (keyDown[GLFW_KEY_LEFT_CONTROL])
                camera.linAcc.fma(-accFactor, camera.up(tmp));
            camera.angVel.set(mouseY, mouseX, rotateZ);

            /* Compute delta time */
            long thisTime = System.nanoTime();
            float diff = (float) ((thisTime - lastTime) / 1E9);
            lastTime = thisTime;
            /* And let the camera make its update */
            camera.update(diff);

            this.window.glViewportDefault();
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Get the window width and height
            final int windowWidth = this.window.getWidth();
            final int windowHeight = this.window.getHeight();

            glMatrixMode(GL_PROJECTION);
            glLoadMatrixf(mat.setPerspective((float) Math.toRadians(45), (float) windowWidth / windowHeight, 0.01f, 100.0f).get(fb));

            /*
             * Obtain the camera's view matrix
             */
            glMatrixMode(GL_MODELVIEW);
            glLoadMatrixf(camera.apply(mat.identity()).get(fb));

            renderGrid();
            renderCube();

            this.window.glSwapBuffers();
            glfwPollEvents();
        }
    }
}
