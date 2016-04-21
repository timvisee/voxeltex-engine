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

package com.timvisee.keybarricade.game.prefab.entity;

import com.timvisee.keybarricade.game.asset.GameResourceBundle;
import com.timvisee.voxeltex.component.light.LightSourceComponent;
import com.timvisee.voxeltex.gameobject.GameObject;
import com.timvisee.voxeltex.light.Light;
import com.timvisee.voxeltex.material.Material;
import com.timvisee.voxeltex.prefab.primitive.QuadPrefab;
import com.timvisee.voxeltex.util.Color;
import org.joml.Vector2f;

public class LampPrefab extends QuadPrefab {

    /**
     * Game object name.
     */
    private static final String GAME_OBJECT_NAME = "LampPrefab";

    /**
     * Color.
     */
    private Color color;

    /**
     * Constructor.
     *
     * @param color Lamp color.
     */
    public LampPrefab(Color color) {
        this(GAME_OBJECT_NAME, color);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param color Lamp color.
     */
    public LampPrefab(String name, Color color) {
        // Construct the parent with the proper size
        super(name, new Vector2f(0.3f));

        // Set the color
        this.color = color;

        // Generate the padlock material
        Material lockMaterial = new Material(GameResourceBundle.getInstance().TEXTURE_LAMP);

        // Set the material
        setMaterial(lockMaterial);

        // Create a child game object that holds the light
        GameObject padlockLightObject = new GameObject("LampLight");
        padlockLightObject.getTransform().getPosition().y = 0.5f;
        padlockLightObject.addComponent(new LightSourceComponent(Light.LIGHT_TYPE_POINT, getColor().toVector3f(), 0.3f));
        addChild(padlockLightObject);
    }

    /**
     * Get the color.
     *
     * @return Lamp color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Set the color.
     *
     * @param color Lamp color.
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
