package me.keybarricade.voxeltex.shader.raw;

import me.keybarricade.voxeltex.util.FileUtil;

public class FileSystemRawShader extends RawShader {

    /**
     * Constructor.
     */
    public FileSystemRawShader() { }

    /**
     * Constructor.
     *
     * @param vertexAssetPath Vertex shader file system path.
     * @param fragmentAssetPath Fragment shader file system path.
     */
    public FileSystemRawShader(String vertexAssetPath, String fragmentAssetPath) {
        // Load the shaders
        loadVertexShaderFromPath(vertexAssetPath);
        loadFragmentShaderFromPath(fragmentAssetPath);
    }

    /**
     * Load the vertex shader from file system path.
     *
     * @param vertexAssetPath Vertex shader file system path.
     *
     * @return this for method chaining.
     */
    public FileSystemRawShader loadVertexShaderFromPath(String vertexAssetPath) {
        // Load and set the vertex source shader
        setVertexShader(FileUtil.read(vertexAssetPath));

        // Return this for method chaining
        return this;
    }

    /**
     * Load the fragment shader from file system path.
     *
     * @param fragmentAssetPath Fragment shader file system path.
     *
     * @return this for method chaining.
     */
    public FileSystemRawShader loadFragmentShaderFromPath(String fragmentAssetPath) {
        // Load and set the fragment source shader
        setFragmentShader(FileUtil.read(fragmentAssetPath));

        // Return this for method chaining
        return this;
    }
}
