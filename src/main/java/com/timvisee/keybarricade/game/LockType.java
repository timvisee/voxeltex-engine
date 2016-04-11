package com.timvisee.keybarricade.game;

import com.timvisee.voxeltex.util.Color;

public enum LockType {

    /**
     * Green type.
     */
    GREEN(new Color(0, 1, 0)),

    /**
     * Red type.
     */
    RED(new Color(1, 0, 0)),

    /**
     * Light blue type.
     */
    LIGHT_BLUE(new Color(0, 0.746f, 1)),

    /**
     * Yellow type.
     */
    YELLOW(new Color(1, 1, 0));

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

    /**
     * Get a copy of the type color.
     *
     * @return Type color copy.
     */
    public Color getColorCopy() {
        return new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), this.color.getAlpha());
    }

    /**
     * Get the correct lock type based on the given data value.
     *
     * @param dataValue Data value.
     *
     * @return Lock type.
     */
    public static LockType fromDataValue(int dataValue) {
        return values()[dataValue];
    }
}
