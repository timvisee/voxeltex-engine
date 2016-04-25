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

package com.timvisee.voxeltex.structure.component.camera;

import com.timvisee.voxeltex.global.Input;
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
    public void updateCamera() {
        // Update the super camera
        super.updateCamera();

        // Determine the movement speed, move 10 times faster when shift is held
        final float flySpeed = Input.isKeyDown(GLFW_KEY_LEFT_SHIFT) ? this.flySpeedFast : this.flySpeedNormal;

        // Get the linear velocity of the object, and set it back to it's identity
        Vector3f target = getTransform().getLinearVelocity().zero();

        // Determine the linear velocity based on user input
        target.add(
                (Input.isKeyDown(GLFW_KEY_D) ? flySpeed : 0) + (Input.isKeyDown(GLFW_KEY_A) ? -flySpeed : 0),
                0.0f,
                (Input.isKeyDown(GLFW_KEY_W) ? -flySpeed : 0) + (Input.isKeyDown(GLFW_KEY_S) ? flySpeed : 0)
        );

        // Rotate the linear velocity vector based on the rotation of the object
        target.rotate(getTransform().getRotation());

        // Add the vertical movement
        target.add(
                0.0f,
                (Input.isKeyDown(GLFW_KEY_SPACE) ? flySpeed : 0) + (Input.isKeyDown(GLFW_KEY_LEFT_CONTROL) ? -flySpeed : 0),
                0.0f
        );
    }
}
