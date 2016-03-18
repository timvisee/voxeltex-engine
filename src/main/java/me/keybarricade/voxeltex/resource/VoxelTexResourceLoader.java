package me.keybarricade.voxeltex.resource;

import java.io.InputStream;

public class VoxelTexResourceLoader {

    /**
     * The root of the VoxelTex resource directory.
     */
    public static final String RESOURCE_ROOT = "res/voxeltex/";

    public static InputStream loadResourceAsStream(String path) {
        return ResourceLoader.loadResourceAsStream(RESOURCE_ROOT + path);
    }

    public static String loadResourceAsString(String path) {
        return ResourceLoader.loadResourceAsString(RESOURCE_ROOT + path);
    }
}
