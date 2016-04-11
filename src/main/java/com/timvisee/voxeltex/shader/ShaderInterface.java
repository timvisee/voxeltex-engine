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
import com.timvisee.voxeltex.scene.AbstractScene;
import org.joml.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public interface ShaderInterface {

    /**
     * Get the shader program ID as defined by OpenGL.
     *
     * @return Shader program ID.
     */
    int getProgramId();

    /**
     * Update the shader before using it, to update all parameters.
     * @param scene The scene this shader is rendered in.
     * @param material Material this shader should be updated for.
     */
    void update(AbstractScene scene, Material material);

    /**
     * Bind the compiled shader to the current OpenGL instance.
     */
    void bind();

    /**
     * Unbind the shader from the current OpenGL instance.
     */
    void unbind();

    /**
     * Dispose the shader.
     */
    void dispose();

    /**
     * Set a variable to a float value.
     *
     * @param name Variable name.
     * @param value Float value.
     */
    void setUniform1f(String name, float value);

    /**
     * Set a variable to an integer value.
     *
     * @param name Variable name.
     * @param value Integer value.
     */
    void setUniform1i(String name, int value);

    /**
     * Set a variable to an array of integers.
     *
     * @param name Variable name.
     * @param buff Integer buffer.
     */
    void setUniform1iv(String name, IntBuffer buff);

    /**
     * Set a variable to two float values.
     *
     * @param name Variable name.
     * @param value Float values.
     */
    void setUniform2f(String name, Vector2f value);

    /**
     * Set a variable to two integer values.
     *
     * @param name Variable name.
     * @param value Integer values.
     */
    void setUniform2i(String name, Vector2i value);

    /**
     * Set a variable to three float values.
     *
     * @param name Variable name.
     * @param value Float values.
     */
    void setUniform3f(String name, Vector3f value);

    /**
     * Set a variable to three integer values.
     *
     * @param name Variable name.
     * @param value Integer values.
     */
    void setUniform3i(String name, Vector3i value);

    /**
     * Set a variable to an array of three floats.
     *
     * @param name Variable name.
     * @param buff Float buffer.
     */
    void setUniform3fv(String name, FloatBuffer buff);

    /**
     * Set a variable to four float values.
     *
     * @param name Variable name.
     * @param value Float values.
     */
    void setUniform4f(String name, Vector4f value);

    /**
     * Set a variable to four integer values.
     *
     * @param name Variable name.
     * @param value Integer values.
     */
    void setUniform4i(String name, Vector4i value);

    /**
     * Set a variable to a four sized float array.
     *
     * @param name Variable name.
     * @param buff Float buffers.
     */
    void setUniform4fv(String name, FloatBuffer buff);

    /**
     * Set a variable to a float matrix.
     *
     * @param name Variable name.
     * @param matrix Float matrix.
     */
    void setUniformMatrix4f(String name, Matrix4f matrix);

    /**
     * Set a variable to a float matrix.
     *
     * @param name Variable name.
     * @param matrix Float matrix.
     * @param buff Float buffer. (allocation free)
     */
    void setUniformMatrix4f(String name, Matrix4f matrix, FloatBuffer buff);
}
