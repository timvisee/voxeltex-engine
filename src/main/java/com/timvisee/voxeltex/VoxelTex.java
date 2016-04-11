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

package com.timvisee.voxeltex;

import com.timvisee.voxeltex.scene.TestEnvironmentScene;

public class VoxelTex {

    /**
     * Engine name.
     */
    public static final String ENGINE_NAME = "VoxelTex";

    /**
     * Engine version name.
     */
    public static final String ENGINE_VERSION_NAME = "0.1";

    /**
     * Engine version code.
     */
    public static final int ENGINE_VERSION_CODE = 1;

    /**
     * Get the full engine name string, including the version number.
     *
     * @return Engine name string.
     */
    public static String getEngineNameFull() {
        return ENGINE_NAME + " v" + ENGINE_VERSION_NAME;
    }

    /**
     * Run the VoxelTex engine with the test environment.
     *
     * @param args Startup arguments.
     */
    public static void main(String[] args) {
        // Define the engine variable
        VoxelTexEngine engine;

        // Create a VoxelTex engine instance
        engine = new VoxelTexEngine();

        // Set the title
        engine.setTitle(ENGINE_NAME + " v" + ENGINE_VERSION_NAME + " - Test Environment");

        // Initialize the engine
        engine.init();

        // Load the test environment scene
        engine.getSceneManager().loadScene(new TestEnvironmentScene());

        // Start the engine
        engine.start();
    }
}
