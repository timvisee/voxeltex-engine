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

package com.timvisee.yamlwrapper.configuration;

import java.io.*;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class FileConfiguration extends Configuration {

    /**
     * Constructor.
     */
    public FileConfiguration() {
        super();
    }

    /**
     * Save the configuration to a file.
     *
     * @param file File to save the configuration in.
     *
     * @throws IOException
     */
    public void save(File file) throws IOException {
        // Make sure the file is not null
        if(file == null)
            return;

        // Create the parent directories
        //noinspection ResultOfMethodCallIgnored
        file.getParentFile().mkdirs();

        // Get the configuration string
        String data = saveToString();

        // Save the data
        try(FileWriter writer = new FileWriter(file)) {
            writer.write(data);
        }
    }

    /**
     * Save the configuration to a file.
     *
     * @param file File path to save the configuration at.
     *
     * @throws IOException
     */
    public void save(String file) throws IOException {
        // Make sure the file path is not null
        if(file == null || file.length() == 0)
            return;

        // Save the file
        save(new File(file));
    }

    /**
     * Save the configuration to a string.
     *
     * @return Configuration string.
     */
    public abstract String saveToString();

    /**
     * Load the configuration from a file.
     *
     * @param file File to load the configuration from.
     *
     * @throws IOException
     */
    public void load(File file) throws IOException {
        load(new FileInputStream(file));
    }

    /**
     * Load the configuration from a input stream.
     *
     * @param stream Input stream to load the configuration from.
     *
     * @throws IOException
     */
    public void load(InputStream stream) throws IOException {
        // Make sure the input stream is not null
        if(stream == null)
            return;

        // Create some readers
        InputStreamReader reader = new InputStreamReader(stream);
        StringBuilder builder = new StringBuilder();

        // Read the input
        try(BufferedReader input = new BufferedReader(reader)) {
            // Current line
            String line;

            // Parse each line and put it into the string builder
            while((line = input.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
        }

        // Load the YAML configuration from the string that was built
        loadFromString(builder.toString());
    }

    /**
     * Load the configuration from a file path.
     *
     * @param file File path to the configuration file.
     *
     * @throws IOException
     */
    public void load(String file) throws IOException {
        // Make sure the file path is not null
        if(file == null || file.length() == 0)
            return;

        // Load the file
        load(new File(file));
    }

    /**
     * Load the configuration from a string.
     *
     * @param contents Configuration string.
     */
    public abstract void loadFromString(String contents);
}