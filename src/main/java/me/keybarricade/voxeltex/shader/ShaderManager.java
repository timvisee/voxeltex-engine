package me.keybarricade.voxeltex.shader;

public class ShaderManager {

    /**
     * Default textured shader.
     */
    public static Shader DEFAULT_TEXTURED;

    /**
     * Load the engine shaders.
     */
    public static void load() {
        DEFAULT_TEXTURED = new TexturedShader();
    }
}
