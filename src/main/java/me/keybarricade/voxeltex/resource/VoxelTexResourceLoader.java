package me.keybarricade.voxeltex.resource;

import me.keybarricade.voxeltex.util.BufferUtil;

import java.io.ByteArrayOutputStream;
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
        // Get the resource as input stream
        InputStream resourceStream = loadResourceAsStream(path);

        try {
            // Create a buffer and output stream for reading
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            int line;
            // Read bytes from the stream, buffer them and put them in the output stream afterwards
            while((line = resourceStream.read(buffer)) != -1)
                os.write(buffer, 0, line);

            // Flush and close the resource and ouput streams
            resourceStream.close();
            os.flush();
            os.close();

            // Allocate a new byte buffer (important)
            ByteBuffer byteBuffer = BufferUtil.createByteBuffer(os.size() + 1);

            // Put the byte array into the buffer
            byteBuffer.put(os.toByteArray());

            // Flip the buffer and return
            byteBuffer.flip();
            return byteBuffer;

        } catch(IOException ex) {
            ex.printStackTrace();
        }

        // Failed, return null
        return null;
    }
}
