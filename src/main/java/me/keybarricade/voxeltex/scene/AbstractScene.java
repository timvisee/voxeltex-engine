package me.keybarricade.voxeltex.scene;

import me.keybarricade.voxeltex.VoxelTexEngine;

public abstract class AbstractScene {

    /**
     * The engine instance this scene is loaded in.
     */
    private VoxelTexEngine engine;

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
        // Make sure the scene hasn't been started already
        if(isStarted())
            return;

        // Set the started flag
        this.started = started;
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
