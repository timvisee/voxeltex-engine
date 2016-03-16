package com.timvisee.yamlwrapper;

import java.util.ArrayList;
import java.util.Arrays;

import com.timvisee.yamlwrapper.configuration.YamlConfiguration;

public class Main {

	public static void main(String[] args) {
		// Construct a YamlConfiguration instance
		YamlConfiguration config = new YamlConfiguration();

		// Show a status message
		System.out.println("Setting some values...");
		
		// Set some values
		config.set("key", "value");
		config.set("test.subkey", 1);
		config.set("test.subkey2", 2);
		config.set("a.b.c.d.e.f.g", true);
		config.set("list",
				new ArrayList<String>(Arrays.asList(
						new String[]{
							"item1",
							"item2",
							"item3",
							"item4",
							"item5"
						})
					)
				);
		
		// Show a status message
		System.out.println("Reading some values...\n");

		// Read some values
		System.out.println("key: " + config.getString("key"));
		System.out.println("test.subkey: " + config.getInt("test.subkey"));
		System.out.println("test.subkey2: " + config.getInt("test.subkey2"));
		System.out.println("a.b.c.d.e.f.g: " + config.getBoolean("a.b.c.d.e.f.g"));
		System.out.println("list: " + config.getList("list").toString());
		System.out.println("unknown.key: " + config.getString("unknown.key", "Default value"));
	}
}
