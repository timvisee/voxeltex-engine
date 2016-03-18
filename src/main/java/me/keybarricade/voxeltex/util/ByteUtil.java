package me.keybarricade.voxeltex.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteUtil {

    /**
     * Get an array of bytes from an input stream.
     *
     * @param inputStream Input stream.
     *
     * @return Returns an array of bytes.
     *
     * @throws IOException Throws if an error occurred.
     */
    public static byte[] getBytesFromInputStream(InputStream inputStream) throws IOException {
        // Try to get the input bytes
        try(ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            // Create a new buffer
            byte[] buffer = new byte[0xFFFF];

            // Load the bytes from the input stream
            for(int len; (len = inputStream.read(buffer)) != -1; )
                os.write(buffer, 0, len);

            // Flush
            os.flush();

            // Convert the byte array output stream to a byte array
            return os.toByteArray();
        }
    }
}
