package me.keybarricade.voxeltex.component.camera;

import me.keybarricade.voxeltex.global.MainCamera;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

public class CameraComponent extends AbstractCameraComponent {

    /**
     * Float buffer for the matrix.
     */
    private FloatBuffer fb = BufferUtils.createFloatBuffer(16);

    @Override
    public void create() { }

    @Override
    public void start() {
        // Register the camera as main camera
        MainCamera.setCamera(this);
    }

    @Override
    public synchronized void update() { }

    @Override
    public void updateCamera() { }

    @Override
    public Matrix4f apply(Matrix4f m) {
        // Get the camera position
        Vector3f pos = getTransform().getPosition();

        // Apply the camera transformation to the matrix
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
