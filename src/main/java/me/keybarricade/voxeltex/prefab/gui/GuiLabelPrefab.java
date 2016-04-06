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

package me.keybarricade.voxeltex.prefab.gui;

import me.keybarricade.voxeltex.component.overlay.gui.GuiLabelComponentA;
import me.keybarricade.voxeltex.component.transform.HorizontalTransformAnchorType;
import me.keybarricade.voxeltex.component.transform.RectangleTransform;
import me.keybarricade.voxeltex.component.transform.VerticalTransformAnchorType;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.util.Color;
import org.joml.Vector2f;

public class GuiLabelPrefab extends GameObject {

    /**
     * The default name of the cube.
     */
    private static final String DEFAULT_NAME = "GuiLabelPrefab";

    /**
     * The rectangle transform component.
     */
    private RectangleTransform transform;

    /**
     * Button text.
     */
    private String text;

    /**
     * The GUI label component.
     */
    private GuiLabelComponentA label;

    /**
     * Constructor.
     */
    public GuiLabelPrefab() {
        this(DEFAULT_NAME, "Label");
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     */
    public GuiLabelPrefab(String name, String text) {
        // Construct the super
        super(name);

        // Set the button text
        this.text = text;

        // Create and configure the rectangle transform component
        this.transform = new RectangleTransform(
                new Vector2f(16, 0),
                new Vector2f(16, 24),
                HorizontalTransformAnchorType.STRETCH,
                VerticalTransformAnchorType.MIDDLE
        );

        // Create the label component
        this.label = new GuiLabelComponentA(this.text, Color.BLACK);

        // Add the transform and label component to the game object
        addComponent(this.transform);
        addComponent(this.label);
    }

    /**
     * Get the rectangle transform component.
     *
     * @return Rectangle transform component
     */
    public RectangleTransform getRectangleTransform() {
        return this.transform;
    }

    /**
     * Get the button text.
     *
     * @return Button text.
     */
    public String getText() {
        return text;
    }

    /**
     * Set the button text.
     *
     * @param text Button text.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get the GUI label component.
     *
     * @return Label component.
     */
    public GuiLabelComponentA getLabel() {
        return this.label;
    }

    /**
     * Get the label font color.
     *
     * @return Label color.
     */
    public Color getColor() {
        return this.label.getColor();
    }

    /**
     * Set the label font color
     *
     * @param color Label color.
     */
    public void setColor(Color color) {
        this.label.setColor(color);
    }
}
