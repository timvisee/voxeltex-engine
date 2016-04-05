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

package me.keybarricade.voxeltex.material;

import me.keybarricade.voxeltex.math.vector.Vector2fFactory;
import me.keybarricade.voxeltex.scene.AbstractScene;
import me.keybarricade.voxeltex.shader.Shader;
import me.keybarricade.voxeltex.shader.ShaderManager;
import me.keybarricade.voxeltex.texture.Texture;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL13;

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
     * Material texture normal.
     */
    private Texture normal;

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
        this(shader, texture, null);
    }

    /**
     * Constructor.
     *
     * @param shader Material shader.
     * @param texture Material texture.
     * @param normal Texture normal.
     */
    public Material(Shader shader, Texture texture, Texture normal) {
        this.shader = shader;
        this.texture = texture;
        this.normal = normal;
    }

    /**
     * Constructor.
     *
     * @param shader Material shader.
     */
    public Material(Shader shader) {
        this(shader, null, null);
    }

    /**
     * Constructor for a textured material. This material will use the default texture shader.
     *
     * @param texture Material texture.
     */
    public Material(Texture texture) {
        this(ShaderManager.SHADER_DEFAULT_TEXTURED, texture, null);
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
    public Texture getNormal() {
        return this.normal;
    }

    @Override
    public boolean hasNormal() {
        return this.normal != null;
    }

    @Override
    public void setNormal(Texture normal) {
        this.normal = normal;
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

        // Bind the texture and normal if available
        if(hasTexture())
            this.texture.bind(GL13.GL_TEXTURE0);
//        if(hasNormal())
//            this.normal.bind(GL13.GL_TEXTURE1);
    }

    @Override
    public void unbind() {
        // Unbind the texture if available
        if(hasTexture())
            Texture.unbind();

        // Unbind the shader
        this.shader.unbind();
    }

    @Override
    public void update(AbstractScene scene) {
        this.shader.update(scene, this);
    }
}
