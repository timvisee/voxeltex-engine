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

package com.timvisee.voxeltex.architecture.component.overlay.transform;

import com.timvisee.voxeltex.util.math.vector.Vector2fFactory;
import org.joml.Vector2f;

public class Rectangle {

    /**
     * Rectangle position.
     */
    private final Vector2f position = Vector2fFactory.identity();

    /**
     * Rectangle size.
     */
    private final Vector2f size = Vector2fFactory.identity();

    /**
     * Constructor.
     */
    public Rectangle() { }

    /**
     * Constructor.
     *
     * @param size Rectangle size.
     */
    public Rectangle(Vector2f size) {
        setSize(size);
    }

    public Rectangle(Vector2f position, Vector2f size) {
        setPosition(position);
        setSize(size);
    }

    /**
     * Get the rectangle position.
     *
     * @return Rectangle position.
     */
    public Vector2f getPosition() {
        return this.position;
    }

    /**
     * Get the X position.
     *
     * @return X position.
     */
    public float getX() {
        return this.position.x;
    }

    /**
     * Get the Y position.
     *
     * @return Y position.
     */
    public float getY() {
        return this.position.y;
    }

    /**
     * Set the rectangle position.
     *
     * @param position Rectangle position.
     */
    public Rectangle setPosition(Vector2f position) {
        // Set the position and size
        this.position.set(position);

        // Return this
        return this;
    }

    /**
     * Set the rectangle position.
     *
     * @param x Rectangle X position.
     * @param y Rectangle Y position.
     */
    public Rectangle setPosition(float x, float y) {
        // Set the position and size
        this.position.set(x, y);

        // Return this
        return this;
    }

    /**
     * Set the X position.
     *
     * @param x X position.
     *
     * @return this
     */
    public Rectangle setX(float x) {
        this.position.x = x;

        // Return this
        return this;
    }

    /**
     * Set the Y position.
     *
     * @param y Y position.
     *
     * @return this
     */
    public Rectangle setY(float y) {
        this.position.y = y;

        // Return this
        return this;
    }

    /**
     * Get the rectangle size.
     *
     * @return Rectangle size.
     */
    public Vector2f getSize() {
        return this.size;
    }

    /**
     * Get the rectangle width.
     *
     * @return Rectangle width.
     */
    public float getWidth() {
        return this.size.x;
    }

    /**
     * Get the rectangle height.
     *
     * @return Rectangle height.
     */
    public float getHeight() {
        return this.size.y;
    }

    /**
     * Set the rectangle size.
     *
     * @param size Rectangle size.
     */
    public Rectangle setSize(Vector2f size) {
        // Set the position and size
        this.size.set(size);

        // Return this
        return this;
    }

    /**
     * Set the rectangle size.
     *
     * @param w Rectangle width.
     * @param h Rectangle height.
     */
    public Rectangle setSize(float w, float h) {
        // Set the position and size
        this.size.set(w, h);

        // Return this
        return this;
    }

    /**
     * Set the rectangle width.
     *
     * @param width Rectangle width.
     *
     * @return this
     */
    public Rectangle setWidth(float width) {
        this.size.x = width;

        // Return this
        return this;
    }

    /**
     * Set the rectangle height.
     *
     * @param height Rectangle height.
     *
     * @return this
     */
    public Rectangle setHeight(float height) {
        this.size.y = height;

        // Return this
        return this;
    }

    /**
     * Set the rectangle to match the given rectangle.
     *
     * @param rectangle Other rectangle.
     */
    public Rectangle set(Rectangle rectangle) {
        // Set the position and size
        set(rectangle.getPosition(), rectangle.getSize());

        // Return this
        return this;
    }

    /**
     * Set the position and size of the rectangle.
     *
     * @param position Rectangle position.
     * @param size Rectangle size.
     */
    public Rectangle set(Vector2f position, Vector2f size) {
        // Set the position and size
        setPosition(position);
        setSize(size);

        // Return this
        return this;
    }

    /**
     * Set the position and size of the rectangle.
     *
     * @param x Rectangle X position.
     * @param y Rectangle Y position.
     * @param w Rectangle width.
     * @param h Rectangle height.
     */
    public Rectangle set(float x, float y, float w, float h) {
        // Set the position and size
        setPosition(x, y);
        setSize(w, h);

        // Return this
        return this;
    }
}
