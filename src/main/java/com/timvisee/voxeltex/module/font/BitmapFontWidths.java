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

package com.timvisee.voxeltex.module.font;

import com.timvisee.voxeltex.module.texture.Texture;
import com.timvisee.voxeltex.util.ini.IniConfig;

public class BitmapFontWidths {

    /**
     * Number of characters in a character bitmap.
     */
    private static final int CHARACTER_COUNT = BitmapFont.BITMAP_FONT_TILE_SIZE * BitmapFont.BITMAP_FONT_TILE_SIZE;

    /**
     * Configuration section name for the character widths.
     */
    private static final String CONFIG_SECTION_CHAR_WIDTHS = "Char Widths";

    /**
     * Default font width.
     */
    private int maxCharacterWidth = 64;

    /**
     * Font widths.
     */
    private int widths[] = null;

    /**
     * Constructor.
     *
     * @param fontTexture Font texture.
     */
    public BitmapFontWidths(Texture fontTexture) {
        // Reset the default widths
        this.widths = null;

        // Calculate and define the maximum character width
        this.maxCharacterWidth = (int) (fontTexture.getWidth() / BitmapFont.BITMAP_FONT_TILE_SIZE);
    }

    /**
     * Constructor.
     *
     * @param fontTexture Font texture.
     * @param widthsConfig Font widths configuration.
     */
    public BitmapFontWidths(Texture fontTexture, IniConfig widthsConfig) {
        // Calculate and define the maximum character width
        this.maxCharacterWidth = (int) (fontTexture.getWidth() / BitmapFont.BITMAP_FONT_TILE_SIZE);

        // Create the widths array
        this.widths = new int[CHARACTER_COUNT];

        // Put the character widths in the array
        for(int i = 0; i < CHARACTER_COUNT; i++)
            this.widths[i] = widthsConfig.getInt(CONFIG_SECTION_CHAR_WIDTHS, String.valueOf(i), this.maxCharacterWidth);
    }

    /**
     * Get the character width for the given character.
     *
     * @param c Character.
     *
     * @return Character width.
     */
    public int getCharacterWidth(char c) {
        return getCharacterWidth(BitmapFontUtil.getCharIndex(c));
    }

    /**
     * Get the character width for the given character.
     *
     * @param c Character index.
     *
     * @return Character width.
     */
    public int getCharacterWidth(int c) {
        // Return the default width if the width hasn't been configured for this character
        if(this.widths == null)
            return this.maxCharacterWidth;

        // Return the character width
        return this.widths[c];
    }

    /**
     * Get the character width factor for the given character.
     *
     * @param c Character.
     *
     * @return Character width factor.
     */
    public float getCharacterWidthFactor(char c) {
        return getCharacterWidthFactor(BitmapFontUtil.getCharIndex(c));
    }

    /**
     * Get the character width factor for the given character.
     *
     * @param c Character index.
     *
     * @return Character width factor.
     */
    public float getCharacterWidthFactor(int c) {
        // Return the default width if the width hasn't been configured for this character
        if(this.widths == null)
            return 1.0f;

        // Return the character width
        return (float) this.widths[c] / (float) this.maxCharacterWidth;
    }

    /**
     * Get the string width factor for the given string.
     *
     * @param str String.
     *
     * @return String width factor.
     */
    public float getStringWidthFactor(String str) {
        // Return zero if the string is empty
        if(str.length() == 0)
            return 0.0f;

        // Define a variable to accumulate the string with in
        float stringWidthFactor = 0.0f;

        // Loop through the string
        for(int i = 0; i < str.length(); i++)
            stringWidthFactor += getCharacterWidthFactor(str.charAt(i));

        // Return the string width factor
        return stringWidthFactor;
    }
}