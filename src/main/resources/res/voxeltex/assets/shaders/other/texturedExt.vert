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

// Matrix data
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix = mat4(1.0);
uniform mat4 modelMatrix = mat4(1.0);

uniform vec3 lightPosition = vec3(0.0, 1.5, 0.0);

varying vec3 surfaceNormal;

varying vec4 position;

void main() {
    // Determine the position
    position = modelMatrix * gl_Vertex;

    // Pass the texture coordinates to the fragment shader
	gl_TexCoord[0] = gl_MultiTexCoord0;

    // Set the vertex point position
	//gl_Position = projectionMatrix * viewMatrix * modelMatrix * gl_Vertex;
	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;

    // Calculate the surface normal
    surfaceNormal = (modelMatrix * vec4(gl_Normal, 0.0)).xyz;

//    vec4 vertex_position_camera = gl_ModelViewMatrix * gl_Vertex;
//    vec3 normal_camera = normalize(gl_NormalMatrix * gl_Normal);
//    vec4 light_position_camera = gl_ModelViewMatrix * vec4(lightPosition, 1.0);
//
//    vec3 light_vert = normalize(vec3(light_position_camera - vertex_position_camera));
//    vec3 light_refl = normalize(reflect(light_vert, normal_camera));
//
//    // diffuse light
//    diffuse_intensity = max(dot(light_vert, normal_camera), 0.0);
//
//    // specular light
//    specular_intensity = max(dot(light_refl, normalize(vec3(vertex_position_camera))), 0.0);
//    specular_intensity = pow(specular_intensity, 6.0);
}
