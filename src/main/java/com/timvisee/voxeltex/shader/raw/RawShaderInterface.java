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

package com.timvisee.voxeltex.shader.raw;

public interface RawShaderInterface {

    /**
     * Get the vertex shader source if set.
     *
     * @return Vertex shader source.
     */
    String getVertexShader();

    /**
     * Set the vertex shader source.
     *
     * @param vertexSource Vertex shader source.
     */
    void setVertexShader(String vertexSource);

    /**
     * Check whether the vertex shader source is set.
     *
     * @return True if set, false if not.
     */
    boolean hasVertexShader();

    /**
     * Get the fragment shader source if set.
     *
     * @return Fragment shader source.
     */
    String getFragmentShader();

    /**
     * Set the fragment shader source.
     *
     * @param fragmentSource Fragment shader source.
     */
    void setFragmentShader(String fragmentSource);

    /**
     * Check whether the fragment shader source is set.
     *
     * @return True if set, false if not.
     */
    boolean hasFragmentShader();
}
