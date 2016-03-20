package me.keybarricade.voxeltex.shader.raw;

import me.keybarricade.voxeltex.resource.engine.EngineAssetLoader;

public class EngineAssetsRawShader extends RawShader {

    /**
     * Constructor.
     */
    public EngineAssetsRawShader() { }

    /**
     * Constructor.
     *
     * @param vertexAssetPath Vertex shader engine asset path.
     * @param fragmentAssetPath Fragment shader engine asset path.
     */
    public EngineAssetsRawShader(String vertexAssetPath, String fragmentAssetPath) {
        // Load the shaders
        loadVertexShaderFromEngineAssets(vertexAssetPath);
        loadFragmentShaderFromEngineAssets(fragmentAssetPath);
    }

    /**
     * Load the vertex shader from engine assets.
     *
     * @param vertexAssetPath Vertex shader engine asset path.
     *
     * @return this for method chaining.
     */
    public EngineAssetsRawShader loadVertexShaderFromEngineAssets(String vertexAssetPath) {
        // Load and set the vertex source shader
        setVertexShader(EngineAssetLoader.getInstance().loadResourceString(vertexAssetPath));

        // Return this for method chaining
        return this;
    }

    /**
     * Load the fragment shader from engine assets.
     *
     * @param fragmentAssetPath Fragment shader engine asset path.
     *
     * @return this for method chaining.
     */
    public EngineAssetsRawShader loadFragmentShaderFromEngineAssets(String fragmentAssetPath) {
        // Load and set the fragment source shader
        setFragmentShader(EngineAssetLoader.getInstance().loadResourceString(fragmentAssetPath));

        // Return this for method chaining
        return this;
    }
}
