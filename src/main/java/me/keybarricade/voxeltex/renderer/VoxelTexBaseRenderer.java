package me.keybarricade.voxeltex.renderer;

public abstract class VoxelTexBaseRenderer {

    /**
     * Run the renderer.
     * This will initialize and start the rendering loop.
     */
    public abstract void start();

    /**
     * Initialize the renderer.
     */
    protected abstract void init();

    /**
     * Rendering loop.
     */
    protected abstract void loop();
}
