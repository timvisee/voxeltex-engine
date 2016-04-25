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

package com.timvisee.voxeltex.shader.specific;

import com.timvisee.voxeltex.material.Material;
import com.timvisee.voxeltex.math.vector.Vector4fFactory;
import com.timvisee.voxeltex.shader.Shader;
import com.timvisee.voxeltex.shader.raw.AbstractRawShader;
import com.timvisee.voxeltex.shader.raw.EngineAssetsRawShader;
import com.timvisee.voxeltex.structure.scene.AbstractScene;
import com.timvisee.voxeltex.util.Color;
import org.joml.Vector4f;

public class GuiTextureShader extends Shader {

    /**
     * The engine asset path of the vertex shader.
     */
    private static final String SHADER_VERTEX_ASSET_PATH = "shaders/gui/guiTexture.vert";

    /**
     * The engine asset path of the fragment shader.
     */
    private static final String SHADER_FRAGMENT_ASSET_PATH = "shaders/gui/guiTexture.frag";

    /**
     * Color channel intensity.
     */
    private Color color = new Color(1, 1, 1, 1);

    /**
     * Temporary vector, used while performing certain calculations.
     * Using and recycling this temporary matrix minimizes object allocation, resulting in better performance.
     */
    private final Vector4f tempVector4f = Vector4fFactory.identity();

    /**
     * Constructor.
     */
    public GuiTextureShader() {
        this(new EngineAssetsRawShader(SHADER_VERTEX_ASSET_PATH, SHADER_FRAGMENT_ASSET_PATH));
    }

    /**
     * Constructor.
     *
     * @param programId OpenGL shader program ID.
     */
    public GuiTextureShader(int programId) {
        super(programId);
    }

    /**
     * Constructor.
     *
     * @param rawShader Raw shader.
     */
    public GuiTextureShader(AbstractRawShader rawShader) {
        super(rawShader);
    }

    @Override
    public void update(AbstractScene scene, Material material) {
        // Call the parent
        super.update(scene, material);

        // Send texture tiling data to the shader
        if(material != null)
            setUniform2f("tiling", material.getTiling());

        // Make sure we aren't using the temporary vector more than once at the same time
        synchronized(this.tempVector4f) {
            // Send the color channel intensity
            setUniform4f("color", this.color.toVector4f(this.tempVector4f));
        }
    }

    /**
     * Get the color channel intensity.
     *
     * @return Color channel intensity.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Set the color channel intensity.
     *
     * @return Color channel intensity.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Set the color channel intensity.
     *
     * @param alpha Alpha channel intensity.
     */
    public void setAlpha(float alpha) {
        this.color.setAlpha(alpha);
    }
}
