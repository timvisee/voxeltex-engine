package me.keybarricade.voxeltex.resource;

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
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            int line = 0;
            // read bytes from stream, and store them in buffer
            while ((line = resourceStream.read(buffer)) != -1) {
                // Writes bytes from byte array (buffer) into output stream.
                os.write(buffer, 0, line);
            }
            resourceStream.close();
            os.flush();
            os.close();

            // Convert to a bytes array
            byte[] bytes = os.toByteArray();

            // Create a byte buffer for the bytes array
            ByteBuffer buff = ByteBuffer.allocate(bytes.length);

            // Put the bytes into the buffer and return it
            buff.put(bytes);
            return buff;

        } catch(IOException ex) {
            ex.printStackTrace();
        }

        // Failed, return null
        return null;
    }
}
