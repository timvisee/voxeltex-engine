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

package com.timvisee.voxeltex.prefab.gui;

import com.timvisee.voxeltex.structure.component.overlay.gui.GuiButtonComponent;
import com.timvisee.voxeltex.structure.component.overlay.transform.HorizontalTransformAnchorType;
import com.timvisee.voxeltex.structure.component.overlay.transform.RectangleTransform;
import com.timvisee.voxeltex.structure.component.overlay.transform.VerticalTransformAnchorType;
import com.timvisee.voxeltex.structure.gameobject.GameObject;
import org.joml.Vector2f;

public class GuiButtonPrefab extends GameObject {

    /**
     * The default name of the cube.
     */
    private static final String DEFAULT_NAME = "GuiButtonPrefab";

    /**
     * The rectangle transform component.
     */
    private RectangleTransform transform;

    /**
     * The GUI button component.
     */
    private GuiButtonComponent button;

    /**
     * Button text.
     */
    private String text;

    /**
     * Constructor.
     */
    public GuiButtonPrefab() {
        this(DEFAULT_NAME, "Button");
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     */
    public GuiButtonPrefab(String name, String text) {
        // Construct the super
        super(name);

        // Set the button text
        this.text = text;

        // Create and configure the rectangle transform component
        this.transform = new RectangleTransform(
                new Vector2f(16, 0),
                new Vector2f(16, 40),
                HorizontalTransformAnchorType.STRETCH,
                VerticalTransformAnchorType.MIDDLE
        );

        // Store the current instance
        final GuiButtonPrefab instance = this;

        // Create the button component
        this.button = new GuiButtonComponent(this.text) {
            @Override
            public void onClick() {
                // Call the super
                super.onClick();

                // Route the click
                instance.onClick();
            }
        };

        // Add the transform and button component to the game object
        addComponent(this.transform);
        addComponent(this.button);
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
     * Get the button component.
     *
     * @return Button component.
     */
    public GuiButtonComponent getButton() {
        return this.button;
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
     * Called when a button is clicked.
     */
    public void onClick() { }
}
