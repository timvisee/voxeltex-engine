package me.keybarricade.voxeltex.resource;

import java.io.InputStream;
import java.nio.ByteBuffer;

public interface ResourceLoaderInterface {

    /**
     * Get the base path the resource loader loads from.
     *
     * @return Base path.
     */
    String getBasePath();

    /**
     * Load a resource as stream.
     *
     * @param path Path of the resource to load.
     *
     * @return Resource stream.
     */
    InputStream loadResourceStream(String path);

    /**
     * Load a resource as string.
     *
     * @param path Path of the resource to load.
     *
     * @return Resource string.
     */
    String loadResourceString(String path);

    /**
     * Load a resource in a byte buffer.
     *
     * @param path Path of the resource to load.
     *
     * @return Byte buffer.
     */
    ByteBuffer loadResourceByteBuffer(String path);
}
