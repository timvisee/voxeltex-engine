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

package me.keybarricade.voxeltex.component.drawable.line;

import me.keybarricade.voxeltex.component.drawable.AbstractDrawableComponent;
import org.lwjgl.opengl.GL11;

public class AxisDrawComponent extends AbstractDrawableComponent {

    /**
     * Width of the axis lines.
     */
    private float lineWidth = 5.0f;

    /**
     * Constructor.
     */
    public AxisDrawComponent() { }

    /**
     * Constructor.
     *
     * @param lineWidth Line width.
     */
    public AxisDrawComponent(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    @Override
    public void create() { }

    @Override
    public void start() { }

    @Override
    public synchronized void update() { }

    @Override
    public void destroy() { }

    @Override
    public synchronized void draw() {
        // Set the thickness of the axis drawn
        GL11.glLineWidth(this.lineWidth);

        // Enable line drawing mode
        GL11.glBegin(GL11.GL_LINES);

        // Green for X
        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glVertex3f(1.0f, 0.0f, 0.0f);

        // Green for Y
        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 1.0f, 0.0f);

        // Green for Z
        GL11.glColor3f(0.0f, 0.0f, 1.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 1.0f);

        // Finish drawing
        GL11.glEnd();
    }

    /**
     * Get the line width of the axis lines.
     *
     * @return Line width.
     */
    public float getLineWidth() {
        return this.lineWidth;
    }

    /**
     * Set the line width of the axis lines.
     *
     * @param lineWidth Line width.
     */
    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }
}
