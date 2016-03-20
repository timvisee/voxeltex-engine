package me.keybarricade.voxeltex.shader.specific;

import me.keybarricade.voxeltex.shader.Shader;
import me.keybarricade.voxeltex.shader.raw.AbstractRawShader;
import me.keybarricade.voxeltex.shader.raw.EngineAssetsRawShader;
import me.keybarricade.voxeltex.util.Color;

public class DefaultShader extends Shader {

    /**
     * The engine asset path of the vertex shader.
     */
    private static final String SHADER_VERTEX_ASSET_PATH = "shaders/default.vert";

    /**
     * The engine asset path of the fragment shader.
     */
    private static final String SHADER_FRAGMENT_ASSET_PATH = "shaders/default.frag";

    /**
     * Constructor.
     */
    public DefaultShader() {
        this(new EngineAssetsRawShader(SHADER_VERTEX_ASSET_PATH, SHADER_FRAGMENT_ASSET_PATH));
    }

    /**
     * Constructor.
     *
     * @param programId OpenGL shader program ID.
     */
    public DefaultShader(int programId) {
        super(programId);
    }

    /**
     * Constructor.
     *
     * @param rawShader Raw shader.
     */
    public DefaultShader(AbstractRawShader rawShader) {
        super(rawShader);
    }

    @Override
    public void update() {
        // Update the super
        super.update();

        // Set the shader color
        setUniform4f("colour", Color.ORANGE.toVector4f());
    }
}
