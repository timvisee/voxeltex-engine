package me.keybarricade.voxeltex.font;

import me.keybarricade.voxeltex.texture.Image;
import me.keybarricade.voxeltex.texture.Texture;

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

        // Create and return the bitmap font
        return new BitmapFont(fontName, fontTexture);
    }
}
