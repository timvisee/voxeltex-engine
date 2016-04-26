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

package com.timvisee.voxeltex.architecture.prefab.light;

import com.timvisee.voxeltex.architecture.component.light.LightSourceComponent;
import com.timvisee.voxeltex.architecture.gameobject.GameObject;
import com.timvisee.voxeltex.engine.light.Light;
import com.timvisee.voxeltex.module.Color;
import org.joml.Vector3f;

public class LightPrefab extends GameObject {

    /**
     * The default name of the cube.
     */
    private static final String DEFAULT_NAME = "LightPrefab";

    /**
     * Light source component.
     */
    private LightSourceComponent lightSource;

    /**
     * Constructor.
     */
    public LightPrefab() {
        this(DEFAULT_NAME);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     */
    public LightPrefab(String name) {
        this(name, -1, Color.WHITE, -1);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param lightType Light type.
     */
    public LightPrefab(String name, int lightType) {
        this(name, lightType, Color.WHITE, -1);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param lightBrightness Light brightness.
     */
    public LightPrefab(String name, float lightBrightness) {
        this(name, -1, Color.WHITE, lightBrightness);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param lightColor Light color intensity.
     */
    public LightPrefab(String name, Vector3f lightColor) {
        this(name, -1, lightColor, -1);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param lightType Light type.
     * @param lightColor Light color intensity.
     */
    public LightPrefab(String name, int lightType, Vector3f lightColor) {
        this(name, lightType, lightColor, -1);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param lightType Light type.
     * @param lightColor Light color intensity.
     */
    public LightPrefab(String name, int lightType, Color lightColor) {
        this(name, lightType, lightColor, -1);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param lightType Light type.
     * @param lightBrightness Light brightness.
     */
    public LightPrefab(String name, int lightType, float lightBrightness) {
        this(name, lightType, Color.WHITE, lightBrightness);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param lightType Light type.
     * @param lightColor Light color intensity.
     * @param lightBrightness Light brightness.
     */
    public LightPrefab(String name, int lightType, Color lightColor, float lightBrightness) {
        // Construct the super
        super(name);

        // Create a light source component
        this.lightSource = new LightSourceComponent(lightType, lightColor.toVector3f(), lightBrightness);

        // Add the light source component to the object
        addComponent(this.lightSource);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param lightType Light type.
     * @param lightColor Light color intensity.
     * @param lightBrightness Light brightness.
     */
    public LightPrefab(String name, int lightType, Vector3f lightColor, float lightBrightness) {
        // Construct the super
        super(name);

        // Create a light source component
        this.lightSource = new LightSourceComponent(lightType, lightColor, lightBrightness);

        // Add the light source component to the object
        addComponent(this.lightSource);
    }

    /**
     * Get the light source component.
     *
     * @return Light source component.
     */
    public LightSourceComponent getLightSourceComponent() {
        return this.lightSource;
    }

    /**
     * Get the light instance.
     *
     * @return Light instance.
     */
    public Light getLight() {
        return lightSource.getLight();
    }

    /**
     * Get the light type.
     *
     * Will be one of:
     * - LIGHT_TYPE_DIRECTIONAL
     * - LIGHT_TYPE_POINT
     * - LIGHT_TYPE_SPOT
     *
     * @return Light type.
     */
    public int getLightType() {
        return this.lightSource.getLightType();
    }

    /**
     * Set the light type.
     *
     * Choose from:
     * - LIGHT_TYPE_DIRECTIONAL
     * - LIGHT_TYPE_POINT
     * - LIGHT_TYPE_SPOT
     *
     * @param type Light type.
     */
    public void setLightType(int type) {
        this.lightSource.setLightType(type);
    }

    /**
     * Get the color intensity of the light.
     *
     * @return Color intensity.
     */
    public Vector3f getLightColor() {
        return this.lightSource.getLightColor();
    }

    /**
     * Set the color intensity of the light.
     *
     * @param color Color intensity.
     */
    public void setLightColor(Vector3f color) {
        this.lightSource.setLightColor(color);
    }

    /**
     * Get the light brightness.
     *
     * @return Light brightness.
     */
    public float getLightBrightness() {
        return this.lightSource.getLightBrightness();
    }

    /**
     * Set the light brightness.
     *
     * @param brightness Light brightness.
     */
    public void setLightBrightness(float brightness) {
        this.lightSource.setLightBrightness(brightness);
    }
}
