package me.keybarricade.voxeltex.material;

import me.keybarricade.voxeltex.math.vector.Vector2fFactory;
import me.keybarricade.voxeltex.shader.Shader;
import me.keybarricade.voxeltex.shader.ShaderManager;
import me.keybarricade.voxeltex.texture.Texture;
import org.joml.Vector2f;

public class Material implements MaterialInterface {

    /**
     * Material shader.
     */
    private Shader shader;

    /**
     * Material texture.
     */
    private Texture texture;

    /**
     * Texture tiling.
     */
    private Vector2f tile = Vector2fFactory.one();

    /**
     * Constructor.
     *
     * @param shader Material shader.
     * @param texture Material texture.
     */
    public Material(Shader shader, Texture texture) {
        this.shader = shader;
        this.texture = texture;
    }

    /**
     * Constructor.
     *
     * @param shader Material shader.
     */
    public Material(Shader shader) {
        this.shader = shader;
    }

    /**
     * Constructor for a textured material. This material will use the default texture shader.
     *
     * @param texture Material texture.
     */
    public Material(Texture texture) {
        this.shader = ShaderManager.SHADER_DEFAULT_TEXTURED;
        this.texture = texture;
    }

    @Override
    public Shader getShader() {
        return shader;
    }

    @Override
    public void setShader(Shader shader) {
        this.shader = shader;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public boolean hasTexture() {
        return getTexture() != null;
    }

    @Override
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public Vector2f getTiling() {
        return this.tile;
    }

    @Override
    public void setTiling(Vector2f tile) {
        this.tile = tile;
    }

    @Override
    public void bind() {
        // Bind the shader
        this.shader.bind();

        // Bind the texture if available
        if(hasTexture())
            this.texture.bind();
    }

    @Override
    public void unbind() {
        // Unbind the texture if available
        if(hasTexture())
            Texture.unbind();

        // Unbind the shader
        this.shader.unbind();
    }
}
