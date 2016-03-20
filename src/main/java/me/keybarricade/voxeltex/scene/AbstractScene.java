package me.keybarricade.voxeltex.scene;

public abstract class AbstractScene {

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
