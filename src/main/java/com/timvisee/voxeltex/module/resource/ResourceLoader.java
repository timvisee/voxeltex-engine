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

package com.timvisee.voxeltex.module.resource;

import com.timvisee.voxeltex.util.BufferUtil;
import com.timvisee.voxeltex.util.ByteUtil;

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
