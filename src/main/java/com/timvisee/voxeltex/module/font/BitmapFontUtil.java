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

import org.joml.Vector2f;

public class BitmapFontUtil {
    /**
     * Get the index of the given character.
     *
     * @param c Character.
     *
     * @return Character index.
     */
    public static int getCharIndex(char c) {
        return (int) c;
    }

    /**
     * Get the bitmap tile position of the given character.
     *
     * @param c Character.
     *
     * @return Bitmap tile position.
     */
    public static Vector2f getCharCoords(char c) {
        // Calculate the character index
        final int charIndex = getCharIndex(c);

        // Determine the character position on the bitmap
        return new Vector2f(
                charIndex % BitmapFont.BITMAP_FONT_TILE_SIZE,
                charIndex / BitmapFont.BITMAP_FONT_TILE_SIZE
        );
    }
}
