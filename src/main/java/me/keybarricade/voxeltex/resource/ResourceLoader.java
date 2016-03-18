package me.keybarricade.voxeltex.resource;

import me.keybarricade.voxeltex.util.ByteUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class ResourceLoader {

    /**
     * Load a resource as stream.
     *
     * @param path Path of the resource to load.
     *
     * @return Resource stream.
     */
    public static InputStream loadResourceAsStream(String path) {
        return System.class.getResourceAsStream("/" + path);
    }

    /**
     * Load a resource as string.
     *
     * @param path Path of the resource to load.
     *
     * @return Resource string.
     */
    public static String loadResourceAsString(String path) {
        // Create a scanner for reading
        Scanner scanner = new Scanner(loadResourceAsStream(path));

        // Set the delimiter
        scanner.useDelimiter("\\A");

        // Read the resource and return it as a string
        return scanner.hasNext() ? scanner.next() : "";
    }

    /**
     * Load a resource in a byte buffer.
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
