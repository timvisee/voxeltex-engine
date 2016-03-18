package me.keybarricade.voxeltex.resource;

import java.io.InputStream;
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
}
