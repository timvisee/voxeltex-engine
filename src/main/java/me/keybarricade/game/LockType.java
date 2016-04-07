package me.keybarricade.game;

import me.keybarricade.voxeltex.util.Color;

public enum LockType {

    /**
     * Yellow type.
     */
    YELLOW(new Color(1, 1, 0)),

    /**
     * Greem type.
     */
    GREEN(new Color(0, 1, 0));

    /**
     * Type color.
     */
    private Color color;

    /**
     * Constructor.
     *
     * @param color Color.
     */
    LockType(Color color) {
        this.color = color;
    }

    /**
     * Get the type color.
     *
     * @return Type color.
     */
    public Color getColor() {
        return this.color;
    }
}
