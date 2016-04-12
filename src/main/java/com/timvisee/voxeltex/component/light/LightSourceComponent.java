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

package com.timvisee.voxeltex.component.light;

import com.timvisee.voxeltex.light.Light;
import com.timvisee.voxeltex.util.Color;
import org.joml.Vector3f;

public class LightSourceComponent extends AbstractLightSourceComponent {

    /**
     * Light instance.
     */
    private Light light;

    /**
     * Light type buffer. Used on creation if set.
     */
    private int lightTypeBuffer = -1;

    /**
     * Light color buffer. Used on creation if set.
     */
    private Vector3f lightColorBuffer = null;

    /**
     * Light brightness buffer. Used on creation if set.
     */
    private float lightBrightnessBuffer = -1f;

    /**
     * Constructor.
     */
    public LightSourceComponent() { }

    /**
     * Constructor.
     *
     * @param lightType Light type.
     */
    public LightSourceComponent(int lightType) {
        this.lightTypeBuffer = lightType;
    }

    /**
     * Constructor.
     *
     * @param lightBrightness Light brightness.
     */
    public LightSourceComponent(float lightBrightness) {
        this.lightBrightnessBuffer = lightBrightness;
    }

    /**
     * Constructor.
     *
     * @param lightColor Light color intensity.
     */
    public LightSourceComponent(Vector3f lightColor) {
        this.lightColorBuffer = lightColor;
    }

    /**
     * Constructor.
     *
     * @param lightColor Light color and intensity.
     */
    public LightSourceComponent(Color lightColor) {
        this.lightColorBuffer = lightColor.toVector3f();
    }

    /**
     * Constructor.
     *
     * @param lightType Light type.
     * @param lightColor Light color intensity.
     */
    public LightSourceComponent(int lightType, Vector3f lightColor) {
        this.lightTypeBuffer = lightType;
        this.lightColorBuffer = lightColor;
    }

    /**
     * Constructor.
     *
     * @param lightType Light type.
     * @param lightBrightness Light brightness.
     */
    public LightSourceComponent(int lightType, float lightBrightness) {
        this.lightTypeBuffer = lightType;
        this.lightBrightnessBuffer = lightBrightness;
    }

    /**
     * Constructor.
     *
     * @param lightType Light type.
     * @param lightColor Light color intensity.
     * @param lightBrightness Light brightness.
     */
    public LightSourceComponent(int lightType, Vector3f lightColor, float lightBrightness) {
        this.lightTypeBuffer = lightType;
        this.lightColorBuffer = lightColor;
        this.lightBrightnessBuffer = lightBrightness;
    }

    @Override
    public synchronized void update() {
        // Update the position of the light
        this.light.updatePosition(getOwner());
    }

    @Override
    public void destroy() {
        // Make sure a light instance was created
        if(this.light == null)
            return;

        // Remove the light instance from the light manager to ensure it isn't handled anymore
        getScene().getLightManager().removeLight(this.light);

        // Reset the light instance
        this.light = null;

        // Call the super
        super.destroy();
    }

    @Override
    public void onEnable() {
        // Call the super
        super.onEnable();

        // Create a light source instance in the light manager, use the buffered defaults if set
        this.light = getScene().getLightManager().createLight(
                this.lightTypeBuffer >= 0 ? this.lightTypeBuffer : Light.LIGHT_TYPE_POINT,
                getOwner(),
                this.lightColorBuffer != null ? this.lightColorBuffer : new Vector3f(1.0f, 0.9f, 0.9f),
                this.lightBrightnessBuffer >= 0 ? this.lightBrightnessBuffer : 1f
        );
    }

    @Override
    public void onDisable() {
        // Call the super
        super.onDisable();

        // Remove the light from the light manager
        if(this.light != null)
            getScene().getLightManager().removeLight(this.light);

        // Reset the light instance
        this.light = null;
    }

    @Override
    public Light getLight() {
        return this.light;
    }

    @Override
    public int getLightType() {
        return this.light.getType();
    }

    @Override
    public void setLightType(int type) {
        this.light.setType(type);
    }

    @Override
    public Vector3f getLightColor() {
        return this.light.getColor();
    }

    @Override
    public void setLightColor(Vector3f color) {
        this.light.setColor(color);
    }

    @Override
    public float getLightBrightness() {
        return this.light.getBrightness();
    }

    @Override
    public void setLightBrightness(float brightness) {
        this.light.setBrightness(brightness);
    }
}
