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

package me.keybarricade.voxeltex.component.overlay.gui;

import me.keybarricade.voxeltex.component.transform.HorizontalTransformAnchorType;
import me.keybarricade.voxeltex.component.transform.Rectangle;
import me.keybarricade.voxeltex.component.transform.RectangleTransform;
import me.keybarricade.voxeltex.component.transform.VerticalTransformAnchorType;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.global.Input;
import me.keybarricade.voxeltex.render.RenderOverlayHelper;
import me.keybarricade.voxeltex.util.Color;
import org.joml.Vector2f;

public class GuiButtonComponent extends AbstractGuiComponent {

    /**
     * Temporary rectangle variable, used to minimize object allocation at runtime to improve overall performance.
     */
    private final Rectangle tempRectangle = new Rectangle();

    /**
     * Button font component.
     */
    private GuiLabelComponent font;

    /**
     * Button text.
     */
    private String text = "Button";

    @Override
    public void create() {
        // Create a child object that renders the text
        GameObject textRenderer = new GameObject("ButtonText");

        // Add a transform component
        textRenderer.addComponent(new RectangleTransform(
                new Vector2f(8, 8),
                new Vector2f(8, 8),
                HorizontalTransformAnchorType.STRETCH,
                VerticalTransformAnchorType.STRETCH
        ));

        // Create and add a font component
        this.font = new GuiLabelComponent(this.text);
        textRenderer.addComponent(font);

        // Add the text renderer component to this object as child
        getOwner().addChild(textRenderer);
    }

    /**
     * Constructor.
     */
    public GuiButtonComponent() { }

    @Override
    public void drawOverlay() {
        // Define variables to store whether the button is hovered and/or pressed
        boolean hover = false;
        boolean pressed = false;

        // Get the X and Y coordiante of the cursor in overlay space
        float mouseX = Input.getMouseXOverlay();
        float mouseY = Input.getMouseYOverlay();

        // Synchronize to ensure we aren't using this temporary variable in multiple spots at the same time
        synchronized(this.tempRectangle) {
            // Get the transform
            // TODO: Buffer this
            getComponent(RectangleTransform.class).getOverlayRectangle(this.tempRectangle);

            // Check whether the button is hovered or pressed
            if(mouseX >= this.tempRectangle.getX() && mouseX <= this.tempRectangle.getX() + this.tempRectangle.getWidth() &&
                    mouseY >= this.tempRectangle.getY() && mouseY <= this.tempRectangle.getY() + this.tempRectangle.getHeight()) {
                hover = true;
                pressed = Input.isMouseButtonDown(0);
            }

            // Update the button visuals
            if(pressed) {
                RenderOverlayHelper.color(0.2f, 0.2f, 0.2f, .9f);
                font.setColor(Color.WHITE);
            } else if(hover) {
                RenderOverlayHelper.color(.6f, .6f, .6f, .7f);
                font.setColor(Color.BLACK);
            } else {
                RenderOverlayHelper.color(0.9f, 0.9f, 0.9f, .7f);
                font.setColor(Color.BLACK);
            }

            // Render the rectangle
            RenderOverlayHelper.renderRectangle(
                    this.tempRectangle.getX(), this.tempRectangle.getY(),
                    this.tempRectangle.getWidth(), this.tempRectangle.getHeight()
            );
        }
    }

    /**
     * Get the bitmap font component that is used for font rendering.
     *
     * @return Bitmap font component.
     */
    public GuiLabelComponent getBitmapFontComponent() {
        return this.font;
    }

    /**
     * Get the text on the button.
     *
     * @return Button text.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Set the text on the button
     *
     * @param text Button text.
     */
    public void setText(String text) {
        // Set the button text
        this.text = text;

        // Set the button text on the font component
        this.font.setText(text);
    }
}
