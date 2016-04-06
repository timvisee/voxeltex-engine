package me.keybarricade;

import me.keybarricade.voxeltex.VoxelTexEngine;

public class Game {

    /**
     * VoxelTex engine instance.
     */
    private VoxelTexEngine engine;

    /**
     * Constructor.
     */
    public Game() { }

    /**
     * Initialize.
     */
    public void init() {
        // Show initialization message
        System.out.println("Initializing " + KeyBarricade.APP_NAME + "...");

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
        this.engine.setTitle(KeyBarricade.APP_NAME + " v" + KeyBarricade.APP_VERSION_NAME);

        // Initialize the engine
        this.engine.init();
    }

    /**
     * Start the VoxelTex engine after it has been initialized.
     */
    public void startEngine() {
        // Load the default scene
        this.engine.getSceneManager().loadScene(new MainMenuScene());

        // Start the engine
        this.engine.start();
    }
}
