package me.keybarricade.game.level;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {

    /**
     * List of loaded levels.
     */
    private List<Level> levels = new ArrayList<>();

    /**
     * Constructor.
     */
    public LevelManager() { }

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
}
