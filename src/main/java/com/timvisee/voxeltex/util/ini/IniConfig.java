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

package com.timvisee.voxeltex.util.ini;

import java.util.HashMap;
import java.util.Map;

public class IniConfig {

    /**
     * Entries in this INI configuration.
     */
    private Map<String, Map<String, String>> entries = new HashMap<>();

    /**
     * Constructor.
     *
     * @param entries Entries.
     */
    public IniConfig(Map<String, Map<String, String>> entries) {
        this.entries = entries;
    }

    /**
     * Get a string value.
     *
     * @param section INI section name.
     * @param key Key name.
     * @param defaultValue Default value.
     *
     * @return String value.
     */
    public String getString(String section, String key, String defaultValue) {
        // Get the key value pairs for the given section
        Map<String, String> pairs = entries.get(section);

        // Make sure the pairs are valid, return the default value if not
        if(pairs == null)
            return defaultValue;

        // Get and parse the value
        return pairs.get(key);
    }

    /**
     * Get a integer value.
     *
     * @param section INI section name.
     * @param key Key name.
     * @param defaultValue Default value.
     *
     * @return Integer value.
     */
    public int getInt(String section, String key, int defaultValue) {
        // Get the key value pairs for the given section
        Map<String, String> pairs = entries.get(section);

        // Make sure the pairs are valid, return the default value if not
        if(pairs == null)
            return defaultValue;

        // Get and parse the value
        return Integer.parseInt(pairs.get(key));
    }

    /**
     * Get a float value.
     *
     * @param section INI section name.
     * @param key Key name.
     * @param defaultValue Default value.
     *
     * @return Float value.
     */
    public float getFloat(String section, String key, float defaultValue) {
        // Get the key value pairs for the given section
        Map<String, String> pairs = entries.get(section);

        // Make sure the pairs are valid, return the default value if not
        if(pairs == null)
            return defaultValue;

        // Get and parse the value
        return Float.parseFloat(pairs.get(key));
    }

    /**
     * Get a double value.
     *
     * @param section INI section name.
     * @param key Key name.
     * @param defaultValue Default value.
     *
     * @return Double value.
     */
    public double getDouble(String section, String key, double defaultValue) {
        // Get the key value pairs for the given section
        Map<String, String> pairs = entries.get(section);

        // Make sure the pairs are valid, return the default value if not
        if(pairs == null)
            return defaultValue;

        // Get and parse the value
        return Double.parseDouble(pairs.get(key));
    }
}
