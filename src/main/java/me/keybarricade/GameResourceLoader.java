package me.keybarricade;

import me.keybarricade.voxeltex.resource.ResourceLoader;

import java.io.InputStream;

public class GameResourceLoader {

    /**
     * The root of the game resource directory.
     */
    public static final String RESOURCE_ROOT = "res/game/";

    /**
     * Load a game resource as stream.
     *
     * @param path Relative path of the resource to load.
     *
     * @return Resource stream.
     */
    public static InputStream loadResourceAsStream(String path) {
        return ResourceLoader.loadResourceAsStream(RESOURCE_ROOT + path);
    }

    /**
     * Load a game resource as string.
     *
     * @param path Relative path of the resource to load.
     *
     * @return Resource string.
     */
    public static String loadResourceAsString(String path) {
        return ResourceLoader.loadResourceAsString(RESOURCE_ROOT + path);
    }
}
