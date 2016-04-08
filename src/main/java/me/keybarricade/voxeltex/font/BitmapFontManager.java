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

import java.util.ArrayList;
import java.util.List;

public class BitmapFontManager {

    /**
     * Name of the default font.
     */
    public static final String DEFAULT_FONT = "Ubuntu";

    /**
     * List of loaded bitmap fonts.
     */
    private static List<BitmapFont> fonts = new ArrayList<>();

    static {
        // Load the default font on start
        loadFont(DEFAULT_FONT);
    }

    /**
     * Get the main bitmap font.
     *
     * @return Main bitmap font.
     */
    public static BitmapFont getDefault() {
        return fonts.get(0);
    }

    /**
     * Add a bitmap font to the manager.
     *
     * @param font Bitmap font.
     */
    public static void addFont(BitmapFont font) {
        fonts.add(font);
    }

    /**
     * Load a bitmap font with the given name.
     *
     * @param fontName Bitmap font name.
     */
    public static void loadFont(String fontName) {
        // Show a status message
        System.out.println("Loading bitmap font '" + fontName + "'...");

        // Load and add the font
        addFont(BitmapFontLoader.loadFont(fontName));
    }
}
