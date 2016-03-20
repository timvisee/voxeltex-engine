package me.keybarricade.voxeltex.shader.specific;

import me.keybarricade.voxeltex.math.vector.Vector2fFactory;
import me.keybarricade.voxeltex.shader.Shader;
import me.keybarricade.voxeltex.shader.raw.AbstractRawShader;
import me.keybarricade.voxeltex.shader.raw.EngineAssetsRawShader;
import org.joml.Vector2f;

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
     * Texture tiling factor.
     */
    private Vector2f tile = Vector2fFactory.one();

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

    /**
     * Get the texture tiling factor.
     *
     * @return Tiling factor.
     */
    public Vector2f getTile() {
        return this.tile;
    }

    /**
     * Set the texture tiling factor.
     *
     * @param tile Tiling factor.
     */
    public void setTile(Vector2f tile) {
        this.tile = tile;
    }

    @Override
    public void update() {
        // Update the super
        super.update();

        // Send the texture tiling values
        setUniform2f("tile", this.tile);
    }
}
