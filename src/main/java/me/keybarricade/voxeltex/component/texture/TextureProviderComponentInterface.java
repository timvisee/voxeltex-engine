package me.keybarricade.voxeltex.component.texture;

import me.keybarricade.voxeltex.texture.Texture;

public interface TextureProviderComponentInterface {

    /**
     * Get the texture instance.
     *
     * @return Provided texture.
     */
    Texture getTexture();

    /**
     * Bind the texture to the current OpenGL instance.
     */
    void bindTexture();
}
