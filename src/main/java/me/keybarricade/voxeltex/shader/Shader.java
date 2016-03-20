package me.keybarricade.voxeltex.shader;

import me.keybarricade.voxeltex.global.MainCamera;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.renderer.VoxelTexRenderer;
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
    public void update(Material material) {
        // Configure the projection and view matrix of the shader
        setUniformMatrix4f("projectionMatrix", VoxelTexRenderer.mat);
        setUniformMatrix4f("viewMatrix", MainCamera.createCameraViewMatrix());

        // Configure the tiling
        if(material != null)
            setUniform2f("tiling", material.getTiling());
    }
}
