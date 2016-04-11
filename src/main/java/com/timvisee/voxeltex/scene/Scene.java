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

import com.timvisee.voxeltex.gameobject.AbstractGameObject;

import java.util.ArrayList;
import java.util.List;

public class Scene extends AbstractScene {

    /**
     * List of game objects in this scene.
     */
    private List<AbstractGameObject> gameObjects = new ArrayList<>();

    /**
     * List of game objects queued to be destroyed.
     */
    private List<AbstractGameObject> gameObjectsRemoveQueue = new ArrayList<>();

    @Override
    public List<AbstractGameObject> getGameObjects() {
        return this.gameObjects;
    }

    @Override
    public int getGameObjectCount(boolean recursive) {
        // Return the number if not recursive
        if(!recursive)
            this.gameObjects.size();

        // Count the number of game objects
        int count = 0;

        // Loop through all game objects and count the total
        //noinspection ForLoopReplaceableByForEach
        for(int i = 0, size = this.gameObjects.size(); i < size; i++)
            count += this.gameObjects.get(i).getChildCount(true);

        // Return the object count
        return count;
    }

    @Override
    public boolean hasGameObjects() {
        return getGameObjectCount(false) > 0;
    }

    @Override
    public int getTotalGameObjectCount() {
        return getGameObjectCount(true);
    }

    @Override
    public void addGameObject(AbstractGameObject gameObject) {
        // Set the scene of the game object
        gameObject.setScene(this);

        // Add the game object to the list
        this.gameObjects.add(gameObject);

        // Create the game object
        gameObject.create();

        // Start the game object if the scene has started
        if(isStarted())
            gameObject.start();
    }

    @Override
    public AbstractGameObject getGameObject(int i) {
        return this.gameObjects.get(i);
    }

    @Override
    public boolean removeGameObject(AbstractGameObject gameObject) {
        return this.gameObjectsRemoveQueue.add(gameObject);
    }

    @Override
    public AbstractGameObject removeGameObject(int i) {
        // Get the game object
        AbstractGameObject gameObject = getGameObject(i);

        // Remove the game object and return it
        removeGameObject(gameObject);
        return gameObject;
    }

    @Override
    public void load() {
        // Set up and configure the physics engine for this scene
        getPhysicsEngine().setUp();
    }

    @Override
    public void update() {
        // Update all game objects
        //noinspection ForLoopReplaceableByForEach
        for(int i = 0, size = this.gameObjects.size(); i < size; i++)
            if(this.gameObjects.get(i).isEnabled())
                this.gameObjects.get(i).update();

        // Update the physics engine and simulate the next physics step
        getPhysicsEngine().update();

        // Remove all game objects that were queued to be removed
        //noinspection ForLoopReplaceableByForEach
        for(int i = 0, size = this.gameObjectsRemoveQueue.size(); i < size; i++) {
            // Reset the parent of the game object
            this.gameObjectsRemoveQueue.get(i).setParent(null);

            // Remove the game object
            this.gameObjects.remove(this.gameObjectsRemoveQueue.get(i));
        }

        // Clear the list of game objects queued to be removed
        this.gameObjectsRemoveQueue.clear();
    }

    @Override
    public void draw() {
        // Draw all game objects if enabled
        //noinspection ForLoopReplaceableByForEach
        for(int i = 0, size = this.gameObjects.size(); i < size; i++)
            if(this.gameObjects.get(i).isEnabled())
                this.gameObjects.get(i).draw();
    }

    @Override
    public void drawOverlay() {
        // Draw the overlay of all game objects if enabled
        //noinspection ForLoopReplaceableByForEach
        for(int i = 0, size = this.gameObjects.size(); i < size; i++)
            if(this.gameObjects.get(i).isEnabled())
                this.gameObjects.get(i).drawOverlay();
    }

    @Override
    public void destroy() {
        // Destroy game objects
        //noinspection ForLoopReplaceableByForEach
        for(int i = 0, size = this.gameObjects.size(); i < size; i++)
            this.gameObjects.get(i).destroy();

        // Show status message
        System.out.println(this + " is queued to be unloaded.");

        // Force the scene to finalize
        try {
            //noinspection FinalizeCalledExplicitly
            finalize();
        } catch(Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
