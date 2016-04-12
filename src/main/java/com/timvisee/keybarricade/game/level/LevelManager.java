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

package com.timvisee.keybarricade.game.level;

import com.timvisee.keybarricade.game.asset.GameAssetLoader;
import com.timvisee.yamlwrapper.configuration.ConfigurationSection;
import com.timvisee.yamlwrapper.configuration.YamlConfiguration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LevelManager {

    /**
     * Location of the level data file.
     */
    public static final String LEVEL_FILE_PATH = "level/levels.yml";

    /**
     * List of loaded levels.
     */
    private List<Level> levels = new ArrayList<>();

    /**
     * Constructor.
     * This constructor doesn't load the levels. The levels should thus be loaded manually.
     */
    public LevelManager() {
        this(false);
    }

    /**
     * Constructor.
     *
     * @param load True to immediately load all levels.
     */
    public LevelManager(boolean load) {
        // Load the levels
        if(load)
            load();
    }

    /**
     * Get the list of levels.
     *
     * @return List of levels.
     */
    public List<Level> getLevels() {
        return this.levels;
    }

    /**
     * Get the number of levels in this level manager.
     *
     * @return Number of levels.
     */
    public int getLevelCount() {
        return this.levels.size();
    }

    /**
     * Add the given level.
     *
     * @param level Level.
     */
    public void addLevel(Level level) {
        this.levels.add(level);
    }

    /**
     * Return the level at the given index.
     *
     * @param i Level index.
     *
     * @return Level.
     */
    public Level getLevel(int i) {
        return this.levels.get(i);
    }

    /**
     * Clear the list of loaded levels.
     */
    public void clear() {
        this.levels.clear();
    }

    /**
     * Load the level data from the levels file.
     */
    public void load() {
        // Show a status message
        System.out.println("Loading game levels...");

        // Clear the current list of levels
        this.levels.clear();

        // Get the input stream for the level file
        InputStream inputStream = GameAssetLoader.getInstance().loadResourceStream(LEVEL_FILE_PATH);

        // Load the YAML configuration
        YamlConfiguration config = YamlConfiguration.loadConfiguration(inputStream);

        // Get a list of level indexes that are in the level configuration
        final List<String> levelIndexes = config.getKeys("levels");

        // Loop through all levels
        for(int i = 0, size = levelIndexes.size(); i < size; i++) {
            // Get the configuration section for this level
            ConfigurationSection levelConfig = config.getConfigurationSection("levels." + levelIndexes.get(i));

            // Create a level representation with this configuration and add it to the manager
            this.levels.add(new Level(levelConfig));
        }

        // Levels loaded successfully, show a status message
        System.out.println(this.levels.size() + " levels have been loaded!");
    }
}
