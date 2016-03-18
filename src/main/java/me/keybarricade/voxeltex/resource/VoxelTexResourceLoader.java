package me.keybarricade.voxeltex.resource;

import java.io.InputStream;

public class VoxelTexResourceLoader {

    /**
     * The root of the VoxelTex resource directory.
     */
    public static final String RESOURCE_ROOT = "res/voxeltex/";

    /**
     * Load a VoxelTex resource as stream.
     *
     * @param path Relative path of the resource to load.
     *
     * @return Resource stream.
     */
    public static InputStream loadResourceAsStream(String path) {
        return ResourceLoader.loadResourceAsStream(RESOURCE_ROOT + path);
    }

    /**
     * Load a VoxelTex resource as string.
     *
     * @param path Relative path of the resource to load.
     *
     * @return Resource string.
     */
    public static String loadResourceAsString(String path) {
        return ResourceLoader.loadResourceAsString(RESOURCE_ROOT + path);
    }
}
