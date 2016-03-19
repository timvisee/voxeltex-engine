package me.keybarricade.voxeltex.material;

import me.keybarricade.voxeltex.shader.Shader;
import me.keybarricade.voxeltex.texture.Texture;

public interface MaterialInterface {

    // TODO: Make texture optional!

    /**
     * Get the material shader.
     *
     * @return Material shader.
     */
    Shader getShader();

    /**
     * Set the material shader.
     *
     * @param shader Material shader.
     */
    void setShader(Shader shader);

    /**
     * Get the material texture.
     *
     * @return Material texture.
     */
    Texture getTexture();

    /**
     * Check whether this material has any texture assigned.
     *
     * @return True if a texture is assigned, false if not.
     */
    boolean hasTexture();

    /**
     * Set the material texture.
     *
     * @param texture Material texture.
     */
    void setTexture(Texture texture);

    /**
     * Bind the current material to OpenGL before rendering.
     */
    void bind();

    /**
     * Unbind the material from OpenGL after rendering.
     */
    void unbind();
}
