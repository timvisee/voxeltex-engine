package com.timvisee.yamlwrapper.configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationSection {
	
	ConfigurationSection parent;
	String key;
	Object val;
	
	/**
	 * Constructor
	 * @param String key Key
	 * @param Object val Value
	 */
	public ConfigurationSection(String key, Object val) {
		this.parent = null;
		this.key = key;
		this.val = val;
	}
	
	/**
	 * Constructor
	 * @param ConfigurationSection parent Parent section
	 * @param String key Key
	 * @param Object val Value
	 */
	public ConfigurationSection(ConfigurationSection parent, String key, Object val) {
		this.parent = parent;
		this.key = key;
		this.val = val;
	}
	
	/**
	 * Return the parent of this configuration section
	 * @return
	 */
	public ConfigurationSection getParent() {
		return this.parent;
	}
	
	/**
	 * Is the configuration section the root of the config file
	 * @return True if root
	 */
	public boolean isRoot() {
		return (this.parent == null);
	}
	
	/**
	 * Get the root configuration section
	 * @return
	 */
	public ConfigurationSection getRoot() {
		// If the current configuration seciton is the root, return this
		if(isRoot())
			return this;
		
		// Return the parent's root
		return this.parent.getRoot();
	}
	
	/**
	 * Get the (key) path
	 * @return (Key) Path
	 */
	public String getPath() {
		// Is this the root
		if(isRoot())
			return "";
		
		// Is the parent the root
		if(this.parent.isRoot())
			return this.key;
		
		String path = "";
		if(!isRoot())
			path = this.parent.getPath() + ".";
		path += this.key;
		return path;
	}
	
	/**
	 * Get the name of this configuration section
	 * @return Name of this configuration section
	 */
	public String getName() {
		return getKey();
	}
	
	/**
	 * Get the key
	 * @return Key
	 */
	public String getKey() {
		return this.key;
	}
	
	/**
	 * Get a value
	 * @param path Path to configuration section
	 * @return Value
	 */
	public Object get(String path) {
		return get(path, null);
	}
	
	/**
	 * Get a value
	 * @param path Path to configuration section
	 * @param def Default value
	 * @return Value
	 */
	public Object get(String path, Object def) {
		// Make sure the path is not null
		if(path == null)
			return def;
		
		// Trim the path
		path = path.trim();
		
		// Is the path leading to this section
		if(path.equals(""))
			return this.val;
		
		// Get the section this path is leading to
		ConfigurationSection section = getConfigurationSection(path);
		
		// Make sure the section is not null
		if(section == null)
			return def;
					
		// Return the value
		return section.get("");
	}
	
	/**
	 * Get a String value
	 * @param path Path to configuration section
	 * @return String value
	 */
	public String getString(String path) {
		return getString(path, "");
	}
	
	/**
	 * Get a String value
	 * @param path Path to configuration section
	 * @param def Default value
	 * @return String value
	 */
	public String getString(String path, String def) {
		// Get the value
		Object val = get(path);
		
		// Make sure the value is not null
		if(val == null)
			return def;
		
		// The value has to be an instance of a boolean
		if(val instanceof String)
			return (String) val;
		return def;
	}
	
	/**
	 * Is the value an instance of a string
	 * @param path Path to the value
	 * @return True if the value is an instance of a string
	 */
	public boolean isString(String path) {
		// Get the value
		Object val = get(path);
		
		// Make sure the value is not null
		if(val == null)
			return false;
		
		// Is the value an instance of a string
		return (val instanceof String);
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
	 * Get a String value
	 * @param path Path to configuration section
	 * @param def Default value
	 * @return Integer value
	 */
	public int getInt(String path, int def) {
		// Get the value
		Object val = get(path);
		
		// Make sure the value is not null
		if(val == null)
			return def;
		
		// The value has to be an instance of a int
		if(val instanceof Integer)
			return (Integer) val;
		return def;
	}
	
	/**
	 * Is the value an instance of an integer
	 * @param path Path to the value
	 * @return True if the value is an instance of an integer
	 */
	public boolean isInt(String path) {
		// Get the value
		Object val = get(path);
		
		// Make sure the value is not null
		if(val == null)
			return false;
		
		// Is the value an instance of a integer
		return (val instanceof Integer);
	}
	
	/**
	 * Get a boolean value
	 * @param path Path to configuration section
	 * @return Boolean value
	 */
	public boolean getBoolean(String path) {
		return getBoolean(path, false);
	}
	
	/**
	 * Get a boolean value
	 * @param path Path to configuration section
	 * @param def Default value
	 * @return Boolean value
	 */
	public boolean getBoolean(String path, boolean def) {
		// Get the value
		Object val = get(path);
		
		// Make sure the value is not null
		if(val == null)
			return def;
		
		// The value has to be an instance of a boolean
		if(val instanceof Boolean)
			return (Boolean) val;
		return def;
	}
	
	/**
	 * Is the value an instance of a boolean
	 * @param path Path to the value
	 * @return True if the value is an instance of a boolean
	 */
	public boolean isBoolean(String path) {
		// Get the value
		Object val = get(path);
		
		// Make sure the value is not null
		if(val == null)
			return false;
		
		// Is the value an instanceof a boolean
		return (val instanceof Boolean);
	}
	
	/**
	 * Get a boolean value
	 * @param path Path to configuration section
	 * @return Boolean value
	 */
	public double getDouble(String path) {
		return getDouble(path, 0);
	}
	
	/**
	 * Get a boolean value
	 * @param path Path to configuration section
	 * @param def Default value
	 * @return Double value
	 */
	public double getDouble(String path, double def) {
		// Get the value
		Object val = get(path);
		
		// Make sure the value is not null
		if(val == null)
			return def;
		
		// The value has to be an instance of a boolean
		if(val instanceof Double)
			return (Double) val;
		return def;
	}
	
	/**
	 * Is the value an instance of a double
	 * @param path Path to the value
	 * @return True if the value is an instance of a double
	 */
	public boolean isDouble(String path) {
		// Get the value
		Object val = get(path);
		
		// Make sure the value is not null
		if(val == null)
			return false;
		
		// Is the value an instance of a double
		return (val instanceof Double);
	}
	
	/**
	 * Get a float value
	 * @param path Path to configuration section
	 * @return Float value
	 */
	public float getFloat(String path) {
		return getFloat(path, 0);
	}
	
	/**
	 * Get a float value
	 * @param path Path to configuration section
	 * @param def Default value
	 * @return Float value
	 */
	public float getFloat(String path, float def) {
		// Get the value
		Object val = get(path);
		
		// Make sure the value is not null
		if(val == null)
			return def;
		
		// The value has to be an instance of a boolean
		if(val instanceof Float)
			return (Float) val;
		return def;
	}
	
	/**
	 * Is the value an instance of a float
	 * @param path Path to the value
	 * @return True if the value is an instance of a float
	 */
	public boolean isFloat(String path) {
		// Get the value
		Object val = get(path);
		
		// Make sure the value is not null
		if(val == null)
			return false;
		
		// Is the value an instance of a double
		return (val instanceof Float);
	}
	
	/**
	 * Get a long value
	 * @param path Path to configuration section
	 * @return Long value
	 */
	public long getLong(String path) {
		return getLong(path, 0);
	}
	
	/**
	 * Get a long value
	 * @param path Path to configuration section
	 * @param def Default value
	 * @return Long value
	 */
	public long getLong(String path, long def) {
		// Get the value
		Object val = get(path);
		
		// Make sure the value is not null
		if(val == null)
			return def;
		
		// The value has to be an instance of a long
		if(val instanceof Long)
			return (Long) val;
		return def;
	}
	
	/**
	 * Is the value an instance of a long
	 * @param path Path to the value
	 * @return True if the value is an instance of a long
	 */
	public boolean isLong(String path) {
		// Get the value
		Object val = get(path);
		
		// Make sure the value is not null
		if(val == null)
			return false;
		
		// Is the value an instance of a long
		return (val instanceof Long);
	}
	
	/**
	 * Get a list
	 * @param path Path to configuration section
	 * @return List value
	 */
	public List<?> getList(String path) {
		return getList(path, null);
	}
	
	/**
	 * Get a list
	 * @param path Path to configuration section
	 * @param def Default value
	 * @return List
	 */
	public List<?> getList(String path, List<?> def) {
		// Get the value
		Object val = get(path);
		
		// Make sure the value is not null
		if(val == null)
			return def;
		
		// The value has to be an instance of a boolean
		if(val instanceof List)
			return (List<?>) val;
		return def;
	}
	
	/**
	 * Is the value an instance of a list
	 * @param path Path to the value
	 * @return True if the value is an instance of a list
	 */
	public boolean isList(String path) {
		// Get the value
		Object val = get(path);
		
		// Make sure the value is not null
		if(val == null)
			return false;
		
		// Is the value an instance of a list
		return (val instanceof List);
	}
	
	/**
	 * Return a list of keys from a configuration section
	 * @param path Path to the configuration section to get the keys from
	 * @return List of keys
	 */
	public List<String> getKeys(String path) {
		// Make sure the path is not null
		if(path == null)
			return new ArrayList<String>();
		
		// Trim the path
		path = path.trim();
		
		// Make sure this configuration section exists
		if(!isConfigurationSection(path))
			return new ArrayList<String>();
		
		// Get the configuration sections to get the keys from
		ConfigurationSection section = getConfigurationSection(path);
		
		// Make sure the configuration section holds other configuration sections
		if(!section.isHoldingConfigurationSections())
			return new ArrayList<String>();
		
		// Return the list of keys
		@SuppressWarnings("unchecked")
		List<ConfigurationSection> sections = (List<ConfigurationSection>) section.get("");
		List<String> keys = new ArrayList<String>();
		
		for(ConfigurationSection entry : sections)
			keys.add(entry.getKey());
		
		return keys;
	}
	
	/**
	 * Get the configuration section
	 * @param path Path to the section
	 * @return ConfigurationSection or null when no section was found
	 */
	public ConfigurationSection getSection(String path) {
		return getConfigurationSection(path);
	}
	
	/**
	 * Get the configuration section
	 * @param path Path to the section
	 * @return ConfigurationSection or null when no section was found
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
		
		// Does the path contain any subpaths
		if(!path.contains(".")) {
			
			final String key = path;
			
			// Make sure the path is locating to a configuration section
			if(!isConfigurationSection(path))
				return null;
			
			// Get and return the configuration section
			if(this.val instanceof List) {
				// Get the configuration section
				try {
					@SuppressWarnings("unchecked")
					List<ConfigurationSection> sections = (List<ConfigurationSection>) this.val;
					for(ConfigurationSection section : sections) {
						if(section == null) 
							continue;

						// Is this the section we are searching for
						if(section.getKey().equals(key))
							return section;
					}
				} catch(ClassCastException e) { }
				return null;
				
			} else
				return null;
			
		} else {
			
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
	}

	/**
	 * Create a new configuration section
	 * @param path Configuration section
	 * @return New configuration section, null if path was invalid.<br>
	 * If path is an empty string it will return the section the method was called on.
	 */
	public ConfigurationSection createSection(String path) {
		return createConfigurationSection(path);
	}
	
	/**
	 * Create a new configuration section
	 * @param path Configuration section
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
		if(path.equals("")) {
			// Return this section without resetting the value
			return this;
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
			subPath = subPath.trim();
		}
		
		// Is the first key of the path leading to an already exsisting section
		if(isConfigurationSection(key)) {
			// Get the section
			ConfigurationSection section = getConfigurationSection(key);
			
			// Are there any subkeys
			if(subPath.length() == 0) {
				// Return the section
				return section;
			}
			
			// Create the sub key sections in the section and return the result
			return section.createConfigurationSection(subPath);
		
		} else {
			
			// Create a section
			//ConfigurationSection section = new ConfigurationSection(this, key, null);
			if(this.val instanceof List) {
				try {
					@SuppressWarnings("unchecked")
					List<ConfigurationSection> sections = (List<ConfigurationSection>) this.val;
					ConfigurationSection section = new ConfigurationSection(this, key, null);
					sections.add(section);
					this.val = sections;
					
					// Are there any subkeys
					if(subPath.length() == 0) {
						// Return the section
						return section;
					}
					
					// Create the sub key sections in the section and return the result
					return section.createConfigurationSection(subPath);
					
				} catch(ClassCastException ex) { }
			}
				
			// Create a new section
			ConfigurationSection section = new ConfigurationSection(this, key, null);
			List<ConfigurationSection> sections = new ArrayList<ConfigurationSection>();
			sections.add(section);
			this.val = sections;
			
			// Are there any subkeys
			if(subPath.length() == 0) {
				// Return the section
				return section;
			}
			
			// Create the sub key sections in the section and return the result
			return section.createConfigurationSection(subPath);
		}
	}
	
	/**
	 * Set the value
	 * @param val Value
	 */
	public void set(String path, Object val) {
		// Make sure the path is not null
		if(path == null)
			return;
		
		// Trim the path
		path = path.trim();
		
		// Is the path leading to this section
		if(path.equals("")) {
			this.val = val;
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
			section.set(subPath, val);
			return;
		
		} else {
			// Create a section
			ConfigurationSection section = new ConfigurationSection(this, key, null);
			if(this.val instanceof List) {
				try {
					@SuppressWarnings("unchecked")
					List<ConfigurationSection> sections = (List<ConfigurationSection>) this.val;
					sections.add(section);
					
				} catch(ClassCastException ex) {
					// Create a new section
					List<ConfigurationSection> sections = new ArrayList<ConfigurationSection>();
					sections.add(section);
					this.val = sections;
				}
			} else {
				// Create a new section
				List<ConfigurationSection> sections = new ArrayList<ConfigurationSection>();
				sections.add(section);
				this.val = sections;
			}
			
			// Set the value in the new section
			section.set(subPath, val);
		}
	}
	
	/**
	 * Is the value set
	 * @return True if the value was set
	 */
	public boolean isSet(String path) {
		// Make sure the path param is not null
		if(path == null)
			return false;
		
		// Get the section the path is leading to
		ConfigurationSection section = getConfigurationSection(path);
		
		// Make sure the section is not null
		if(section == null)
			return false;
		
		// Is the value of the section null
		return (section.get("") != null);
	}
	
	/**
	 * Is the value set
	 * @return True if the value was set
	 */
	public boolean isHoldingConfigurationSections() {
		// Is the current value null
		if(this.val == null)
			return false;
		
		try {
    		@SuppressWarnings("unchecked")
			List<ConfigurationSection> sections = (List<ConfigurationSection>) this.val;
    		if(sections.size() <= 0)
    			return false;
    		
    		return (sections.get(0) instanceof ConfigurationSection);
    	} catch(ClassCastException e) {
    		return false;
    	}
	}
	
	/**
	 * Is the value a configuration section
	 * @return True if the value is a configuration section
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
		if(!(this.val instanceof List))
			return false;
		
		// Get the list of configuration sections
		try {
			@SuppressWarnings("unchecked")
			List<ConfigurationSection> sections = (List<ConfigurationSection>) this.val;
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
			
			return false;
			
		} catch(ClassCastException e) {
			return false;
		}
	}
	
	/**
	 * Get all the values from the section as a Map list
	 * @return Values from the section as a Map list
	 */
	public Map<String, Object> getValues() {
        // Define a map list to store the values in
		Map<String, Object> out = new LinkedHashMap<String, Object>();
        
		// Make sure the value is not null
		if(this.key == null)
			return out;
		
		// Add the values to the 
        if(this.val instanceof List) {
        	try {
        		@SuppressWarnings("unchecked")
				List<ConfigurationSection> sections = (List<ConfigurationSection>) this.val;
        		//Map<String, Object> values = new LinkedHashMap<String, Object>();
        		for(ConfigurationSection entry : sections) {
        			if(entry.isHoldingConfigurationSections())
            			out.put(entry.getKey(), entry.getValues());
        			else
        				out.put(entry.getKey(), entry.get(""));
        		}
        		//out.put(getKey(), values);
        	} catch(ClassCastException e) {
        		out.put(getKey(), this.val);
        	}
        } else
        	out.put(getKey(), this.val);
        
        // Return the output
        return out;
	}
}
