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

package me.keybarricade.voxeltex.component.transform;

import me.keybarricade.voxeltex.math.vector.Vector2fFactory;
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
     * Set the rectangle position.
     *
     * @param position Rectangle position.
     */
    public void setPosition(Vector2f position) {
        this.position.set(position);
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
     * Set the rectangle size.
     *
     * @param size Rectangle size.
     */
    public void setSize(Vector2f size) {
        this.size.set(size);
    }

    /**
     * Set the rectangle to match the given rectangle.
     *
     * @param rectangle Other rectangle.
     */
    public void set(Rectangle rectangle) {
        set(rectangle.getPosition(), rectangle.getSize());
    }

    /**
     * Set the position and size of the rectangle.
     *
     * @param position Rectangle position.
     * @param size Rectangle size.
     */
    public void set(Vector2f position, Vector2f size) {
        setPosition(position);
        setSize(size);
    }
}
