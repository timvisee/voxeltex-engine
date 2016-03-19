package me.keybarricade.voxeltex.component.texture;

import me.keybarricade.voxeltex.texture.Texture;

public class TextureProviderComponent extends AbstractTextureProviderComponent {

    /**
     * Texture.
     */
    protected Texture texture;

    /**
     * Constructor.
     *
     * @param texture Texture.
     */
    public TextureProviderComponent(Texture texture) {
        this.texture = texture;
    }

    @Override
    public Texture getTexture() {
        return this.texture;
    }

    /**
     * Set the texture.
     *
     * @param texture Texture.
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
