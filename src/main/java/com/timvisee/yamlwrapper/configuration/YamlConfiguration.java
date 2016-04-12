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
    
	private DumperOptions yamlOptions = new DumperOptions();
    private Representer yamlRepresenter = new Representer();
    private Yaml yaml = new Yaml(new Constructor(), yamlRepresenter, yamlOptions);

	public String saveToString() {	    
		yamlOptions.setIndent(2);
        yamlOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        yamlRepresenter.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        // Return the configuration string
        return yaml.dump(getValues());
	}
	
    public void loadFromString(String contents) {
        // Make sure the contents are not null
    	if(contents == null)
    		return;

        Map<?, ?> input = null;
        try {
            input = (Map<?, ?>) yaml.load(contents);
        } catch (YAMLException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        
        if (input != null) {
            convertMapsToSections(input, this);
        }
    }

    public static YamlConfiguration loadConfiguration(File f) {
        // Make sure the file param is not null
    	if(f == null)
    		return new YamlConfiguration();

    	// Create a new configuration instance
        YamlConfiguration config = new YamlConfiguration();

        try {
            config.load(f);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        return config;
    }

    public static YamlConfiguration loadConfiguration(InputStream stream) {
    	// Make sure the stream param is not null
    	if(stream == null)
    		return new YamlConfiguration();

    	// Create a new configuration instance
        YamlConfiguration config = new YamlConfiguration();

        try {
            config.load(stream);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        return config;
    }
    
    
    
    
    
    
    
    
    private void convertMapsToSections(Map<?, ?> input, ConfigurationSection section) {
    	for(Map.Entry<?, ?> entry : input.entrySet()) {
            String key = entry.getKey().toString();
            Object val = entry.getValue();

            if (val instanceof Map)
                convertMapsToSections((Map<?, ?>) val, section.createSection(key));
            else
                section.set(key, val);
        }
    }
}
