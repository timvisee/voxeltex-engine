package me.keybarricade.voxeltex.material;

import me.keybarricade.voxeltex.shader.Shader;
import me.keybarricade.voxeltex.texture.Texture;

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
