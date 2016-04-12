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

package com.timvisee.voxeltex.scene;

import com.timvisee.voxeltex.VoxelTexEngine;

public class SceneManager {

    /**
     * The engine instance this scene manager is for.
     */
    private VoxelTexEngine engine;

    /**
     * Current loaded scene.
     */
    private Scene scene = null;

    /**
     * Constructor.
     *
     * @param engine VoxelTex engine instance.
     */
    public SceneManager(VoxelTexEngine engine) {
        this.engine = engine;
    }

    /**
     * Get the VoxelTex engine instance.
     *
     * @return Engine instance.
     */
    public VoxelTexEngine getEngine() {
        return engine;
    }

    /**
     * Get the loaded scene.
     *
     * @return Loaded scene.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Check whether a scene is loaded.
     *
     * @return True if a scene is loaded, false if not.
     */
    public boolean isSceneLoaded() {
        return this.scene != null;
    }

    /**
     * Load a scene.
     *
     * @param scene Scene to load
     */
    public void loadScene(Scene scene) {
        // Immediately start the scene if the current scene was loaded
        boolean start = false;
        if(this.scene != null) {
            // Store the started flag
            start = this.scene.isStarted();

            // Destroy the current scene
            this.scene.destroy();
        }

        // Set the loaded scene
        this.scene = scene;

        // Set the scene engine
        scene.setEngine(getEngine());

        // Load the scene
        System.out.println("Loading " + scene + "...");
        scene.load();
        System.out.println(scene + " loaded");

        // Start the scene
        if(start)
            scene.start();
    }

    /**
     * Start the current scene if it hasn't started yet.
     */
    public void start() {
        // Make sure a scene is loaded, then start it
        if(isSceneLoaded())
            this.scene.start();
    }

    /**
     * Update the scene that is currently loaded.
     */
    public void update() {
        // Make sure a scene is loaded, then update it
        if(isSceneLoaded())
            this.scene.update();
    }

    /**
     * Draw the 3D scene that is currently loaded.
     */
    public void draw() {
        // Make sure a scene is loaded, then draw it
        if(isSceneLoaded())
            this.scene.onDraw();
    }

    /**
     * Draw the overlay scene that is currently loaded.
     */
    public void drawOverlay() {
        // Make sure a scene is loaded, then draw it
        if(isSceneLoaded())
            this.scene.onDrawOverlay();
    }
}
