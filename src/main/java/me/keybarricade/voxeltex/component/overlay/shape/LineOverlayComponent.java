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
import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

public class LineOverlayComponent extends AbstractOverlayComponent {

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
        // Line width
        // TODO: Make line width configurable!
        GL11.glLineWidth(2.0f);

        // Enable line drawing mode
        GL11.glBegin(GL11.GL_LINES);

        // Set the line color
        GL11.glColor3f(1, 0, 0);

        // Draw the line
        GL11.glVertex3f(this.position.x, this.position.y, 0f);
        GL11.glVertex3f(this.position.x + this.size.x, this.position.y + this.size.y, 0f);

        // Finish drawing
        GL11.glEnd();
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
