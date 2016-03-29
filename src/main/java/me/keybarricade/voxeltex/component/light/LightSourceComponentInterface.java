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

package me.keybarricade.voxeltex.component.light;

import me.keybarricade.voxeltex.light.Light;
import org.joml.Vector3f;

public interface LightSourceComponentInterface {

    /**
     * Get the light instance.
     *
     * @return Light instance.
     */
    Light getLight();

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
    int getLightType();

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
    void setLightType(int type);

    /**
     * Get the color intensity of the light.
     *
     * @return Color intensity.
     */
    Vector3f getLightColor();

    /**
     * Set the color intensity of the light.
     *
     * @param color Color intensity.
     */
    void setLightColor(Vector3f color);

    /**
     * Get the light brightness.
     *
     * @return Light brightness.
     */
    float getLightBrightness();

    /**
     * Set the light brightness.
     *
     * @param brightness Light brightness.
     */
    void setLightBrightness(float brightness);
}
