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
uniform vec2 tileSize = vec2(16.0, 16.0);
uniform vec2 tilePosition = vec2(1.0, 4.0);
uniform vec4 color = vec4(1);
uniform float charWidth = 1.0;

// Fragment position data
varying vec4 position;

void main(void) {
    // Calculate the character offset and it's centered offset
    vec2 charOffset = vec2(tilePosition / tileSize);
    vec2 charOffsetSized = charOffset + vec2(1.0 / tileSize.x * (1.0 - charWidth) / 2.0, 0);

    // Create the character width factor vector
    vec2 charSizeFactor = vec2(charWidth, 1.0);

    // Determine and set the fragment color
    gl_FragColor = texture2D(texture, gl_TexCoord[0].st * tiling / tileSize * charSizeFactor + charOffsetSized) * color;
}
