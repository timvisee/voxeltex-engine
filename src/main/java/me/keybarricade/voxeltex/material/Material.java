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
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void bind() {
        this.shader.bind();
        this.texture.bind();
    }

    @Override
    public void unbind() {
        Texture.unbind();
        Shader.unbind();
    }
}
