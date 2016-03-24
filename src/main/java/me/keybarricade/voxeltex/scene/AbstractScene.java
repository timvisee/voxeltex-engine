package me.keybarricade.voxeltex.scene;

import me.keybarricade.voxeltex.VoxelTexEngine;
import me.keybarricade.voxeltex.gameobject.AbstractGameObject;
import me.keybarricade.voxeltex.light.LightManager;
import me.keybarricade.voxeltex.physics.ScenePhysicsEngine;

import java.util.List;

public abstract class AbstractScene {

    /**
     * The engine instance this scene is loaded in.
     */
    private VoxelTexEngine engine;

    /**
     * Light manager for this scene.
     */
    private LightManager lightManager = new LightManager();

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
     * This is called once each frame after updating the scene.
     */
    public abstract void draw();

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
     * Remove the given game object from the scene.
     *
     * @return True if any game object was removed, false if not.
     */
    public abstract boolean removeGameObject(AbstractGameObject gameObject);

    /**
     * Remove the game object at the given index.
     *
     * @param i Game object index.
     *
     * @return The game object that was removed, or null if none was removed.
     */
    public abstract AbstractGameObject removeGameObject(int i);










    private ScenePhysicsEngine physics = new ScenePhysicsEngine(this);

    public ScenePhysicsEngine getPhysics() {
        return this.physics;
    }
}
