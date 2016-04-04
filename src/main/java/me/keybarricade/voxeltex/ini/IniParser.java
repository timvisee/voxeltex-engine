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

package me.keybarricade.voxeltex.ini;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IniParser {

    /**
     * Regex pattern for INI sections.
     */
    private static final Pattern REGEX_PATTERN_SECTION = Pattern.compile("\\s*\\[([^]]*)\\]\\s*");

    /**
     * Regex pattern for INI key value pairs.
     */
    private static final Pattern REGEX_PATTERN_KEY_VALUE = Pattern.compile("\\s*([^=]*)=(.*)");

    /**
     * Parse an INI configuration from the given reader.
     *
     * @param reader Reader.
     *
     * @return Parsed INI configuration.
     *
     * @throws IOException Throws if an error occurred.
     */
    public static IniConfig parse(BufferedReader reader) throws IOException {
        Map<String, Map<String, String>> entries = new HashMap<>();

        // Define a variable for the current line and section
        String line;
        String section = null;

        // Parse all lines
        while((line = reader.readLine()) != null) {
            // Create a section matcher for the current line
            Matcher m = REGEX_PATTERN_SECTION.matcher(line);

            // Get the section if the matcher matches
            if(m.matches()) {
                section = m.group(1).trim();
                continue;
            }

            // Create a key value pair matcher for the current line
            m = REGEX_PATTERN_KEY_VALUE.matcher(line);

            // Get the key value pair if the matcher matches
            if(section != null && m.matches()) {
                // Get the key and value
                String key = m.group(1).trim();
                String value = m.group(2).trim();

                // Create a key value pair
                Map<String, String> keyValue = entries.get(section);

                // If the key value pair is null, put a hash map in it
                if(keyValue == null)
                    entries.put(section, keyValue = new HashMap<>());

                // Put the raw key and value in the pair
                keyValue.put(key, value);
            }
        }

        // Create and return an INI configuration with the parsed entries
        return new IniConfig(entries);
    }
}
