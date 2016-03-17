package me.keybarricade.voxeltex.component.camera;

import me.keybarricade.voxeltex.input.Input;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.glLoadMatrixf;
import static org.lwjgl.opengl.GL11.glMatrixMode;

public class CameraComponent extends AbstractCameraComponent {

    /**
     * Float buffer for the matrix.
     */
    private FloatBuffer fb = BufferUtils.createFloatBuffer(16);

    /**
     * Mouse sensitivity on the X axis.
     */
    private float mouseSensX = 2.0f;

    /**
     * Mouse sensitivity on the Y axis.
     */
    private float mouseSensY = 2.0f;

    @Override
    public void update() { }

    @Override
    public void updateCamera() {
        // Determine the movement speed, move 10 times faster when shift is held
        final float speed = Input.isKeyDown(GLFW_KEY_LEFT_SHIFT) ? 100f : 10f;

        // Get the linear velocity of the object, and set it back to it's identity
        Vector3f target = getTransform().getLinearVelocity().zero();

        // Determine the linear velocity based on user input
        target.set(
                (Input.isKeyDown(GLFW_KEY_D) ? speed : 0) + (Input.isKeyDown(GLFW_KEY_A) ? -speed : 0),
                (Input.isKeyDown(GLFW_KEY_SPACE) ? speed : 0) + (Input.isKeyDown(GLFW_KEY_LEFT_CONTROL) ? -speed : 0),
                (Input.isKeyDown(GLFW_KEY_W) ? -speed : 0) + (Input.isKeyDown(GLFW_KEY_S) ? speed : 0)
        );

        // Rotate the linear velocity vector based on the rotation of the object
        target.rotate(getTransform().getRotation());

        // Determine the mouse movement
        float yRot = Input.getMouseX() * mouseSensX;
        float xRot = Input.getMouseY() * mouseSensY;

        // Rotate the current object around it's axis to move the view
        getTransform().getRotation().rotateAxis(-xRot, 1, 0, 0);
        getTransform().getRotation().rotateAxis(-yRot, getTransform().up(Vector3fFactory.identity()));

        // Center the mouse cursor
        Input.centerMouseCursor();
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
