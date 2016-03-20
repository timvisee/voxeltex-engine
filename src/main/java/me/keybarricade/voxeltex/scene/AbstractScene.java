package me.keybarricade.voxeltex.scene;

import me.keybarricade.voxeltex.VoxelTexEngine;

public abstract class AbstractScene {

    /**
     * The engine instance this scene is loaded in.
     */
    private VoxelTexEngine engine;

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
