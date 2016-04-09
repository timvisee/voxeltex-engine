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
uniform float ambientBrightness = 0.25;
uniform vec4 color = vec4(1, 1, 1, 1);

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
            // Calculate the dot product of both vectors and clamp the brightness to zero and above
            float brightness = max(dot(normalize(surfaceNormal), normalize(lightRotation[i])), 0.0) * lightColor[i].w;

            // Calculate the diffuse color and append it to the result
            diffuse += brightness * lightColor[i].xyz;
        }

        // Process a point light
        if(lightType[i] == 2) {
            // Calculate the distance to the light
            float lightDistance = distance(position.xyz, lightPosition[i]);

            // Skip the light processing if the light is too far away for optimization
            // TODO: Is this calibrated properly?
            if(lightDistance > lightColor[i].w * lightColor[i].w * 100.0)
                continue;

            // Calculate the light direction
            vec3 lightDirection = lightPosition[i] - position.xyz;

            // Calculate the dot product of both vectors and clamp the brightness to zero and above
            float brightness = max(dot(normalize(surfaceNormal), normalize(lightDirection)), 0.0) /
                    (lightDistance * lightDistance) *
                    lightColor[i].w;

            // Calculate the diffuse color and append it to the result
            diffuse += brightness * lightColor[i].xyz;
        }

        // Process a spot light
        // TODO: Process spot light here!
        // if(lightType[i] == 3) { }
    }

    // Multiply the diffuse lighting by three for better appearance and add the ambient light
    diffuse = diffuse * 3.0 + vec3(ambientBrightness);

    // Determine and set the fragment color
    gl_FragColor = vec4(diffuse, 1.0) * texture2D(texture, gl_TexCoord[0].st * tiling) * color;
}
