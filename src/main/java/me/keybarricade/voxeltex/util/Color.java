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

package me.keybarricade.voxeltex.util;

import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Color {

    /**
     * Red color intensity, from 0 to 1.
     */
    private float red = 0f;

    /**
     * Green color intensity, from 0 to 1.
     */
    private float green = 0f;

    /**
     * Blue color intensity, from 0 to 1.
     */
    private float blue = 0f;

    /**
     * Alpha channel intensity, from 0 to 1.
     */
    private float alpha = 1f;

    /**
     * White color default.
     */
    public static final Color WHITE = new Color(0xFFFFFF);

    /**
     * Black color default.
     */
    public static final Color BLACK = new Color(0x000000);

    /**
     * Red color default.
     */
    public static final Color RED = new Color(0xFF0000);

    /**
     * Green color default.
     */
    public static final Color GREEN = new Color(0x00FF00);

    /**
     * Blue color default.
     */
    public static final Color BLUE = new Color(0x0000FF);

    /**
     * Orange color default.
     */
    public static final Color ORANGE = new Color(0xFFA500);

    /**
     * Constructor.
     *
     * @param red Red channel intensity, from 0 to 1.
     * @param green Green channel intensity, from 0 to 1.
     * @param blue Blue channel intensity, from 0 to 1.
     */
    public Color(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Constructor.
     *
     * @param red Red channel intensity, from 0 to 1.
     * @param green Green channel intensity, from 0 to 1.
     * @param blue Blue channel intensity, from 0 to 1.
     * @param alpha Alpha channel intensity, from 0 to 1.
     */
    public Color(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    /**
     * Constructor.
     *
     * @param rgba Hexadecimal color value (with alpha channel).
     */
    public Color(int rgba) {
        set(rgba);
    }

    /**
     * Create a randomized color.
     *
     * @return Color.
     */
    public static final Color random() {
        return new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
    }

    /**
     * Set the color.
     *
     * @param red Red channel intensity, from 0 to 1.
     * @param green Green channel intensity, from 0 to 1.
     * @param blue Blue channel intensity, from 0 to 1.
     * @param alpha Alpha channel intensity, from 0 to 1.
     */
    public void set(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    /**
     * Set the color.
     *
     * @param rgba Hexadecimal color value (with alpha channel).
     */
    public void set(int rgba) {
        this.red = ((rgba & 0x00FF0000) >> 16) / 255.0f;
        this.green = ((rgba & 0x0000FF00) >> 8) / 255.0f;
        this.blue = (rgba & 0x000000FF) / 255.0f;
        this.alpha = 1 - ((rgba & 0xFF000000) >> 24) / 255.0f;
    }

    /**
     * Get red channel intensity, from 0 to 1.
     *
     * @return Red channel.
     */
    public float getRed() {
        return red;
    }

    /**
     * Set red channel intensity, from 0 to 1.
     *
     * @param red Red channel.
     */
    public void setRed(float red) {
        this.red = red;
    }

    /**
     * Get green channel intensity, from 0 to 1.
     *
     * @return Green channel.
     */
    public float getGreen() {
        return green;
    }

    /**
     * Set green channel intensity, from 0 to 1.
     *
     * @param green Green channel.
     */
    public void setGreen(float green) {
        this.green = green;
    }

    /**
     * Get blue channel intensity, from 0 to 1.
     *
     * @return Blue channel.
     */
    public float getBlue() {
        return blue;
    }

    /**
     * Set blue channel intensity, from 0 to 1.
     *
     * @param blue Blue channel.
     */
    public void setBlue(float blue) {
        this.blue = blue;
    }

    /**
     * Get alpha channel intensity, from 0 to 1.
     *
     * @return Alpha channel.
     */
    public float getAlpha() {
        return alpha;
    }

    /**
     * Set alpha channel intensity, from 0 to 1.
     *
     * @param alpha Alpha channel.
     */
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    /**
     * Convert the color to a hexadecimal value. (alpha channel included)
     *
     * @return Color value.
     */
    public int toInt() {
        // Get the values
        int red = (int) (getRed() * 255);
        int green = (int) (getGreen() * 255);
        int blue = (int) (getBlue() * 255);
        int alpha = (int) (getAlpha() * 255);

        // Combine the values into the result
        return red | (green << 8) | (blue << 16) | (alpha << 24);
    }

    /**
     * Get the vector representation of this color without the alpha channel.
     *
     * This method allocates a new vector and should thus be used with caution,
     * use the allocation free variant when possible.
     *
     * @return Vector.
     */
    public Vector3f toVector3f() {
        return toVector3f(Vector3fFactory.identity());
    }

    /**
     * Get the vector representation of this color without the alpha channel.
     *
     * @param dest Destination vector. (allocation free)
     *
     * @return Vector.
     */
    public Vector3f toVector3f(Vector3f dest) {
        return dest.set(this.red, this.green, this.blue);
    }

    /**
     * Get the vector representation of this color.
     *
     * This method allocates a new vector and should thus be used with caution,
     * use the allocation free variant when possible.
     *
     * @return Vector.
     */
    public Vector4f toVector4f() {
        return toVector4f(new Vector4f());
    }

    /**
     * Get the vector representation of this color.
     *
     * @param dest Destination vector. (allocation free)
     *
     * @return Vector.
     */
    public Vector4f toVector4f(Vector4f dest) {
        return dest.set(this.red, this.green, this.blue, this.alpha);
    }
}
