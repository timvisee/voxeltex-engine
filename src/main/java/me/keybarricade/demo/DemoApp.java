package me.keybarricade.demo;

import com.timvisee.voxeltex.VoxelTexEngine;
import me.keybarricade.KeyBarricade;

public class DemoApp {

    /**
     * VoxelTex engine instance.
     */
    private VoxelTexEngine engine;

    /**
     * Constructor.
     */
    public DemoApp() { }

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
        this.engine.getSceneManager().loadScene(new DemoScene());

        // Start the engine
        this.engine.start();
    }
}
