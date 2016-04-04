package me.keybarricade.voxeltex.font;

import java.util.ArrayList;
import java.util.List;

public class BitmapFontManager {

    /**
     * Name of the default font.
     */
    public static final String DEFAULT_FONT = "Arial";

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
    public static BitmapFont getMainFont() {
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
