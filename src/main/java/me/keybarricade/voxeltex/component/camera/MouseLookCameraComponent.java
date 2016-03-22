package me.keybarricade.voxeltex.component.camera;

import me.keybarricade.voxeltex.global.Input;
import me.keybarricade.voxeltex.input.mouse.MouseInputManager;
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
        super.start();

        // Center the mouse cursor in the window first (Note: Doesn't work on Mac OS X based systems)
        Input.centerMouseCursor();

        // Capture the mouse cursor
        Input.setMouseCursorMode(MouseInputManager.CURSOR_MODE_CAPTURED);
    }

    @Override
    public void updateCamera() {
        // Update the super camera
        super.updateCamera();

        // Determine the mouse movement
        float yRot = Input.getMouseDeltaX() * this.mouseSensitivityX / getScene().getEngine().getRenderer().getWindow().getWidth();
        float xRot = Input.getMouseDeltaY() * this.mouseSensitivityY / getScene().getEngine().getRenderer().getWindow().getHeight();

        // Rotate the current object around it's axis to move the view
        getTransform().getRotation().rotateAxis(-xRot, 1, 0, 0);
        getTransform().getRotation().rotateAxis(-yRot, getTransform().up(Vector3fFactory.identity()));
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
