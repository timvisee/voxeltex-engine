package me.keybarricade.voxeltex.shader;

import me.keybarricade.voxeltex.resource.engine.EngineAssetLoader;
import me.keybarricade.voxeltex.util.FileUtil;
import org.joml.*;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

    /**
     * Program ID of this shader as defined by OpenGL.
     */
    private int programId;

    /**
     * Vertex shader source.
     */
    private static String vertexSource;

    /**
     * Fragment shader source.
     */
    private static String fragmentSource;

    /**
     * Constructor.
     *
     * @param programId Program ID of this shader.
     */
    private Shader(int programId) {
        this.programId = programId;
    }

    /**
     * Load a shader from the specified path.
     *
     * @param vertexPath Vertex shader path.
     * @param fragmentPath Fragment shader path.
     *
     * @return Loaded shader.
     */
    public static Shader fromPath(String vertexPath, String fragmentPath) {
        // Load the shader sources
        vertexSource = FileUtil.read(vertexPath);
        fragmentSource = FileUtil.read(fragmentPath);

        // Load and return the shader
        return Shader.load();
    }

    /**
     * Load a shader from engine resources.
     *
     * @param vertexResourcePath Vertex shader engine resource path.
     * @param fragmentResourcePath Fragment shader engine resource path.
     *
     * @return Loaded shader.
     */
    public static Shader fromEngineAssets(String vertexResourcePath, String fragmentResourcePath) {
        // Load the shader sources
        vertexSource = EngineAssetLoader.getInstance().loadResourceString(vertexResourcePath);
        fragmentSource = EngineAssetLoader.getInstance().loadResourceString(fragmentResourcePath);

        // Load and return the shader
        return Shader.load();
    }

    /**
     * Load a shader from raw sources.
     *
     * @param vertexSource Vertex shader source.
     * @param fragmentSource Fragment shader source.
     *
     * @return Loaded shader.
     */
    public static Shader fromSource(String vertexSource, String fragmentSource) {
        // Set the shader sources
        Shader.vertexSource = vertexSource;
        Shader.fragmentSource = fragmentSource;

        // Load and return the shader
        return Shader.load();
    }

    /**
     * Get the shader program ID as defined by OpenGL.
     *
     * @return Shader program ID.
     */
    public int getProgramId() {
        return programId;
    }

    /**
     * Get the vertex shader source.
     *
     * @return Vertex shader course.
     */
    public static String getVertexSource() {
        return vertexSource;
    }

    /**
     * Get the fragment shader source.
     *
     * @return Fragment shader source.
     */
    public static String getFragmentSource() {
        return fragmentSource;
    }

    /**
     * Load the actual shader from the given sources.
     *
     * @return Loaded shader.
     */
    private static Shader load() {
        // Create an OpenGL shader program
        int program = glCreateProgram();

        // Create the shaders from the sources
        int vertex = glCreateShader(GL_VERTEX_SHADER);
        int fragment = glCreateShader(GL_FRAGMENT_SHADER);

        // Attach the vertex shader source and compile it
        glShaderSource(vertex, vertexSource);
        glCompileShader(vertex);

        // Check for compiling errors
        int compile = glGetShaderi(vertex, GL_COMPILE_STATUS);
        if(compile == GL_FALSE) {
            // TODO: Use proper logger here
            System.out.println("Failed to compile the vertex shader!");
            System.out.println(glGetShaderInfoLog(vertex));

            // Delete the shader before returning
            glDeleteShader(vertex);
            return null;
        }

        // Attach the fragment shader source and compile it
        glShaderSource(fragment, fragmentSource);
        glCompileShader(fragment);

        // Check for compiling errors
        compile = glGetShaderi(fragment, GL_COMPILE_STATUS);
        if(compile == GL_FALSE) {
            // TODO: Use proper logger here
            System.out.println("Failed to compile the fragment shader!");
            System.out.println(glGetShaderInfoLog(fragment));

            // Delete the shader before returning
            glDeleteShader(fragment);
            return null;
        }

        // Attach both compiled shaders to the program
        glAttachShader(program, vertex);
        glAttachShader(program, fragment);

        // Link the shader program to OpenGL and link it
        glLinkProgram(program);
        glValidateProgram(program);

        // Shaders have been attached to the program, delete their compiled sources to save memory
        glDeleteShader(vertex);
        glDeleteShader(fragment);

        // Return the created shader
        return new Shader(program);
    }

    /**
     * Bind the compiled shader to the current OpenGL instance.
     */
    public void bind() {
        glUseProgram(this.programId);
    }

    /**
     * Unbind any shader from the current OpenGL instance.
     */
    public static void unbind() {
        glUseProgram(0);
    }

    /**
     * Dispose the shader.
     */
    public void dispose() {
        glDeleteProgram(this.programId);
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

    /**
     * Set a variable to a float value.
     *
     * @param name Variable name.
     * @param value Float value.
     */
    public void setUniform1f(String name, float value) {
        glUniform1f(getUniformLocation(name), value);
    }

    /**
     * Set a variable to an integer value.
     *
     * @param name Variable name.
     * @param value Integer value.
     */
    public void setUniform1i(String name, int value) {
        glUniform1i(getUniformLocation(name), value);
    }

    /**
     * Set a variable to two float values.
     *
     * @param name Variable name.
     * @param value Float values.
     */
    public void setUniform2f(String name, Vector2f value) {
        glUniform2f(getUniformLocation(name), value.x, value.y);
    }

    /**
     * Set a variable to two integer values.
     *
     * @param name Variable name.
     * @param value Integer values.
     */
    public void setUniform2i(String name, Vector2i value) {
        glUniform2i(getUniformLocation(name), value.x, value.y);
    }

    /**
     * Set a variable to three float values.
     *
     * @param name Variable name.
     * @param value Float values.
     */
    public void setUniform3f(String name, Vector3f value) {
        glUniform3f(getUniformLocation(name), value.x, value.y, value.z);
    }

    /**
     * Set a variable to three integer values.
     *
     * @param name Variable name.
     * @param value Integer values.
     */
    public void setUniform3i(String name, Vector3i value) {
        glUniform3i(getUniformLocation(name), value.x, value.y, value.z);
    }

    /**
     * Set a variable to four float values.
     *
     * @param name Variable name.
     * @param value Float values.
     */
    public void setUniform4f(String name, Vector4f value) {
        glUniform4f(getUniformLocation(name), value.x, value.y, value.z, value.w);
    }

    /**
     * Set a variable to two integer values.
     *
     * @param name Variable name.
     * @param value Integer values.
     */
    public void setUniform4i(String name, Vector4i value) {
        glUniform4i(getUniformLocation(name), value.x, value.y, value.z, value.w);
    }

    /**
     * Set a variable to a float matrix.
     *
     * @param name Variable name.
     * @param matrix Float matrix.
     */
    public void setUniformMatrix4f(String name, Matrix4f matrix) {
        setUniformMatrix4f(name, matrix, BufferUtils.createFloatBuffer(16));
    }

    /**
     * Set a variable to a float matrix.
     *
     * @param name Variable name.
     * @param matrix Float matrix.
     * @param buff Float buffer. (allocation free)
     */
    public void setUniformMatrix4f(String name, Matrix4f matrix, FloatBuffer buff) {
        glUniformMatrix4fv(getUniformLocation(name), false, matrix.get(buff));
    }
}
