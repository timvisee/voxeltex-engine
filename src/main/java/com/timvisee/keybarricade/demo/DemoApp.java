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

package com.timvisee.keybarricade.demo;

import com.timvisee.keybarricade.KeyBarricade;
import com.timvisee.voxeltex.VoxelTexEngine;

public class DemoApp {

    /**
     * VoxelTex engine instance.
     */
    private VoxelTexEngine engine;

    /**
     * Constructor.
     */
    public DemoApp() { }

    /**
     * Initialize.
     */
    public void init() {
        // Show initialization message
        System.out.println("Initializing " + KeyBarricade.APP_NAME + "...");

        // Initialize the VoxelTex engine
        initEngine();

        // Start the VoxelTex engine
        startEngine();
    }

    /**
     * Initialize the VoxelTex engine.
     */
    public void initEngine() {
        // Create a VoxelTex engine instance
        this.engine = new VoxelTexEngine();

        // Set the title
        this.engine.setTitle(KeyBarricade.APP_NAME + " v" + KeyBarricade.APP_VERSION_NAME);

        // Initialize the engine
        this.engine.init();
    }

    /**
     * Start the VoxelTex engine after it has been initialized.
     */
    public void startEngine() {
        // Load the default scene
        this.engine.getSceneManager().loadScene(new DemoScene());

        // Start the engine
        this.engine.loop();
    }
}
