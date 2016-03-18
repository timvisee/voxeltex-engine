package me.keybarricade.voxeltex.resource;

import me.keybarricade.voxeltex.util.ByteUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

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

    /**
     * Load a VoxelTex resource in a byte buffer.
     *
     * @param path Path of the resource to load.
     *
     * @return Byte buffer.
     */
    public static ByteBuffer loadResourceInByteBuffer(String path) {
        try {
            // Get the resource as byte array
            byte[] bytes = ByteUtil.getBytesFromInputStream(loadResourceAsStream(path));

            // Create a byte buffer and return
            return ByteBuffer.wrap(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Failed, return null
        return null;
    }
}
