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
import me.keybarricade.voxeltex.ini.IniParser;
import me.keybarricade.voxeltex.resource.engine.EngineAssetLoader;
import me.keybarricade.voxeltex.texture.Image;
import me.keybarricade.voxeltex.texture.Texture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BitmapFontLoader {

    /**
     * Load a bitmap font from engine resources with the given name.
     *
     * @param fontName Font name.
     *
     * @return Loaded bitmap font.
     */
    public static BitmapFont loadFont(String fontName) {
        // Load the font image from resources
        Image fontImage = Image.loadFromEngineAssets("font/bitmap/" + fontName + ".tga");

        // Create a bitmap font texture
        Texture fontTexture = Texture.fromImage(fontImage);

        // Load the font widths configuration
        InputStream iniStream = EngineAssetLoader.getInstance().loadResourceStream("font/bitmap/" + fontName + ".ini");

        try {
            // Load and parse the ini configuration
            System.out.println("Loading font character widths...");
            IniConfig iniConfig = IniParser.parse(new BufferedReader(new InputStreamReader(iniStream)));

            // Create and return the bitmap font
            return new BitmapFont(fontName, fontTexture, iniConfig);

        } catch(IOException e) {
            e.printStackTrace();
        }

        // Failed, throw a runtime exception
        throw new RuntimeException("Failed to load font.");
    }
}
