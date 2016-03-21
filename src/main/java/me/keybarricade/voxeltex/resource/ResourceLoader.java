package me.keybarricade.voxeltex.resource;

import me.keybarricade.voxeltex.util.BufferUtil;
import me.keybarricade.voxeltex.util.ByteUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class ResourceLoader implements ResourceLoaderInterface {

    /**
     * Relative base path.
     */
    private static final String RELATIVE_BASE_PATH = "/";

    /**
     * Resource loader instance.
     */
    private static ResourceLoader instance;

    /**
     * Get a resource loader instance.
     *
     * @return Resource loader instance.
     */
    public static ResourceLoader getInstance() {
        // Return the instance if it does exist
        if(instance != null)
            return instance;

        // Create an instance, then store and return it
        instance = new ResourceLoader();
        return instance;
    }

    @Override
    public String getBasePath() {
        return RELATIVE_BASE_PATH;
    }

    @Override
    public InputStream loadResourceStream(String path) {
        // Show a loading message in the console
        System.out.println("Loading resource (" + this.getClass().getSimpleName() + "): " + getBasePath() + path);

        // Load from resources and return the input stream
        return System.class.getResourceAsStream(getBasePath() + path);
    }

    @Override
    public String loadResourceString(String path) {
        // Create a scanner for reading
        Scanner scanner = new Scanner(loadResourceStream(path));

        // Set the delimiter
        scanner.useDelimiter("\\A");

        // Read the resource and return it as a string
        return scanner.hasNext() ? scanner.next() : "";
    }

    @Override
    public ByteBuffer loadResourceByteBuffer(String path) {
        try {
            // Get the resource as byte array
            byte[] bytes = ByteUtil.getBytesFromInputStream(loadResourceStream(path));

            // Allocate the byte buffer in STB space
            ByteBuffer buffer = BufferUtil.createByteBuffer(bytes.length + 1);

            // Load the bytes into the buffer
            buffer.put(bytes);

            // Flip the buffer and return
            buffer.flip();
            return buffer;

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Failed, return null
        return null;
    }
}
