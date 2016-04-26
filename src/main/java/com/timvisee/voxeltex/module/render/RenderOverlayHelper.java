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

package com.timvisee.voxeltex.module.render;

import com.timvisee.voxeltex.module.Color;
import com.timvisee.voxeltex.module.font.BitmapFont;
import com.timvisee.voxeltex.module.transform.rectangle.Rectangle;
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
     * Set the drawing color.
     *
     * @param color Drawing color.
     */
    public static void color(Color color) {
        color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    /**
     * Set the drawing color.
     *
     * @param r Red channel intensity.
     * @param g Green channel intensity.
     * @param b Blue channel intensity.
     */
    public static void color(float r, float g, float b) {
        GL11.glColor3f(r, g, b);
    }

    /**
     * Set the drawing color.
     *
     * @param r Red channel intensity.
     * @param g Green channel intensity.
     * @param b Blue channel intensity.
     * @param a Alpha channel intensity.
     */
    public static void color(float r, float g, float b, float a) {
        GL11.glColor4f(r, g, b, a);
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

        // Draw the grid
        GL11.glVertex3f(x, y, 0f);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex3f(x + w, y, 0f);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex3f(x + w, y + h, 0f);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(x, y + h, 0f);
        GL11.glTexCoord2f(0, 1);

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

        // Draw the grid
        GL11.glVertex3f(x, y, 0f);
        GL11.glVertex3f(x + w, y + h, 0f);

        // Finish drawing
        GL11.glEnd();
    }

    /**
     * Render a font inside the given rectangle with the given text.
     * The size of the font will be adjusted automatically to fit the rectangle.
     *
     * @param rectangle Rectangle to draw in, in overlay space.
     * @param font The font to draw.
     * @param text The text to draw.
     */
    public static void renderFont(Rectangle rectangle, BitmapFont font, String text) {
        // Get the window ratio factor
        final float windowRatio = OverlayUtil.getWindowRatioFactor();

        // Determine the size, to fit the button
        float size = rectangle.getHeight();

        // Calculate the total width of the string
        float fontWidthX = font.getFontWidths().getStringWidthFactor(text) / windowRatio * size;

        // Make sure the string will fit, if not decrease the size and update the width accordingly
        if(fontWidthX > rectangle.getWidth()) {
            size *= rectangle.getWidth() / fontWidthX;
            fontWidthX *= rectangle.getWidth() / fontWidthX;
        }

        // Determine the X and Y offset of the string
        float fontOffsetX = (rectangle.getWidth() - fontWidthX) / 2.0f;
        float fontOffsetY = (rectangle.getHeight() - size) / 2.0f;

        // Bind and render each character separately
        for(int i = 0; i < text.length(); i++) {
            // Get the current character
            final char c = text.charAt(i);

            // Calculate the width factor of the current character
            final float widthFactor = font.getFontWidths().getCharacterWidthFactor(c);

            // Bind the font material with the current character and the proper character width
            font.getMaterial().bind(c, widthFactor);

            // Calculate the character width offset
            final float characterWidthOffset = size * font.getFontWidths().getStringWidthFactor(text.substring(0, i));

            // Render the rectangle, and compensate with the window ratio factor
            renderRectangle(
                    rectangle.getX() + characterWidthOffset / windowRatio + fontOffsetX, rectangle.getY() + fontOffsetY,
                    size * widthFactor / windowRatio, size
            );

            // Unbind the material
            font.getMaterial().unbind();
        }
    }
}
