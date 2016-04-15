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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ConfigurationSection {

    /**
     * Configuration section.
     */
    private ConfigurationSection parent;

    /**
     * Key as a string.
     */
    private String key;

    /**
     * Value.
     */
    private Object value;

    /**
     * Constructor.
     *
     * @param key Key as a string.
     * @param value Value.
     */
    public ConfigurationSection(String key, Object value) {
        this(null, key, value);
    }

    /**
     * Constructor.
     *
     * @param parent Parent section.
     * @param key Key as a string.
     * @param value Value.
     */
    public ConfigurationSection(ConfigurationSection parent, String key, Object value) {
        this.parent = parent;
        this.key = key;
        this.value = value;
    }

    /**
     * Return the parent of this configuration section.
     *
     * @return Configuration section parent.
     */
    public ConfigurationSection getParent() {
        return this.parent;
    }

    /**
     * Is the configuration section the root of the config file.
     *
     * @return True if root, false otherwise.
     */
    public boolean isRoot() {
        return (this.parent == null);
    }

    /**
     * Get the root configuration section.
     *
     * @return Root configuration section.
     */
    public ConfigurationSection getRoot() {
        // If the current configuration section is the root, return this
        if(isRoot())
            return this;

        // Return the parent's root
        return this.parent.getRoot();
    }

    /**
     * Get the path of this configuration section.
     *
     * @return Configuration section path.
     */
    public String getPath() {
        // Is this the root
        if(isRoot())
            return "";

        // Is the parent the root
        if(this.parent.isRoot())
            return this.key;

        // Build the path
        StringBuilder pathBuilder = new StringBuilder();

        // Append the parent path
        if(!isRoot())
            pathBuilder.append(this.parent.getPath()).append(".");

        // Append the current key
        pathBuilder.append(this.key);

        // Return the path
        return pathBuilder.toString();
    }

    /**
     * Get the name of this configuration section
     * .
     * @return Name of this configuration section.
     */
    public String getName() {
        return getKey();
    }

    /**
     * Get the key.
     *
     * @return Key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Get the value at the given path.
     *
     * @param path Path to configuration section.
     *
     * @return Value.
     */
    public Object get(String path) {
        return get(path, null);
    }

    /**
     * Get a value at the given path.
     *
     * @param path Path to configuration section.
     * @param def Default value.
     *
     * @return Value.
     */
    public Object get(String path, Object def) {
        // Make sure the path is not null
        if(path == null)
            return def;

        // Trim the path
        path = path.trim();

        // Is the path leading to this section
        if(path.equals(""))
            return this.value;

        // Get the section this path is leading to
        ConfigurationSection section = getConfigurationSection(path);

        // Return the value
        return section == null ? def : section.get("");
    }

    /**
     * Get a String value.
     *
     * @param path Path to configuration section.
     *
     * @return String value.
     */
    public String getString(String path) {
        return getString(path, "");
    }

    /**
     * Get a String value.
     *
     * @param path Path to configuration section.
     * @param def Default value.
     *
     * @return String value.
     */
    public String getString(String path, String def) {
        // Get the value
        Object value = get(path);

        // Make sure the value is not null
        if(value == null)
            return def;

        // The value has to be an instance of a boolean
        return value instanceof String ? (String) value : def;
    }

    /**
     * Is the value an instance of a string.
     *
     * @param path Path to the value.
     *
     * @return True if the value is an instance of a string.
     */
    public boolean isString(String path) {
        // Get the value
        Object value = get(path);

        // Return the result
        return value != null && value instanceof String;
    }

    /**
     * Get a String value
     * @param path Path to configuration section
     * @return Integer value
     */
    public int getInt(String path) {
        return getInt(path, 0);
    }

    /**
     * Get a String value.
     *
     * @param path Path to configuration section.
     * @param def Default value.
     *
     * @return Integer value.
     */
    public int getInt(String path, int def) {
        // Get the value
        Object value = get(path);

        // Make sure the value is not null
        if(value == null)
            return def;

        // Return the result
        return value instanceof Integer ? (Integer) value : def;
    }

    /**
     * Is the value an instance of an integer.
     *
     * @param path Path to the value.
     *
     * @return True if the value is an instance of an integer.
     */
    public boolean isInt(String path) {
        // Get the value
        Object value = get(path);

        // Return the result
        return value != null && value instanceof Integer;
    }

    /**
     * Get a boolean value.
     *
     * @param path Path to configuration section
     *
     * @return Boolean value.
     */
    public boolean getBoolean(String path) {
        return getBoolean(path, false);
    }

    /**
     * Get a boolean value.
     *
     * @param path Path to configuration section.
     * @param def Default value.
     *
     * @return Boolean value.
     */
    public boolean getBoolean(String path, boolean def) {
        // Get the value
        Object value = get(path);

        // Make sure the value is not null
        if(value == null)
            return def;

        // Return the result
        return value instanceof Boolean ? (Boolean) value : def;
    }

    /**
     * Is the value an instance of a boolean.
     *
     * @param path Path to the value.
     *
     * @return True if the value is an instance of a boolean.
     */
    public boolean isBoolean(String path) {
        // Get the value
        Object value = get(path);

        // Return the result
        return value != null && value instanceof Boolean;
    }

    /**
     * Get a boolean value.
     *
     * @param path Path to configuration section.
     *
     * @return Boolean value.
     */
    public double getDouble(String path) {
        return getDouble(path, 0);
    }

    /**
     * Get a boolean value.
     * @param path Path to configuration section.
     * @param def Default value.
     *
     * @return Double value.
     */
    public double getDouble(String path, double def) {
        // Get the value
        Object value = get(path);

        // Make sure the value is not null
        if(value == null)
            return def;

        // Return the result
        return value instanceof Double ? (Double) value : def;
    }

    /**
     * Is the value an instance of a double.
     *
     * @param path Path to the value.
     *
     * @return True if the value is an instance of a double.
     */
    public boolean isDouble(String path) {
        // Get the value
        Object value = get(path);

        // Return the result
        return value != null && value instanceof Double;
    }

    /**
     * Get a float value.
     *
     * @param path Path to configuration section.
     *
     * @return Float value.
     */
    public float getFloat(String path) {
        return getFloat(path, 0);
    }

    /**
     * Get a float value.
     *
     * @param path Path to configuration section.
     * @param def Default value.
     *
     * @return Float value.
     */
    public float getFloat(String path, float def) {
        // Get the value
        Object value = get(path);

        // Make sure the value is not null
        if(value == null)
            return def;

        // Return the result
        return value instanceof Float ? (Float) value : def;
    }

    /**
     * Is the value an instance of a float.
     *
     * @param path Path to the value.
     *
     * @return True if the value is an instance of a float.
     */
    public boolean isFloat(String path) {
        // Get the value
        Object value = get(path);

        // Return the result
        return value != null && value instanceof Float;
    }

    /**
     * Get a long value.
     *
     * @param path Path to configuration section.
     *
     * @return Long value.
     */
    public long getLong(String path) {
        return getLong(path, 0);
    }

    /**
     * Get a long value.
     *
     * @param path Path to configuration section.
     * @param def Default value.
     *
     * @return Long value.
     */
    public long getLong(String path, long def) {
        // Get the value
        Object value = get(path);

        // Make sure the value is not null
        if(value == null)
            return def;

        // Return the result
        return value instanceof Long ? (Long) value : def;
    }

    /**
     * Is the value an instance of a
     * ong.
     * @param path Path to the value.
     *
     * @return True if the value is an instance of a long.
     */
    public boolean isLong(String path) {
        // Get the value
        Object value = get(path);

        // Return the result
        return value != null && value instanceof Long;
    }

    /**
     * Get a list.
     *
     * @param path Path to configuration section.
     *
     * @return List value.
     */
    public List<?> getList(String path) {
        return getList(path, null);
    }

    /**
     * Get a list.
     *
     * @param path Path to configuration section.
     * @param def Default value.
     *
     * @return List.
     */
    public List<?> getList(String path, List<?> def) {
        // Get the value
        Object value = get(path);

        // Make sure the value is not null
        if(value == null)
            return def;

        // Return the result
        return value instanceof List ? (List<?>) value : def;
    }

    /**
     * Is the value an instance of a list.
     *
     * @param path Path to the value.
     *
     * @return True if the value is an instance of a list.
     */
    public boolean isList(String path) {
        // Get the value
        Object value = get(path);

        // Return the result
        return value != null && value instanceof List;
    }

    /**
     * Return a list of keys from a configuration section.
     *
     * @param path Path to the configuration section to get the keys from.
     *
     * @return List of keys.
     */
    public List<String> getKeys(String path) {
        // Make sure the path is not null
        if(path == null)
            return new ArrayList<>();

        // Trim the path
        path = path.trim();

        // Make sure this configuration section exists
        if(!isConfigurationSection(path))
            return new ArrayList<>();

        // Get the configuration sections to get the keys from
        ConfigurationSection section = getConfigurationSection(path);

        // Make sure the configuration section holds other configuration sections
        if(!section.isHoldingConfigurationSections())
            return new ArrayList<>();

        // Create a list of keys to return
        @SuppressWarnings("unchecked")
        List<ConfigurationSection> sections = (List<ConfigurationSection>) section.get("");
        List<String> keys = new ArrayList<>();

        // Loop through all sections, and add their key to the list of keys
        for(ConfigurationSection entry : sections)
            keys.add(entry.getKey());

        // Return the list of keys
        return keys;
    }

    /**
     * Get the configuration section.
     *
     * @param path Path to the section.
     *
     * @return ConfigurationSection or null when no section was found.
     */
    public ConfigurationSection getSection(String path) {
        return getConfigurationSection(path);
    }

    /**
     * Get the configuration section.
     *
     * @param path Path to the section.
     *
     * @return ConfigurationSection or null when no section was found.
     */
    public ConfigurationSection getConfigurationSection(String path) {
        // Make sure the path param is not null
        if(path == null)
            return null;

        // Trim the path
        path = path.trim();

        // Is the path locating to this configuration section
        if(path.equals(""))
            return this;

        // Make sure the value of the current section is set
        if(!isSet(""))
            return null;

        // Does the path contain any sub paths
        if(!path.contains(".")) {
            // Make sure the path is locating to a configuration section
            if(!isConfigurationSection(path))
                return null;

            // Get and return the configuration section
            if(this.value instanceof List) {
                // Get the configuration section
                try {
                    @SuppressWarnings("unchecked")
                    List<ConfigurationSection> sections = (List<ConfigurationSection>) this.value;
                    for(ConfigurationSection section : sections) {
                        if(section == null)
                            continue;

                        // Is this the section we are searching for
                        if(section.getKey().equals(path))
                            return section;
                    }
                } catch(ClassCastException ignored) {
                }
            }

            return null;
        }

        // Get the keys
        String[] keys = path.split("\\.");
        String key = path;
        if(keys.length > 0)
            key = keys[0];
        String subPath = "";
        if(keys.length > 1) {
            subPath = keys[1];
            for(int i = 2; i < keys.length; i++)
                subPath += "." + keys[i];
        }

        // Make sure the key is not empty
        if(key.equals(""))
            return this;

        // Get the configuration section
        ConfigurationSection section = getConfigurationSection(key);

        // Make sure the section is not null
        if(section == null)
            return null;

        // Get the value from the child section
        return section.getConfigurationSection(subPath);
    }

    /**
     * Create a new configuration section.
     *
     * @param path Configuration section.
     *
     * @return New configuration section, null if path was invalid.<br>
     * If path is an empty string it will return the section the method was called on.
     */
    public ConfigurationSection createSection(String path) {
        return createConfigurationSection(path);
    }

    /**
     * Create a new configuration section.
     *
     * @param path Configuration section.
     *
     * @return New configuration section, null if path was invalid.<br>
     * If path is an empty string it will return the section the method was called on.
     */
    public ConfigurationSection createConfigurationSection(String path) {
        // Make sure the path is not null
        if(path == null)
            return null;

        // Trim the path
        path = path.trim();

        // Is the path leading to the current section
        if(path.equals(""))
            // Return this section without resetting the value
            return this;

        // Get the keys
        String[] keys = path.split("\\.");
        String key = path;
        if(keys.length > 0)
            key = keys[0];
        String subPath = "";
        if(keys.length > 1) {
            subPath = keys[1];
            for(int i = 2; i < keys.length; i++)
                subPath += "." + keys[i];
            subPath = subPath.trim();
        }

        // Is the first key of the path leading to an already excising section
        if(isConfigurationSection(key)) {
            // Get the section
            ConfigurationSection section = getConfigurationSection(key);

            // Are there any sub keys
            if(subPath.length() == 0)
                // Return the section
                return section;

            // Create the sub key sections in the section and return the result
            return section.createConfigurationSection(subPath);
        }

        // Create a section
        //ConfigurationSection section = new ConfigurationSection(this, key, null);
        if(this.value instanceof List) {
            try {
                @SuppressWarnings("unchecked")
                List<ConfigurationSection> sections = (List<ConfigurationSection>) this.value;
                ConfigurationSection section = new ConfigurationSection(this, key, null);
                sections.add(section);
                this.value = sections;

                // Are there any sub keys
                if(subPath.length() == 0)
                    // Return the section
                    return section;

                // Create the sub key sections in the section and return the result
                return section.createConfigurationSection(subPath);

            } catch(ClassCastException ignored) {
            }
        }

        // Create a new section
        ConfigurationSection section = new ConfigurationSection(this, key, null);
        List<ConfigurationSection> sections = new ArrayList<>();
        sections.add(section);
        this.value = sections;

        // Are there any sub keys
        if(subPath.length() == 0)
            // Return the section
            return section;

        // Create the sub key sections in the section and return the result
        return section.createConfigurationSection(subPath);
    }

    /**
     * Set the value.
     *
     * @param value Value.
     */
    public void set(String path, Object value) {
        // Make sure the path is not null
        if(path == null)
            return;

        // Trim the path
        path = path.trim();

        // Is the path leading to this section
        if(path.equals("")) {
            this.value = value;
            return;
        }

        // Get the keys
        String[] keys = path.split("\\.");
        String key = path;
        if(keys.length > 0)
            key = keys[0];
        String subPath = "";
        if(keys.length > 1) {
            subPath = keys[1];
            for(int i = 2; i < keys.length; i++)
                subPath += "." + keys[i];
        }

        // Is there any section this key leads to
        if(isConfigurationSection(key)) {
            // Get the section
            ConfigurationSection section = getConfigurationSection(key);
            section.set(subPath, value);
            return;

        }

        // Create a section
        ConfigurationSection section = new ConfigurationSection(this, key, null);
        if(this.value instanceof List) {
            try {
                @SuppressWarnings("unchecked")
                List<ConfigurationSection> sections = (List<ConfigurationSection>) this.value;
                sections.add(section);

            } catch(ClassCastException ex) {
                // Create a new section
                List<ConfigurationSection> sections = new ArrayList<>();
                sections.add(section);
                this.value = sections;
            }
        } else {
            // Create a new section
            List<ConfigurationSection> sections = new ArrayList<>();
            sections.add(section);
            this.value = sections;
        }

        // Set the value in the new section
        section.set(subPath, value);
    }

    /**
     * Is the value set.
     *
     * @return True if the value was set.
     */
    public boolean isSet(String path) {
        // Make sure the path param is not null
        if(path == null)
            return false;

        // Get the section the path is leading to
        ConfigurationSection section = getConfigurationSection(path);

        // Return the result
        return section != null && section.get("") != null;
    }

    /**
     * Is the value set.
     *
     * @return True if the value was set.
     */
    public boolean isHoldingConfigurationSections() {
        // Is the current value null
        if(this.value == null)
            return false;

        try {
            // Create a list of sections
            @SuppressWarnings("unchecked")
            List<ConfigurationSection> sections = (List<ConfigurationSection>) this.value;

            // Determine and return the result
            return sections.size() > 0 && (sections.get(0) != null);

        } catch(ClassCastException ignored) {
        }

        return false;
    }

    /**
     * Is the value a configuration section.
     *
     * @return True if the value is a configuration section.
     */
    public boolean isConfigurationSection(String path) {
        // Make sure the path is not null
        if(path == null)
            return false;

        // Trim the path
        path = path.trim();

        // Is the path leading to this section
        if(path.equals(""))
            return true;

        // Is the value of this configuration section a list instance, if not it can't hold any sub sections (so return false)
        if(!(this.value instanceof List))
            return false;

        // Get the list of configuration sections
        try {
            // Create a list of sections
            @SuppressWarnings("unchecked")
            List<ConfigurationSection> sections = (List<ConfigurationSection>) this.value;

            // Loop through all sections
            for(ConfigurationSection section : sections) {
                // Make sure the current entry is not null
                if(section == null)
                    continue;

                // Get the keys
                String[] keys = path.split("\\.");
                String key = path;
                if(keys.length > 0)
                    key = keys[0];
                String subPath = "";
                if(keys.length > 1) {
                    subPath = keys[1];
                    for(int i = 2; i < keys.length; i++)
                        subPath += "." + keys[i];
                }

                // Make sure the key of the current entry equals
                if(!section.getKey().equals(key))
                    continue;

                return section.isConfigurationSection(subPath);
            }

        } catch(ClassCastException ignored) {
        }

        // Return the default
        return false;
    }

    /**
     * Get all the values from the section as a Map list.
     *
     * @return Values from the section as a Map list.
     */
    public Map<String, Object> getValues() {
        // Define a map list to store the values in
        Map<String, Object> out = new LinkedHashMap<>();

        // Make sure the value is not null
        if(this.key == null)
            return out;

        // Add the values to the
        if(this.value instanceof List) {
            try {
                // Create a list of sections
                @SuppressWarnings("unchecked")
                List<ConfigurationSection> sections = (List<ConfigurationSection>) this.value;

                // Loop through all sections
                for(ConfigurationSection entry : sections)
                    if(entry.isHoldingConfigurationSections())
                        out.put(entry.getKey(), entry.getValues());
                    else
                        out.put(entry.getKey(), entry.get(""));

            } catch(ClassCastException e) {
                out.put(getKey(), this.value);
            }

        } else
            out.put(getKey(), this.value);

        // Return the output
        return out;
    }
}
