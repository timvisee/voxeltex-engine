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

package com.timvisee.keybarricade.game.entity;

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
    YELLOW(new Color(1, 1, 0)),

    /**
     * Purple type.
     */
    PURPLE(new Color(141 / 256f, 56 / 256f, 201 / 256f));

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
