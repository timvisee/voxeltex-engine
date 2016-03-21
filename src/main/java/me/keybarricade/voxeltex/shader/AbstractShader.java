package me.keybarricade.voxeltex.shader;

import org.joml.*;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public abstract class AbstractShader implements ShaderInterface {

    /**
     * Program ID of this shader as defined by OpenGL.
     */
    protected int programId;

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
     * Retrieve uniform location of a variable from the shader.
     *
     * @param name Variable name.
     *
     * @return Uniform variable location.
     */
    private int getUniformLocation(String name) {
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
    public void setUniform4f(String name, Vector4f value) {
        glUniform4f(getUniformLocation(name), value.x, value.y, value.z, value.w);
    }

    @Override
    public void setUniform4i(String name, Vector4i value) {
        glUniform4i(getUniformLocation(name), value.x, value.y, value.z, value.w);
    }

    @Override
    public void setUniformMatrix4f(String name, Matrix4f matrix) {
        setUniformMatrix4f(name, matrix, BufferUtils.createFloatBuffer(16));
    }

    @Override
    public void setUniformMatrix4f(String name, Matrix4f matrix, FloatBuffer buff) {
        glUniformMatrix4fv(getUniformLocation(name), false, matrix.get(buff));
    }
}
