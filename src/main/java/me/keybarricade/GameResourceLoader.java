package me.keybarricade;

import me.keybarricade.voxeltex.resource.ResourceLoader;

import java.io.InputStream;

public class GameResourceLoader {

    /**
     * The root of the game resource directory.
     */
    public static final String RESOURCE_ROOT = "res/game/";

    public static InputStream loadResourceAsStream(String path) {
        return ResourceLoader.loadResourceAsStream(RESOURCE_ROOT + path);
    }

    public static String loadResourceAsString(String path) {
        return ResourceLoader.loadResourceAsString(RESOURCE_ROOT + path);
    }
}
