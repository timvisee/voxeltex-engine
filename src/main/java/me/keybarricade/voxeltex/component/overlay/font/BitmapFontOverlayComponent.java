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

package me.keybarricade.voxeltex.component.overlay.font;

import me.keybarricade.voxeltex.component.overlay.AbstractOverlayComponent;
import me.keybarricade.voxeltex.font.BitmapFont;
import me.keybarricade.voxeltex.font.BitmapFontManager;
import me.keybarricade.voxeltex.math.vector.Vector2fFactory;
import me.keybarricade.voxeltex.render.OverlayUtil;
import me.keybarricade.voxeltex.render.RenderOverlayHelper;
import me.keybarricade.voxeltex.util.Color;
import org.joml.Vector2f;

public class BitmapFontOverlayComponent extends AbstractOverlayComponent {

    /**
     * Bitmap font that is used.
     */
    private BitmapFont font = BitmapFontManager.getDefault();

    /**
     * Text to render.
     */
    private String text = "";

    /**
     * Font color.
     */
    private Color color = Color.WHITE;

    /**
     * Rectangle position.
     */
    private Vector2f position = Vector2fFactory.identity();

    /**
     * Rectangle size.
     */
    private float size;

    /**
     * Constructor.
     *
     * @param position Position of the text to render.
     * @param size Size of the text to render.
     * @param text Text to render.
     */
    public BitmapFontOverlayComponent(Vector2f position, float size, String text) {
        this(position, size, text, BitmapFontManager.getDefault());
    }

    /**
     * Constructor.
     *
     * @param position Position of the text to render.
     * @param size Size of the text to render.
     * @param text Text to render.
     * @param font Font.
     */
    public BitmapFontOverlayComponent(Vector2f position, float size, String text, BitmapFont font) {
        this.position.set(position);
        this.size = size;
        this.text = text;
        this.font = font;
    }

    /**
     * Constructor.
     *
     * @param position Position of the text to render.
     * @param text Text to render.
     * @param text Text to render.
     * @param color Font color.
     */
    public BitmapFontOverlayComponent(Vector2f position, float size, String text, Color color) {
        this(position, size, text, BitmapFontManager.getDefault(), color);
    }

    /**
     * Constructor.
     *
     * @param position Position of the text to render.
     * @param text Text to render.
     * @param text Text to render.
     * @param font Font.
     * @param color Font color.
     */
    public BitmapFontOverlayComponent(Vector2f position, float size, String text, BitmapFont font, Color color) {
        this.position.set(position);
        this.size = size;
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
    public void drawOverlay() {
        // Apply the font color to the material
        this.font.getMaterial().setColor(this.color);

        // TODO: Use the font rendering code available in the RenderOverlayHelper class!

        // Bind and render each character separately
        for(int i = 0; i < text.length(); i++) {
            // Get the current character
            final char c = text.charAt(i);

            // Calculate the width factor of the current character
            final float widthFactor = this.font.getFontWidths().getCharacterWidthFactor(c);

            // Bind the font material with the current character and the proper character width
            this.font.getMaterial().bind(c, widthFactor);

            // Calculate the character width offset
            final float characterWidthOffset = this.size * this.font.getFontWidths().getStringWidthFactor(text.substring(0, i));

            // Get the window ratio factor
            final float windowRatio = OverlayUtil.getWindowRatioFactor();

            // Render the rectangle, and compensate with the window ratio factor
            RenderOverlayHelper.renderRectangle(
                    position.x + characterWidthOffset / windowRatio, position.y,
                    this.size * widthFactor / windowRatio, this.size
            );

            // Unbind the material
            this.font.getMaterial().unbind();
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
     * Get the size of the font.
     *
     * @return Size.
     */
    public float getSize() {
        return this.size;
    }

    /**
     * Set the size of the font.
     *
     * @param size Size.
     */
    public void setSize(float size) {
        this.size = size;
    }
}
