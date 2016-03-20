package me.keybarricade.voxeltex.shader.raw;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class RawShader extends AbstractRawShader {

    /**
     * Vertex shader source.
     */
    protected String vertexSource;

    /**
     * Fragment shader source.
     */
    protected String fragmentSource;

    /**
     * Constructor.
     */
    public RawShader() { }

    /**
     * Constructor.
     *
     * @param vertexSource Vertex shader source.
     * @param fragmentSource Fragment shader source.
     */
    public RawShader(String vertexSource, String fragmentSource) {
        this.vertexSource = vertexSource;
        this.fragmentSource = fragmentSource;
    }

    @Override
    public String getVertexShader() {
        return this.vertexSource;
    }

    @Override
    public void setVertexShader(String vertexSource) {
        this.vertexSource = vertexSource;
    }

    @Override
    public String getFragmentShader() {
        return this.fragmentSource;
    }

    @Override
    public void setFragmentShader(String fragmentSource) {
        this.fragmentSource = fragmentSource;
    }

    @Override
    protected int compile() {
        // TODO: Use proper logger here
        // Show a status message
        System.out.print("Compiling shader... ");

        // Create a new OpenGL shader program
        int program = GL20.glCreateProgram();

        // Shader IDs
        int vertexId = 0;
        int fragmentId = 0;

        // Compile the vertex shader if available
        if(hasVertexShader()) {
            // Create the vertex shader
            vertexId = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);

            // Attach the vertex shader source and compile it
            GL20.glShaderSource(vertexId, vertexSource);
            GL20.glCompileShader(vertexId);

            // Check for compiling errors
            if(GL20.glGetShaderi(vertexId, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
                // Show an error message
                System.out.println("FAIL\nFailed to compile the vertex shader");
                System.out.println(GL20.glGetShaderInfoLog(vertexId));

                // Delete the shader before returning
                GL20.glDeleteShader(vertexId);
                throw new RuntimeException("Failed to compile the vertex shader");
            }
        }

        // Compile the fragment shader if available
        if(hasFragmentShader()) {
            // Create the fragment shader
            fragmentId = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);

            // Attach the fragment shader source and compile it
            GL20.glShaderSource(fragmentId, fragmentSource);
            GL20.glCompileShader(fragmentId);

            // Check for compiling errors
            if(GL20.glGetShaderi(fragmentId, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
                // Show an error message
                System.out.println("FAIL\nFailed to compile the fragment shader");
                System.out.println(GL20.glGetShaderInfoLog(fragmentId));

                // Delete the shader before returning
                GL20.glDeleteShader(fragmentId);
                throw new RuntimeException("Failed to compile the vertex shader");
            }
        }

        // Attach all compiled shaders
        if(hasVertexShader())
            GL20.glAttachShader(program, vertexId);
        if(hasFragmentShader())
            GL20.glAttachShader(program, fragmentId);

        // Link the shader program to OpenGL and link it
        GL20.glLinkProgram(program);
        GL20.glValidateProgram(program);

        // Shaders have been attached to the program, delete their compiled sources to save memory
        if(hasVertexShader())
            GL20.glDeleteShader(vertexId);
        if(hasFragmentShader())
            GL20.glDeleteShader(fragmentId);

        // Show a status message
        System.out.println("OK");

        // Return the created OpenGL shader program
        return program;
    }
}
