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

package com.timvisee.voxeltex.font;

import com.timvisee.voxeltex.material.Material;
import com.timvisee.voxeltex.shader.ShaderManager;
import com.timvisee.voxeltex.texture.Texture;
import com.timvisee.voxeltex.util.Color;

public class BitmapFontMaterial extends Material {

    /**
     * Font color.
     */
    private Color color = Color.WHITE;

    /**
     * Constructor.
     *
     * @param fontTexture Font texture.
     */
    public BitmapFontMaterial(Texture fontTexture) {
        // Construct the super
        super(ShaderManager.SHADER_DEFAULT_BITMAP_FONT, fontTexture);
    }

    /**
     * Get the current font rendering color.
     *
     * @return Font color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Font color.
     *
     * @param color Font color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void bind() {
        this.bind('?');
    }

    /**
     * Character to draw with this material.
     *
     * @param c Character.
     */
    public void bind(char c) {
        bind(c, 1.0f);
    }

    /**
     * Character to draw with this material.
     *
     * @param c Character.
     * @param characterWidthFactor Character width factor.
     */
    public void bind(char c, float characterWidthFactor) {
        // Bind the parent
        super.bind();

        // Send the tile position to the font shader
        getShader().setUniform2f("tilePosition", BitmapFontUtil.getCharCoords(c));

        // Send the tile position to the font shader
        getShader().setUniform4f("color", this.color.toVector4f());

        // Send the character width factor
        getShader().setUniform1f("charWidth", characterWidthFactor);
    }
}
