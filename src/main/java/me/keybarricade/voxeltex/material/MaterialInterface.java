package me.keybarricade.voxeltex.material;

import me.keybarricade.voxeltex.shader.Shader;
import me.keybarricade.voxeltex.texture.Texture;
import org.joml.Vector2f;

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
     * Get the material normal.
     *
     * @return Material normal.
     */
    Texture getNormal();

    /**
     * Check whether this material has any normal assigned.
     *
     * @return True if a normal is assigned, false if not.
     */
    boolean hasNormal();

    /**
     * Set the material normal.
     *
     * @param normal Material normal.
     */
    void setNormal(Texture normal);
    
    /**
     * Get the texture tiling factor.
     *
     * @return Tiling factor.
     */
    Vector2f getTiling();

    /**
     * Set the texture tiling factor.
     *
     * @param tile Tiling factor.
     */
    void setTiling(Vector2f tile);

    /**
     * Bind the current material to OpenGL before rendering.
     */
    void bind();

    /**
     * Unbind the material from OpenGL after rendering.
     */
    void unbind();
}
