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

package me.keybarricade.voxeltex.font;

import me.keybarricade.voxeltex.ini.IniConfig;
import me.keybarricade.voxeltex.texture.Texture;

public class BitmapFont {

    /**
     * Bitmap font tile size.
     */
    public static final int BITMAP_FONT_TILE_SIZE = 16;

    /**
     * The texture name.
     */
    private String name;

    /**
     * The texture texture.
     */
    private Texture texture;

    /**
     * Font widths.
     */
    private BitmapFontWidths widths;

    /**
     * Font material.
     */
    private BitmapFontMaterial material = null;

    /**
     * Constructor.
     *
     * @param name Font name.
     * @param fontTexture Font texture.
     */
    public BitmapFont(String name, Texture fontTexture) {
        this(name, fontTexture, null);
    }

    /**
     * Constructor.
     *
     * @param name Font name.
     * @param fontTexture Font texture.
     */
    public BitmapFont(String name, Texture fontTexture, IniConfig fontWidthsConfig) {
        // Set the name and texture fields
        this.name = name;
        this.texture = fontTexture;

        // Create and load the font widths configuration
        if(fontWidthsConfig != null)
            this.widths = new BitmapFontWidths(fontTexture, fontWidthsConfig);
        else
            this.widths = new BitmapFontWidths(fontTexture);
    }

    /**
     * Get the texture name.
     *
     * @return Font name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the texture name.
     *
     * @param name Font name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the texture texture.
     *
     * @return Font texture.
     */
    public Texture getFontTexture() {
        return this.texture;
    }

    /**
     * Set the texture texture.
     *
     * @param font Font texture.
     */
    public void setFontTexture(Texture font) {
        this.texture = font;
    }

    /**
     * Get the bitmap texture material.
     *
     * @return Bitmap texture material.
     */
    public BitmapFontMaterial getMaterial() {
        // Make sure a texture material has been created
        if(this.material == null) {
            // Show a status message
            System.out.println("Creating bitmap material for '" + getName() + "' font...");

            // Create the actual material
            this.material = new BitmapFontMaterial(this.texture);
        }

        // Return the material
        return this.material;
    }

    /**
     * Get the font widths manager.
     *
     * @return Font widths.
     */
    public BitmapFontWidths getFontWidths() {
        return this.widths;
    }
}
