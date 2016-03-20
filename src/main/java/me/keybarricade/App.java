package me.keybarricade;

import me.keybarricade.voxeltex.VoxelTexEngine;

public class App {

    /**
     * VoxelTex engine instance.
     */
    private VoxelTexEngine engine;

    /**
     * Constructor.
     */
    public App() { }

    /**
     * Initialize.
     */
    public void init() {
        // Show initialization message
        System.out.println("Initializing...");

        // Initialize the VoxelTex engine
        initEngine();

        // Start the VoxelTex engine
        startEngine();
    }

    /**
     * Initialize the VoxelTex engine.
     */
    public void initEngine() {
        // Create a VoxelTex engine instance
        this.engine = new VoxelTexEngine();

        // Set the title
        //this.engine.setTitle(KeyBarricade.APP_NAME + " v" + KeyBarricade.APP_VERSION_NAME);

        // Initialize the engine
        this.engine.init();
    }

    /**
     * Start the VoxelTex engine after it has been initialized.
     */
    public void startEngine() {
        // Start the engine
        this.engine.start();
    }
}
