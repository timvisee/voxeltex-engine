package me.keybarricade.voxeltex.shader.specific;

import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.shader.Shader;
import me.keybarricade.voxeltex.shader.raw.AbstractRawShader;
import me.keybarricade.voxeltex.shader.raw.EngineAssetsRawShader;

public class Textured2Shader extends Shader {

    /**
     * The engine asset path of the vertex shader.
     */
    // TODO: Revert this!
    private static final String SHADER_VERTEX_ASSET_PATH = "shaders/other/textured2.vert";

    /**
     * The engine asset path of the fragment shader.
     */
    // TODO: Revert this!
    private static final String SHADER_FRAGMENT_ASSET_PATH = "shaders/other/textured2.frag";

    /**
     * Constructor.
     */
    public Textured2Shader() {
        this(new EngineAssetsRawShader(SHADER_VERTEX_ASSET_PATH, SHADER_FRAGMENT_ASSET_PATH));
    }

    /**
     * Constructor.
     *
     * @param programId OpenGL shader program ID.
     */
    public Textured2Shader(int programId) {
        super(programId);
    }

    /**
     * Constructor.
     *
     * @param rawShader Raw shader.
     */
    public Textured2Shader(AbstractRawShader rawShader) {
        super(rawShader);
    }

    @Override
    public void update(Material material) {
        // Call the parent
        super.update(material);

        // Send texture tiling data
        if(material != null)
            setUniform2f("tiling", material.getTiling());

        // TODO: Remove this?
//        final Vector3f LIGHT_POS = new Vector3f(0, 1, 0);
//        //Light RGB and intensity (alpha)
//        final Vector4f LIGHT_COLOR = new Vector4f(1f, 0.8f, 0.6f, 1f);
//        //Ambient RGB and intensity (alpha)
//        final Vector4f AMBIENT_COLOR = new Vector4f(0.6f, 0.6f, 1f, 0.2f);
//        //Attenuation coefficients for light falloff
//        final Vector3f FALLOFF = new Vector3f(.4f, 3f, 20f);
//
//        //light/ambient colors
//        setUniform2f("Resolution", new Vector2f(1920, 1080));
//        setUniform4f("LightColor", LIGHT_COLOR);
//        setUniform4f("AmbientColor", AMBIENT_COLOR);
//        setUniform3f("Falloff", FALLOFF);
//
//        //send a Vector4f to GLSL
//        setUniform3f("LightPos", LIGHT_POS.normalize());
    }
}
