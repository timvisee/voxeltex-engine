/******************************************************************************
 * Copyright (c) Tim Visee 2016. All rights reserved.                         *
 *                                                                            *
 * @author Tim Visee                                                          *
 * @website http://timvisee.com/                                              *
 *                                                                            *
 * Open Source != No Copyright                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * You should have received a copy of The MIT License (MIT) along with this   *
 * program. If not, see <http://opensource.org/licenses/MIT/>.                *
 ******************************************************************************/

package com.timvisee.voxeltex.material;

import com.timvisee.voxeltex.scene.AbstractScene;
import com.timvisee.voxeltex.shader.Shader;
import com.timvisee.voxeltex.texture.Texture;
import org.joml.Vector2f;

public interface MaterialInterface {

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

    /**
     * Update the material and it's shader.
     *
     * @param scene Scene to update the material and shader for.
     */
    void update(AbstractScene scene);
}
