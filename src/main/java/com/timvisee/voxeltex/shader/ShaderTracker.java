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

package com.timvisee.voxeltex.shader;

import java.util.ArrayList;
import java.util.List;

public class ShaderTracker {

    /**
     * List of shaders managed by the engine.
     */
    private static List<AbstractShader> shaders = new ArrayList<>();

    /**
     * Add a new shader to the tracker.
     *
     * @param shader Shader to add.
     */
    public static void trackShader(AbstractShader shader) {
        shaders.add(shader);
    }

    /**
     * Get the list of shaders.
     *
     * @return List of shaders.
     */
    public static List<AbstractShader> getShaders() {
        return shaders;
    }

    /**
     * Get the number of managed shaders.
     *
     * @return Shader count.
     */
    public static int getShaderCount() {
        return shaders.size();
    }

    /**
     * Remove the given shader from the tracker.
     *
     * @param shader Shader to remove.
     *
     * @return True if a shader was removed from the tracker, false othewrise.
     */
    public static boolean untrackShader(AbstractShader shader) {
        return shaders.remove(shader);
    }

    /**
     * Remove the shader from the tracker at the position of the given index.
     *
     * @param i Shader index.
     *
     * @return Removed shader.
     */
    public static AbstractShader untrackShader(int i) {
        return shaders.remove(i);
    }

    /**
     * Dispose all tracked shaders and remove them from the tracker.
     */
    public static void disposeAll() {
        // Loop through all shaders and dispose them
        // Loop through all textures and dispose them
        for(int i = getShaderCount() - 1; i >= 0; i--)
            shaders.get(i).dispose();

        // Remove all shaders (should have been removed already)
        shaders.clear();
    }
}
