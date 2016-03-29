package me.keybarricade.voxeltex.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

    /**
     * Get the contents of a file as string.
     *
     * @param path Path of the file.
     *
     * @return File contents as a string.
     */
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
            if(reader.read(chars) == -1) {
                System.out.println("Failed to read from file reader.");
                return null;
            }

            // Convert the char array into a string
            content = new String(chars);

            // Close the reader
            reader.close();

        } catch(IOException e) {
            System.out.println("Failed to load file '" + path + "'.");
        }

        // Return the content
        return content;
    }
}
