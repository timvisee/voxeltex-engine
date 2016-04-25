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

import com.timvisee.voxeltex.global.MainCamera;
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
    public void start() {
        // Call the super
        super.start();

        // Register the camera as main camera
        MainCamera.setCamera(this);
    }

    @Override
    public void destroy() {
        // Reset the MainCamera instance if it was attached to this camera component
        if(MainCamera.getCamera().equals(this))
            MainCamera.setCamera(null);

        // Call the super
        super.destroy();
    }

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
