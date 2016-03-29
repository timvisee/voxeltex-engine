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

package me.keybarricade.voxeltex.shader.raw;

import me.keybarricade.voxeltex.util.FileUtil;

public class FileSystemRawShader extends RawShader {

    /**
     * Constructor.
     */
    public FileSystemRawShader() { }

    /**
     * Constructor.
     *
     * @param vertexAssetPath Vertex shader file system path.
     * @param fragmentAssetPath Fragment shader file system path.
     */
    public FileSystemRawShader(String vertexAssetPath, String fragmentAssetPath) {
        // Load the shaders
        loadVertexShaderFromPath(vertexAssetPath);
        loadFragmentShaderFromPath(fragmentAssetPath);
    }

    /**
     * Load the vertex shader from file system path.
     *
     * @param vertexAssetPath Vertex shader file system path.
     *
     * @return this for method chaining.
     */
    public FileSystemRawShader loadVertexShaderFromPath(String vertexAssetPath) {
        // Load and set the vertex source shader
        setVertexShader(FileUtil.read(vertexAssetPath));

        // Return this for method chaining
        return this;
    }

    /**
     * Load the fragment shader from file system path.
     *
     * @param fragmentAssetPath Fragment shader file system path.
     *
     * @return this for method chaining.
     */
    public FileSystemRawShader loadFragmentShaderFromPath(String fragmentAssetPath) {
        // Load and set the fragment source shader
        setFragmentShader(FileUtil.read(fragmentAssetPath));

        // Return this for method chaining
        return this;
    }
}
