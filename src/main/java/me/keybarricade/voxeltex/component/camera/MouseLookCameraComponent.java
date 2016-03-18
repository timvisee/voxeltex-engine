package me.keybarricade.voxeltex.component.camera;

import me.keybarricade.voxeltex.global.Input;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;

public class MouseLookCameraComponent extends CameraComponent {

    /**
     * Mouse sensitivity on the X axis.
     */
    private float mouseSensitivityX = 2.0f;

    /**
     * Mouse sensitivity on the Y axis.
     */
    private float mouseSensitivityY = 2.0f;

    @Override
    public void start() {
        // Call the super
        super.start();
    }

    @Override
    public void update() { }

    @Override
    public void updateCamera() {
        // Update the super camera
        super.updateCamera();

        // Determine the mouse movement
        float yRot = Input.getMouseX() * this.mouseSensitivityX;
        float xRot = Input.getMouseY() * this.mouseSensitivityY;

        // Rotate the current object around it's axis to move the view
        getTransform().getRotation().rotateAxis(-xRot, 1, 0, 0);
        getTransform().getRotation().rotateAxis(-yRot, getTransform().up(Vector3fFactory.identity()));

        // Center the mouse cursor
        Input.centerMouseCursor();
    }

    /**
     * Get the mouse sensitivity on the X coordinate.
     *
     * @return Sensitivity.
     */
    public float getMouseSensitivityX() {
        return mouseSensitivityX;
    }

    /**
     * Set the mouse sensitivity on the X coordinate.
     *
     * @param mouseSensitivityX Sensitivity.
     */
    public void setMouseSensitivityX(float mouseSensitivityX) {
        this.mouseSensitivityX = mouseSensitivityX;
    }

    /**
     * Get the mouse sensitivity on the Y coordinate.
     *
     * @return Sensitivity.
     */
    public float getMouseSensitivityY() {
        return mouseSensitivityY;
    }

    /**
     * Set the mouse sensitivity on the Y coordinate.
     *
     * @param mouseSensitivityY Sensitivity.
     */
    public void setMouseSensitivityY(float mouseSensitivityY) {
        this.mouseSensitivityY = mouseSensitivityY;
    }
}
