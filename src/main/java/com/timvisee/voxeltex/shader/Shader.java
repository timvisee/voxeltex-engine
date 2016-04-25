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

package com.timvisee.voxeltex.shader;

import com.timvisee.voxeltex.material.Material;
import com.timvisee.voxeltex.shader.raw.AbstractRawShader;
import com.timvisee.voxeltex.structure.scene.AbstractScene;

public class Shader extends AbstractShader {

    /**
     * Constructor.
     *
     * @param programId OpenGL shader program ID.
     */
    public Shader(int programId) {
        super(programId);
    }

    /**
     * Constructor.
     *
     * @param rawShader Raw shader.
     */
    public Shader(AbstractRawShader rawShader) {
        // Compile the shader and use it's program ID
        this(rawShader.compile());
    }

    @Override
    public void update(AbstractScene scene, Material material) {
        // Configure the projection and view matrix of the shader
//        setUniformMatrix4f("projectionMatrix", MainCamera.getProjectionMatrix());
//        setUniformMatrix4f("viewMatrix", MainCamera.createCameraViewMatrix());
    }
}
