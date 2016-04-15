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

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class YamlConfiguration extends FileConfiguration {

    /**
     * YAML options instance.
     */
    private DumperOptions yamlOptions = new DumperOptions();

    /**
     * YAML representing instance.
     */
    private Representer yamlRepresenting = new Representer();

    /**
     * YAML instance.
     */
    private Yaml yaml = new Yaml(new Constructor(), yamlRepresenting, yamlOptions);

    /**
     * Save the YAML configuration to a string.
     *
     * @return YAML string.
     */
    public String saveToString() {
        yamlOptions.setIndent(2);
        yamlOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        yamlRepresenting.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        // Return the configuration string
        return yaml.dump(getValues());
    }

    /**
     * Load a YAML configuration from a string.
     *
     * @param contents Configuration string.
     */
    public void loadFromString(String contents) {
        // Make sure the contents are not null
        if(contents == null)
            return;

        // Create a map with the YAML input
        Map<?, ?> input = null;
        try {
            input = (Map<?, ?>) yaml.load(contents);

        } catch(YAMLException | ClassCastException e) {
            e.printStackTrace();
        }

        // Convert the map into sections if it contains anything
        if(input != null)
            convertMapsToSections(input, this);
    }

    /**
     * Load a YAML configuration from the given file.
     *
     * @param file YAML file.
     *
     * @return Loaded YAML configuration.
     */
    public static YamlConfiguration loadConfiguration(File file) {
        // Make sure the file param is not null
        if(file == null)
            return new YamlConfiguration();

        // Create a new configuration instance
        YamlConfiguration config = new YamlConfiguration();

        // Try to load the configuration
        try {
            config.load(file);

        } catch(FileNotFoundException ignored) {
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Return the loaded configuration
        return config;
    }

    /**
     * Load a YAML configuration from the given input stream.
     *
     * @param stream Input stream.
     *
     * @return YAML configuration.
     */
    public static YamlConfiguration loadConfiguration(InputStream stream) {
        // Make sure the stream param is not null
        if(stream == null)
            return new YamlConfiguration();

        // Create a new configuration instance
        YamlConfiguration config = new YamlConfiguration();

        // Try to load the configuration
        try {
            config.load(stream);

        } catch(FileNotFoundException ignored) {
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Return the configuration
        return config;
    }

    /**
     * Convert the maps into different sections.
     * Helper method to process a YAML file.
     *
     * @param input Input to convert.
     * @param section Section output.
     */
    private void convertMapsToSections(Map<?, ?> input, ConfigurationSection section) {
        // Loop through each entry
        for(Map.Entry<?, ?> entry : input.entrySet()) {
            // Get the key and it's value
            String key = entry.getKey().toString();
            Object val = entry.getValue();

            // Convert maps recursively
            if(val instanceof Map)
                convertMapsToSections((Map<?, ?>) val, section.createSection(key));
            else
                section.set(key, val);
        }
    }
}
