package me.keybarricade.voxeltex.shader;

import me.keybarricade.voxeltex.shader.specific.DefaultShader;
import me.keybarricade.voxeltex.shader.specific.TestShader;
import me.keybarricade.voxeltex.shader.specific.TexturedShader;

public class ShaderManager {

    /**
     * Default shader.
     */
    public static Shader SHADER_DEFAULT;

    /**
     * Default textured shader.
     */
    public static Shader SHADER_DEFAULT_TEXTURED;

    /**
     * Load the engine shaders.
     */
    public static void load() {
        SHADER_DEFAULT = new DefaultShader();
        SHADER_DEFAULT_TEXTURED = new TexturedShader();
    }
}
