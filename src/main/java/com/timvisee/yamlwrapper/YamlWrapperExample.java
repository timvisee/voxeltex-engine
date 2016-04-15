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

package com.timvisee.yamlwrapper;

import com.timvisee.yamlwrapper.configuration.YamlConfiguration;

import java.util.ArrayList;
import java.util.Arrays;

public class YamlWrapperExample {

    /**
     * Example implementation of YAML wrapper.
     *
     * @param args Startup arguments.
     */
    public static void main(String[] args) {
        // Construct a YamlConfiguration instance
        YamlConfiguration config = new YamlConfiguration();

        // Show a status message
        System.out.println("Setting some values...");

        // Set some values
        config.set("key", "value");
        config.set("first.second", 1);
        config.set("first.second2", 2);
        config.set("a.b.c.d.e.f.g", true);
        config.set("list",
                new ArrayList<>(Arrays.asList(
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
        System.out.println("first.second: " + config.getInt("first.second"));
        System.out.println("first.second2: " + config.getInt("first.second2"));
        System.out.println("a.b.c.d.e.f.g: " + config.getBoolean("a.b.c.d.e.f.g"));
        System.out.println("list: " + config.getList("list").toString());
        System.out.println("unknown.key: " + config.getString("unknown.key", "Default value"));
    }
}
