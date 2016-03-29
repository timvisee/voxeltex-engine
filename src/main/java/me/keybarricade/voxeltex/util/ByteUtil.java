/******************************************************************************
 * Copyright (c) Tim Visee 2016. All rights reserved.                         *
 *                                                                            *
 * @author Tim Visee                                                          *
 * @website http://timvisee.com/                                              *
 *                                                                            *
 * Open Source != No Copyright                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * You should have received a copy of The MIT License (MIT) along with this   *
 * program. If not, see <http://opensource.org/licenses/MIT/>.                *
 ******************************************************************************/

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
