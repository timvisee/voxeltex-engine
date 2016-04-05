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
import me.keybarricade.voxeltex.util.Color;
import org.joml.Vector2f;

public class LineOverlayComponent extends AbstractOverlayComponent {

    /**
     * Width of the line.
     */
    private float lineWidth = 1.0f;

    /**
     * Line position.
     */
    // TODO: Use a 2D transform object.
    private Vector2f position = Vector2fFactory.identity();

    /**
     * Line size.
     */
    // TODO: Use a 2D transform object.
    private Vector2f size = Vector2fFactory.identity();

    /**
     * Constructor.
     *
     * @param x Line X position.
     * @param y Line Y position.
     * @param w Line width.
     * @param h Line height.
     */
    public LineOverlayComponent(float x, float y, float w, float h) {
        this.position.set(x, y);
        this.size.set(w, h);
    }

    /**
     * Constructor.
     *
     * @param position Line position.
     * @param size Line size.
     */
    public LineOverlayComponent(Vector2f position, Vector2f size) {
        this.position.set(position);
        this.size.set(size);
    }

    @Override
    public void drawOverlay() {
        // Set the thickness of the line drawn
        RenderOverlayHelper.lineWidth(this.lineWidth);

        // Set the drawing color
        RenderOverlayHelper.color(Color.RED);

        // Render the line
        RenderOverlayHelper.renderLine(this.position, this.size);
    }

    /**
     * Get the line width.
     *
     * @return Line width.
     */
    public float getLineWidth() {
        return this.lineWidth;
    }

    /**
     * Set the line width.
     *
     * @param lineWidth Line width.
     */
    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    /**
     * Get the position of the line.
     *
     * @return Position.
     */
    public Vector2f getPosition() {
        return this.position;
    }

    /**
     * Set the position of the line.
     *
     * @param position Position.
     */
    public void setPosition(Vector2f position) {
        this.position = position;
    }

    /**
     * Get the size of the line.
     *
     * @return Size.
     */
    public Vector2f getSize() {
        return this.size;
    }

    /**
     * Set the size of the line.
     *
     * @param size Size.
     */
    public void setSize(Vector2f size) {
        this.size = size;
    }
}
