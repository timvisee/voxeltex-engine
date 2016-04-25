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

package com.timvisee.voxeltex.structure.component.overlay.gui;

import com.timvisee.voxeltex.font.BitmapFont;
import com.timvisee.voxeltex.font.BitmapFontManager;
import com.timvisee.voxeltex.render.RenderOverlayHelper;
import com.timvisee.voxeltex.structure.component.overlay.transform.Rectangle;
import com.timvisee.voxeltex.util.Color;

public class GuiLabelComponent extends AbstractGuiComponent {

    /**
     * Bitmap font that is used.
     */
    private BitmapFont font = BitmapFontManager.getDefault();

    /**
     * Text to render.
     */
    private String text;

    /**
     * Font color.
     */
    private Color color = Color.BLACK;

    /**
     * Temporary rectangle variable, used to minimize object allocation at runtime to improve overall performance.
     */
    private final Rectangle tempRectangle = new Rectangle();

    /**
     * Constructor.
     *
     * @param text Text to render.
     */
    public GuiLabelComponent(String text) {
        this(text, BitmapFontManager.getDefault());
    }

    /**
     * Constructor.
     *
     * @param text Text to render.
     * @param font Bitmap font.
     */
    public GuiLabelComponent(String text, BitmapFont font) {
        this.text = text;
        this.font = font;
    }

    /**
     * Constructor.
     *
     * @param text Text to render.
     * @param color Font color.
     */
    public GuiLabelComponent(String text, Color color) {
        this(text, BitmapFontManager.getDefault(), color);
    }

    /**
     * Constructor.
     *
     * @param text Text to render.
     * @param font Bitmap font.
     * @param color Font color.
     */
    public GuiLabelComponent(String text, BitmapFont font, Color color) {
        this.text = text;
        this.font = font;
        this.color = color;
    }

    /**
     * Get the font color.
     *
     * @return Font color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Set the font color.
     *
     * @param color Font color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void onDrawOverlay() {
        // Apply the font color to the material
        this.font.getMaterial().setColor(this.color);

        // Synchronize to ensure we aren't using this temporary variable in multiple spots at the same time
        synchronized(this.tempRectangle) {
            // Make sure we've a valid transform component, if not, skip the following code with an error message
            if(!hasRectangleTransform()) {
                System.out.println("No RectangleTransform component in " + this + " of " + getOwner() + ", unable to render");
                return;
            }

            // Get the transform
            getRectangleTransform().getOverlayRectangle(this.tempRectangle);

            // Draw the font
            RenderOverlayHelper.renderFont(this.tempRectangle, this.font, text);
        }
    }

    /**
     * Get the text to render.
     *
     * @return Text.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Set the text to render.
     *
     * @param text Text.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get the bitmap font used.
     *
     * @return Bitmap font.
     */
    public BitmapFont getFont() {
        return this.font;
    }

    /**
     * Set the bitmap font used.
     *
     * @param font Bitmap font.
     */
    public void setFont(BitmapFont font) {
        this.font = font;
    }
}
