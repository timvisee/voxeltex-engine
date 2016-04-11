package com.timvisee.keybarricade.game.level;

import com.timvisee.yamlwrapper.configuration.ConfigurationSection;

public class Level {

    /**
     * Configuration section for this level.
     */
    private ConfigurationSection config;

    /**
     * Constructor.
     *
     * @param config Configuration section for this level.
     */
    public Level(ConfigurationSection config) {
        this.config = config;
    }

    /**
     * Get the configuration section for this level.
     *
     * @return Level configuration section.
     */
    public ConfigurationSection getConfig() {
        return this.config;
    }
}
