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

package me.keybarricade.voxeltex.render;

import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

public class RenderOverlayHelper {

    /**
     * Set the width of lines being rendered.
     *
     * @param lineWidth Line width.
     */
    public static void lineWidth(float lineWidth) {
        GL11.glLineWidth(lineWidth);
    }

    /**
     * Render a rectangle at the given position.
     *
     * @param position Rectangle position.
     * @param size Rectangle size.
     */
    public static void renderRectangle(Vector2f position, Vector2f size) {
        renderRectangle(position.x, position.y, size.x, size.y);
    }

    /**
     * Render a rectangle at the given position.
     *
     * @param x Rectangle X position.
     * @param y Rectangle Y position.
     * @param w Rectangle width.
     * @param h Rectangle height.
     */
    public static void renderRectangle(float x, float y, float w, float h) {
        // Enable line drawing mode
        GL11.glBegin(GL11.GL_QUADS);

        // Set the grid color
        GL11.glColor4f(1, 0, 0, 0.5f);

        // Draw the grid
        GL11.glVertex3f(x, y, 0f);
        GL11.glVertex3f(x, y + h, 0f);
        GL11.glVertex3f(x + w, y + h, 0f);
        GL11.glVertex3f(x + w, y, 0f);

        // Finish drawing
        GL11.glEnd();
    }

    /**
     * Render a line at the given position.
     *
     * @param position Line position.
     * @param size Line size.
     */
    public static void renderLine(Vector2f position, Vector2f size) {
        renderLine(position.x, position.y, size.x, size.y);
    }

    /**
     * Render a line at the given position.
     *
     * @param x Line X position.
     * @param y Line Y position.
     * @param w Line width.
     * @param h Line height.
     */
    public static void renderLine(float x, float y, float w, float h) {
        // Enable line drawing mode
        GL11.glBegin(GL11.GL_LINES);

        // Set the grid color
        GL11.glColor4f(1, 0, 0, 0.5f);

        // Draw the grid
        GL11.glVertex3f(x, y, 0f);
        GL11.glVertex3f(x + w, y + h, 0f);

        // Finish drawing
        GL11.glEnd();
    }
}
