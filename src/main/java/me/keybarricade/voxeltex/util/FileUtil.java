package me.keybarricade.voxeltex.util;

import java.io.*;

public class FileUtil {

    /**
     * Get the contents of a file as string.
     *
     * @param path Path of the file.
     *
     * @return File contents as a string.
     */
    // TODO: Improve this methode!
    public static String read(String path) {
        // Create a string for the file contents along with the file to load
        String content = null;
        File file = new File(path);

        // Try to load the file
        try {
            // Create a file reader
            FileReader reader = new FileReader(file);

            // Create an array of chars to read the file
            char[] chars = new char[(int) file.length()];

            // Read the file into the char array
            reader.read(chars);

            // Convert the char array into a string
            content = new String(chars);

            // Close the reader
            reader.close();

        } catch(IOException e) {
            // TODO: Use logger here!
            System.out.println("Failed to load file '" + path + "'.");
        }

        // Return the content
        return content;
    }
}
