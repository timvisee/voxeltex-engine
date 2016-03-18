package me.keybarricade.voxeltex.component.camera;

import me.keybarricade.voxeltex.global.Input;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class FpsCameraComponent extends MouseLookCameraComponent {

    /**
     * The normal flying speed of the FPS camera.
     */
    @SuppressWarnings("FieldCanBeLocal")
    private float flySpeedNormal = 10.0f;

    /**
     * The fast flying speed of the FPS camera.
     */
    @SuppressWarnings("FieldCanBeLocal")
    private float flySpeedFast = 100.0f;

    @Override
    public void start() { }

    @Override
    public void update() { }

    @Override
    public void updateCamera() {
        // Update the super camera
        super.updateCamera();

        // Determine the movement speed, move 10 times faster when shift is held
        final float flySpeed = Input.isKeyDown(GLFW_KEY_LEFT_SHIFT) ? this.flySpeedFast : this.flySpeedNormal;

        // Get the linear velocity of the object, and set it back to it's identity
        Vector3f target = getTransform().getLinearVelocity().zero();

        // Determine the linear velocity based on user input
        target.set(
                (Input.isKeyDown(GLFW_KEY_D) ? flySpeed : 0) + (Input.isKeyDown(GLFW_KEY_A) ? -flySpeed : 0),
                (Input.isKeyDown(GLFW_KEY_SPACE) ? flySpeed : 0) + (Input.isKeyDown(GLFW_KEY_LEFT_CONTROL) ? -flySpeed : 0),
                (Input.isKeyDown(GLFW_KEY_W) ? -flySpeed : 0) + (Input.isKeyDown(GLFW_KEY_S) ? flySpeed : 0)
        );

        // Rotate the linear velocity vector based on the rotation of the object
        target.rotate(getTransform().getRotation());
    }
}
