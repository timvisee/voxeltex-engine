package me.keybarricade.voxeltex.scene;

import me.keybarricade.voxeltex.VoxelTexEngine;
import me.keybarricade.voxeltex.light.LightManager;

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
}
