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

import com.timvisee.voxeltex.architecture.scene.SceneManager;
import com.timvisee.voxeltex.engine.render.VoxelTexRenderer;
import com.timvisee.voxeltex.module.resource.bundle.EngineResourceBundle;

public class VoxelTexEngine {

    /**
     * Scene manager for this engine.
     */
    private SceneManager sceneManager = new SceneManager(this);

    /**
     * Renderer instance.
     */
    private VoxelTexRenderer renderer = null;

    /**
     * Engine and window title.
     */
    private String title = VoxelTex.getEngineNameFull() + " Window";

    /**
     * Get the scene manager.
     *
     * @return Scene manager.
     */
    public SceneManager getSceneManager() {
        return this.sceneManager;
    }

    /**
     * Get the renderer instance.
     *
     * @return Renderer instance.
     */
    public VoxelTexRenderer getRenderer() {
        return this.renderer;
    }

    /**
     * Check whether the engine has a renderer attached.
     *
     * @return True if a renderer is attached, false if not.
     */
    public boolean hasRenderer() {
        return this.renderer != null;
    }

    /**
     * Set the engine title.
     *
     * @param title Engine title.
     */
    public void setTitle(String title) {
        // Set the title
        this.title = title;

        // Update the title if the window has been created already
        if(hasRenderer())
            this.renderer.getWindow().setTitle(title);
    }

    /**
     * Initialize the engine.
     * This will configure the engine and load everything required before starting.
     *
     * @param load True to load the all engine resources in advance, false if not.
     */
    public void init(boolean load) {
        // Show a status message
        System.out.println("Initializing " + VoxelTex.getEngineNameFull() + " engine...");

        // Create the voxel renderer
        this.renderer = new VoxelTexRenderer(this);

        // Initialize the renderer
        this.renderer.init();

        // Set the window title
        this.renderer.getWindow().setTitle(title);

        // Show a status message
        System.out.println(VoxelTex.ENGINE_NAME + " engine initialized successfully!");

        // Load the engine resources if specified
        if(load)
            load();
    }

    /**
     * Load all engine resources.
     * These resources are required by the engine to run properly.
     *
     * Note: These resources can be loaded automatically if with the {@code init(load = true);} method.
     */
    public void load() {
        // Load the engine resources
        EngineResourceBundle.getInstance().load();

        // Show a status message
        System.out.println("Engine resources loaded successfully!");
    }

    /**
     * Start and loop the engine.
     */
    public void loop() {
        // Start the renderer
        this.renderer.start();

        // Show a status message
        System.out.println(VoxelTex.ENGINE_NAME + " engine has stopped");
    }
}
