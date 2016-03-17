package me.keybarricade.voxeltex.component.camera;

import me.keybarricade.voxeltex.input.Input;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_CONTROL;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.glLoadMatrixf;
import static org.lwjgl.opengl.GL11.glMatrixMode;

public class CameraComponent extends AbstractCameraComponent {

    /**
     * Float buffer for the matrix.
     */
    private FloatBuffer fb = BufferUtils.createFloatBuffer(16);

    @Override
    public void update() { }

    @Override
    public void updateCamera() {
//        Vector3f tmp = new Vector3f();
//
//        // Reset the camera acceleration
//        getTransform().getLinearAcceleration().zero();
//
//        // Define the acceleration factor
//        float accFactor = 6.0f;
//        float rotateZ = 0.0f;
//
//        // Handle camera inputs
//        if(Input.isKeyDown(GLFW_KEY_W))
//            getTransform().getLinearAcceleration().fma(-accFactor, getTransform().forward(tmp));
//        if(Input.isKeyDown(GLFW_KEY_S))
//            getTransform().getLinearAcceleration().fma(accFactor, getTransform().forward(tmp));
//        if(Input.isKeyDown(GLFW_KEY_D))
//            getTransform().getLinearAcceleration().fma(accFactor, getTransform().right(tmp));
//        if(Input.isKeyDown(GLFW_KEY_A))
//            getTransform().getLinearAcceleration().fma(-accFactor, getTransform().right(tmp));
//        if(Input.isKeyDown(GLFW_KEY_Q))
//            rotateZ -= 1.0f;
//        if(Input.isKeyDown(GLFW_KEY_E))
//            rotateZ += 1.0f;
//        if(Input.isKeyDown(GLFW_KEY_SPACE))
//            getTransform().getLinearAcceleration().fma(accFactor, getTransform().up(tmp));
//        if(Input.isKeyDown(GLFW_KEY_LEFT_CONTROL))
//            getTransform().getLinearAcceleration().fma(-accFactor, getTransform().up(tmp));
//
//        // Set the angular velocity of the camera
//        getTransform().getAngularVelocity().set(Input.getMouseY(), Input.getMouseX(), rotateZ);
    }

    @Override
    public Matrix4f apply(Matrix4f m) {
        // Get the camera position
        Vector3f pos = getTransform().getPosition();

        // Apply the camera transformation to the matrix
        // TODO: Should we do this the other way around?
        return m.rotate(getTransform().getRotation()).translate(-pos.x, -pos.y, -pos.z);
    }

    @Override
    public Matrix4f apply() {
        return apply(new Matrix4f());
    }

    @Override
    public void applyViewMatrix() {
        // Set the matrix mode
        glMatrixMode(GL_MODELVIEW);

        // Apply the matrix
        glLoadMatrixf(apply().get(fb));
    }
}
