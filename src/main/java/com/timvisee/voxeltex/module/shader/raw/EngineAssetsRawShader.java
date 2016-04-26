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

package com.timvisee.voxeltex.module.shader.raw;

import com.timvisee.voxeltex.module.resource.engine.EngineAssetLoader;

public class EngineAssetsRawShader extends RawShader {

    /**
     * Constructor.
     */
    public EngineAssetsRawShader() { }

    /**
     * Constructor.
     *
     * @param vertexAssetPath Vertex shader engine asset path.
     * @param fragmentAssetPath Fragment shader engine asset path.
     */
    public EngineAssetsRawShader(String vertexAssetPath, String fragmentAssetPath) {
        // Load the shaders
        loadVertexShaderFromEngineAssets(vertexAssetPath);
        loadFragmentShaderFromEngineAssets(fragmentAssetPath);
    }

    /**
     * Load the vertex shader from engine assets.
     *
     * @param vertexAssetPath Vertex shader engine asset path.
     *
     * @return this for method chaining.
     */
    public EngineAssetsRawShader loadVertexShaderFromEngineAssets(String vertexAssetPath) {
        // Load and set the vertex source shader
        setVertexShader(EngineAssetLoader.getInstance().loadResourceString(vertexAssetPath));

        // Return this for method chaining
        return this;
    }

    /**
     * Load the fragment shader from engine assets.
     *
     * @param fragmentAssetPath Fragment shader engine asset path.
     *
     * @return this for method chaining.
     */
    public EngineAssetsRawShader loadFragmentShaderFromEngineAssets(String fragmentAssetPath) {
        // Load and set the fragment source shader
        setFragmentShader(EngineAssetLoader.getInstance().loadResourceString(fragmentAssetPath));

        // Return this for method chaining
        return this;
    }
}
