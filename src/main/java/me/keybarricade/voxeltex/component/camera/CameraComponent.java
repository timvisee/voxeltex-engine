package me.keybarricade.voxeltex.component.camera;

import me.keybarricade.voxeltex.gameobject.KinematicGameObject;
import me.keybarricade.voxeltex.renderer.VoxelTexRenderer;
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
        // Get the camera owner
        KinematicGameObject owner = (KinematicGameObject) getOwner();

        Vector3f tmp = new Vector3f();

        // Reset the camera acceleration
        owner.getLinearAcceleration().zero();

        // Define the acceleration factor
        float accFactor = 6.0f;
        float rotateZ = 0.0f;

        // Handle camera inputs
        if(VoxelTexRenderer.keyDown[GLFW_KEY_W])
            owner.getLinearAcceleration().fma(accFactor, forward(tmp));
        if(VoxelTexRenderer.keyDown[GLFW_KEY_S])
            owner.getLinearAcceleration().fma(-accFactor, forward(tmp));
        if(VoxelTexRenderer.keyDown[GLFW_KEY_D])
            owner.getLinearAcceleration().fma(accFactor, right(tmp));
        if(VoxelTexRenderer.keyDown[GLFW_KEY_A])
            owner.getLinearAcceleration().fma(-accFactor, right(tmp));
        if(VoxelTexRenderer.keyDown[GLFW_KEY_Q])
            rotateZ -= 1.0f;
        if(VoxelTexRenderer.keyDown[GLFW_KEY_E])
            rotateZ += 1.0f;
        if(VoxelTexRenderer.keyDown[GLFW_KEY_SPACE])
            owner.getLinearAcceleration().fma(accFactor, up(tmp));
        if(VoxelTexRenderer.keyDown[GLFW_KEY_LEFT_CONTROL])
            owner.getLinearAcceleration().fma(-accFactor, up(tmp));

        // Set the angular velocity of the camera
        owner.getAngularVelocity().set(VoxelTexRenderer.mouseY, VoxelTexRenderer.mouseX, rotateZ);
    }

    @Override
    public Matrix4f apply(Matrix4f m) {
        // Get the camera position
        Vector3f pos = getOwner().getPosition();

        // Apply the camera transformation to the matrix
        return m.rotate(getOwner().getRotation()).translate(-pos.x, -pos.y, -pos.z);
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
