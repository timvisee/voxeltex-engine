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
