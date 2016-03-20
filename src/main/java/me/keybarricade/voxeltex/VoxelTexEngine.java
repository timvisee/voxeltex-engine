package me.keybarricade.voxeltex;

import me.keybarricade.voxeltex.renderer.VoxelTexRenderer;

public class VoxelTexEngine {

    /**
     * Renderer instance.
     */
    private VoxelTexRenderer renderer = null;

    /**
     * Engine and window title.
     */
    private String title = VoxelTex.getEngineNameFull() + " Window";

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
     */
    public void init() {
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
    }

    /**
     * Start the engine.
     */
    public void start() {
        this.renderer.start();
    }
}
