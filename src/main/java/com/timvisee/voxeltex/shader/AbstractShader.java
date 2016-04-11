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

import org.joml.*;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;

public abstract class AbstractShader implements ShaderInterface {

    /**
     * Program ID of this shader as defined by OpenGL.
     */
    protected int programId;

    /**
     * Cached float buffer for a matrix.
     * This is used to minimize object allocation which drastically improves performance.
     */
    private static final FloatBuffer matrixFloatBufferCache = BufferUtils.createFloatBuffer(16);

    /**
     * Constructor.
     *
     * @param programId Program ID of this shader.
     */
    protected AbstractShader(int programId) {
        // Set the fields
        this.programId = programId;

        // Track the shader
        ShaderTracker.trackShader(this);
    }

    @Override
    public int getProgramId() {
        return programId;
    }

    @Override
    public void bind() {
        // Bind the shader program to OpenGL
        glUseProgram(this.programId);
    }

    @Override
    public void unbind() {
        glUseProgram(0);
    }

    @Override
    public void dispose() {
        // Dispose the shader from OpenGL
        glDeleteProgram(this.programId);

        // Untrack the shader
        ShaderTracker.untrackShader(this);
    }

    /**
     * Retrieve an attribute location of this shader.
     *
     * @param name Attribute name.
     *
     * @return Attribute location.
     */
    public int getAttributeLocation(String name) {
        return glGetAttribLocation(this.programId, name);
    }

    /**
     * Retrieve uniform location of a variable from the shader.
     *
     * @param name Variable name.
     *
     * @return Uniform variable location.
     */
    public int getUniformLocation(String name) {
        return glGetUniformLocation(this.programId, name);
    }

    @Override
    public void setUniform1f(String name, float value) {
        glUniform1f(getUniformLocation(name), value);
    }

    @Override
    public void setUniform1i(String name, int value) {
        glUniform1i(getUniformLocation(name), value);
    }

    @Override
    public void setUniform1iv(String name, IntBuffer buff) {
        glUniform1iv(getUniformLocation(name), buff);
    }

    @Override
    public void setUniform2f(String name, Vector2f value) {
        glUniform2f(getUniformLocation(name), value.x, value.y);
    }

    @Override
    public void setUniform2i(String name, Vector2i value) {
        glUniform2i(getUniformLocation(name), value.x, value.y);
    }

    @Override
    public void setUniform3f(String name, Vector3f value) {
        glUniform3f(getUniformLocation(name), value.x, value.y, value.z);
    }

    @Override
    public void setUniform3i(String name, Vector3i value) {
        glUniform3i(getUniformLocation(name), value.x, value.y, value.z);
    }

    @Override
    public void setUniform3fv(String name, FloatBuffer buff) {
        glUniform3fv(getUniformLocation(name), buff);
    }

    @Override
    public void setUniform4f(String name, Vector4f value) {
        glUniform4f(getUniformLocation(name), value.x, value.y, value.z, value.w);
    }

    @Override
    public void setUniform4i(String name, Vector4i value) {
        glUniform4i(getUniformLocation(name), value.x, value.y, value.z, value.w);
    }

    @Override
    public void setUniform4fv(String name, FloatBuffer buff) {
        glUniform4fv(getUniformLocation(name), buff);
    }

    @Override
    public void setUniformMatrix4f(String name, Matrix4f matrix) {
        // Synchronize so we don't modify the cached float buffer from multiple places at the same time
        synchronized(matrixFloatBufferCache) {
            setUniformMatrix4f(name, matrix, matrixFloatBufferCache);
        }
    }

    @Override
    public void setUniformMatrix4f(String name, Matrix4f matrix, FloatBuffer buff) {
        glUniformMatrix4fv(getUniformLocation(name), false, matrix.get(buff));
    }
}
