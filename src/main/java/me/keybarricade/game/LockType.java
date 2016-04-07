package me.keybarricade.game;

import me.keybarricade.voxeltex.util.Color;

public enum LockType {

    /**
     * Red type.
     */
    RED(Color.RED);

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
