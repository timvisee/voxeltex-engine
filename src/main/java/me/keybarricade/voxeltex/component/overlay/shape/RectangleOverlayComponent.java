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

package me.keybarricade.voxeltex.component.overlay.shape;

import me.keybarricade.voxeltex.component.overlay.AbstractOverlayComponent;
import me.keybarricade.voxeltex.math.vector.Vector2fFactory;
import me.keybarricade.voxeltex.render.RenderOverlayHelper;
import org.joml.Vector2f;

public class RectangleOverlayComponent extends AbstractOverlayComponent {

    /**
     * Rectangle position.
     */
    // TODO: Use a 2D transform object.
    private Vector2f position = Vector2fFactory.identity();

    /**
     * Rectangle size.
     */
    // TODO: Use a 2D transform object.
    private Vector2f size = Vector2fFactory.identity();

    /**
     * Constructor.
     *
     * @param x Rectangle X position.
     * @param y Rectangle Y position.
     * @param w Rectangle width.
     * @param h Rectangle height.
     */
    public RectangleOverlayComponent(float x, float y, float w, float h) {
        this.position.set(x, y);
        this.size.set(w, h);
    }

    /**
     * Constructor.
     *
     * @param position Rectangle position.
     * @param size Rectangle size.
     */
    public RectangleOverlayComponent(Vector2f position, Vector2f size) {
        this.position.set(position);
        this.size.set(size);
    }

    @Override
    public void drawOverlay() {
        // Render the rectangle
        RenderOverlayHelper.renderRectangle(this.position, this.size);
    }

    /**
     * Get the position of the rectangle.
     *
     * @return Position.
     */
    public Vector2f getPosition() {
        return this.position;
    }

    /**
     * Set the position of the rectangle.
     *
     * @param position Position.
     */
    public void setPosition(Vector2f position) {
        this.position = position;
    }

    /**
     * Get the size of the rectangle.
     *
     * @return Size.
     */
    public Vector2f getSize() {
        return this.size;
    }

    /**
     * Set the size of the rectangle.
     *
     * @param size Size.
     */
    public void setSize(Vector2f size) {
        this.size = size;
    }
}
