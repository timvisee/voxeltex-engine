package com.timvisee.yamlwrapper.configuration;

import com.timvisee.yamlwrapper.configuration.ConfigurationSection;

public class Configuration extends ConfigurationSection {

	public Configuration() {
		super(null, "", null);
	}
	
    /* /**
     * Get a list of values
     * @return List of values
     * /
    public Map<String, Object> getValues() {
        Map<String, Object> out = new LinkedHashMap<String, Object>();

        // Get all the values
        out.putAll(this.config.getValues());
        
        // TODO: Finish this bellow
        /*Configuration root = getRoot();
        if (root != null && root.options().copyDefaults()) {
            ConfigurationSection defaults = getDefaultSection();

            if (defaults != null) {
                out.putAll(defaults.getValues(deep));
            }
        }

        mapChildrenValues(out, this, deep);* /
        
        return out;
    }*/
}
