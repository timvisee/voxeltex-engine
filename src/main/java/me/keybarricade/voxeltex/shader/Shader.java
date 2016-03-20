package me.keybarricade.voxeltex.shader;

import me.keybarricade.voxeltex.shader.raw.AbstractRawShader;

public class Shader extends AbstractShader {

    /**
     * Constructor.
     *
     * @param programId OpenGL shader program ID.
     */
    public Shader(int programId) {
        super(programId);
    }

    /**
     * Constructor.
     *
     * @param rawShader Raw shader.
     */
    public Shader(AbstractRawShader rawShader) {
        // Compile the shader and use it's program ID
        this(rawShader.compile());
    }

    @Override
    public void update() { }
}
