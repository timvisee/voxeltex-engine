package me.keybarricade.voxeltex.util;

import org.lwjgl.BufferUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.channels.FileChannel;

import static org.lwjgl.system.jemalloc.JEmalloc.je_malloc;

public class BufferUtil {

    /**
     * Convert an array of floats into a float buffer.
     *
     * @param array Array to convert.
     *
     * @return Float buffer.
     */
    public static FloatBuffer toFloatBuffer(float[] array) {
        // Create the float buffer with the given length
        FloatBuffer buffer = BufferUtils.createFloatBuffer(array.length);

        // Put the array into the buffer and flip it
        buffer.put(array);
        buffer.flip();

        // Return the buffer
        return buffer;
    }

    /**
     * Create a byte buffer with the specified size.
     *
     * @param size Byte buffer size.
     *
     * @return Byte buffer.
     */
    public static ByteBuffer createByteBuffer(long size) {
        // Allocate the size for the byte buffer through LWJGL
        return je_malloc(size);
    }

    /**
     * Get the contents of a file in a byte buffer.
     *
     * @param path File.
     *
     * @return Byte buffer with file contents.
     *
     * @throws IOException Throws if an error occurred.
     */
    public static ByteBuffer fileToByteBuffer(String path) throws IOException {
        // Define the buffer
        ByteBuffer buffer;

        // Get the file
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();

        // Create the actual buffer
        buffer = createByteBuffer((int) fc.size() + 1);

        // Read the whole file into the buffer
        //noinspection StatementWithEmptyBody
        while(fc.read(buffer) != -1);

        // Close the input stream and channel
        fis.close();
        fc.close();

        // Flip the buffer so we can read from it, return it afterwards
        buffer.flip();
        return buffer;
    }
}
