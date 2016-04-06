/******************************************************************************
 * Copyright (c) Tim Visee 2016. All rights reserved.                         *
 *                                                                            *
 * @author Tim Visee                                                          *
 * @website http://timvisee.com/                                              *
 *                                                                            *
 * Open Source != No Copyright                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * You should have received a copy of The MIT License (MIT) along with this   *
 * program. If not, see <http://opensource.org/licenses/MIT/>.                *
 ******************************************************************************/

package me.keybarricade.voxeltex.component.camera;

import me.keybarricade.voxeltex.global.Input;
import me.keybarricade.voxeltex.input.mouse.MouseInputManager;
import org.joml.Vector3f;

public class MouseLookCameraComponent extends CameraComponent {

    /**
     * Mouse sensitivity on the X axis.
     */
    private float mouseSensitivityX = 2.0f;

    /**
     * Mouse sensitivity on the Y axis.
     */
    private float mouseSensitivityY = 2.0f;

    /**
     * Cached up vector.
     * Used to minimize object allocation which improves performance.
     */
    private static final Vector3f upVectorCache = new Vector3f();

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

        // Only update the mouse look direction if the cursor mode is correct
        if(Input.getMouseCursorMode() != MouseInputManager.CURSOR_MODE_CAPTURED)
            return;

        // Determine the mouse movement
        float yRot = Input.getMouseDeltaX() * this.mouseSensitivityX / getScene().getEngine().getRenderer().getWindow().getWidth();
        float xRot = Input.getMouseDeltaY() * this.mouseSensitivityY / getScene().getEngine().getRenderer().getWindow().getHeight();

        // Synchronize to ensure we don't use the cached vector twice at the same time
        synchronized(upVectorCache) {
            // Rotate the current object around it's axis to move the view
            getTransform().getRotation().rotateAxis(-xRot, 1, 0, 0);
            getTransform().getRotation().rotateAxis(-yRot, getTransform().up(upVectorCache));
        }
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
