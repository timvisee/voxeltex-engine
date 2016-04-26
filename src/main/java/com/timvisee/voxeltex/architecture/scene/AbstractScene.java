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

package com.timvisee.voxeltex.architecture.scene;

import com.timvisee.voxeltex.VoxelTexEngine;
import com.timvisee.voxeltex.architecture.gameobject.AbstractGameObject;
import com.timvisee.voxeltex.engine.light.LightManager;
import com.timvisee.voxeltex.engine.physics.ScenePhysicsEngine;

import java.util.List;

public abstract class AbstractScene {

    /**
     * The engine instance this scene is loaded in.
     */
    private VoxelTexEngine engine;

    /**
     * Light manager for this scene.
     */
    private final LightManager lightManager = new LightManager();

    /**
     * Physics engine for this scene.
     */
    private final ScenePhysicsEngine physicsEngine = new ScenePhysicsEngine(this);

    /**
     * Defines whether the scene has started. True if started, false if not.
     */
    private boolean started;

    /**
     * The engine instance this scene is in.
     *
     * @return Engine.
     */
    public VoxelTexEngine getEngine() {
        return this.engine;
    }

    /**
     * Set the engine instance.
     *
     * @param engine Engine instance.
     *
     * @return Engine.
     */
    public void setEngine(VoxelTexEngine engine) {
        this.engine = engine;
    }

    /**
     * Get the light manager for this scene.
     *
     * @return Scene light manager.
     */
    public LightManager getLightManager() {
        return this.lightManager;
    }

    /**
     * Get the physicsEngine engine instance for this scene.
     *
     * @return Physics engine.
     */
    public ScenePhysicsEngine getPhysicsEngine() {
        return this.physicsEngine;
    }

    /**
     * Check whether the scene is started.
     *
     * @return True if started, false if not.
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Start the scene.
     */
    public void start() {
        // Show a status message
        System.out.println("Starting scene...");

        // Make sure the scene hasn't been started already
        if(isStarted())
            return;

        // Start all game objects
        for(AbstractGameObject gameObject : this.getGameObjects())
            gameObject.start();

        // Set the started flag
        this.started = true;
    }

    /**
     * Load the scene.
     * This can be used to add all required game objects.
     */
    public abstract void load();

    /**
     * Update the scene.
     *
     * This is called once each frame before drawing the scene.
     */
    public abstract void update();

    /**
     * Draw the scene.
     *
     * This is called when the scene is drawn.
     */
    public abstract void onDraw();

    /**
     * Draw the scene overlay.
     *
     * This is called when the scene overlay is drawn.
     */
    public abstract void onDrawOverlay();

    /**
     * Destroy the scene.
     * This will remove the scene from the scene manager automatically before the next update.
     * All references created by the VoxelTex engine to this scene will be cleared.
     * The scene may not be used anymore after it has been destroyed.
     */
    public abstract void destroy();

    /**
     * Get all game objects in this scene.
     *
     * @return Game objects.
     */
    public abstract List<AbstractGameObject> getGameObjects();

    /**
     * Get the number of game objects in this scene.
     *
     * @param recursive True to count recursively, false to only count the root objects.
     *
     * @return Game object count.
     */
    public abstract int getGameObjectCount(boolean recursive);

    /**
     * Check whether the scene has any game objects.
     *
     * @return True if the scene has any game object, false if not.
     */
    public abstract boolean hasGameObjects();

    /**
     * Get the total number of game objects inside this scene.
     *
     * @return Total game object count.
     */
    public abstract int getTotalGameObjectCount();

    /**
     * Add a game object to the scene.
     *
     * @param gameObject Game object.
     */
    public abstract void addGameObject(AbstractGameObject gameObject);

    /**
     * Get the game object at the given index.
     *
     * @param i Game object index.
     *
     * @return Game object.
     */
    public abstract AbstractGameObject getGameObject(int i);

    /**
     * Remove a game object from the scene before the next update.
     * To destroy the game object, call {@see AbstractGameObject.destroy()} instead since this method doesn't imply
     * destruction. This method will be called automatically after destruction.
     *
     * @param gameObject Game object to remove.
     *
     * @return True if any game object will be removed, false if not.
     */
    public abstract boolean removeGameObject(AbstractGameObject gameObject);

    /**
     * Remove a game object from the scene before the next update.
     * To destroy the game object, call {@see AbstractGameObject.destroy()} instead since this method doesn't imply
     * destruction. This method will be called automatically after destruction.
     *
     * @param i Index of the game object to remove.
     *
     * @return True if any game object will be removed, false if not.
     */
    public abstract AbstractGameObject removeGameObject(int i);

    /**
     * Get the name of the scene type.
     *
     * @return Scene name.
     */
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "Scene[" + getName() + "]";
    }
}
