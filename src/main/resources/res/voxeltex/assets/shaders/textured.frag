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

#version 120

// Texture and tiling data
uniform sampler2D texture;
uniform vec2 tiling = vec2(1.0, 1.0);
uniform float ambientBrightness = 0.05;

// Fragment position data
varying vec4 position;

// Surface normal
varying vec3 surfaceNormal;

// Light data
const int LIGHT_COUNT_MAX = 16;
uniform int lightCount;
uniform int lightType[LIGHT_COUNT_MAX];
uniform vec3 lightPosition[LIGHT_COUNT_MAX];
uniform vec3 lightRotation[LIGHT_COUNT_MAX];
uniform vec4 lightColor[LIGHT_COUNT_MAX];

void main(void) {
    // Create a variable to define the diffuse color in
    vec3 diffuse = vec3(0.0);

    // Calculate the lighting for all different lights
    for(int i = 0; i < lightCount; i++) {

        // Process a directional light
        if(lightType[i] == 1) {
            // Get the light rotation, normalize it and normalize the normal vector of the surface
            vec3 unitNormal = normalize(surfaceNormal);
            vec3 unitLightDirection = normalize(lightRotation[i]);

            // Calculate the dot product of both vectors and clamp the brightness to zero and above
            float brightness = max(dot(unitNormal, unitLightDirection), 0.0) * lightColor[i].w;

            // Calculate the diffuse color and append it to the result
            diffuse += brightness * lightColor[i].xyz;
        }

        // Process a point light
        if(lightType[i] == 2) {
            // Get the light position
            vec3 pos = lightPosition[i];

            // Calculate the distance to the light
            float lightDistance = distance(position.xyz, pos);

            // Calculate the light direction
            vec3 lightDirection = pos - position.xyz;

            // Normalize the surface and light vector
            vec3 unitNormal = normalize(surfaceNormal);
            vec3 unitLightDirection = normalize(lightDirection);

            // Calculate the light fading factor
            float lightFadeFactor = 1 / pow(lightDistance, 2);

            // Calculate the dot product of both vectors and clamp the brightness to zero and above
            float brightness = max(dot(unitNormal, unitLightDirection), 0.0) * lightFadeFactor * lightColor[i].w;

            // Calculate the diffuse color and append it to the result
            diffuse += brightness * lightColor[i].xyz;
        }

        // Process a spot light
        if(lightType[i] == 3) {
            // TODO: Process spot light here!
        }
    }

    // Multiply the diffuse lighting by three for better appearance
    diffuse *= vec3(3);

    // Add ambient light
    diffuse += vec3(ambientBrightness);

    // Determine and set the fragment color
    gl_FragColor = vec4(diffuse, 1.0) * texture2D(texture, gl_TexCoord[0].st * tiling);
}
