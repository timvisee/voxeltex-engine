package me.keybarricade.voxeltex.shader.raw;

public interface RawShaderInterface {

    /**
     * Get the vertex shader source if set.
     *
     * @return Vertex shader source.
     */
    String getVertexShader();

    /**
     * Set the vertex shader source.
     *
     * @param vertexSource Vertex shader source.
     */
    void setVertexShader(String vertexSource);

    /**
     * Check whether the vertex shader source is set.
     *
     * @return True if set, false if not.
     */
    boolean hasVertexShader();

    /**
     * Get the fragment shader source if set.
     *
     * @return Fragment shader source.
     */
    String getFragmentShader();

    /**
     * Set the fragment shader source.
     *
     * @param fragmentSource Fragment shader source.
     */
    void setFragmentShader(String fragmentSource);

    /**
     * Check whether the fragment shader source is set.
     *
     * @return True if set, false if not.
     */
    boolean hasFragmentShader();
}
