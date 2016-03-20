package me.keybarricade.voxeltex.shader;

import org.joml.*;

import java.nio.FloatBuffer;

public interface ShaderInterface {

    /**
     * Get the shader program ID as defined by OpenGL.
     *
     * @return Shader program ID.
     */
    int getProgramId();

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
     * Set a variable to four float values.
     *
     * @param name Variable name.
     * @param value Float values.
     */
    void setUniform4f(String name, Vector4f value);

    /**
     * Set a variable to two integer values.
     *
     * @param name Variable name.
     * @param value Integer values.
     */
    void setUniform4i(String name, Vector4i value);

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
