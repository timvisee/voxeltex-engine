package me.keybarricade.voxeltex.shader;

import me.keybarricade.voxeltex.global.MainCamera;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.scene.AbstractScene;
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
    public void update(AbstractScene scene, Material material) {
        // Configure the projection and view matrix of the shader
        // TODO: Should we still send this data?
        setUniformMatrix4f("projectionMatrix", MainCamera.getProjectionMatrix());
        setUniformMatrix4f("viewMatrix", MainCamera.createCameraViewMatrix());
    }
}
