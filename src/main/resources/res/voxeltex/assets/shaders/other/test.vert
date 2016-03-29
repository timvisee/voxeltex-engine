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

//combined projection and view matrix
//uniform mat4 u_projView;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix = mat4(1.0);
uniform mat4 modelMatrix = mat4(1.0);

//"in" attributes from our SpriteBatch
//attribute vec2 Position;
attribute vec2 TexCoord;
attribute vec4 Color;

//"out" varyings to our fragment shader
varying vec2 vTexCoord;
varying vec4 vColor;
 
void main() {
	gl_Position = projectionMatrix * viewMatrix * modelMatrix * gl_Vertex;


	vColor = Color;
	vTexCoord = TexCoord;
    vTexCoord = gl_MultiTexCoord0;
}