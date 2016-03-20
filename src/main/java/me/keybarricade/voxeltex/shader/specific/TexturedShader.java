package me.keybarricade.voxeltex.shader.specific;

import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.shader.Shader;
import me.keybarricade.voxeltex.shader.raw.AbstractRawShader;
import me.keybarricade.voxeltex.shader.raw.EngineAssetsRawShader;

public class TexturedShader extends Shader {

    /**
     * The engine asset path of the vertex shader.
     */
    private static final String SHADER_VERTEX_ASSET_PATH = "shaders/textured.vert";

    /**
     * The engine asset path of the fragment shader.
     */
    private static final String SHADER_FRAGMENT_ASSET_PATH = "shaders/textured.frag";

    /**
     * Constructor.
     */
    public TexturedShader() {
        this(new EngineAssetsRawShader(SHADER_VERTEX_ASSET_PATH, SHADER_FRAGMENT_ASSET_PATH));
    }

    /**
     * Constructor.
     *
     * @param programId OpenGL shader program ID.
     */
    public TexturedShader(int programId) {
        super(programId);
    }

    /**
     * Constructor.
     *
     * @param rawShader Raw shader.
     */
    public TexturedShader(AbstractRawShader rawShader) {
        super(rawShader);
    }

    @Override
    public void update(Material material) {
        // Call the parent
        super.update(material);

        // Send texture tiling data
        if(material != null)
            setUniform2f("tiling", material.getTiling());
    }
}
